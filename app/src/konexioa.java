import java.sql.*;

/**
 * Konexioa klasea datu basearekin konexioa kudeatzen du.
 */
public class konexioa {
    private static final String URL = "jdbc:mySQL://localhost:3306/erronka5";
    private static final String ERABILTZAILEA = "root";
    private static final String PASAHITZA = "Lazcoz0720";

    /**
     * Datu basearekin konexioa lortzen du.
     * @return konexioa
     */
    public static Connection getKonexioa() throws SQLException {
        return DriverManager.getConnection(URL, ERABILTZAILEA, PASAHITZA);
    }
}
