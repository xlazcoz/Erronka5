import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String bil = "";
        int saiakerak1 = 0;
        int biltegia = 0;
        biltegia b = new biltegia(0, null, null);
        ArrayList<biltegia> blist = new ArrayList<biltegia>();
        blist = b.biltegiakBistaratu();
        System.out.println("\n--- Biltegien lista ---");
        for (biltegia b1 : blist) {
            System.out
                    .println("ID: " + b1.kodea_biltegia + " | Nombre: " + b1.izena + " | Departamento: " + b1.kokapena);
        }
        while (saiakerak1 < 3) {
            System.out.println("ze biltegitan zaude sartu ID-a");
            biltegia = sc.nextInt();
            sc.nextLine();
            b = new biltegia(0, null, null);
            bil = b.biltegiaKomprobatu(biltegia);

            if (bil != "") {

                saiakerak1 = 3;
            } else {
                saiakerak1++;
            }
        }

        int saiakerak = 0;
        String baimena = "";

        while (saiakerak < 3) {
            System.out.println("Kaixo, sartu erabiltzailea mesedez:");
            String erabiltzailea = sc.nextLine();
            System.out.println("sartu pasahitza mesedez:");
            String pasahitza = sc.nextLine();

            erabiltzailea e = new erabiltzailea(erabiltzailea, pasahitza);
            baimena = e.erabiltzaileaIdentificatu(e);

            if (baimena != "") {
                Main m = new Main();
                m.aurrenekomenua(baimena, e, biltegia);
                saiakerak = 3;
            } else {
                saiakerak++;
            }
        }

        sc.close();
    }

    public void aurrenekomenua(String baimena, erabiltzailea e, int biltegia) {
        Scanner sc = new Scanner(System.in);

        int menua;

        do {
            if (baimena.equals("admin")) {
                System.out.println("Menua: \r\n" +
                        "1. Erabiltzaileak  \r\n" +
                        "2. Produktuak \r\n" +
                        "3. Biltegia\r\n" +
                        "4. Irteerak\r\n" +
                        "5. Donazioa\r\n" +
                        "0. Irten ");
            } else if (baimena.equals("kudeatzailea")) {
                System.out.println("Menua: \r\n" +
                        "4. Irteerak\r\n" +
                        "5. Donazioa\r\n" +
                        "0. Irten ");
            } else if (baimena.equals("info")) {
                System.out.println("Menua: \r\n" +
                        "2. Produktuak  \r\n" +
                        "3. Biltegia\r\n" +
                        "0. Irten ");
            }

            menua = sc.nextInt();

            switch (menua) {
                case 1:
                    // Solo admin
                    if (baimena.equals("admin")) {
                        menu.menuErabiltzaileak(e);
                    } else {
                        System.out.println("Ez duzu baimenik.");
                    }
                    break;

                case 2:
                    // admin e info
                    if (baimena.equals("admin") || baimena.equals("info")) {
                        menu.menuProductos();
                    } else {
                        System.out.println("Ez duzu baimenik.");
                    }
                    break;

                case 3:
                    // admin e info
                    if (baimena.equals("admin") || baimena.equals("info")) {
                        menu.menuBiltegia(e, biltegia);
                    } else {
                        System.out.println("Ez duzu baimenik.");
                    }
                    break;

                case 4:
                    // admin y kudeatzailea
                    if (baimena.equals("admin") || baimena.equals("kudeatzailea")) {
                        menu.menuIrteerak(biltegia);
                    } else {
                        System.out.println("Ez duzu baimenik.");
                    }
                    break;

                case 5:
                    // admin y kudeatzailea
                    if (baimena.equals("admin") || baimena.equals("kudeatzailea")) {
                        menu.menuDonazioa(biltegia);
                    } else {
                        System.out.println("Ez duzu baimenik.");
                    }
                    break;

                case 0:
                    System.out.println("Irteten...");
                    break;

                default:
                    System.out.println("Sartu balio balido bat.");
                    break;
            }

        } while (menua != 0);
        sc.close();

    }

}
