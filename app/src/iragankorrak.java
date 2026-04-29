import java.sql.*;

/**
 * Iragankorrak klasea iragankor produktuen informazioa kudeatzen du.
 */
public class iragankorrak extends produktuak {
    boolean hoztu;

    /**
     * Iragankorrak objektua sortzen du.
     * @param id produktua ID
     * @param erreferentzia erreferentzia
     * @param izena izena
     * @param empresa empresa
     * @param hoztu hoztu behar den
     */
    public iragankorrak(int id, String erreferentzia, String izena, String empresa,
            boolean hoztu) {
        super(id, erreferentzia, izena, empresa);
        this.hoztu = hoztu;
    }

    /**
     * Iragankorrak objektua sortzen du.
     * @param erreferentzia erreferentzia
     * @param izena izena
     * @param empresa empresa
     * @param hoztu hoztu behar den
     */
    public iragankorrak(String erreferentzia, String izena, String empresa,
            boolean hoztu) {
        super(erreferentzia, izena, empresa);
        this.hoztu = hoztu;
    }

    /**
     * Produktua sortzen du.
     * @param ir iragankorrak objektua
     * @return true ondo joan bada
     */
    public boolean produktuaSortu(iragankorrak ir) {
        String sql = "{call iragankorra_sortu(?, ?, ?, ?)}";

        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, ir.izena);
            cstmt.setString(2, ir.erreferentzia);
            cstmt.setString(3, ir.empresa);
            cstmt.setBoolean(4, ir.hoztu);
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Produktua aldatzen du.
     * @param ir iragankorrak objektua
     * @return true ondo joan bada
     */
    public boolean produktuaAldatu(iragankorrak ir) {
        String sql = "{call iragankorra_aldatu(?, ?, ?, ?)}";

        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, ir.izena);
            cstmt.setString(2, ir.empresa);
            cstmt.setBoolean(3, ir.hoztu);
            cstmt.setInt(4, ir.id);
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
