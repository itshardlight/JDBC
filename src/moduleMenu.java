import java.util.Scanner;

public class moduleMenu {
    public static void menu() {
        Boolean loop = true;
        Scanner sc = new Scanner(System.in);
        while (loop) {
            System.out.println("--------------------------");
            System.out.println("Enter 1: Course Management System");
            System.out.println("Enter 2: Student Management System");
            System.out.println("Enter 3: Attendance Management System");
            System.out.println("Enter 4: Result Management System");
            System.out.println("Enter 0: Exit");
            System.out.println("--------------------------");

            int page = sc.nextInt();
            sc.nextLine();
            switch (page) {
                case 0:
                    loop = false;
                    break;
                case 1:
                    moduleCourse.menu();
                    break;
                case 2:
                    moduleStudent.menu();
                    break;
                case 3:
                    moduleAttendance.menu();
                    break;
                case 4:
                    moduleResult.menu();
                    break;
                default:
                    loop = false;
                    break;

            }
        }
    }
}