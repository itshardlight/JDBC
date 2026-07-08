package utils;

import java.util.Scanner;

public class Input {
    private static Scanner sc = new Scanner(System.in);

    public static int getInt(String print) {
        System.out.print(print);
        int a = sc.nextInt();
        sc.nextLine();
        return a;
    }

    public static String getString(String print) {
        System.out.print(print);
        return sc.nextLine();
    }
}