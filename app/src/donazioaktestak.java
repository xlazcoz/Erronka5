import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class DonazioZuzenakTest {

    @Test
    void testDonazioaSortu_PK11_PK12() {
        // PK11: Existitzen den donatzailea
        donazioak d1 = new donazioak("Eroski Fundazioa", LocalDate.of(2026, 4, 15), 3);
        
        // PK12: Donatzaile berria
        donazioak d2 = new donazioak("Bilbo Elkartea", LocalDate.of(2026, 4, 20), 2);
        
        // Objektuak ondo eraikitzen direla ziurtatu
        assertEquals("Eroski Fundazioa", d1.donatzailea);
        assertEquals("Bilbo Elkartea", d2.donatzailea);
        
        // assertTrue(d1.donazioaSortuIDarekin(d1)); // DB test
    }
}