import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * ProduktuBiltegia klasea biltegiko produktuen informazioa kudeatzen du.
 */
public class produktuBiltegia {
    int id_produktua;
    int id_biltegia;
    String izena;
    int pasilokozen;
    int kokapenkodea;
    int kantitatea;
    LocalDate iraungintze_data;

    /**
     * ProduktuBiltegia objektua sortzen du.
     * @param id_produktua produktua ID
     * @param id_biltegia biltegia ID
     * @param pasilokozen pasiloko zenbakia
     * @param kokapenkodea kokapen kodea
     * @param kantitatea kantitatea
     * @param iraungintze_data iraungintze data
     */
    public produktuBiltegia(int id_produktua, int id_biltegia, int pasilokozen, int kokapenkodea, int kantitatea,
            LocalDate iraungintze_data) {
        this.id_produktua = id_produktua;
        this.id_biltegia = id_biltegia;
        this.pasilokozen = pasilokozen;
        this.kokapenkodea = kokapenkodea;
        this.kantitatea = kantitatea;
        this.iraungintze_data = iraungintze_data;
    }

    /**
     * ProduktuBiltegia objektua sortzen du.
     * @param kantitatea kantitatea
     * @param izena izena
     * @param id_produktua produktua ID
     */
    public produktuBiltegia(int kantitatea, String izena, int id_produktua) {
        this.kantitatea = kantitatea;
        this.izena = izena;
        this.id_produktua = id_produktua;
    }

    /**
     * Produktua biltegian sartzen du.
     * @param pb produktuBiltegia objektua
     * @return true ondo joan bada
     */
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

    /**
     * Produktua biltegitik ezabatzen du.
     * @param id_producto produktua ID
     * @param biltegia biltegia ID
     * @return true ondo joan bada
     */
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

    /**
     * Produktua biltegian aldatzen du.
     * @param pb produktuBiltegia objektua
     * @return true ondo joan bada
     */
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

    /**
     * Stocka kentzen du.
     * @param id_produktua produktua ID
     * @param id_biltegia biltegia ID
     * @param atera_kantitatea atera kantitatea
     * @return true ondo joan bada
     */
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

    
    /**
     * Biltegiko produktuak bistaratzen ditu.
     * @param id_biltegia biltegia ID
     * @return produktuen zerrenda
     */
    public ArrayList<produktuBiltegia> biltegikoProduktuakBistaratu(int id_biltegia) {
        String sql = "{call biltegiko_produktuak_ikusi(?)}";
        ArrayList<produktuBiltegia> lista = new ArrayList<>();
        
        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, id_biltegia);
            
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    int id_prod = rs.getInt("id_produktua");
                    String nombre = rs.getString("izena");
                    int cant = rs.getInt("kantitatea");
                    int pasillo = rs.getInt("pasilokozen");
                    int ubicacion = rs.getInt("kokapenkodea");
                    LocalDate caducidad = LocalDate.parse(rs.getString("iraungintze_data"));

                    produktuBiltegia pb = new produktuBiltegia(id_prod, id_biltegia, pasillo, ubicacion, cant, caducidad);
                    pb.izena = nombre; 
                    lista.add(pb);
                }
            }
            return lista;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
