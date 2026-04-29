import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ProduktuValidazioTest {

    @Test
    void testErreferentziaFormatua_PK28_PK30() {
        produktuak p = new produktuak(null, null, null);
        
        // Balio onak
        assertEquals(1, p.erreferentziaKomprobatu("IR12345"), "Iragankorra izan behar da");
        assertEquals(2, p.erreferentziaKomprobatu("EZ99999"), "Ez-iragankorra izan behar da");
        assertEquals(3, p.erreferentziaKomprobatu("ER00001"), "Erdi-iragankorra izan behar da");
        
        // PK28: Formatua okerra (digitu gutxiago)
        assertEquals(0, p.erreferentziaKomprobatu("IR123"), "Okerra eman behar du");
        
        // PK30: Letrak digituak ordez
        assertEquals(0, p.erreferentziaKomprobatu("ER0000A"), "Okerra eman behar du");
    }
}