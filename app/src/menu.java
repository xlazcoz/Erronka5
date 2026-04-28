import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class menu {

    public static void menuProductos() {
        Scanner sc = new Scanner(System.in);
        int menua;
        do {
            System.out.println("Menua: \r\n" +
                    "1. Produktua gehitu  \r\n" +
                    "2. Produktua aldatu \r\n" +
                    "3. Produktua ezabatu\r\n" +
                    "4. Produktuak bistaratu\r\n" +
                    "0. bueltatu ");
            menua = sc.nextInt();
            sc.nextLine();

            switch (menua) {
                case 1:
                    System.out.println("sartu produktuaren izena");
                    String izena = sc.nextLine();
                    System.out.println("sartu produktuakren empresa");
                    String empresa = sc.nextLine();
                    System.out.println("sartu produktuaren erreferentzia");
                    String ref = sc.nextLine();
                    produktuak p = new produktuak(izena, empresa, ref);
                    int reslt = p.erreferentziaKomprobatu(ref);
                    if (reslt == 1) {
                        System.out.println("sartu produktua hoztea bearrezkoa den ala ez (true/false)");
                        boolean hoztea = sc.nextBoolean();
                        sc.nextLine();
                        iragankorrak ir = new iragankorrak(ref, izena, empresa, hoztea);
                        boolean sortu = ir.produktuaSortu(ir);
                        if (sortu) {
                            System.out.println("produktua ondo sortu da");
                        } else {
                            System.out.println("produktua ez da sortu, saiatu berriro");
                        }
                    } else if (reslt == 2) {
                        System.out.println("sartu produktua kontserba den ala ez (true/false)");
                        boolean kontserba = sc.nextBoolean();
                        sc.nextLine();
                        ez_iragankorrak ez = new ez_iragankorrak(ref, izena, empresa, kontserba);
                        boolean sortu = ez.produktuaSortu(ez);
                        if (sortu) {
                            System.out.println("produktua ondo sortu da");
                        } else {
                            System.out.println("produktua ez da sortu, saiatu berriro");
                        }
                    } else if (reslt == 3) {
                        System.out.println("sartu produktua hoztea bearrezkoa den ala ez (true/false)");
                        boolean hoztea = sc.nextBoolean();
                        sc.nextLine();
                        System.out.println("sartu produktuaren hezetazu maximoa");
                        int hezetasunmax = sc.nextInt();
                        sc.nextLine();
                        erdi_iragankorrak er = new erdi_iragankorrak(ref, izena, empresa, hoztea, hezetasunmax);
                        boolean sortu = er.produktuaSortu(er);
                        if (sortu) {
                            System.out.println("produktua ondo sortu da");
                        } else {
                            System.out.println("produktua ez da sortu, saiatu berriro");
                        }

                    }

                    break;
                case 2:
                    System.out.println("sartu id-a");
                    int id_producto = sc.nextInt();
                    sc.nextLine();
                    produktuak p2 = new produktuak(null, null, null);
                    String refe = p2.erreferentziabueltatu(id_producto);
                    if (refe == null || refe.isEmpty()) {
                        System.out.println("Errorea: Ez da aurkitu produktu hori datu-basean.");
                    } else {
                        int tipo = p2.erreferentziaKomprobatu(refe);

                        System.out.println("Sartu produktuaren izen berria:");
                        String izenBerria = sc.nextLine();

                        System.out.println("Sartu enpresa berria:");
                        String enpresaBerria = sc.nextLine();

                        switch (tipo) {
                            case 1:
                                System.out.println("Hoztea beharrezkoa da? (true/false):");
                                boolean hoztu = sc.nextBoolean();
                                sc.nextLine();

                                iragankorrak ir = new iragankorrak(id_producto, refe, izenBerria, enpresaBerria, hoztu);
                                if (ir.produktuaAldatu(ir)) {
                                    System.out.println("Produktua ondo aldatu da.");
                                } else {
                                    System.out.println("Errorea produktua aldatzean.");
                                }
                                break;

                            case 2:
                                System.out.println("Kontserba da? (true/false):");
                                boolean kontserba = sc.nextBoolean();
                                sc.nextLine();

                                ez_iragankorrak ez = new ez_iragankorrak(id_producto, refe, izenBerria, enpresaBerria,
                                        kontserba);
                                if (ez.produktuaAldatu(ez)) {
                                    System.out.println("Produktua ondo aldatu da.");
                                } else {
                                    System.out.println("Errorea produktua aldatzean.");
                                }
                                break;

                            case 3:
                                System.out.println("Hoztea beharrezkoa da? (true/false):");
                                boolean hoztuErdi = sc.nextBoolean();
                                sc.nextLine();
                                System.out.println("Zein da hezetasun maximoa?:");
                                int hezetasuna = sc.nextInt();
                                sc.nextLine();

                                erdi_iragankorrak er = new erdi_iragankorrak(id_producto, refe, izenBerria,
                                        enpresaBerria, hoztuErdi, hezetasuna);
                                if (er.produktuaAldatu(er)) {
                                    System.out.println("Produktua ondo aldatu da.");
                                } else {
                                    System.out.println("Errorea produktua aldatzean.");
                                }
                                break;
                        }
                        break;
                    }
                case 3:
                    System.out.println("Sartu ezabatu nahi duzun produktuaren ID-a:");
                    int idEzabatu = sc.nextInt();
                    sc.nextLine();

                    produktuak pEzabatu = new produktuak(null, null, null);

                    boolean ezabatuDa = pEzabatu.produktuaEzabatu(idEzabatu);

                    if (ezabatuDa) {
                        System.out.println("Produktua (eta bere dependentzia guztiak) ondo ezabatu dira.");
                    } else {
                        System.out.println(
                                "Errorea: Ezin izan da produktua ezabatu (agian ez da existitzen edo errorea egon da datu-basean).");
                    }
                    break;
                case 4:
                    produktuak p1 = new produktuak(null, null, null);
                    ArrayList<produktuak> plist = p1.ProduktuakBistaratu();

                    if (plist == null) {
                        System.out.println("Errorea datu-basearekin konektatzean.");
                    } else {
                        System.out.println("\n--- PRODUKTUEN LISTA ---");
                        for (produktuak p3 : plist) {
                            System.out.println("ID: " + p3.id +
                                    " | Ref: " + p3.erreferentzia +
                                    " | Izena: " + p3.izena +
                                    " | Enpresa: " + p3.empresa);
                        }
                        System.out.println("------------------------\n");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Sartu balio balido bat");
                    break;
            }
        } while (menua != 0);
    }

    public static void menuErabiltzaileak(erabiltzailea e) {
        Scanner sc = new Scanner(System.in);
        int menua;
        do {
            System.out.println("Menua: \r\n" +
                    "1. Erabiltzailea sortu  \r\n" +
                    "2. Erabiltzailea aldatu \r\n" +
                    "3. Erabiltzailea ezabatu\r\n" +
                    "4. Erabiltzaileak bistaratu\r\n" +
                    "5. Rolan ID-ak bistaratu\r\n" +
                    "0. bueltatu ");
            menua = sc.nextInt();
            sc.nextLine();
            switch (menua) {
                case 1:

                    System.out.println("sartu erabiltzailearen izena");
                    String izena = sc.nextLine();
                    System.out.println("sartu erabiltzailearen pasahitza");
                    String pasahitza = sc.nextLine();
                    System.out.println("sartu baimenaren id-a");
                    int id = sc.nextInt();
                    sc.nextLine();
                    erabiltzailea erab = new erabiltzailea(izena, pasahitza, id);
                    boolean creado = e.erabiltzaileaSortu(erab);
                    if (creado) {
                        System.out.println("erabiltzailea ondo sortu da");
                    } else {
                        System.out.println("erabiltzailea ez da sortu, saiatu berriro");
                    }

                    break;
                case 2:
                    System.out.println("sartu aldatu nahi duzun erabiltzailearen ID-a");
                    int aldatu = sc.nextInt();
                    sc.nextLine();
                    System.out.println("sartu erabiltzailearen izen berria");
                    String izenaBerria = sc.nextLine();
                    System.out.println("sartu erabiltzailearen pasahitza berria");
                    String pasahitzaBerria = sc.nextLine();
                    System.out.println("sartu baimen berriaren id-a");
                    int idBerria = sc.nextInt();
                    sc.nextLine();
                    erabiltzailea erab2 = new erabiltzailea(aldatu, izenaBerria, pasahitzaBerria, idBerria);
                    boolean cambiado = e.erabiltzaileaAldatu(erab2);
                    if (cambiado) {
                        System.out.println("erabiltzailea ondo aldatu da");
                    } else {
                        System.out.println("erabiltzailea ez da aldatu, saiatu berriro");
                    }
                    break;
                case 3:
                    System.out.println("sartu ezabatu nahi duzun erabiltzailearen ID-a");
                    int borrar = sc.nextInt();
                    sc.nextLine();
                    boolean borrado = e.erabiltzaileaEzabatu(borrar);

                    if (borrado) {
                        System.out.println("erabiltzailea ondo ezabatu da");
                    } else {
                        System.out.println("erabiltzailea ez da ezabatu, saiatu berriro");
                    }
                    break;
                case 4:
                    ArrayList<erabiltzailea> eList = e.erabiltzaileakBistaratu();
                    System.out.println("\n--- Erabiltzaileen lista ---");
                    for (erabiltzailea e1 : eList) {
                        System.out.println(
                                "ID: " + e1.id_erabiltzailea + " | Izena: " + e1.izena + " | baimena: " + e1.baimena);
                    }

                    break;
                case 5:
                    baimena b = new baimena(0, null);
                    ArrayList<baimena> blist = b.erakutsiBaimenak();
                    System.out.println("\n--- Baimenen lista ---");
                    for (baimena b1 : blist) {
                        System.out.println("ID: " + b1.id + " | Izena: " + b1.izena);
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Sartu balio balido bat");
                    break;
            }
        } while (menua != 0);
    }

    public static void menuBiltegia(erabiltzailea e, int biltegia) {
        Scanner sc = new Scanner(System.in);
        int menua;
        do {
            System.out.println("Menua: \r\n" +
                    "1. Agortutako produktuak  \r\n" +
                    "2. Produktuak 0 donaziokin \r\n" +
                    "3. Stock maximoa duen produktua \r\n" +
                    "4. Produktua biltegitik borratu \r\n" +
                    "5. Biltegiko produktuak ikusi \r\n" +
                    "0. bueltatu ");
            menua = sc.nextInt();
            sc.nextLine();

            switch (menua) {
                case 1:
                    biltegia b1 = new biltegia(null, null, null);
                    ArrayList<produktuBiltegia> pblist = b1.agortutakoProduktuak();
                    if (pblist == null) {
                        System.out.println("Errorea: Ezin izan da datu-basearekin konektatu.");
                    } else {
                        System.out.println("\n--- PRODUKTU AGORTUEN LISTA ---");
                        for (produktuBiltegia pb : pblist) {
                            System.out.println("ID: " + pb.id_produktua +
                                    " | Izena: " + pb.izena +
                                    " | Stock: " + pb.kantitatea);
                        }
                        System.out.println("-------------------------------\n");
                    }
                    break;
                case 2:
                    biltegia b3 = new biltegia(null, null, null);
                    ArrayList<produktuak> p1 = b3.donazio0();

                    if (p1 == null) {
                        System.out.println("Errorea: Ezin izan da datu-basearekin konektatu.");
                    } else {
                        System.out.println("\n--- INOIZ DONATU EZ DIREN PRODUKTUAK ---");
                        for (produktuak p : p1) {
                            System.out.println("ID: " + p.id + " | Izena: " + p.izena);
                        }
                        System.out.println("-------------------------------\n");
                    }
                    break;
                case 3:
                    biltegia b = new biltegia(0, null, null);
                    produktuak p = b.StockMax(biltegia);
                    System.out.println(
                            "izena: " + p.izena + " | empresa: " + p.empresa + " | erreferentzia: " + p.erreferentzia);

                    break;
                case 4:
                    System.out.println("Sartu biltegitik ezabatu nahi duzun produktuaren ID-a:");
                    int idEzabatu = sc.nextInt();
                    sc.nextLine();

                    produktuBiltegia b2 = new produktuBiltegia(0, null, 0);

                    boolean ezabatuDa = b2.produktuaEzabatu(idEzabatu, biltegia);

                    if (ezabatuDa) {
                        System.out.println("Produktua biltegitik ondo ezabatu da.");
                    } else {
                        System.out.println(
                                "Errorea: Ezin izan da produktua ezabatu biltegitik (agian ez da existitzen edo errorea egon da datu-basean).");
                    }
                    break;

                case 5:
                    produktuBiltegia pbInbentarioa = new produktuBiltegia(0, null, 0);
                    ArrayList<produktuBiltegia> inbentarioa = pbInbentarioa.biltegikoProduktuakBistaratu(biltegia);

                    if (inbentarioa == null) {
                        System.out.println("Errorea: Ezin izan da datu-basearekin konektatu.");
                    } else if (inbentarioa.isEmpty()) {
                        System.out.println("Biltegi hau hutsik dago momentuan.");
                    } else {
                        System.out.println("\n--- BILTEGIKO INBENTARIOA ---");
                        for (produktuBiltegia pDatu : inbentarioa) {
                            System.out.println("ID: " + pDatu.id_produktua +
                                    " | Izena: " + pDatu.izena +
                                    " | Stock: " + pDatu.kantitatea +
                                    " | Pasiloa: " + pDatu.pasilokozen +
                                    " | Kokapena: " + pDatu.kokapenkodea +
                                    " | Iraungintze data: " + pDatu.iraungintze_data);
                        }
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Sartu balio balido bat");
                    break;
            }
        } while (menua != 0);
    }

    public static void menuIrteerak(int biltegia) {
        Scanner sc = new Scanner(System.in);
        int menua;
        do {
            System.out.println("Menua: \r\n" +
                    "1. Irteera sortu  \r\n" +
                    "2. Irteera aldatu \r\n" +
                    "3. Irteera ezabatu\r\n" +
                    "4. Irteerak bistaratu\r\n" +
                    "0. bueltatu ");
            menua = sc.nextInt();
            sc.nextLine();
            switch (menua) {
                case 1:
                    System.out.println("Sartu produktuaren izena:");
                    String izena = sc.nextLine();
                    System.out.println("Sartu produktuaren enpresa:");
                    String empresa = sc.nextLine();

                    produktuak comprobar = new produktuak(null, null, null);
                    int idIgual = comprobar.ProduktuaKomprobatu(izena, empresa);

                    if (idIgual == 0) {
                        System.out.println(
                                "Errorea: Produktu hori ez da existitzen datu-basean. Ezin da irteera bat egin.");
                        break;
                    }

                    System.out.println("Sartu atera nahi duzun kantitatea:");
                    int ateraKantitatea = sc.nextInt();
                    sc.nextLine();

                    produktuBiltegia pb = new produktuBiltegia(0, null, 0);
                    boolean stockOndo = pb.stockaKendu(idIgual, biltegia, ateraKantitatea);

                    if (!stockOndo) {
                        System.out.println(
                                "Errorea: Ez dago stock nahikorik biltegian edo produktua ez dago biltegi honetan.");
                                System.out.println(
                                "Comprobatu biltegian dauden produktuen informazioa eta saiatu berriro");
                        break;
                    }

                    System.out.println("Sartu helmugaren izena:");
                    String helmugaIzena = sc.nextLine();

                    System.out.println("Sartu bidalketaren data (formatu honetan: YYYY-MM-DD):");
                    String data = sc.nextLine();
                    LocalDate bidalketaData = LocalDate.parse(data);

                    irteerak iSortu = new irteerak(helmugaIzena, bidalketaData, idIgual);
                    boolean sortuIrteera = iSortu.irteeraSortu(iSortu);

                    if (sortuIrteera) {
                        System.out.println("Irteera ondo sortu da eta stock-a eguneratu da.");
                    } else {
                        System.out.println("Errorea irteera sortzean, saiatu berriro.");
                    }
                    break;
                case 2:
                    System.out.println("Sartu aldatu nahi duzun irteeraren ID-a:");
                    int idIrteera = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Sartu helmuga berriaren ID-a:");
                    int idHelmugaBerria = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Sartu bidalketaren data berria (YYYY-MM-DD):");
                    String dataString = sc.nextLine();
                    LocalDate dataBerria = LocalDate.parse(dataString);

                    System.out.println("Sartu produktu berriaren IDa (Edo utzi zegoena):");
                    int idProduktuBerria = sc.nextInt();
                    sc.nextLine();

                    irteerak irteeraAldatua = new irteerak(idIrteera, idHelmugaBerria, dataBerria, idProduktuBerria);

                    boolean aldatuDa = irteeraAldatua.irteeraAldatu(irteeraAldatua);
                    if (aldatuDa) {
                        System.out.println("Irteera ondo aldatu da datu-basean.");
                    } else {
                        System.out.println("Errorea irteera aldatzean.");
                    }
                    break;
                case 3:
                    System.out.println("Sartu ezabatu nahi duzun irteeraren ID-a:");
                    int borrar = sc.nextInt();
                    sc.nextLine();
                    irteerak iBorrar = new irteerak(null, null, 0);
                    boolean borrado = iBorrar.irteeraEzabatu(borrar);

                    if (borrado) {
                        System.out.println("Irteera ondo ezabatu da.");
                    } else {
                        System.out.println("Irteera ez da ezabatu, saiatu berriro.");
                    }
                    break;
                case 4:
                    irteerak iBistaratu = new irteerak(null, null, 0);
                    ArrayList<irteerak> listaIrteerak = iBistaratu.irteerakBistaratu(biltegia);

                    if (listaIrteerak == null) {
                        System.out.println("Errorea datu-basearekin konektatzean.");
                    } else {
                        System.out.println("\n--- IRTEEREN LISTA ---");
                        for (irteerak i2 : listaIrteerak) {
                            System.out.println("ID: " + i2.id_irteera +
                                    " | Data: " + i2.bidalketadata +
                                    " | Helmuga: " + i2.helmuga +
                                    " | Produktua: " + i2.produktuaren_izena);
                        }
                        System.out.println("------------------------\n");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Sartu balio balido bat");
                    break;
            }
        } while (menua != 0);
    }

    public static void menuDonazioa(int biltegia) {
        Scanner sc = new Scanner(System.in);
        int menua;
        do {
            System.out.println("Menua: \r\n" +
                    "1. Donazioa sortu/gehitu \r\n" +
                    "2. Donazioa aldatu \r\n" +
                    "3. Donazioa ezabatu\r\n" +
                    "4. Donazioak bistaratu\r\n" +
                    "5. Donatzaileak bistaratu\r\n" +
                    "0. bueltatu ");
            menua = sc.nextInt();
            sc.nextLine();

            switch (menua) {
                case 1:
                    System.out.println("Sartu produktuaren izena:");
                    String izena = sc.nextLine();
                    System.out.println("Sartu produktuaren enpresa:");
                    String empresa = sc.nextLine();

                    produktuak comprobar = new produktuak(null, null, null);
                    int idIgual = comprobar.ProduktuaKomprobatu(izena, empresa);

                    if (idIgual == 0) {
                        System.out.println("Produktua berria da. Sartu produktuaren erreferentzia:");
                        String referencia = sc.nextLine();

                        produktuak p = new produktuak(izena, empresa, referencia);
                        int reslt = p.erreferentziaKomprobatu(referencia);

                        if (reslt == 1) {
                            System.out.println("Sartu produktua hoztea beharrezkoa den ala ez (true/false):");
                            boolean hoztea = sc.nextBoolean();
                            sc.nextLine();

                            iragankorrak ir = new iragankorrak(referencia, izena, empresa, hoztea);
                            if (ir.produktuaSortu(ir))
                                System.out.println("Produktua ondo sortu da.");

                        } else if (reslt == 2) {
                            System.out.println("Sartu produktua kontserba den ala ez (true/false):");
                            boolean kontserba = sc.nextBoolean();
                            sc.nextLine();

                            ez_iragankorrak ez = new ez_iragankorrak(referencia, izena, empresa, kontserba);
                            if (ez.produktuaSortu(ez))
                                System.out.println("Produktua ondo sortu da.");

                        } else if (reslt == 3) {
                            System.out.println("Sartu produktua hoztea beharrezkoa den ala ez (true/false):");
                            boolean hoztea = sc.nextBoolean();
                            System.out.println("Sartu produktuaren hezetasun maximoa:");
                            int hezetasunmax = sc.nextInt();
                            sc.nextLine();

                            erdi_iragankorrak er = new erdi_iragankorrak(referencia, izena, empresa, hoztea,
                                    hezetasunmax);
                            if (er.produktuaSortu(er))
                                System.out.println("Produktua ondo sortu da.");
                        }

                        idIgual = comprobar.ProduktuaKomprobatu(izena, empresa);
                    }

                    System.out.println("Sartu donatzailearen izena:");
                    String donizena = sc.nextLine();

                    System.out.println("Sartu donazioaren data (formatu honetan: YYYY-MM-DD):");
                    String data = sc.nextLine();
                    LocalDate donaziodata = LocalDate.parse(data);

                    donazioak d = new donazioak(donizena, donaziodata, idIgual);
                    boolean sortuDonazioa = d.donazioaSortuIDarekin(d);

                    if (sortuDonazioa) {
                        System.out.println("Donazioa ondo sortu da datu-basean.");
                        System.out.println("sartu produktuaren kantitatea");
                        int kantidad = sc.nextInt();
                        sc.nextLine();
                        System.out.println("sartu produktuaren iraungitze data (YYYY-MM-DD)");
                        String iraungintze_data = sc.nextLine();
                        LocalDate iraungitze = LocalDate.parse(iraungintze_data);
                        System.out.println("sartu produktuaren pasiloko zenbakia");
                        int pasilokozen = sc.nextInt();
                        sc.nextLine();
                        System.out.println("sartu produktuaren kokapen kodea ");
                        int kokapenkodea = sc.nextInt();
                        sc.nextLine();
                        produktuBiltegia pb = new produktuBiltegia(idIgual, biltegia, pasilokozen, kokapenkodea,
                                kantidad, iraungitze);
                        boolean creado = pb.produktuaBiltegianSartu(pb);
                        if (creado) {
                            System.out.println("produktua ondo sartu da biltegia");
                        } else {
                            System.out.println("produktua ez da sartu biltegian, saitu berriro");
                        }
                    } else {
                        System.out.println("Errorea donazioa sortzean, saiatu berriro.");
                    }
                    break;
                case 2:
                    System.out.println("Sartu aldatu nahi duzun donazioaren ID-a:");
                    int idDonazioa = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Sartu donatzaile berriaren ida (ikusi 5. aukera ID-ak jakiteko):");
                    int idDonatzaileBerria = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Sartu donazioaren data berria (YYYY-MM-DD):");
                    String dataString = sc.nextLine();
                    LocalDate dataBerria = LocalDate.parse(dataString);

                    System.out.println("Sartu produktu berriaren IDa (Edo utzi zegoena):");
                    int idProduktuBerria = sc.nextInt();
                    sc.nextLine();

                    donazioak donazioAldatua = new donazioak(idDonazioa, idDonatzaileBerria, dataBerria,
                            idProduktuBerria);

                    boolean aldatuDa = donazioAldatua.donazioaAldatu(donazioAldatua);
                    if (aldatuDa) {
                        System.out.println("Donazioa ondo aldatu da datu-basean.");
                    } else {
                        System.out.println("Errorea donazioa aldatzean. (Agian ID hori ez da existitzen).");
                    }
                    break;
                case 3:
                    System.out.println("sartu ezabatu nahi duzun donazioaren ID-a");
                    int borrar = sc.nextInt();
                    sc.nextLine();
                    donazioak dborrar = new donazioak(null, null, 0);
                    boolean borrado = dborrar.donazioaEzabatu(borrar);

                    if (borrado) {
                        System.out.println("donazioa ondo ezabatu da");
                    } else {
                        System.out.println("donazioa ez da ezabatu, saiatu berriro");
                    }
                    break;
                case 4:
                    donazioak dBistaratu = new donazioak(null, null, 0);
                    ArrayList<donazioak> listaDonazioak = dBistaratu.donazioaBistaratu(biltegia);

                    if (listaDonazioak == null) {
                        System.out.println("Errorea datu-basearekin konektatzean.");
                    } else {
                        System.out.println("\n--- DONAZIOEN LISTA ---");
                        for (donazioak d2 : listaDonazioak) {

                            System.out.println("ID: " + d2.id_donazioa +
                                    " | Data: " + d2.donazioarendata +
                                    " | Donatzailea: " + d2.donatzailea +
                                    " | Produktua: " + d2.produktua);
                        }
                        System.out.println("------------------------\n");
                    }
                    break;
                case 5:
                    donazioak dDonatzaileak = new donazioak(null, null, 0);
                    dDonatzaileak.donatzaileakBistaratu();
                    break;
                case 0:
                    break;

                default:
                    System.out.println("Sartu balio balido bat");
                    break;
            }
        } while (menua != 0);
    }
}