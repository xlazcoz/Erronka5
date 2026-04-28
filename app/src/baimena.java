import java.sql.*;
import java.util.ArrayList;

public class baimena {
    int id;
    String izena;

    public baimena(int id, String izena) {
        this.id = id;
        this.izena = izena;
    }

    public ArrayList<baimena> erakutsiBaimenak() {

        String sql = "{call baimenak_ikusi()}";
        ArrayList<baimena> blist = new ArrayList<baimena>();
        baimena b;
        try (
                Connection conn = konexioa.getKonexioa();
                CallableStatement cstmt = conn.prepareCall(sql);
                ResultSet resultSet = cstmt.executeQuery()) {

            while (resultSet.next()) {

                int id = resultSet.getInt("id_baimena");
                String izena = resultSet.getString("izena");

                b = new baimena(id, izena);
                blist.add(b);
            }

            return blist;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}