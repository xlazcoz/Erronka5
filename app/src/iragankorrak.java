import java.sql.*;

public class iragankorrak extends produktuak {
    boolean hoztu;

    /**
     * Iragankor produktua sortzeko konstruktorea.
     */
    public iragankorrak(int id, String erreferentzia, String izena, String empresa,
            boolean hoztu) {
        super(id, erreferentzia, izena, empresa);
        this.hoztu = hoztu;
    }

    /**
     * Iragankor produktu berria sortzeko konstruktorea.
     */
    public iragankorrak(String erreferentzia, String izena, String empresa,
            boolean hoztu) {
        super(erreferentzia, izena, empresa);
        this.hoztu = hoztu;
    }

    /**
     * Iragankor produktua datu-basean sortzen du.
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
     * Iragankor produktua datu-basean eguneratzen du.
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
