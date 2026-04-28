import java.util.Scanner;

public class laguntzaileak {

    public static boolean true_false(String textua) {
        System.out.println(textua);

        Scanner sc = new Scanner(System.in);
        String erant = sc.nextLine();
        while (!erant.equals("b") && !erant.equals("e")) {
            System.out.println("mesedez, soilik b edo e");
            erant = sc.nextLine();
        }

        if (erant == "b") {
            sc.close();
            return true;

        } else { // "e"
            sc.close();
            return false;
        }
    }
}
