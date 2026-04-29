import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.*;


public class Donazioak {
    int id_donazioa;
    String donatzailea;
    int id_donatzailea;
    LocalDate donazioarendata;
    int produktua;
    String produktuaren_izena;

    /**
     * Donazioa objektua sortzen du.
     * @param donatzailea donatzailearen izena
     * @param donazioarendata donazioaren data
     * @param produktua produktua
     */
    public Donazioak(String donatzailea, LocalDate donazioarendata, int produktua) {
        this.donatzailea = donatzailea;
        this.donazioarendata = donazioarendata;
        this.produktua = produktua;
    }

    /**
     * Donazioa objektua sortzen du.
     * @param id_donazioa donazioaren ID
     * @param donatzailea donatzailearen izena
     * @param donazioarendata donazioaren data
     * @param produktuaren_izena produktuaren izena
     */
    public Donazioak(int id_donazioa, String donatzailea, LocalDate donazioarendata, String produktuaren_izena) {
        this.id_donazioa = id_donazioa;
        this.donatzailea = donatzailea;
        this.donazioarendata = donazioarendata;
        this.produktuaren_izena = produktuaren_izena;
    }

    /**
     * Donazioa objektua sortzen du.
     * @param id_donazioa donazioaren ID
     * @param id_donatzailea donatzailearen ID
     * @param donazioarendata donazioaren data
     * @param produktua produktua
     */
    public Donazioak(int id_donazioa, int id_donatzailea, LocalDate donazioarendata, int produktua) {
        this.id_donazioa = id_donazioa;
        this.id_donatzailea = id_donatzailea;
        this.donazioarendata = donazioarendata;
        this.produktua = produktua;
    }

    /**
     * Donazioa sortzen du.
     * @param d donazioa objektua
     * @return true ondo joan bada
     */
    public boolean donazioaSortuIDarekin(Donazioak d) {
        String sql = "{call donazioa_sortu(?, ?, ?)}";

        try (
                Connection conn = Konexioa.konexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, d.donatzailea);
            cstmt.setDate(2, java.sql.Date.valueOf(d.donazioarendata));
            cstmt.setInt(3, d.produktua);
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Donazioa aldatzen du.
     * @param d donazioa objektua
     * @return true ondo joan bada
     */
    public boolean donazioaAldatu(Donazioak d) {
        String sql = "{call donazioa_aldatu(?, ?, ?, ?)}";

        try (
                Connection conn = Konexioa.konexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, d.id_donatzailea);
            cstmt.setDate(2, java.sql.Date.valueOf(d.donazioarendata));
            cstmt.setInt(3, d.produktua);
            cstmt.setInt(4, d.id_donazioa);
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Donazioa ezabatzen du.
     * @param id_donazioa donazioaren ID
     * @return true ondo joan bada
     */
    public boolean donazioaEzabatu(int id_donazioa) {
        String sql = "{call donazioa_borratu(?)}";

        try (
                Connection conn = Konexioa.konexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, id_donazioa);
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Donazioak bistaratzen ditu.
     * @param biltegia biltegiaren ID
     * @return donazioen zerrenda
     */
    public ArrayList<Donazioak> donazioaBistaratu(int biltegia) {
        String sql = "{call donazioak_ikusi()}";
        ArrayList<Donazioak> dList = new ArrayList<Donazioak>();
        Donazioak dona;
        try (
                Connection conn = Konexioa.konexioa();
                CallableStatement cstmt = conn.prepareCall(sql);
                ResultSet resultSet = cstmt.executeQuery()) {

            while (resultSet.next()) {

                int id = resultSet.getInt("id_donazioa");
                String donatzailea = resultSet.getString("donatzailea");
                String donazio_data = resultSet.getString("donazio_data");
                String produktua = resultSet.getString("produktua");

                LocalDate donaziodata = LocalDate.parse(donazio_data);
                dona = new Donazioak(id, donatzailea, donaziodata, produktua);
                dList.add(dona);
            }
            return dList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Donatzaileak bistaratzen ditu.
     */
    public void donatzaileakBistaratu() {
        String sql = "{call donatzaileak_ikusi()}";
        try (
                Connection conn = Konexioa.konexioa();
                CallableStatement cstmt = conn.prepareCall(sql);
                ResultSet rs = cstmt.executeQuery()) {
            System.out.println("\n--- DONATZAILEEN LISTA ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_donatzailea") + " | Izena: " + rs.getString("izena"));
            }
            System.out.println("--------------------------\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}