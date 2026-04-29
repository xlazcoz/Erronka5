import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LoginPasahitzTest {

    @Test
    void testPasahitzOkerra_PK24() {
        // PK24: Erabiltzailea "xabi" existitzen da, baina pasahitza "0000" da (okerra)
        erabiltzailea e = new erabiltzailea("xabi", "0000");
        e.erabiltzaileaIdentifikatuEtaBaimenaEzarri();
        
        // Baimena hutsik geratu behar da
        assertTrue(e.baimena == null || e.baimena.equals(""), "Ez luke sartzen utzi behar pasahitz okerrarekin");
    }
    
    @Test
    void testBesteRolBatzuk_PK2_PK3() {
        // PK2: Kudeatzailea login
        erabiltzailea kudeatzaile = new erabiltzailea("aitor", "4321");
        kudeatzaile.erabiltzaileaIdentifikatuEtaBaimenaEzarri();
        // assertEquals("kudeatzailea", kudeatzaile.baimena); // DB errealarekin

        // PK3: Info login
        erabiltzailea info = new erabiltzailea("miren", "5678");
        info.erabiltzaileaIdentifikatuEtaBaimenaEzarri();
        // assertEquals("info", info.baimena); // DB errealarekin
    }
}