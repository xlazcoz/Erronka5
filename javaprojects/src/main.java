import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    private static Connection konexioa;
    private static Scanner sc = new Scanner(System.in);
    private static int biltegiAukera; 

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargado con éxito");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: ¡No se encontró el JAR del conector en el proyecto!");
        }
        main m = new main();
        biltegiAukera = m.BiltegiaAukeratu();
        m.logina();

    }

    public void logina() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Kaixo, sartu erabiltzailea mesedez:");
        String erabiltzailea = sc.nextLine();
        System.out.println("Kaixo, sartu pasahitza mesedez:");
        String pasahitza = sc.nextLine();

        // DATU BASEARI DEIA
        String url = "jdbc:mysql://localhost:3306/erronka5?autoReconnect=true";
        String user = "root";
        String password = "";

        String sql = "{call erabiltzaile_logina(?, ?, ?)}";

        try {
            konexioa = DriverManager.getConnection(url, user, password);
            try (CallableStatement cstmt = konexioa.prepareCall(sql)) {
                cstmt.setString(1, erabiltzailea);
                cstmt.setString(2, pasahitza);

                cstmt.registerOutParameter(3, Types.VARCHAR);

                cstmt.execute();

                String rolObtenido = cstmt.getString(3);

                if (rolObtenido != null) {
                    System.out.println("Acceso exitoso. Rol: " + rolObtenido);
                    if (rolObtenido.equals("admin")) {
                        erakutsiMenua();
                    } else if (rolObtenido.equals("Produktu kudeatzailea")) {
                        erakutsiMenuaProduktuKudeatzailea();
                    } else {
                    }
                } else {
                    System.out.println("Credenciales incorrectas.");
                    try {
                        konexioa.close();
                    } catch (SQLException ignored) {
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (konexioa != null)
                    konexioa.close();
            } catch (SQLException ignored) {
            }
        }
        sc.close();
    }

    private static void erakutsiMenua() {
        int aukera = 0;
        do {
            System.out.println(
                    "Menua: \n 1 Gehitu Produktua \n 2 Produktuak aldatu \n 3 Sarrerak eta irteerak \n 4 Produktuak bistaratu \n 5 Stock kontsultak \n 0 Irten");
            System.out.print("Aukera: ");
            aukera = sc.nextInt();

            switch (aukera) {
                case 1:
                    gehituProduktua(konexioa);
                    break;
                case 2:
                    zerrendatuProduktuak(konexioa);
                    break;
                case 3:
                    SarreraIrteeraMenua(konexioa, biltegiAukera);
                    break;
                case 4:
                    ArrayList<elikagaiak> elikagaiak = ProduktuakBistaratu(konexioa);
                    for (elikagaiak elikagaiak2 : elikagaiak) {
                System.out.println("ID: " + elikagaiak2.elikagaiak_id + " | Izena: " + elikagaiak2.izena + " | Fabrikatzailea: "
                 + elikagaiak2.fabrikatzailea
                 + " | Mota: " + elikagaiak2.getClass().getSimpleName());
                    }
                    break;
                case 5:
                    StockKontsultak(konexioa, biltegiAukera);
                    break;
                case 0:
                    System.out.println("Programa amaitzen... Agur!");
                    break;
                default:
                    System.out.println("Aukera ez da baliozkoa.");
            }

        } while (aukera != 0);

    }

    private static void erakutsiMenuaProduktuKudeatzailea() {
        int aukera = 0;
        do {
            System.out.println(
                    "Menua: \n 1 Gehitu Produktua \n 2 Produktuak aldatu \n 3 Produktuak bistaratu \n 0 Irten");
            System.out.print("Aukera: ");
            aukera = sc.nextInt();

            switch (aukera) {
                case 1:
                    gehituProduktua(konexioa);
                    break;
                case 2:
                    zerrendatuProduktuak(konexioa);

                    break;
                case 3:
                    ProduktuakBistaratu(konexioa);
                    break;

                case 0:
                    System.out.println("Programa amaitzen. Agur!");
                    break;
                default:
                    System.out.println("Aukera ez da baliozkoa.");
            }

        } while (aukera != 0);

    }

    private static void erakutsiMenuaIrteeraSarreraKudeatzailea() {
        int aukera = 0;
        do {
            System.out.println("Menua: \n 1 Sarrerak eta irteerak \n 2 Stock kontsultak \n 0 Irten");
            System.out.print("Aukera: ");
            aukera = sc.nextInt();

            switch (aukera) {
                case 1:
                    SarreraIrteeraMenua(konexioa, biltegiAukera);
                    break;
                case 2:
                    StockKontsultak(konexioa, biltegiAukera);
                    break;

                case 0:
                    System.out.println("Programa amaitzen... Agur!");
                    break;
                default:
                    System.out.println("Aukera ez da baliozkoa.");
            }

        } while (aukera != 0);

    }
   public static void StockKontsultak(Connection konexioa, int biltegiAukera) {
        int aukera = 0;
        do {
            System.out.println("\n--- STOCK KONTSULTAK ---");
            System.out.println("1 - Stock gehien duen produktua (Stock Maximoa)");
            System.out.println("2 - Agortutako produktuak (Stock = 0)");
            System.out.println("0 - Irten menura");
            System.out.print("Aukera: ");
            aukera = sc.nextInt();

            switch (aukera) {
                case 1:
                    StockMaximoa(biltegiAukera);
                    break;
                case 2:
                    AgortutakoProduktuak(biltegiAukera);
                    break;
                case 3:
                    break;
                case 0:
                    System.out.println("Aurreko menura itzultzen");
                    break;
                default:
                    System.out.println("Aukera ez da baliozkoa.");
            }

        } while (aukera != 0);
    }
    public static void gehituProduktua(Connection konexioa) {
    Scanner scanner = new Scanner(System.in);

    try {
        System.out.print("Sartu produktuaren biltegi kodea: ");
        int biltegiKodea = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Sartu produktuaren pasiloa: ");
        int pasiloa = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Sartu produktuaren kokapena: ");
        String kokapena = scanner.nextLine(); 

        System.out.print("Zein Mota? (IR, ER, EZ): ");
        String mota = scanner.nextLine().toUpperCase();

        String izena = null, fabrikatzailea = null, iraungitzea = null;
        boolean hoztea = false, kontserbaDen = false;
        int hezetasuna = 0;

        System.out.print("Sartu produktuaren izena: ");
        izena = scanner.nextLine();
        System.out.print("Sartu produktuaren fabrikatzailea: ");
        fabrikatzailea = scanner.nextLine();

        if (mota.contains("IR")) {
            System.out.print("Sartu iraungitze-data (dd/mm/aaaa): ");
            iraungitzea = scanner.nextLine();
            System.out.print("Produktua hoztu behar da? (true/false): ");
            hoztea = scanner.nextBoolean();
            scanner.nextLine(); 
        } else if (mota.contains("ER")) {
            System.out.print("Sartu iraungitzea (dd/mm/aaaa): ");
            iraungitzea = scanner.nextLine();
            System.out.print("Produktua hoztu behar da? (true/false): ");
            hoztea = scanner.nextBoolean();
            System.out.print("Sartu hezetasuna: ");
            hezetasuna = scanner.nextInt();
            scanner.nextLine();
        } else if (mota.contains("EZ")) {
            System.out.print("Sartu kontserban dago (true/false): ");
            kontserbaDen = scanner.nextBoolean();
            scanner.nextLine();
        }

        String sql = "{call ProduktuaGehitu(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cstmt = konexioa.prepareCall(sql);

        cstmt.setString(1, izena);
        cstmt.setString(2, fabrikatzailea);
        cstmt.setString(3, mota);
        cstmt.setInt(4, biltegiKodea);
        cstmt.setInt(5, pasiloa);
        cstmt.setString(6, kokapena);
        cstmt.setString(7, iraungitzea);
        cstmt.setBoolean(8, hoztea);
        cstmt.setInt(9, hezetasuna);
        cstmt.setBoolean(10, kontserbaDen);

        cstmt.executeUpdate();
        System.out.println("¡Produktua ondo gehitu da!");
        cstmt.close();

    } catch (SQLException e) {
        System.out.println("Errorea datu-basean: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Datu okerrak: " + e.getMessage());
    }
}
    public static void zerrendatuProduktuak(Connection konexioa) {
        try {
            String sql = "{call ProduktuakBistaratu()}";

            CallableStatement cstmt = konexioa.prepareCall(sql);

            ResultSet rs = cstmt.executeQuery();

            System.out.println("PRODUKTU ZERRENDA ");

            while (rs.next()) {
                int id = rs.getInt("id_produktua");
                String izena = rs.getString("izena");
                String fabrikatzailea = rs.getString("fabrikatzailea");
                String mota = rs.getString("mota");

                System.out.println("ID: " + id + " | Izena: " + izena + " | Fabrikatzailea: " + fabrikatzailea
                        + " | Mota: " + mota);
            }

            rs.close();
            cstmt.close();
            aldatuProduktua(konexioa, sc);

        } catch (SQLException e) {
            System.out.println("Errorea zerrendatzerakoan: " + e.getMessage());
        }
    }

    public static ArrayList<elikagaiak> ProduktuakBistaratu(Connection konexioa) {
        try {
            ArrayList<elikagaiak> elikagaiak = new ArrayList<>();
            elikagaiak eli;
            String sql = "{call ProduktuakBistaratu()}";

            CallableStatement cstmt = konexioa.prepareCall(sql);

            ResultSet rs = cstmt.executeQuery();

            System.out.println("PRODUKTU ZERRENDA ");

            while (rs.next()) {
                int id = rs.getInt("id_produktua");
                String izena = rs.getString("izena");
                String fabrikatzailea = rs.getString("fabrikatzailea");
                String mota = rs.getString("mota");

                eli = new elikagaiak(id, mota, izena, fabrikatzailea);
                elikagaiak.add(eli);
               
            }

            rs.close();
            cstmt.close();

            return elikagaiak;
        } catch (SQLException e) {
            System.out.println("Errorea zerrendatzerakoan: " + e.getMessage());
            return null;
        }
    }

    public static void aldatuProduktua(Connection konexioa, Scanner sc) {
        try {

            System.out.print("Sartu aldatu nahi duzun produktuaren ID-a: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Sartu izen berria: ");
            String izena = sc.nextLine();

            System.out.print("Sartu fabrikatzaile berria: ");
            String fabrikatzailea = sc.nextLine();

            System.out.print("Sartu mota berria(IR, ER, EZ + 5 zifrako zenbakia) : ");
            String mota = sc.nextLine();

            String sql = "{call ProduktuaAldatu(?, ?, ?, ?)}";
            CallableStatement cstmt = konexioa.prepareCall(sql);

            cstmt.setInt(1, id);
            cstmt.setString(2, izena);
            cstmt.setString(3, fabrikatzailea);
            cstmt.setString(4, mota);

            int aldatutakoErrenkadak = cstmt.executeUpdate();

            if (aldatutakoErrenkadak > 0) {
                System.out.println("Produktua ondo eguneratu da!");
            } else {
                System.out.println("Ez da aurkitu ID hori duen produkturik.");
            }

            cstmt.close();

        } catch (SQLException e) {
            System.out.println("Errorea datu-basean produktua aldatzerakoan: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Datu okerrak sartu dituzu. ID-a zenbaki bat izan behar da.");
            sc.nextLine();
        }
    }

    public int BiltegiaAukeratu() {
        System.out.println("Aukeratu biltegaren id-a (zenbaki oso bat):");
        Scanner scanner = new Scanner(System.in);
        int biltegiAukera = scanner.nextInt();
        scanner.nextLine();
        return biltegiAukera;
    }
public static void AgortutakoProduktuak(int biltegiAukera) {
        String sql = "{call AgortutakoakBistaratu(?)}"; 

        try (CallableStatement cstmt = konexioa.prepareCall(sql)) {
            
            cstmt.setInt(1, biltegiAukera);
            ResultSet rs = cstmt.executeQuery();

            System.out.println("\n--- AGORTUTAKO PRODUKTUAK (BILTEGIA: " + biltegiAukera + ") ---");
            boolean aurkitua = false;
            
            while (rs.next()) {
                aurkitua = true;
                int id = rs.getInt("id_produktua");
                String izena = rs.getString("izena");
                
                System.out.println("ID: " + id + " | Produktua: " + izena + " | Egoera: AGORTUTA");
            }

            if (!aurkitua) {
                System.out.println("Zorionak! Ez dago agortutako produkturik biltegi honetan.");
            }
            
            rs.close();

        } catch (SQLException e) {
            System.out.println("Errorea AgortutakoProduktuak egitean: " + e.getMessage());
        }
    }
    public static void StockMaximoa(int biltegiAukera) {
        String sql = "{call StockMax(?, ?)}";

        try (CallableStatement cstmt = konexioa.prepareCall(sql)) {

            cstmt.setInt(1, biltegiAukera);

            cstmt.registerOutParameter(2, Types.VARCHAR);

            cstmt.execute();

            String produktuIzena = cstmt.getString(2);

            System.out.println("\n BILTEGIKO STOCK MAXIMOA ");
            if (produktuIzena != null) {
                System.out.println("Produktua: " + produktuIzena);
            } else {
                System.out.println("Ez da produkturik aurkitu biltegi honetan.");
            }

        } catch (SQLException e) {
            System.out.println("Errorea StockMaximoa egitean: ");
        }
    }
public static void SarreraIrteeraMenua(Connection konexioa, int biltegiAukera) {
        int aukera = 0;
        do {
            System.out.println("\n--- SARRERAK ETA IRTEERAK ---");
            System.out.println("1 - Donazio berria erregistratu (Sarrera)");
            System.out.println("2 - Bidalketa erregistratu (Irteera)");
            System.out.println("0 - Irten menura");
            System.out.print("Aukera: ");
            
            try {
                aukera = sc.nextInt();
                sc.nextLine(); 
                
                switch (aukera) {
                    case 1:
                        donazioaEgin(konexioa, biltegiAukera);
                        break;
                    case 2:
                        irteeraEgin(konexioa, biltegiAukera);
                        break;
                    case 0:
                        System.out.println("Aurreko menura itzultzen...");
                        break;
                    default:
                        System.out.println("Aukera ez da baliozkoa.");
                }
            } catch (Exception e) {
                System.out.println("ERROREA: Formatu okerra sartu duzu. Zenbaki bat izan behar da.");
                sc.nextLine();
                aukera = -1;
            }

        } while (aukera != 0);
    }

    public static void donazioaEgin(Connection konexioa, int biltegiAukera) {
        try {
            System.out.print("Sartu produktuaren ID-a: ");
            int idProd = sc.nextInt();
            
            System.out.print("Sartu kantitatea: ");
            int kantitatea = sc.nextInt();
            sc.nextLine(); 

            System.out.print("Sartu donatzailearen izena: ");
            String donatzailea = sc.nextLine();

            System.out.print("Sartu donazioaren data (UUUU-HH-EE formatuan, adibidez 2023-10-25): ");
            String data = sc.nextLine();

      
            String sql = "{call DonazioaGehitu(?, ?, ?, ?, ?)}";
            CallableStatement cstmt = konexioa.prepareCall(sql);
            cstmt.setInt(1, idProd);
            cstmt.setInt(2, biltegiAukera);
            cstmt.setInt(3, kantitatea);
            cstmt.setString(4, donatzailea);
            cstmt.setString(5, data);

            cstmt.executeUpdate();
            System.out.println("ZORIONAK! Donazioa ondo erregistratu da eta stocka eguneratu da.");
            cstmt.close();

        } catch (SQLException e) {
            System.out.println("Errorea datu-basean donazioa egitean: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROREA: Datuen formatua ez da zuzena. Kantitateek eta IDek zenbakiak izan behar dute.");
            sc.nextLine(); 
        }
    }

    public static void irteeraEgin(Connection konexioa, int biltegiAukera) {
        try {
            System.out.print("Sartu bidaliko den produktuaren ID-a: ");
            int idProd = sc.nextInt();
            
            System.out.print("Sartu kantitatea: ");
            int kantitatea = sc.nextInt();
            sc.nextLine(); 

            System.out.print("Sartu helmuga (nora bidaliko da?): ");
            String helmuga = sc.nextLine();

            System.out.print("Sartu bidalketaren data (UUUU-HH-EE formatuan): ");
            String data = sc.nextLine();

            String sql = "{call IrteeraGehitu(?, ?, ?, ?, ?)}";
            CallableStatement cstmt = konexioa.prepareCall(sql);
            cstmt.setInt(1, idProd);
            cstmt.setInt(2, biltegiAukera);
            cstmt.setInt(3, kantitatea);
            cstmt.setString(4, helmuga);
            cstmt.setString(5, data);

            cstmt.executeUpdate();
            System.out.println("BIDALKETA BURUTUA! Irteera erregistratu da eta stocka kendu da.");
            cstmt.close();

        } catch (SQLException e) {
            System.out.println("Errorea datu-basean irteera egitean: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROREA: Datuen formatua ez da zuzena. Kantitateek eta IDek zenbakiak izan behar dute.");
            sc.nextLine(); 
        }
    }
}
