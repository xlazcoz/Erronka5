import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class DatenKontrolaTest {

    @Test
    void testIraungitzeDataIraganean_PK38() {
        // PK38: 2020-01-01 (Iraganeko data)
        LocalDate iraganean = LocalDate.of(2020, 1, 1);
        produktuBiltegia pb = new produktuBiltegia(1, 1, 1, 1, 100, iraganean);
        
        // Datu-basean trigger edo check bat ez badago, honek true emango luke.
        // Baina testak false espero beharko luke ezin delako produktu iraungirik sartu.
        assertFalse(pb.produktuaBiltegianSartu(pb), "Produktu iraungia ez luke utzi behar sartzen");
    }

    @Test
    void testEtorkizunekoTransakzioak_PK50_PK54() {
        LocalDate etorkizunean = LocalDate.of(2027, 1, 1);

        // PK50: Donazioa etorkizunean
        donazioak dEtorkizuna = new donazioak("Eroski", etorkizunean, 1);
        assertFalse(dEtorkizuna.donazioaSortuIDarekin(dEtorkizuna), "Donazioaren data ezin da etorkizunean egon");

        // PK54: Irteera etorkizunean
        irteerak iEtorkizuna = new irteerak("Gurutze Gorria", etorkizunean, 1);
        assertFalse(iEtorkizuna.irteeraSortu(iEtorkizuna), "Irteeraren data ezin da etorkizunean egon");
    }
}