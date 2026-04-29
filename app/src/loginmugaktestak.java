import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LoginMugakTest {

    @Test
    void testErabiltzaileHutsik_PK22() {
        // PK22: Erabiltzailea hutsik
        erabiltzailea e = new erabiltzailea("", "1234");
        e.erabiltzaileaIdentifikatuEtaBaimenaEzarri();
        assertTrue(e.baimena == null || e.baimena.equals(""), "Ezin da logeatu erabiltzaile izenik gabe");
    }

    @Test
    void testErabiltzaileLuzeegia_PK23() {
        // PK23: Erabiltzailea > 50 karaktere
        String luzeegia = "a".repeat(51);
        erabiltzailea e = new erabiltzailea(luzeegia, "1234");
        e.erabiltzaileaIdentifikatuEtaBaimenaEzarri();
        assertTrue(e.baimena == null || e.baimena.equals(""), "Ezin da logeatu izen luzeegiarekin");
    }

    @Test
    void testPasahitzHutsik_PK25() {
        // PK25: Pasahitza hutsik
        erabiltzailea e = new erabiltzailea("xabi", "");
        e.erabiltzaileaIdentifikatuEtaBaimenaEzarri();
        assertTrue(e.baimena == null || e.baimena.equals(""), "Ezin da logeatu pasahitzik gabe");
    }

    @Test
    void testPasahitzLuzeegia_PK26() {
        // PK26: Pasahitza > 50 karaktere
        String luzeegia = "a".repeat(51);
        erabiltzailea e = new erabiltzailea("xabi", luzeegia);
        e.erabiltzaileaIdentifikatuEtaBaimenaEzarri();
        assertTrue(e.baimena == null || e.baimena.equals(""), "Ezin da logeatu pasahitz luzeegiarekin");
    }
}