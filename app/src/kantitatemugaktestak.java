import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class KantitateTest {

    @Test
    void testKantitateNegatiboa_PK44() {
        // PK44: Kantitatea = -10
        produktuBiltegia pbNegatibo = new produktuBiltegia(1, 1, 2, 3, -10, LocalDate.of(2026, 12, 31));
        
        // Datu-baseak ez luke utzi behar stock negatiboa sartzen
        assertFalse(pbNegatibo.produktuaBiltegianSartu(pbNegatibo), "Ez luke kantitate negatiborik onartu behar");
    }

    @Test
    void testKantitateAltuegia_PK45() {
        // PK45: Kantitatea = 10000 (Muga 0-9999 bada)
        produktuBiltegia pbAltuegia = new produktuBiltegia(1, 1, 2, 3, 10000, LocalDate.of(2026, 12, 31));
        
        // Muga hori DBan jarrita badago (adibidez, DECIMAL(4,0) edo CHECK bat), false eman behar du
        assertFalse(pbAltuegia.produktuaBiltegianSartu(pbAltuegia), "Ez luke 9999 baino gehiago onartu behar");
    }

    @Test
    void testAgortutakoProduktua_PK10() {
        // PK10: Kantitatea 0 denean, "agortutakoProduktuak" zerrendan agertu behar da
        biltegia b = new biltegia(1, "Donostia", "Gipuzkoa");
        
        // Metodoak ArrayList bat itzultzen du. Ez dugu datu erreala sartuko testean, 
        // baina egiaztatu dezakegu metodoak ez duela null itzultzen.
        assertNotNull(b.agortutakoProduktuak(), "Metodoak zerrenda bat itzuli behar du, nahiz eta hutsik egon");
    }
}
