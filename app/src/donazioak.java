import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.*;

public class donazioak {
    int id_donazioa;
    String donatzailea;
    int id_donatzailea;
    LocalDate donazioarendata;
    int produktua;
    String produktuaren_izena;

    public donazioak(String donatzailea, LocalDate donazioarendata, int produktua) {
        this.donatzailea = donatzailea;
        this.donazioarendata = donazioarendata;
        this.produktua = produktua;
    }

    public donazioak(int id_donazioa, String donatzailea, LocalDate donazioarendata, String produktuaren_izena) {
        this.id_donazioa = id_donazioa;
        this.donatzailea = donatzailea;
        this.donazioarendata = donazioarendata;
        this.produktuaren_izena = produktuaren_izena;
    }

    public donazioak(int id_donazioa, int id_donatzailea, LocalDate donazioarendata, int produktua) {
        this.id_donazioa = id_donazioa;
        this.id_donatzailea = id_donatzailea;
        this.donazioarendata = donazioarendata;
        this.produktua = produktua;
    }

    public boolean donazioaSortuIDarekin(donazioak d) {
        String sql = "{call donazioa_sortu(?, ?, ?)}";

        try (
                Connection conn = konexioa.getKonexioa();
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

    public boolean donazioaAldatu(donazioak d) {
        String sql = "{call donazioa_aldatu(?, ?, ?, ?)}";

        try (
                Connection conn = konexioa.getKonexioa();
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

    public boolean donazioaEzabatu(int id_donazioa) {
        String sql = "{call donazioa_borratu(?)}";

        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, id_donazioa);
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<donazioak> donazioaBistaratu(int biltegia) {
        String sql = "{call donazioak_ikusi()}";
        ArrayList<donazioak> dList = new ArrayList<donazioak>();
        donazioak dona;
        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql);
                ResultSet resultSet = cstmt.executeQuery()) {

            while (resultSet.next()) {

                int id = resultSet.getInt("id_donazioa");
                String donatzailea = resultSet.getString("donatzailea");
                String donazio_data = resultSet.getString("donazio_data");
                String produktua = resultSet.getString("produktua");

                LocalDate donaziodata = LocalDate.parse(donazio_data);
                dona = new donazioak(id, donatzailea, donaziodata, produktua);
                dList.add(dona);
            }
            return dList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void donatzaileakBistaratu() {
        String sql = "{call donatzaileak_ikusi()}";
        try (
                Connection conn = konexioa.getKonexioa();
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