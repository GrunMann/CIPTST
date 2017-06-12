import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;

public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
        if (dispatcher != null) {
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder jb = new StringBuilder();
        String line = null;

        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }
}