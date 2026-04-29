import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Biltegia {
    
    int produktuid;
    int idBiltegia;
    int pasilokozenbakia;
    int kokapenkodea;
    int stock;
    int kantitatea;
    LocalDate iraungitze_data;

    public Biltegia(int produktuid, int idBiltegia, int pasilokozenbakia, int kokapenkodea, int kantitatea, LocalDate iraungitze_data ){

        this.produktuid = produktuid;
        this.idBiltegia = idBiltegia;
        this.pasilokozenbakia = pasilokozenbakia;
        this.kokapenkodea = kokapenkodea;
        this.kantitatea = kantitatea;
        this.iraungitze_data = iraungitze_data;

    }


    public boolean produktuaBiltegianSartu(Biltegia b) {
        String sql = "{call ProduktuaGehitu(?, ?, ?, ?, ?, ?)}";

        try (
                Connection conn = Konexioa.konexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, b.produktuid);
            cstmt.setInt(2, b.idBiltegia);
            cstmt.setInt(3, b.pasilokozenbakia);
            cstmt.setInt(4, b.kokapenkodea);
            cstmt.setInt(5, b.kantitatea);
            cstmt.setDate(6, java.sql.Date.valueOf(b.iraungitze_data));
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean produktuaEzabatu(int produktuid, int idBiltegia) {
        String sql = "{call EzabatuProduktua(?, ?)}";

        try (
                Connection conn = Konexioa.konexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, produktuid);
            cstmt.setInt(2, idBiltegia);
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ProduktuaAldatu(Biltegia b) {
        String sql = "{call AldatuProduktua(?, ?, ?, ?, ?, ?)}";
        try (
                Connection conn = Konexioa.konexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, b.produktuid);
            cstmt.setInt(2, b.idBiltegia);
            cstmt.setInt(3, b.pasilokozenbakia);
            cstmt.setInt(4, b.kokapenkodea);
            cstmt.setInt(5, b.kantitatea);
            cstmt.setDate(6, java.sql.Date.valueOf(b.iraungitze_data));
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}


