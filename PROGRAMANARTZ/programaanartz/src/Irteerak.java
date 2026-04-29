import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.*;

public class Irteerak {
    int id_irteera;
    String helmuga;
    int id_helmuga;
    LocalDate bidalketadata;
    int produktua;
    String produktuaren_izena;

    public Irteerak(String helmuga, LocalDate bidalketadata, int produktua) {
        this.helmuga = helmuga;
        this.bidalketadata = bidalketadata;
        this.produktua = produktua;
    }

    public Irteerak(int id_irteera, String helmuga, LocalDate bidalketadata, String produktuaren_izena) {
        this.id_irteera = id_irteera;
        this.helmuga = helmuga;
        this.bidalketadata = bidalketadata;
        this.produktuaren_izena = produktuaren_izena;
    }

    public Irteerak(int id_irteera, int id_helmuga, LocalDate bidalketadata, int produktua) {
        this.id_irteera = id_irteera;
        this.id_helmuga = id_helmuga;
        this.bidalketadata = bidalketadata;
        this.produktua = produktua;
    }

    public boolean irteeraSortu(Irteerak i) {
        String sql = "{call irteera_sortu(?, ?, ?)}";

        try (
                Connection conn = Konexioa.konexioa();
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

    public boolean irteeraAldatu(Irteerak i) {
        String sql = "{call irteera_aldatu(?, ?, ?, ?)}";

        try (
                Connection conn = Konexioa.konexioa();
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

    public boolean irteeraEzabatu(int id_irteera) {
        String sql = "{call irteera_borratu(?)}";

        try (
                Connection conn = Konexioa.konexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, id_irteera);
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Irteerak> irteerakBistaratu(int biltegia) {
        String sql = "{call irteerak_ikusi()}";
        ArrayList<Irteerak> iList = new ArrayList<Irteerak>();
        Irteerak irteera;

        try (
                Connection conn = Konexioa.konexioa();
                CallableStatement cstmt = conn.prepareCall(sql);
                ResultSet resultSet = cstmt.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_irteera");
                String helmuga = resultSet.getString("helmuga");
                String bidalketa_data = resultSet.getString("bidalketa_data");
                String produktua = resultSet.getString("produktua");

                LocalDate bidalketadata = LocalDate.parse(bidalketa_data);

                irteera = new Irteerak(id, helmuga, bidalketadata, produktua);
                iList.add(irteera);
            }
            return iList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
