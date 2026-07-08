import java.util.Scanner;

import utils.Input;

public class moduleDashboard {
    public static void main() {
        Scanner sc = new Scanner(System.in);
        Boolean loop = true;
        while (loop) {
            System.out.println("Enter 1: Login");
            System.out.println("Enter 0: Exit");
            int page = sc.nextInt();
            sc.nextLine();
            switch (page) {
                case 0:
                    loop = false;
                    break;
                case 1:
                    moduleAuthentication.main();
                    break;
                default:
                    loop = false;
                    break;

            }
        }

    }
}
