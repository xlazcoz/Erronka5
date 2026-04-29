import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.*;

public class irteerak {
    int id_irteera;
    String helmuga;
    int id_helmuga;
    LocalDate bidalketadata;
    int produktua;
    String produktuaren_izena;

    /**
     * Irteera berri bat sortzeko konstruktorea.
     */
    public irteerak(String helmuga, LocalDate bidalketadata, int produktua) {
        this.helmuga = helmuga;
        this.bidalketadata = bidalketadata;
        this.produktua = produktua;
    }

    /**
     * Irteera objektu bat informazioarekin sortzen du.
     */
    public irteerak(int id_irteera, String helmuga, LocalDate bidalketadata, String produktuaren_izena) {
        this.id_irteera = id_irteera;
        this.helmuga = helmuga;
        this.bidalketadata = bidalketadata;
        this.produktuaren_izena = produktuaren_izena;
    }

    /**
     * Irteera objektua ID eta bestelako datuekin sortzen du.
     */
    public irteerak(int id_irteera, int id_helmuga, LocalDate bidalketadata, int produktua) {
        this.id_irteera = id_irteera;
        this.id_helmuga = id_helmuga;
        this.bidalketadata = bidalketadata;
        this.produktua = produktua;
    }

    /**
     * Irteera berria datu-basean sortzen du.
     */
    public boolean irteeraSortu(irteerak i) {
        String sql = "{call irteera_sortu(?, ?, ?)}";

        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, i.helmuga);
            cstmt.setDate(2, java.sql.Date.valueOf(i.bidalketadata));
            cstmt.setInt(3, i.produktua);
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Irteera baten informazioa datu-basean eguneratzen du.
     */
    public boolean irteeraAldatu(irteerak i) {
        String sql = "{call irteera_aldatu(?, ?, ?, ?)}";

        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, i.id_helmuga);
            cstmt.setDate(2, java.sql.Date.valueOf(i.bidalketadata));
            cstmt.setInt(3, i.produktua);
            cstmt.setInt(4, i.id_irteera);
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Irteera bat datu-baseatik ezabatzeko metodoa.
     */
    public boolean irteeraEzabatu(int id_irteera) {
        String sql = "{call irteera_borratu(?)}";

        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, id_irteera);
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Irteeren zerrenda datu-baseatik irakurtzen du.
     */
    public ArrayList<irteerak> irteerakBistaratu(int biltegia) {
        String sql = "{call irteerak_ikusi()}";
        ArrayList<irteerak> iList = new ArrayList<irteerak>();
        irteerak irteera;

        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql);
                ResultSet resultSet = cstmt.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_irteera");
                String helmuga = resultSet.getString("helmuga");
                String bidalketa_data = resultSet.getString("bidalketa_data");
                String produktua = resultSet.getString("produktua");

                LocalDate bidalketadata = LocalDate.parse(bidalketa_data);

                irteera = new irteerak(id, helmuga, bidalketadata, produktua);
                iList.add(irteera);
            }
            return iList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}