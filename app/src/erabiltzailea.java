import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Erabiltzailea klasea erabiltzaileen informazioa kudeatzen du.
 */
public class erabiltzailea {
    int id_erabiltzailea;
    String izena;
    String pasahitza;
    String baimena;
    int baimena_id;

    /**
     * Erabiltzailea objektua sortzen du.
     * @param izena erabiltzailearen izena
     * @param pasahitza erabiltzailearen pasahitza
     * @param baimena_id baimenaren ID
     */
    public erabiltzailea(String izena, String pasahitza, int baimena_id) {
        this.izena = izena;
        this.pasahitza = pasahitza;
        this.baimena_id = baimena_id;
    }

    /**
     * Erabiltzailea objektua sortzen du.
     * @param id_erabiltzailea erabiltzailearen ID
     * @param izena erabiltzailearen izena
     * @param pasahitza erabiltzailearen pasahitza
     * @param baimena_id baimenaren ID
     */
    public erabiltzailea(int id_erabiltzailea, String izena, String pasahitza, int baimena_id) {
        this.izena = izena;
        this.pasahitza = pasahitza;
        this.baimena_id = baimena_id;
        this.id_erabiltzailea = id_erabiltzailea;
    }

    /**
     * Erabiltzailea objektua sortzen du.
     * @param id_erabiltzailea erabiltzailearen ID
     * @param izena erabiltzailearen izena
     * @param baimena erabiltzailearen baimena
     */
    public erabiltzailea(int id_erabiltzailea, String izena, String baimena) {
        this.id_erabiltzailea = id_erabiltzailea;
        this.izena = izena;
        this.baimena = baimena;
    }

    /**
     * Erabiltzailea objektua sortzen du.
     * @param izena erabiltzailearen izena
     * @param pasahitza erabiltzailearen pasahitza
     */
    public erabiltzailea(String izena, String pasahitza) {
        this.izena = izena;
        this.pasahitza = pasahitza;
    }

    /**
     * Erabiltzailea sortzen du.
     * @param erab erabiltzailea objektua
     * @return true ondo joan bada
     */
    public boolean erabiltzaileaSortu(erabiltzailea erab) {
        String sql = "{call erabiltzailea_sortu(?, ?, ?)}";

        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, erab.izena);
            cstmt.setString(2, erab.pasahitza);
            cstmt.setInt(3, erab.baimena_id);
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Erabiltzailea aldatzen du.
     * @param erab erabiltzailea objektua
     * @return true ondo joan bada
     */
    public boolean erabiltzaileaAldatu(erabiltzailea erab) {
        String sql = "{call erabiltzailea_aldatu(?, ?, ?, ?)}";

        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, erab.izena);
            cstmt.setString(2, erab.pasahitza);
            cstmt.setInt(3, erab.baimena_id);
            cstmt.setInt(4, erab.id_erabiltzailea);
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Erabiltzailea ezabatzen du.
     * @param id_erabiltzailea erabiltzailearen ID
     * @return true ondo joan bada
     */
    public boolean erabiltzaileaEzabatu(int id_erabiltzailea) {
        String sql = "{call erabiltzailea_borratu(?)}";

        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, id_erabiltzailea);
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Erabiltzailea identifikatzen eta baimena ezartzen du.
     */
    public void erabiltzaileaIdentifikatuEtaBaimenaEzarri() {

        String sql = "{call erabiltzaile_logina(?, ?, ?)}";

        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, this.izena);
            cstmt.setString(2, this.pasahitza);
            cstmt.registerOutParameter(3, Types.VARCHAR);

            cstmt.execute();
            String rolObtenido = cstmt.getString(3);

            if (rolObtenido != null) {
                System.out.println("Acceso exitoso. Rol: " + rolObtenido);
                this.baimena = rolObtenido;
            } else {
                System.out.println("Credenciales incorrectas");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Erabiltzaileak bistaratzen ditu.
     * @return erabiltzaileen zerrenda
     */
    public ArrayList<erabiltzailea> erabiltzaileakBistaratu() {
        String sql = "{call erabiltzaileak_ikusi()}";
        ArrayList<erabiltzailea> eList = new ArrayList<erabiltzailea>();
        erabiltzailea erab;
        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql);
                ResultSet resultSet = cstmt.executeQuery()) {

            while (resultSet.next()) {

                int id = resultSet.getInt("id_erabiltzaileak");
                String izena = resultSet.getString("erabiltzaile_izena");
                String baimena = resultSet.getString("baimen_izena");

                erab = new erabiltzailea(id, izena, baimena);
                eList.add(erab);
            }
            return eList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
