import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class StudentLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String registration = req.getParameter("reg");
        String password = req.getParameter("studentPass");
        String semester = req.getParameter("studentSemester");

        // req.setAttribute("reg",registration);
        // req.setAttribute("studentPass",password);

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM students WHERE Student_Registration=? AND Student_Password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, registration);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                // login success
                req.setAttribute("registration", registration);
                req.setAttribute("semester", semester);

                req.getRequestDispatcher("StudentPage.jsp").forward(req, resp);
            } else {

                // login failed

                resp.setContentType("text/html");
                PrintWriter out = resp.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Invalid registration or password');");
                out.println("location='index.jsp';"); // redirect back to login page
                out.println("</script>");

                // req.setAttribute("error", "Invalid registration or password");

                // req.getRequestDispatcher("index.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            throw new ServletException("Database error", e);
        }

        // req.getRequestDispatcher("/StudentFeature.jsp").forward(req,resp);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

}

// import javax.servlet.*;
// import javax.servlet.http.*;
// import java.io.IOException;
// import java.sql.*;

// public class LoginServlet extends HttpServlet {
// @Override
// protected void doPost(HttpServletRequest request, HttpServletResponse
// response)
// throws ServletException, IOException {

// String username = request.getParameter("username");
// String password = request.getParameter("password");

// try (Connection conn = DBConnection.getConnection()) {
// String sql = "SELECT * FROM users WHERE username=? AND password=?";
// PreparedStatement stmt = conn.prepareStatement(sql);
// stmt.setString(1, username);
// stmt.setString(2, password);

// ResultSet rs = stmt.executeQuery();

// if (rs.next()) {
// // ✅ login success
// request.setAttribute("username", username);
// RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
// rd.forward(request, response);
// } else {
// // ❌ login failed
// request.setAttribute("error", "Invalid username or password");
// RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
// rd.forward(request, response);
// }

// } catch (Exception e) {
// throw new ServletException("Database error", e);
// }
// }
// }
