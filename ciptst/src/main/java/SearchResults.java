import APITasks.VKTasks;
import helpers.DBCheck;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SearchResults  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<!DOCTYPE HTML>");
        resp.getWriter().println("<html><body><p>Возникли проблемы, " +
                "<a href=\"/\"> перейдите на главную страницу</a> и введите данные снова.</p>");
        resp.getWriter().println("</body></html>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = (String) req.getParameter("name");
        String phone = (String) req.getParameter("phone");
        String email = (String) req.getParameter("email");
        String vk = (String) req.getParameter("vk");
        //String buffer="SELECT person.*, phone.* FROM person, phone WHERE ";
        String buffer="SELECT person.* FROM person WHERE ";
        if (name.length()>0)buffer+="person.name='"+name+"' AND ";
        if (email.length()>0)buffer+="person.email='"+email+"' AND ";
        if (vk.length()>0) buffer+="person.vk='"+vk+"' AND ";
        buffer+=" person.id>0";


        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<!DOCTYPE HTML>");
        resp.getWriter().println("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\"></head><body> ");
        resp.getWriter().println("<a href=\"/\"> На главную страницу</a><br/>");
        
        Connection connection = DBCheck.connect();

        try {
            try {
                Statement stmt = connection.createStatement();
                Statement stmt2 = connection.createStatement();
                ResultSet searchResults = stmt.executeQuery(buffer);
                while (searchResults.next()){
                    ResultSet phones = stmt2.executeQuery("SELECT phone.* " +
                            "FROM phone " +
                            "WHERE phone.personid="+searchResults.getString("id"));
                    int i=1;
                    String phoneList="";
                    while (phones.next()){
                        phoneList+="tel."+i+": "+phones.getString("phone")+", ";
                        i+=1;
                    }
                    resp.getWriter().println("<div class=\"searchResult\">"+
                            "<span class=\"name\" style=\"background-image: url("+
                            VKTasks.getPhoto(searchResults.getString("vk"))+
                            ");\">"+searchResults.getString("name") +"</span>"+
                            "<span class=\"tel\">"+phoneList+"</span>"+
                            "<span class=\"email\">"+" email: "+searchResults.getString("email") +"</span>"+
                            "<span class=\"vk\">"+" vk: "+searchResults.getString("vk") +"</span>"+
                            "</div>");
                }
                stmt.close();
            } finally {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.getWriter().println("</body></html>");
    }
}
