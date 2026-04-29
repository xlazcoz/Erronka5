
import java.time.LocalDate;

class elikagaiak {
    public int elikagaiak_id;
    public String erreferentzia;
    public String izena;
    public String fabrikatzailea;

    public elikagaiak(int elikagaiak_id, String erreferentzia, String izena, String fabrikatzailea) {
        this.elikagaiak_id = elikagaiak_id;
        this.erreferentzia = erreferentzia;
        this.izena = izena;
        this.fabrikatzailea = fabrikatzailea;

    }
}

class Iragankorra extends elikagaiak {
    private boolean hoztuBeharra;

    public Iragankorra(int elikagaiak_id, String erreferentzia, String izena, String fabrikatzailea,
            boolean hoztuBeharra) {
        super(elikagaiak_id, erreferentzia, izena, fabrikatzailea);
        this.hoztuBeharra = hoztuBeharra;
    }
}

class erdiIragankorra extends elikagaiak {
    private boolean hoztuBeharra;
    private int hezetasun_maximoa;
    private LocalDate iraungintze_data;

    public erdiIragankorra (int elikagaiak_id, String erreferentzia, String izena, String fabrikatzailea, boolean hoztuBeharra, int hezetasun_maximoa, LocalDate iraungintze_data){
        super(elikagaiak_id,erreferentzia,izena,fabrikatzailea);

    } 
    }
