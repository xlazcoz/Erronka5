import java.sql.*;
import java.util.ArrayList;

public class produktuak {
    int id;
    String erreferentzia;
    String izena;
    String empresa;

    public produktuak(int id, String erreferentzia, String izena, String empresa) {
        this.id = id;
        this.erreferentzia = erreferentzia;
        this.izena = izena;
        this.empresa = empresa;
    }

    public produktuak(int id, String izena) {
        this.id = id;
        this.izena = izena;
    }

    public produktuak(String erreferentzia, String izena, String empresa) {
        this.erreferentzia = erreferentzia;
        this.izena = izena;
        this.empresa = empresa;
    }

    public String erreferentziabueltatu(int id_produkto) {
        String sql = "{call produktu_erreferentzia(?, ?)}";

        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, id_produkto);
            cstmt.registerOutParameter(2, Types.VARCHAR);

            cstmt.execute();
            String refObtenida = cstmt.getString(2);
            return refObtenida;
        }

        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<produktuak> ProduktuakBistaratu() {

        String sql = "{call Produktuak_bistaratu()}";
        ArrayList<produktuak> pList = new ArrayList<produktuak>();
        produktuak p;
        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql);
                ResultSet resultSet = cstmt.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_produktua");
                String izena = resultSet.getString("izena");
                String empresa = resultSet.getString("empresa");
                String erreferentzia = resultSet.getString("erreferentzia");

                p = new produktuak(id, erreferentzia, izena, empresa);
                pList.add(p);

            }
            return pList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int erreferentziaKomprobatu(String ref) {

        if (ref.matches("IR\\d{5}")) {
            return 1;
        } else if (ref.matches("EZ\\d{5}")) {
            return 2;
        } else if (ref.matches("ER\\d{5}")) {
            return 3;
        } else
            return 0;

    }

    public boolean produktuaEzabatu(int id_erabiltzailea) {
        String sql = "{call produktua_borratu(?)}";

        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, id_erabiltzailea);
            int filasAfectadas = cstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int ProduktuaKomprobatu(String nuevonombre, String nuevaempresa) {
        produktuak comprobar = new produktuak(null, null, null);
        ArrayList<produktuak> lista = comprobar.ProduktuakBistaratu();
        for (produktuak p : lista) {
            if (p.izena.equals(nuevonombre) && p.empresa.equals(nuevaempresa)) {
                return p.id;
            }
        }
        return 0;
    }
}
