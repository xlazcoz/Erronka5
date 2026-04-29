import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String bil = "";
        int saiakerak1 = 0;
        int biltegia = 0;
        int saiakerak = 0;
        erabiltzailea e = null;

        while (saiakerak < 3) {
            System.out.println("Kaixo, sartu erabiltzailea mesedez:");
            String erabiltzailea = sc.nextLine();
            System.out.println("sartu pasahitza mesedez:");
            String pasahitza = sc.nextLine();

            e = new erabiltzailea(erabiltzailea, pasahitza);
            e.erabiltzaileaIdentifikatuEtaBaimenaEzarri();

            if (e.baimena != "") {
                break;
            } else {
                saiakerak++;
            }
        }

        biltegia b = new biltegia(0, null, null);
        ArrayList<biltegia> blist = new ArrayList<biltegia>();
        blist = b.biltegiakBistaratu();
        System.out.println("\n--- Biltegien lista ---");
        for (biltegia b1 : blist) {
            System.out.println("ID: " + b1.kodea_biltegia + " | Nombre: " + b1.izena + " | Departamento: " + b1.kokapena);
        }

        while (saiakerak1 < 3) {
            System.out.println("ze biltegitan zaude sartu ID-a");
            biltegia = sc.nextInt();
            sc.nextLine();
            b = new biltegia(0, null, null);
            bil = b.biltegiaKomprobatu(biltegia);

            if (bil != "") {
                break;
            } else {
                saiakerak1++;
            }
        }

        if (e != null && e.baimena != "" && saiakerak1 < 3) {
            Main m = new Main();
            m.aurrenekomenua(e, biltegia);
        }

        sc.close();
    }

    public void aurrenekomenua(erabiltzailea e, int biltegia) {
        Scanner sc = new Scanner(System.in);

        int menua;

        do {
            if (e.baimena.equals("admin")) {
                System.out.println("Menua: \r\n" +
                        "1. Erabiltzaileak  \r\n" +
                        "2. Produktuak \r\n" +
                        "3. Biltegia\r\n" +
                        "4. Irteerak\r\n" +
                        "5. Donazioa\r\n" +
                        "0. Irten ");
            } else if (e.baimena.equals("kudeatzailea")) {
                System.out.println("Menua: \r\n" +
                        "4. Irteerak\r\n" +
                        "5. Donazioa\r\n" +
                        "0. Irten ");
            } else if (e.baimena.equals("info")) {
                System.out.println("Menua: \r\n" +
                        "2. Produktuak  \r\n" +
                        "3. Biltegia\r\n" +
                        "0. Irten ");
            }

            menua = sc.nextInt();

            switch (menua) {
                case 1:
                    if (e.baimena.equals("admin")) {
                        menu.menuErabiltzaileak(e);
                    } else {
                        System.out.println("Ez duzu baimenik.");
                    }
                    break;

                case 2:
                    if (e.baimena.equals("admin") || e.baimena.equals("info")) {
                        menu.menuProductos();
                    } else {
                        System.out.println("Ez duzu baimenik.");
                    }
                    break;

                case 3:
                    if (e.baimena.equals("admin") || e.baimena.equals("info")) {
                        menu.menuBiltegia(e, biltegia);
                    } else {
                        System.out.println("Ez duzu baimenik.");
                    }
                    break;

                case 4:
                    if (e.baimena.equals("admin") || e.baimena.equals("kudeatzailea")) {
                        menu.menuIrteerak(biltegia);
                    } else {
                        System.out.println("Ez duzu baimenik.");
                    }
                    break;

                case 5:
                    if (e.baimena.equals("admin") || e.baimena.equals("kudeatzailea")) {
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