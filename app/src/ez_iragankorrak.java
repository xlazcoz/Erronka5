import java.sql.*;

public class ez_iragankorrak extends produktuak {
    boolean kontserba;

    public ez_iragankorrak(int id, String erreferentzia, String izena, String empresa, boolean kontserba) {
        super(id, erreferentzia, izena, empresa);
        this.kontserba = kontserba;
    }

    public ez_iragankorrak(String erreferentzia, String izena, String empresa,
            boolean kontserba) {
        super(erreferentzia, izena, empresa);
        this.kontserba = kontserba;
    }

    public boolean produktuaSortu(ez_iragankorrak ez) {
        String sql = "{call ez_iragankorra_sortu(?, ?, ?, ?)}";

        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, ez.izena);
            cstmt.setString(2, ez.erreferentzia);
            cstmt.setString(3, ez.empresa);
            cstmt.setBoolean(4, ez.kontserba);
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean produktuaAldatu(ez_iragankorrak ez) {
        String sql = "{call ez_iragankorra_aldatu(?, ?, ?, ?)}";

        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, ez.izena);
            cstmt.setString(2, ez.empresa);
            cstmt.setBoolean(3, ez.kontserba);
            cstmt.setInt(4, ez.id);
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
