import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BiltegiTest {

    @Test
    void testBiltegiIdOkerra_PK15_PK17() {
        biltegia b = new biltegia(0, null, null);
        
        // PK15: ID oso altua
        assertEquals("", b.biltegiaKomprobatu(9999));
        
        // PK17: ID negatiboa
        assertEquals("", b.biltegiaKomprobatu(-5));
        
        // PK16: ID 0
        assertEquals("", b.biltegiaKomprobatu(0));
    }
}
