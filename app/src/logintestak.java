import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class ErabiltzaileTest {

    private erabiltzailea erab;

    @Test
    void testLoginZuzena_PK1() {
        // PK1: Biltegia: 1 · Erab: "xabi" · Pass: "1234"
        erab = new erabiltzailea("xabi", "1234");
        // Kontuan izan metodo honek DB konexioa behar duela edo Mockito bidez simulatu
        erab.erabiltzaileaIdentifikatuEtaBaimenaEzarri();
        
        // Suposatuz DBan xabi/1234 admin dela
        assertNotNull(erab.baimena);
        assertEquals("admin", erab.baimena);
    }

    @Test
    void testLoginOkerra_PK21() {
        // PK21: Erab: "mikel" (existitzen ez dena)
        erab = new erabiltzailea("mikel", "1234");
        erab.erabiltzaileaIdentifikatuEtaBaimenaEzarri();
        
        assertTrue(erab.baimena == null || erab.baimena.equals(""));
    }
}