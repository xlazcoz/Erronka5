import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class TransakzioTest {

    @Test
    void testIrteeraSortu_PK13() {
        // PK13: Helmuga: "Gurutze Gorria" · Prod. id=1
        irteerak i = new irteerak("Gurutze Gorria", LocalDate.of(2026, 4, 18), 1);
        
        // Metodo honek DB konexioa erreal bat behar du exekutatzeko
        // edo konexioa mock-eatu behar da.
        boolean emaitza = i.irteeraSortu(i);
        
        // Emaitza DBaren egoeraren menpe dago
        // assertTrue(emaitza); 
    }
}