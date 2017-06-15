import helpers.DBCheck;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Search extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<!DOCTYPE HTML>");
        resp.getWriter().println("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\"></head>");
        resp.getWriter().println("<body><p>Для добавления новой записи<a href=\"/\"> перейдите на главную страницу</a> и введите данные.</p>");
        resp.getWriter().println(DBCheck.check());
        resp.getWriter().println("<p>Для поиска заполниет форму:</p>");
        resp.getWriter().println("<form name=\"searchPerson\" method=\"post\" action=\"searchresults\">");
        resp.getWriter().println("<input name=\"name\" type=\"text\" placeholder=\"Enter name\">");
        resp.getWriter().println("<input name=\"phone\" type=\"text\" placeholder=\"Enterphone\">");
        resp.getWriter().println("<input name=\"email\" type=\"text\" placeholder=\"Enter e-mail\">");
        resp.getWriter().println(" <input name=\"vk\" type=\"text\" placeholder=\"Enter vk\">");
        resp.getWriter().println("<input type=\"submit\"  value=\"Search\">");
        resp.getWriter().println("</form>");
        resp.getWriter().println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
