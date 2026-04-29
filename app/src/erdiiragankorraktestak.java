import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ErdiIragankorTest {

    @Test
    void testErdiIragankorraOndo_PK6_PK9() {
        // PK6: Erdi-iragankorra (hoztea: true, hezetasuna: 60)
        erdi_iragankorrak er1 = new erdi_iragankorrak("ER00010", "Patatak", "Udapa", true, 60);
        assertEquals(true, er1.hoztu);
        assertEquals(60, er1.hezetasunmax);
        // assertTrue(er1.produktuaSortu(er1)); // DB konektatuta badago

        // PK9: Erdi-iragankorra (hoztea: true, hezetasuna: 50)
        erdi_iragankorrak er2 = new erdi_iragankorrak("ER00011", "Mahatsak", "Fruta SA", true, 50);
        assertEquals(true, er2.hoztu);
        assertEquals(50, er2.hezetasunmax);
    }
}