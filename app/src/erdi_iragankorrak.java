
import java.sql.*;

/**
 * Erdi_iragankorrak klasea erdi iragankor produktuen informazioa kudeatzen du.
 */
public class erdi_iragankorrak extends produktuak {
    boolean hoztu;
    int hezetasunmax;

    /**
     * Erdi_iragankorrak objektua sortzen du.
     * @param id produktua ID
     * @param erreferentzia erreferentzia
     * @param izena izena
     * @param empresa empresa
     * @param hoztu hoztu behar den
     * @param hezetasunmax hezetasun maximoa
     */
    public erdi_iragankorrak(int id, String erreferentzia, String izena, String empresa, boolean hoztu,
            int hezetasunmax) {
        super(id, erreferentzia, izena, empresa);
        this.hoztu = hoztu;
        this.hezetasunmax = hezetasunmax;
    }

    /**
     * Erdi_iragankorrak objektua sortzen du.
     * @param erreferentzia erreferentzia
     * @param izena izena
     * @param empresa empresa
     * @param hoztu hoztu behar den
     * @param hezetasunmax hezetasun maximoa
     */
    public erdi_iragankorrak(String erreferentzia, String izena, String empresa, boolean hoztu, int hezetasunmax) {
        super(erreferentzia, izena, empresa);
        this.hoztu = hoztu;
        this.hezetasunmax = hezetasunmax;
    }

    /**
     * Produktua sortzen du.
     * @param er erdi_iragankorrak objektua
     * @return true ondo joan bada
     */
    public boolean produktuaSortu(erdi_iragankorrak er) {
        String sql = "{call erdi_iragankorra_sortu(?, ?, ?, ?, ?)}";

        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, er.izena);
            cstmt.setString(2, er.erreferentzia);
            cstmt.setString(3, er.empresa);
            cstmt.setBoolean(4, er.hoztu);
            cstmt.setInt(5, er.hezetasunmax);
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Produktua aldatzen du.
     * @param er erdi_iragankorrak objektua
     * @return true ondo joan bada
     */
    public boolean produktuaAldatu(erdi_iragankorrak er) {
        String sql = "{call erdi_iragankorra_aldatu(?, ?, ?, ?, ?)}";

        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, er.izena);
            cstmt.setString(2, er.empresa);
            cstmt.setBoolean(3, er.hoztu);
            cstmt.setInt(4, er.hezetasunmax);
            cstmt.setInt(5, er.id);
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
