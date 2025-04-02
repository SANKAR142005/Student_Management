import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginServlet")
public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String studentId = request.getParameter("studentId");
        String password = request.getParameter("password");

        try (Connection conn = Dbconnection.getLoginConnection()) {
            String query = "INSERT INTO users (student_id, password) VALUES (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, studentId);
                ps.setString(2, password);
                int rowsInserted = ps.executeUpdate();
                if (rowsInserted > 0) {
                    response.sendRedirect("Register.html");
                } else {
                    response.getWriter().println("<h3>Failed to save login details.</h3>");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
