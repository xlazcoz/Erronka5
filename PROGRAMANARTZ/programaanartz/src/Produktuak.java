import java.time.LocalDate;

public class Produktuak {

    int id;
    String erreferentzia;
    String izena;
    String fabrikatzaileenpresa;

    public Produktuak(int id, String erreferentzia, String izena, String fabrikatzaileenpresa){

        this.id = id;
        this.erreferentzia = erreferentzia;
        this.izena = izena;
        this.fabrikatzaileenpresa = fabrikatzaileenpresa;

    }

}

class Iragankorra extends Produktuak{

    LocalDate iraungitzedata;
    boolean hoztu;

    public Iragankorra(int id, String erreferentzia, String izena, String fabrikatzaileenpresa, LocalDate iraungitzedata, boolean hoztu){

        super(id, erreferentzia, izena, fabrikatzaileenpresa);

        this.iraungitzedata = iraungitzedata;
        this.hoztu = hoztu;

    }

}

class EzIragankorra extends Produktuak{

    boolean kontserba;

    public EzIragankorra(int id, String erreferentzia, String izena, String fabrikatzaileenpresa, boolean kontserba){

        super(id, erreferentzia, izena, fabrikatzaileenpresa);

        this.kontserba = kontserba;

    }

}

class ErdiIragankorra extends Iragankorra{

    int hezetasuna;

    public ErdiIragankorra(int id, String erreferentzia, String izena, String fabrikatzaileenpresa, LocalDate iraungitzedata, boolean hoztu, int hezetasuna){

        super(id, erreferentzia, izena, fabrikatzaileenpresa, iraungitzedata, hoztu);

        this.hezetasuna = hezetasuna;

    }

}