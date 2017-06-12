import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddPerson extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<!DOCTYPE HTML>");
        resp.getWriter().println("<html><body><p>Для добавления новой записи<a href=\"/\"> перейдите на главную страницу</a> и введите данные.</p>");
        resp.getWriter().println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = (String) req.getParameter("name");
        String phone = (String) req.getParameter("phone");
        String email = (String) req.getParameter("email");
        String vk = (String) req.getParameter("vk");

        resp.getWriter().println("<!DOCTYPE HTML>");
        resp.getWriter().println("<html><body><p> Добавляем "+name+"</p>");
        resp.getWriter().println("<p>"+phone+"</p>");
        resp.getWriter().println("<p>"+email+"</p>");
        resp.getWriter().println("<p>"+vk+"</p>");

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
        try {
            try {
                Statement stmt = connection.createStatement();
                ResultSet nextIDQuery = stmt.executeQuery("SELECT nextval('global_seq')");
                if (nextIDQuery.next()){
                    String nextID=nextIDQuery.getString(1);
                    resp.getWriter().println("<p>"+nextID+"</p>");
                    stmt.executeUpdate("INSERT INTO person VALUES('"+nextID+"','"+ name+"','"+email+"','"+vk+"')");
                    stmt.executeUpdate("INSERT INTO phone VALUES('"+ phone+"','"+nextID+"')");
                    stmt.close();
                }else {
                    resp.getWriter().println("<p>Something get wrong!!</p>");
                }
                stmt.close();
                //rs.close();
            } finally {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.getWriter().println("</body></html>");
    }
}