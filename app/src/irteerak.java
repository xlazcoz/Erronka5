import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.*;

/**
 * Irteerak klasea irteeren informazioa kudeatzen du.
 */
public class irteerak {
    int id_irteera;
    String helmuga;
    int id_helmuga;
    LocalDate bidalketadata;
    int produktua;
    String produktuaren_izena;

    /**
     * Irteerak objektua sortzen du.
     * @param helmuga helmuga
     * @param bidalketadata bidalketa data
     * @param produktua produktua
     */
    public irteerak(String helmuga, LocalDate bidalketadata, int produktua) {
        this.helmuga = helmuga;
        this.bidalketadata = bidalketadata;
        this.produktua = produktua;
    }

    /**
     * Irteerak objektua sortzen du.
     * @param id_irteera irteera ID
     * @param helmuga helmuga
     * @param bidalketadata bidalketa data
     * @param produktuaren_izena produktuaren izena
     */
    public irteerak(int id_irteera, String helmuga, LocalDate bidalketadata, String produktuaren_izena) {
        this.id_irteera = id_irteera;
        this.helmuga = helmuga;
        this.bidalketadata = bidalketadata;
        this.produktuaren_izena = produktuaren_izena;
    }

    /**
     * Irteerak objektua sortzen du.
     * @param id_irteera irteera ID
     * @param id_helmuga helmuga ID
     * @param bidalketadata bidalketa data
     * @param produktua produktua
     */
    public irteerak(int id_irteera, int id_helmuga, LocalDate bidalketadata, int produktua) {
        this.id_irteera = id_irteera;
        this.id_helmuga = id_helmuga;
        this.bidalketadata = bidalketadata;
        this.produktua = produktua;
    }

    /**
     * Irteera sortzen du.
     * @param i irteerak objektua
     * @return true ondo joan bada
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
     * Irteera aldatzen du.
     * @param i irteerak objektua
     * @return true ondo joan bada
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
     * Irteera ezabatzen du.
     * @param id_irteera irteera ID
     * @return true ondo joan bada
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
     * Irteerak bistaratzen ditu.
     * @param biltegia biltegiaren ID
     * @return irteeren zerrenda
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