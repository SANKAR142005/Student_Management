import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection
{

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String LOGIN_DB = "login_db";
    private static final String REGISTRATION_DB = "registration_db";
    private static final String USER = "root"; 
    private static final String PASSWORD = "prince@142005"; 

    public static Connection getLoginConnection() throws SQLException {
        return DriverManager.getConnection(URL + LOGIN_DB, USER, PASSWORD);
    }

    public static Connection getRegistrationConnection() throws SQLException {
        return DriverManager.getConnection(URL + REGISTRATION_DB, USER, PASSWORD);
    }
}
