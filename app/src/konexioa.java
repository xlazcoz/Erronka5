import java.sql.*;

public class konexioa {
    private static final String URL = "jdbc:mySQL://localhost:3306/erronka5";
    private static final String ERABILTZAILEA = "root";
    private static final String PASAHITZA = "Lazcoz0720";

    public static Connection getKonexioa() throws SQLException {
        return DriverManager.getConnection(URL, ERABILTZAILEA, PASAHITZA);
    }

    public void conectar() {

    }
}
