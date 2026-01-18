import java.util.Scanner;

public class Main_Menu {
    public static boolean show() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Welcome to Tic Tac Toe!\n" +
                "Choose an option\n" +
                "\ta) Play against another Player\n" +
                "\tb) Play against AI\n" +
                "\tc) Exit\n" +
                "\n" +
                "Enter your option: ");

        String option = sc.nextLine();

        switch (option.toLowerCase()) {
            case "a":
                return false;
            case "b":
                return true;
            case "c":
                System.exit(0);
            default:
                System.out.println("invalid input");
                System.exit(1);
        }

        return false;
    }
}

