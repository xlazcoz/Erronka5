import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BalioztatzeTest {

    @Test
    void testIzenaEtaEnpresaHutsik_PK32_PK34() {
        // PK32: Izena hutsik
        iragankorrak pIzenaHutsik = new iragankorrak("IR00009", "", "Kaiku", true);
        assertFalse(pIzenaHutsik.produktuaSortu(pIzenaHutsik), "Ez luke utzi behar izenik gabe");

        // PK34: Enpresa hutsik
        iragankorrak pEnpresaHutsik = new iragankorrak("IR00009", "Esnea", "", true);
        assertFalse(pEnpresaHutsik.produktuaSortu(pEnpresaHutsik), "Ez luke utzi behar enpresarik gabe");
    }

    @Test
    void testTestuLuzeegiak_PK33_PK35() {
        String luzeegia = "HauHogeitaBostKaraktereBainoLuzeagoaDa"; // > 25 karaktere
        
        // PK33: Izena luzeegia
        iragankorrak pIzenaLuze = new iragankorrak("IR00009", luzeegia, "Kaiku", true);
        assertFalse(pIzenaLuze.produktuaSortu(pIzenaLuze)); // Datu-baseak SQLException botako du (Data too long)

        // PK35: Enpresa luzeegia
        iragankorrak pEnpresaLuze = new iragankorrak("IR00009", "Esnea", luzeegia, true);
        assertFalse(pEnpresaLuze.produktuaSortu(pEnpresaLuze));
    }

    @Test
    void testHelmugaEdoDonatzaileHutsik_PK47_PK51() {
        // PK47: Donatzailea hutsik
        donazioak dHutsik = new donazioak("", java.time.LocalDate.now(), 1);
        assertFalse(dHutsik.donazioaSortuIDarekin(dHutsik));

        // PK51: Helmuga hutsik
        irteerak iHutsik = new irteerak("", java.time.LocalDate.now(), 1);
        assertFalse(iHutsik.irteeraSortu(iHutsik));
    }
}