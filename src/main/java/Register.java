import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/RegisterServlet")
public class Register extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String rollNumber = request.getParameter("rollNumber");

        try (Connection conn = Dbconnection.getRegistrationConnection()) {
            String query = "INSERT INTO students (full_name, dob, gender, roll_number) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, name);
                ps.setString(2, dob);
                ps.setString(3, gender);
                ps.setString(4, rollNumber);
                int rowsInserted = ps.executeUpdate();
                if (rowsInserted > 0) {
                    response.getWriter().println("<h3>Registration Successful!</h3>");
                } else {
                    response.getWriter().println("<h3>Failed to save registration details.</h3>");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
