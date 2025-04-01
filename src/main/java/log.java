import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LogServlet")
public class Log extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String studentId = request.getParameter("studentId");
		String password = request.getParameter("password");

		try {
	
			Class.forName("com.mysql.cj.jdbc.Driver");

		
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root", "prince@142005");

	
			PreparedStatement ps = con.prepareStatement("SELECT * FROM students_login WHERE studentId=? AND password=?");
			ps.setString(1, studentId);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				response.sendRedirect("reg.html");
			} else {
				response.getWriter().println("<h3>Invalid credentials! Try again.</h3>");
			}

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("<h3>Database Connection Error!</h3>");
		}
	}
}
