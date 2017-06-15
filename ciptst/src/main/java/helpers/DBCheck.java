package helpers;

import APITasks.VKTasks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBCheck {
    public static Connection connect(){
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/persons",
                            "postgres", "wizard");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return connection;
    }

    public static String check(){
        String buffer="";

        Connection connection=connect();


        buffer=" <span class=\"red\">Отсутствует!</span>";

        if (connection!=null){
            buffer=" <span class=\"green\">Создана!</span>";
        }
        return ("<p>Статус БД "+buffer+"</p>");
    }

    public static  String showAll(){
        String buffer="";
        Connection connection = connect();

        if (connection!=null){
            try {
                try {
                    Statement stmt = connection.createStatement();
                    Statement stmt2 = connection.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM person");
                    while (rs.next()) {
                        ResultSet phones = stmt2.executeQuery("SELECT phone.* " +
                                "FROM phone " +
                                "WHERE phone.personid="+rs.getString("id"));
                        int i=1;
                        String phoneList="";
                        while (phones.next()){
                            phoneList+="tel."+i+": "+phones.getString("phone")+", ";
                            i+=1;
                        }
                        buffer+="<div class=\"searchResult\">"+
                                "<span class=\"name\" style=\"background-image: url("+
                                VKTasks.getPhoto(rs.getString("vk"))+");\">"+
                                rs.getString("name")+"</span>"+
                                "<span class=\"tel\">"+phoneList+
                                "</span>";
                        if (rs.getString("email").length()>0){
                            buffer+="<span class=\"email\">"+" email: "+rs.getString("email") +"</span>";
                        }
                        if (rs.getString("vk").length()>0){
                            buffer+="<span class=\"vk\">"+" vk: "+rs.getString("vk") +"</span>" ;
                        }
                        buffer+="</div>";

                    }
                    rs.close();
                    stmt.close();

                } finally {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (buffer);
    }

}
