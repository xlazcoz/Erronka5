import java.sql.*;
import java.util.ArrayList;

/**
 * Biltegia klasea biltegien informazioa kudeatzen du.
 */
public class biltegia {
    Integer kodea_biltegia;
    String izena;
    String kokapena;

    /**
     * Biltegia objektua sortzen du.
     * @param kodea_biltegia biltegiaren kodea
     * @param izena biltegiaren izena
     * @param kokapena biltegiaren kokapena
     */
    public biltegia(Integer kodea_biltegia, String izena, String kokapena) {
        this.kodea_biltegia = kodea_biltegia;
        this.izena = izena;
        this.kokapena = kokapena;
    }

    /**
     * Agortutako produktuak erakusten ditu.
     * @return agortutako produktuen zerrenda
     */
    public ArrayList<produktuBiltegia> agortutakoProduktuak() {
        String sql = "{call agprtutako_produktuak_ikusi()}";

        ArrayList<produktuBiltegia> pblist = new ArrayList<produktuBiltegia>();
        produktuBiltegia pb;
        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql);
                ResultSet resultSet = cstmt.executeQuery()) {

            while (resultSet.next()) {

                int id = resultSet.getInt("id_produktua");
                String izena = resultSet.getString("izena");
                int kantitatea = resultSet.getInt("kantitatea");

                pb = new produktuBiltegia(kantitatea, izena, id);
                pblist.add(pb);
            }
            return pblist;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Donazioa 0 diren produktuak erakusten ditu.
     * @return donazioa 0 diren produktuen zerrenda
     */
    public ArrayList<produktuak> donazio0() {
        String sql = "{call donazio0_produktuak_ikusi()}";

        ArrayList<produktuak> plist = new ArrayList<produktuak>();
        produktuak p;
        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql);
                ResultSet resultSet = cstmt.executeQuery()) {

            while (resultSet.next()) {

                int id = resultSet.getInt("id_produktua");
                String izena = resultSet.getString("izena");

                p = new produktuak(id, izena);
                plist.add(p);
            }
            return plist;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Biltegi batean stock maximoa duen produktua erakusten du.
     * @param biltegia biltegiaren ID
     * @return stock maximoa duen produktua
     */
    public produktuak StockMax(int biltegia) {
        String sql = "{call Stock_Max(?)}";

        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, biltegia);

            try (ResultSet rs = cstmt.executeQuery()) {

                if (rs.next()) {
                    String izena = rs.getString("izena");
                    String empresa = rs.getString("empresa");
                    String erreferentzia = rs.getString("erreferentzia");

                    produktuak p = new produktuak(erreferentzia, izena, empresa);
                    return p;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Biltegi bat konprobatzen du.
     * @param biltegia biltegiaren ID
     * @return biltegiaren kokapena
     */
    public String biltegiaKomprobatu(int biltegia) {
        String sql = "{call biltegi_Komprobatzen(?, ?)}";

        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, biltegia);
            cstmt.registerOutParameter(2, Types.VARCHAR);

            cstmt.execute();
            String kokapena = cstmt.getString(2);

            if (kokapena != null) {
                System.out.println(kokapena + " ko biltegian zaude");
                return kokapena;
            } else {
                System.out.println("Ez duzu ID balido bat jarri.");
                return "";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Biltegi guztiak bistaratzen ditu.
     * @return biltegien zerrenda
     */
    public ArrayList<biltegia> biltegiakBistaratu() {
        String sql = "{call biltegiak_ikusi()}";
        ArrayList<biltegia> blist = new ArrayList<biltegia>();
        biltegia b;
        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql);
                ResultSet resultSet = cstmt.executeQuery()) {

            while (resultSet.next()) {

                int id = resultSet.getInt("id_biltegia");
                String izena = resultSet.getString("izena");
                String kokapena = resultSet.getString("kokapena");

                b = new biltegia(id, izena, kokapena);
                blist.add(b);
            }

            return blist;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
