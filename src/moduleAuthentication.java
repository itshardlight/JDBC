import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import db.DBConnection;
import utils.Input;

public class moduleAuthentication {
    public static void main() {
        Scanner sc = new Scanner(System.in);
        if (login()) {
            moduleMenu.menu();
        } else {
            System.out.println("Enter 1: Forget Pass");
            System.out.println("Enter 0: Exit");
            int page = sc.nextInt();
            sc.nextLine();
            if (page == 1) {
                forget();
            } else {
                moduleDashboard.main();
            }

        }
    }

    public static Boolean login() {
        Scanner sc = new Scanner(System.in);
        Boolean login = false;
        try {
            Connection con = DBConnection.getConnection();
            String query = "Select * from users";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println("Enter Your User Name:");
                String user = sc.nextLine();
                System.out.println("Enter Your Password:");
                String pass = sc.nextLine();

                if (user.equals(rs.getString("user")) && pass.equals(rs.getString("pass"))) {
                    login = true;
                } else {
                    login = false;
                }

            }
            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return login;
    }

    public static Boolean forget() {
        Scanner sc = new Scanner(System.in);
        Boolean login = false;
        try {
            Connection con = DBConnection.getConnection();
            String query = "Select * from forget";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println("Enter Your Pet Name:");
                String name = sc.nextLine();
                System.out.println("Enter Your Fav Colour:");
                String colour = sc.nextLine();
                System.out.println("Enter Your Birth Location:");
                String birth = sc.nextLine();
                if (name.equals(rs.getString("one")) && colour.equals(rs.getString("two"))
                        && birth.equals(rs.getString("three"))) {
                    System.out.println("Enter new User:");
                    String user = sc.nextLine();
                    System.out.println("Enter new Password:");
                    String pass = sc.nextLine();

                    try {
                        String query2 = "UPDATE users SET user = ?, pass = ? WHERE id = 1";
                        PreparedStatement prestmt = con.prepareStatement(query2);
                        prestmt.setString(1, user);
                        prestmt.setString(2, pass);
                        prestmt.executeUpdate();

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    login = true;
                } else {
                    login = false;
                }
            }
            rs.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return login;
    }
}