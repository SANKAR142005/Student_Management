import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

		if (name != null && !name.isEmpty() && dob != null && !dob.isEmpty() &&
			gender != null && (gender.equals("Male") || gender.equals("Female")) &&
			rollNumber != null && !rollNumber.isEmpty()) {

			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");

				
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/register_db", "root", "prince@142005");

				PreparedStatement ps = con.prepareStatement("INSERT INTO students (name, dob, gender, rollNumber) VALUES (?, ?, ?, ?)");
				ps.setString(1, name);
				ps.setString(2, dob);
				ps.setString(3, gender);
				ps.setString(4, rollNumber);

				int rowsInserted = ps.executeUpdate();
				con.close();

				if (rowsInserted > 0) {
					response.getWriter().println("<h3>Registration Successful!</h3>");
				} else {
					response.getWriter().println("<h3>Failed to Register!</h3>");
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().println("<h3>Database Connection Error!</h3>");
			}
		} else {
			response.getWriter().println("<h3 style='color:red;'>Invalid details! Please enter correct information.</h3>");
		}
	}
}
