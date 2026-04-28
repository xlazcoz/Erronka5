import java.sql.*;
import java.time.LocalDate;

public class produktuBiltegia {
    int id_produktua;
    int id_biltegia;
    String izena;
    int pasilokozen;
    int kokapenkodea;
    int kantitatea;
    LocalDate iraungintze_data;

    public produktuBiltegia(int id_produktua, int id_biltegia, int pasilokozen, int kokapenkodea, int kantitatea,
            LocalDate iraungintze_data) {
        this.id_produktua = id_produktua;
        this.id_biltegia = id_biltegia;
        this.pasilokozen = pasilokozen;
        this.kokapenkodea = kokapenkodea;
        this.kantitatea = kantitatea;
        this.iraungintze_data = iraungintze_data;
    }

    public produktuBiltegia(int kantitatea, String izena, int id_produktua) {
        this.kantitatea = kantitatea;
        this.izena = izena;
        this.id_produktua = id_produktua;
    }

    public boolean produktuaBiltegianSartu(produktuBiltegia pb) {
        String sql = "{call produktua_Biltegian_Sartu(?, ?, ?, ?, ?, ?)}";

        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, pb.id_produktua);
            cstmt.setInt(2, pb.id_biltegia);
            cstmt.setInt(3, pb.pasilokozen);
            cstmt.setInt(4, pb.kokapenkodea);
            cstmt.setInt(5, pb.kantitatea);
            cstmt.setDate(6, java.sql.Date.valueOf(pb.iraungintze_data));
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean produktuaEzabatu(int id_producto, int biltegia) {
        String sql = "{call prduktua_biltegitik_borratu(?, ?)}";

        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, id_producto);
            cstmt.setInt(2, biltegia);
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ProduktuaBiltegianAldatu(produktuBiltegia pb) {
        String sql = "{call Produktua_biltegian_aldatu(?, ?, ?, ?, ?, ?)}";
        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, pb.id_produktua);
            cstmt.setInt(2, pb.id_biltegia);
            cstmt.setInt(3, pb.pasilokozen);
            cstmt.setInt(4, pb.kokapenkodea);
            cstmt.setInt(5, pb.kantitatea);
            cstmt.setDate(6, java.sql.Date.valueOf(pb.iraungintze_data));
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean stockaKendu(int id_produktua, int id_biltegia, int atera_kantitatea) {
        String sql = "{call stocka_kendu(?, ?, ?)}";

        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, id_produktua);
            cstmt.setInt(2, id_biltegia);
            cstmt.setInt(3, atera_kantitatea);
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
