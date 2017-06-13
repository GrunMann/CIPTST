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
        String buffer="SELECT person.*, phone.* FROM person, phone WHERE ";

        if (name.length()>0)buffer+="person.name='"+name+"' AND ";
        if (phone.length()>0)buffer+="phone.phone='"+phone+"' AND ";
        if (email.length()>0)buffer+="person.email='"+email+"' AND ";
        if (vk.length()>0) buffer+="person.vk='"+vk+"' AND ";
        buffer+="person.id=phone.personid";

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<!DOCTYPE HTML>");
        resp.getWriter().println("<html><body> <a href=\"/\"> На главную страницу</a><br/><p> Ищем "+buffer+" </p>");

        Connection connection = DBCheck.connect();

        try {
            try {
                Statement stmt = connection.createStatement();
                ResultSet searchResults = stmt.executeQuery(buffer);

                while (searchResults.next()){
                    resp.getWriter().println("<p> Name "+searchResults.getString("name") +
                            ", tel.: "+searchResults.getString("phone") +
                            ", email: "+searchResults.getString("email") +
                            ", vk: "+searchResults.getString("vk") +
                            "</p>");
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
