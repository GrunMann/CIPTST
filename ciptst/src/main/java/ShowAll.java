import APITasks.VKTasks;
import helpers.DBCheck;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowAll  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<!DOCTYPE HTML>");
        resp.getWriter().println("<html><link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
        resp.getWriter().println("<body><p>Для добавления новой записи<a href=\"/\"> перейдите на главную страницу</a> и введите данные.</p>");
        resp.getWriter().println(DBCheck.check());
        resp.getWriter().println(DBCheck.showAll());
        resp.getWriter().println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
