import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

import db.DBConnection;
import utils.Input;

public class moduleResult {
    public static void menu() {
        Boolean loop = true;
        Scanner sc = new Scanner(System.in);
        while (loop) {
            System.out.println("--------------------------");
            System.out.println("Enter 1: Show Result");
            System.out.println("Enter 2: Add Result");
            System.out.println("Enter 3: Delete Result");
            System.out.println("Enter 4: Update Result");
            System.out.println("Enter 0: Exit");
            System.out.println("--------------------------");

            int page = sc.nextInt();
            sc.nextLine();
            switch (page) {
                case 0:
                    loop = false;
                    break;
                case 1:
                    show();
                    break;
                case 2:
                    add();
                    break;
                case 3:
                    del();
                    break;
                case 4:
                    update();
                    break;
                default:
                    loop = false;
                    break;

            }
        }
    }

    public static void add() {
        try {
            Connection con = DBConnection.getConnection();
            int StudentId = Input.getInt("Enter Student ID:");
            int Science = Input.getInt("Enter Marks Science:");
            int English = Input.getInt("Enter Marks English:");
            int Nepali = Input.getInt("Enter Marks Nepali:");
            int Computer = Input.getInt("Enter Marks Computer:");
            int HPE = Input.getInt("Enter Marks HPE:");
            int Maths = Input.getInt("Enter Marks Maths:");
            int Account = Input.getInt("Enter Marks Account:");
            int Social = Input.getInt("Enter Marks Social:");
            double TotalMarks = Science + English + Nepali + Computer + HPE + Maths + Account + Social;
            double Percentage = (TotalMarks / 800) * 100;
            String query = "INSERT INTO Result (StudentCode, Science, English, Nepali, Computer, HPE, Maths, Account, Social, TotalMarks, Percentage) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement prestmt = con.prepareStatement(query);
            prestmt.setInt(1, StudentId);
            prestmt.setInt(2, Science);
            prestmt.setInt(3, English);
            prestmt.setInt(4, Nepali);
            prestmt.setInt(5, Computer);
            prestmt.setInt(6, HPE);
            prestmt.setInt(7, Maths);
            prestmt.setInt(8, Account);
            prestmt.setInt(9, Social);
            prestmt.setDouble(10, TotalMarks);
            prestmt.setDouble(11, Percentage);

            int rows = prestmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Result Added Successfully");
                con.close();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void del() {
        try {
            Connection con = DBConnection.getConnection();
            int StudentCode = Input.getInt("Enter Student Code To Delete:");
            String query = "DELETE FROM Result WHERE StudentCode = (?)";
            PreparedStatement prestmt = con.prepareStatement(query);
            prestmt.setInt(1, StudentCode);

            int rows = prestmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Course Deleted Successfully");
                con.close();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void show() {
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM Result";
            PreparedStatement prestmt = con.prepareStatement(query);
            ResultSet rs = prestmt.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getInt("StudentCode") + " " +
                                rs.getInt("Science") + " " +
                                rs.getInt("English") + " " +
                                rs.getInt("Nepali") + " " +
                                rs.getInt("Computer") + " " +
                                rs.getInt("HPE") + " " +
                                rs.getInt("Maths") + " " +
                                rs.getInt("Account") + " " +
                                rs.getInt("Social") + " " +
                                rs.getDouble("TotalMarks") + " " +
                                rs.getDouble("Percentage"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void update() {
        try {
            Connection con = DBConnection.getConnection();
            int StudentId = Input.getInt("Enter The Student Id To Update:");
            int Science = Input.getInt("Enter Marks Science:");
            int English = Input.getInt("Enter Marks English:");
            int Nepali = Input.getInt("Enter Marks Nepali:");
            int Computer = Input.getInt("Enter Marks Computer:");
            int HPE = Input.getInt("Enter Marks HPE:");
            int Maths = Input.getInt("Enter Marks Maths:");
            int Account = Input.getInt("Enter Marks Account:");
            int Social = Input.getInt("Enter Marks Social:");
            String query = "UPDATE Result SET Science = ?, English = ?, Nepali = ?, Computer = ?, HPE = ?, Maths = ?, Account = ?, Social = ?, TotalMarks = ?, Percentage = ? WHERE StudentCode = ?";
            PreparedStatement prestmt = con.prepareStatement(query);
            prestmt.setInt(1, StudentId);
            prestmt.setInt(2, Science);
            prestmt.setInt(3, English);
            prestmt.setInt(4, Nepali);
            prestmt.setInt(5, Computer);
            prestmt.setInt(6, HPE);
            prestmt.setInt(7, Maths);
            prestmt.setInt(8, Account);
            prestmt.setInt(9, Social);
            double TotalMarks = Science + English + Nepali + Computer + HPE + Maths + Account + Social;
            double Percentage = (TotalMarks / 800) * 100;
            prestmt.setDouble(10, TotalMarks);
            prestmt.setDouble(11, Percentage);

            int rows = prestmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Course Updated Successfully");
                con.close();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}