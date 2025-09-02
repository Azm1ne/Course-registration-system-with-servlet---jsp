import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String reg = req.getParameter("reg");
        String password = req.getParameter("password");

        req.setAttribute("reg",reg);
        req.setAttribute("studentPass",password);


        req.getRequestDispatcher("/StudentFeature.jsp").forward(req,resp);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        doGet(request, response);
    }

}
