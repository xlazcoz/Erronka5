import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BikoiztuakEtaLuzerakTest {

    @Test
    void testErreferentziaBikoiztua_PK31() {
        // PK31: "IR00001" dagoeneko existitzen bada, bigarren aldiz sortzean errore bat eman beharko luke
        iragankorrak ir1 = new iragankorrak("IR00001", "Esnea", "Kaiku", true);
        iragankorrak ir2 = new iragankorrak("IR00001", "Jogurta", "Danone", true);
        
        // Lehenengoa agian ondo sortuko da (edo existitzen da dagoeneko)
        ir1.produktuaSortu(ir1); 
        
        // Bigarrenak false eman behar du UNIQUE muga bat egon beharko litzatekeelako DBan
        assertFalse(ir2.produktuaSortu(ir2), "Ezin dira bi produktu sortu erreferentzia berarekin");
    }

    @Test
    void testDonatzaileEtaHelmugaLuzeegiak_PK48_PK52() {
        String luzeegia = "a".repeat(51); // 50 karaktere baino gehiago

        // PK48: Donatzaile izena > 50 karaktere
        donazioak dLuzeegia = new donazioak(luzeegia, java.time.LocalDate.now(), 1);
        assertFalse(dLuzeegia.donazioaSortuIDarekin(dLuzeegia), "Ez luke donatzailerik onartu behar 50 karaktere baino gehiagorekin");

        // PK52: Helmuga > 50 karaktere
        irteerak iLuzeegia = new irteerak(luzeegia, java.time.LocalDate.now(), 1);
        assertFalse(iLuzeegia.irteeraSortu(iLuzeegia), "Ez luke helmugarik onartu behar 50 karaktere baino gehiagorekin");
    }
}