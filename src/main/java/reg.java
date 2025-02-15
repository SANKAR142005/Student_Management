import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StudentRegisterServlet")
public class reg extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        // Get form parameters
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String rollNumber = request.getParameter("rollNumber");

        // Validation: Ensure all fields are filled
        if (name != null && !name.isEmpty() && dob != null && !dob.isEmpty() &&
            gender != null && (gender.equals("Male") || gender.equals("Female")) &&
            rollNumber != null && !rollNumber.isEmpty()) {

            response.getWriter().println("<h3>Registration Successful!</h3>");
            response.getWriter().println("<p>Name: " + name + "</p>");
            response.getWriter().println("<p>Date of Birth: " + dob + "</p>");
            response.getWriter().println("<p>Gender: " + gender + "</p>");
            response.getWriter().println("<p>Roll Number: " + rollNumber + "</p>");
        } else {
            response.getWriter().println("<h3 style='color:red;'>Invalid details! Please enter correct information.</h3>");
        }
    }
}
