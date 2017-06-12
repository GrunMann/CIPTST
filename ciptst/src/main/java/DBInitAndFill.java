import helpers.DBCheck;
import helpers.DBInitReinit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DBInitAndFill extends HttpServlet {
    private static String createScriptPath = "/resources/db/dbInit.sql";
    private static String fillScriptPath = "/resources/db/dbFill.sql";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<!DOCTYPE HTML>");
        resp.getWriter().println("<html><link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
        resp.getWriter().println("<body><p> Инициализация и заполнение БД.</p>");
        resp.getWriter().println("<p><a href=\"/\"> Главная страница</a></p>");
        resp.getWriter().println("<p>Функционал отлаживается.</p>");
        //DBInitReinit.runSqlScript(createScriptPath);
        //DBInitReinit.runSqlScript(fillScriptPath);
        resp.getWriter().println(DBCheck.check());
        resp.getWriter().println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
