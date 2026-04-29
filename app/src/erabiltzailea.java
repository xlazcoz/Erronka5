import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class erabiltzailea {
    int id_erabiltzailea;
    String izena;
    String pasahitza;
    String baimena;
    int baimena_id;

    /**
     * Erabiltzaile berri bat sortzeko konstruktorea.
     */
    public erabiltzailea(String izena, String pasahitza, int baimena_id) {
        this.izena = izena;
        this.pasahitza = pasahitza;
        this.baimena_id = baimena_id;
    }

    /**
     * Erabiltzaile objektua ID eta informazioarekin sortzen du.
     */
    public erabiltzailea(int id_erabiltzailea, String izena, String pasahitza, int baimena_id) {
        this.izena = izena;
        this.pasahitza = pasahitza;
        this.baimena_id = baimena_id;
        this.id_erabiltzailea = id_erabiltzailea;
    }

    /**
     * Erabiltzaile objektua baimenarekin sortzen du.
     */
    public erabiltzailea(int id_erabiltzailea, String izena, String baimena) {
        this.id_erabiltzailea = id_erabiltzailea;
        this.izena = izena;
        this.baimena = baimena;
    }

    /**
     * Erabiltzailea login informazioarekin sortzen du.
     */
    public erabiltzailea(String izena, String pasahitza) {
        this.izena = izena;
        this.pasahitza = pasahitza;
    }

    /**
     * Erabiltzaile berria datu-basean gordetzen du.
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
     * Erabiltzailearen datuak datu-basean aldatzen ditu.
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
     * Erabiltzaile bat datu-baseatik ezabatzeko metodoa.
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
     * Erabiltzailearen login egiaztatzen du eta baimena ezartzen du.
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
     * Erabiltzaile guztiak datu-baseatik irakurtzen ditu.
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
