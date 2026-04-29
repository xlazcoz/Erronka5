import java.sql.*;
public class Konexioa {
    private static final String url = "jdbc:mySQL://localhost:3306/erronka5";
    private static final String erabiltzailea = "root";
    private static final String password = "";

    public static Connection konexioa() throws SQLException {
        return DriverManager.getConnection(url, erabiltzailea, password);
    }
}
