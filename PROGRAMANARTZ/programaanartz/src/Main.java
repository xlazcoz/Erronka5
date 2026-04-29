import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Errorea: Konektorea falta da!");
        }
        
        Main m = new Main(); 
        m.logina();
    }

    public void logina() {
        System.out.println("Sartu erabiltzailea: ");
        String erabiltzailea = sc.nextLine();
        System.out.println("Sartu pasahitza: ");
        String pasahitza = sc.nextLine();
        
        System.out.println("Sartu biltegiaren IDa: ");
        int idBiltegia = Integer.parseInt(sc.nextLine()); 

        String sql = "{call erabiltzaile_logina(?, ?, ?)}";

        try (Connection conn = Konexioa.konexioa();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, erabiltzailea);
            cstmt.setString(2, pasahitza);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.execute();

            String rolObtenido = cstmt.getString(3);

            if (rolObtenido != null) {
                System.out.println("Sarpena onartua. Rol-a: " + rolObtenido);
                kudeatuMenua(rolObtenido, idBiltegia);
            } else {
                System.out.println("Erabiltzaile edo pasahitza okerra.");
            }

        } catch (SQLException e) {
            System.out.println("DB Errorea: " + e.getMessage());
        }
    }

    private void kudeatuMenua(String rol, int idBiltegia) {
    int aukeraNagusia;
    
    // Klaseen instantziak metodoak deitzeko
    Irteerak irteeraManager = new Irteerak(0, "", LocalDate.now(), "");
    Biltegia biltegiManager = new Biltegia(0, idBiltegia, 0, 0, 0, LocalDate.now());

    do {
        System.out.println("\n======= MENU NAGUSIA (" + rol.toUpperCase() + ") =======");
        System.out.println("1. PRODUKTUAK");
        System.out.println("2. BILTEGIA");
        System.out.println("3. IRTEERAK");
        System.out.println("4. DONAZIOAK");
        System.out.println("0. IRTEN");
        System.out.print("Aukeratu atal bat: ");
        
        aukeraNagusia = Integer.parseInt(sc.nextLine());

        switch (aukeraNagusia) {
            case 1: // PRODUKTUAK
                menuProduktuak(rol);
                break;

            case 2: // BILTEGIA
                menuBiltegia(rol, idBiltegia, biltegiManager);
                break;

            case 3: // IRTEERAK
                menuIrteerak(rol, idBiltegia, irteeraManager);
                break;

            case 4: // DONAZIOAK
                menuDonazioak(rol);
                break;

            case 0:
                System.out.println("Saioa itzaltzen...");
                break;

            default:
                System.out.println("Aukera okerra.");
        }
    } while (aukeraNagusia != 0);
}

// --- AZPI-MENUAK ---

private void menuProduktuak(String rol) {
    System.out.println("\n--- PRODUKTUAK ---");
    System.out.println("1. Produktua Bistaratu (ID bidez)");
    System.out.println("2. Donaziorik gabeko produktuak ikusi");
    System.out.println("0. Itzuli");
    System.out.print("Aukeratu: ");
    int aukera = Integer.parseInt(sc.nextLine());

    if (aukera == 1) {
        System.out.print("IDa: ");
        int id = Integer.parseInt(sc.nextLine());
        prodBistaratu(id);
    } else if (aukera == 2) {
        donazioGabekoProd();
    }
}

private void menuBiltegia(String rol, int idBiltegia, Biltegia manager) {
    System.out.println("\n--- BILTEGIA (ID: " + idBiltegia + ") ---");
    System.out.println("1. Produktua Biltegian Sartu");
    System.out.println("2. Produktua Aldatu (Stock/Data)");
    System.out.println("3. Produktua Ezabatu Biltegitik");
    System.out.println("4. Stock Maximoa ikusi");
    System.out.println("5. Stock gabe geratu direnak");
    System.out.println("0. Itzuli");
    System.out.print("Aukeratu: ");
    int aukera = Integer.parseInt(sc.nextLine());

    switch (aukera) {
        case 1: 
            if (rol.equals("administratzailea") || rol.equals("kudeatzailea")) {
                System.out.print("Produktuaren IDa: "); int idProd = Integer.parseInt(sc.nextLine());
                System.out.print("Pasiloa: "); int pasiloa = Integer.parseInt(sc.nextLine());
                System.out.print("Kokapena: "); int kokapena = Integer.parseInt(sc.nextLine());
                System.out.print("Kantitatea: "); int kant = Integer.parseInt(sc.nextLine());
                System.out.print("Iraungitze data (YYYY-MM-DD): "); 
                LocalDate data = LocalDate.parse(sc.nextLine());
           
                Biltegia bBerria = new Biltegia(idProd, idBiltegia, pasiloa, kokapena, kant, data);
                
                if (manager.produktuaBiltegianSartu(bBerria)) {
                    System.out.println("Produktua ondo sartu da biltegian.");
                } else {
                    System.out.println("Errorea produktua sartzean.");
                }
            } else {
                System.out.println("Ez duzu baimenik produktuak sartzeko.");
            }
            break;

        case 2: 
            if (rol.equals("administratzailea")) {
                System.out.print("Aldatu nahi den produktuaren IDa: "); int idProd = Integer.parseInt(sc.nextLine());
                System.out.print("Pasilo berria: "); int pasiloa = Integer.parseInt(sc.nextLine());
                System.out.print("Kokapen berria: "); int kokapena = Integer.parseInt(sc.nextLine());
                System.out.print("Kantitate berria: "); int kant = Integer.parseInt(sc.nextLine());
                System.out.print("Iraungitze data berria (YYYY-MM-DD): "); 
                LocalDate data = LocalDate.parse(sc.nextLine());

                // Objektua sortu datu berriekin
                Biltegia bAldatua = new Biltegia(idProd, idBiltegia, pasiloa, kokapena, kant, data);
                
                // Deia egin (P larriz zure klasean daukazun bezala)
                if (manager.ProduktuaAldatu(bAldatua)) {
                    System.out.println("Produktua ondo eguneratu da.");
                } else {
                    System.out.println("Errorea eguneratzean.");
                }
            } else {
                System.out.println("Baimenik ez. Bakarrik administratzaileek aldatu ditzakete datuak.");
            }
            break;

        case 3:
            if (rol.equals("administratzailea")) {
                System.out.print("ID: "); int id = Integer.parseInt(sc.nextLine());
                if (manager.produktuaEzabatu(id, idBiltegia)) {
                    System.out.println("Produktua ezabatu da.");
                } else {
                    System.out.println("Ezin izan da ezabatu.");
                }
            }
            break;

        case 4: stockKontsultatu(idBiltegia); break;
        case 5: stockBukatu(idBiltegia); break;
        case 0: break;
    }
}

private void menuIrteerak(String rol, int idBiltegia, Irteerak manager) {
    int aukera;
    do {
        System.out.println("\n--- IRTEERAK ---");
        System.out.println("1. Irteera berria sortu");
        System.out.println("2. Irteerak bistaratu");
        System.out.println("3. Irteera aldatu");
        System.out.println("4. Irteera ezabatu");
        System.out.println("0. Itzuli");
        System.out.print("Aukeratu: ");
        aukera = Integer.parseInt(sc.nextLine());

        switch (aukera) {
            case 1: 
                System.out.print("Helmuga (izena): ");
                String helmugaIzena = sc.nextLine();
                System.out.print("Produktua (ID): ");
                int prodId = Integer.parseInt(sc.nextLine());
                // Gaurko data automatikoki hartzen dugu
                LocalDate dataGaur = LocalDate.now();

                Irteerak irteeraBerria = new Irteerak(helmugaIzena, dataGaur, prodId);
                if (manager.irteeraSortu(irteeraBerria)) {
                    System.out.println("Irteera ondo sortu da.");
                } else {
                    System.out.println("Errorea irteera sortzean.");
                }
                break;

            case 2: 
                ArrayList<Irteerak> lista = manager.irteerakBistaratu(idBiltegia);
                if (lista != null && !lista.isEmpty()) {
                    System.out.println("\n--- IRTEEREN ZERRENDA ---");
                    System.out.printf("%-5s | %-15s | %-12s | %-15s\n", "ID", "HELMUGA", "DATA", "PRODUKTUA");
                    System.out.println("------------------------------------------------------------");
                    for (Irteerak i : lista) {
                        System.out.printf("%-5d | %-15s | %-12s | %-15s\n", 
                                          i.id_irteera, i.helmuga, i.bidalketadata, i.produktuaren_izena);
                    }
                } else {
                    System.out.println("Ez dago irteerarik erregistratuta.");
                }
                break;

            case 3: 
                if (rol.equals("administratzailea")) {
                    System.out.print("Aldatu nahi den Irteera IDa: ");
                    int idAldatu = Integer.parseInt(sc.nextLine());
                    System.out.print("Helmuga ID berria: ");
                    int helmugaId = Integer.parseInt(sc.nextLine());
                    System.out.print("Data berria (YYYY-MM-DD): ");
                    LocalDate dataBerria = LocalDate.parse(sc.nextLine());
                    System.out.print("Produktu ID berria: ");
                    int prodIdBerria = Integer.parseInt(sc.nextLine());

                    Irteerak irteeraAldatua = new Irteerak(idAldatu, helmugaId, dataBerria, prodIdBerria);
                    if (manager.irteeraAldatu(irteeraAldatua)) {
                        System.out.println("Irteera ondo aldatu da.");
                    } else {
                        System.out.println("Errorea irteera aldatzean.");
                    }
                } else {
                    System.out.println("Baimenik ez. Bakarrik administratzaileek alda ditzakete irteerak.");
                }
                break;

            case 4: // IRTEERA EZABATU
                if (rol.equals("administratzailea")) {
                    System.out.print("Ezabatu nahi den Irteera IDa: ");
                    int idEzabatu = Integer.parseInt(sc.nextLine());
                    if (manager.irteeraEzabatu(idEzabatu)) {
                        System.out.println("Irteera ezabatu da.");
                    } else {
                        System.out.println("Ezin izan da irteera ezabatu.");
                    }
                } else {
                    System.out.println("Baimenik ez.");
                }
                break;

            case 0:
                System.out.println("Menu nagusira itzultzen...");
                break;

            default:
                System.out.println("Aukera okerra.");
        }
    } while (aukera != 0);
}

private void menuDonazioak(String rol) {
    Scanner sc = new Scanner(System.in);
    int aukera = -1;
    
    Donazioak kudeatzailea = new Donazioak(null, null, 0);

    do {
        System.out.println("\n--- DONAZIOAK ---");
        System.out.println("1. Donazioen historia ikusi");
        System.out.println("2. Donazio berria erregistratu");
        System.out.println("3. Ezabatu Donazioa");
        System.out.println("0. Itzuli");
        System.out.print("Aukeratu bat: ");

        try {
            aukera = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Mesedez, sartu baliozko zenbaki bat.");
            continue;
        }

        switch (aukera) {
            case 1:
                
                System.out.println("\nKargatzen...");
                
                ArrayList<Donazioak> lista = kudeatzailea.donazioaBistaratu(0); 
                
                if (lista != null && !lista.isEmpty()) {
                    System.out.println("\n--- DONAZIOEN HISTORIA ---");
                    for (Donazioak d : lista) {
                        System.out.println("ID: " + d.id_donazioa + 
                                           " | Donatzailea: " + d.donatzailea + 
                                           " | Data: " + d.donazioarendata + 
                                           " | Produktua: " + d.produktuaren_izena);
                    }
                    System.out.println("--------------------------");
                } else {
                    System.out.println("Ez dago donaziorik erregistratuta datu-basean.");
                }
                break;

            case 2:
                
                System.out.println("\n--- DONAZIO BERRIA ---");
                
                System.out.print("Sartu donatzailearen izena: ");
                String donatzailea = sc.nextLine();

                System.out.print("Sartu produktuaren ID-a: ");
                int idProduktua;
                try {
                    idProduktua = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Errorea: Produktuaren ID-a zenbaki bat izan behar da. Eragiketa kantzelatu da.");
                    break;
                }

                LocalDate gaurkoData = LocalDate.now(); 
                Donazioak donazioBerria = new Donazioak(donatzailea, gaurkoData, idProduktua);

                if (kudeatzailea.donazioaSortuIDarekin(donazioBerria)) {
                    System.out.println(" Donazioa ondo erregistratu da datu-basean!");
                } else {
                    System.out.println(" Errorea donazioa erregistratzean.");
                }
                break;

            case 3:
                
                System.out.println("\n--- EZABATU DONAZIOA ---");
                System.out.print("Sartu ezabatu nahi duzun donazioaren ID-a: ");
                int idEzabatu;
                try {
                    idEzabatu = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Errorea: ID-a zenbaki bat izan behar da. Eragiketa kantzelatu da.");
                    break;
                }

                if (kudeatzailea.donazioaEzabatu(idEzabatu)) {
                    System.out.println(" Donazioa ondo ezabatu da.");
                } else {
                    System.out.println(" Errorea ezabatzean. Ziurtatu sartutako ID-a existitzen dela.");
                }
                break;

            case 0:
                System.out.println("Aurreko menura itzultzen...");
                break;

            default:
                System.out.println("Aukera okerra. Mesedez, aukeratu 0, 1, 2 edo 3.");
                break;
        }

    } while (aukera != 0);
}

    public void prodBistaratu(int idBiltegia) {

        String sql = "{call produktuak_bistaratu(?)}";

        try (Connection conn = Konexioa.konexioa();
        CallableStatement cstmt = conn.prepareCall(sql)){

            cstmt.setInt(1, idBiltegia);

            cstmt.execute();

            try (ResultSet rs = cstmt.getResultSet()){


                if (rs != null) {
                    
                    while (rs.next()) {

                        String izena = rs.getString("izena");
                        String fabrikatzailea = rs.getString("fabrikatzailea");
                        String mota = rs.getString("mota");

                        System.out.println("Izena: "+izena+" Fabrikatzailea: "+fabrikatzailea+" Mota: "+mota);
                        
                    }
                } else{
                    System.out.println("Ez da produkturik aurkitu biltegian.");
                }

            }
                 
        }catch (SQLException e){
            System.out.println("Errorea datu-basearekin konektatzean: ");
            e.printStackTrace();
        }
    }

    public void stockKontsultatu(int idBiltegia) {
        StockMax(idBiltegia);
    }

    public void StockMax(int idBiltegia) {
        Scanner sc = new Scanner(System.in);    

        String url = "jdbc:mysql://localhost:3306/erronka5";
        String user = "root";
        String password = "";
    
        String sql = "{call stockMax(?, ?, ?)}";

      try (Connection conn = DriverManager.getConnection(url, user, password);
            CallableStatement cstmt = conn.prepareCall(sql)) {

        cstmt.setInt(1, idBiltegia);
        
        cstmt.registerOutParameter(2, Types.VARCHAR);

        cstmt.registerOutParameter(3, Types.INTEGER);

        cstmt.execute();

        String produktuIzena = cstmt.getString(2);
        int stockMaximoa = cstmt.getInt(3);

        if (produktuIzena != null) {
            System.out.println("-------------------------------------------------");
            System.out.println("Biltegi horretan stock gehien duen produktua:");
            System.out.println("Produktua: " + produktuIzena);
            System.out.println("Stocka: " + stockMaximoa);
            System.out.println("-------------------------------------------------");
        } else {
            System.out.println("Ez da produkturik aurkitu biltegi horretan (edo biltegia ez da existitzen).");
        }

    } catch (SQLException e) {
        System.out.println("Errorea datu-basearekin konektatzean:");
        e.printStackTrace();
    } 
}

    
    public void stockBukatu(int idBiltegia){

        Scanner sc = new Scanner(System.in);    

        String url = "jdbc:mysql://localhost:3306/erronka5";
        String user = "root";
        String password = "";
    
        String sql = "{call stock_gabe_geratu(?)}";

        try (Connection conn = DriverManager.getConnection(url, user, password);
        CallableStatement cstmt = conn.prepareCall(sql)){

            cstmt.setInt(1, idBiltegia);

            cstmt.execute();

            try (ResultSet rs = cstmt.getResultSet()){

                if (rs != null) {
                    
                    while (rs.next()) {

                        String izena = rs.getString("izena");
                        String fabrikatzailea = rs.getString("fabrikatzailea");
                        String mota = rs.getString("mota");

                        System.out.println("Izena: "+izena+" Fabrikatzailea: "+fabrikatzailea+" Mota: "+mota);
                        
                    }
                } else{
                    System.out.println("Ez da produkturik aurkitu biltegian.");
                }

            }
                 
        }catch (SQLException e){
            System.out.println("Errorea datu-basearekin konektatzean: ");
            e.printStackTrace();
        }
        
    }

     public void donazioGabekoProd(){

        Scanner sc = new Scanner(System.in);    

        String url = "jdbc:mysql://localhost:3306/erronka5";
        String user = "root";
        String password = "";
    
        String sql = "{call donazio_gabeko_prod()}";

        try (Connection conn = DriverManager.getConnection(url, user, password);
        CallableStatement cstmt = conn.prepareCall(sql)){


            cstmt.execute();

            try (ResultSet rs = cstmt.getResultSet()){

                if (rs != null) {
                    
                    while (rs.next()) {

                        String izena = rs.getString("izena");
                        String fabrikatzailea = rs.getString("fabrikatzailea");
                        String mota = rs.getString("mota");

                        System.out.println("Izena: "+izena+" Fabrikatzailea: "+fabrikatzailea+" Mota: "+mota);
                        
                    }
                } else{
                    System.out.println("Ez da produkturik aurkitu biltegian.");
                }

            }
                 
        }catch (SQLException e){
            System.out.println("Errorea datu-basearekin konektatzean: ");
            e.printStackTrace();
        }

        
    }

    public void biltegiaAukeratu(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Aukeratu biltegi bat: ");
    }
}