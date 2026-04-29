import java.sql.*;

public class konexioa {
    private static final String URL = "jdbc:mySQL://localhost:3306/erronka5";
    private static final String ERABILTZAILEA = "root";
    private static final String PASAHITZA = "Lazcoz0720";

    /**
     * Datu-basera konektatzeko konexio objektua itzultzen du.
     */
    public static Connection getKonexioa() throws SQLException {
        return DriverManager.getConnection(URL, ERABILTZAILEA, PASAHITZA);
    }

    /**
     * Konektatzeko metodo hutsa, ez du ezer egiten.
     */
    public void conectar() {

    }
}
