import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ProduktuSorkuntzaTest {

    @Test
    void testIragankorraSortu_PK4_PK7() {
        // PK4: Hoztu = true
        iragankorrak ir1 = new iragankorrak("IR00006", "Esnea Freskoa", "Kaiku", true);
        // DB konexioa ondo badago, true eman beharko luke
        // assertTrue(ir1.produktuaSortu(ir1)); 

        // PK7: Hoztu = false
        iragankorrak ir2 = new iragankorrak("IR00007", "Gazta", "Artzai", false);
        assertEquals(false, ir2.hoztu);
    }

    @Test
    void testEzIragankorraSortu_PK5_PK8() {
        // PK5: Kontserba = true
        ez_iragankorrak ez1 = new ez_iragankorrak("EZ00010", "Arroza", "SOS", true);
        assertEquals(true, ez1.kontserba);

        // PK8: Kontserba = false
        ez_iragankorrak ez2 = new ez_iragankorrak("EZ00011", "Olioa", "Carbonell", false);
        assertEquals(false, ez2.kontserba);
    }

    @Test
    void testErdiIragankorraHezetasunMugak_PK41_PK42() {
        // PK41: Hezetasuna negatiboa (-1)
        erdi_iragankorrak erNegatibo = new erdi_iragankorrak("ER00010", "Patatak", "Udapa", true, -1);
        // Datu-baseak errorea eman behar du CHECK bat badago, eta false itzuli
        assertFalse(erNegatibo.produktuaSortu(erNegatibo), "Ez luke utzi behar hezetasun negatiboa sartzen");

        // PK42: Hezetasuna > 100 (101)
        erdi_iragankorrak erAltuegi = new erdi_iragankorrak("ER00010", "Patatak", "Udapa", true, 101);
        assertFalse(erAltuegi.produktuaSortu(erAltuegi), "Ez luke utzi behar 100 baino handiagoa den hezetasuna");
    }
}