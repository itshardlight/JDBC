import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.protocol.Resultset;

import db.DBConnection;
import utils.Input;

public class moduleResult {
    public static void main(String[] args) {
        add();

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
            int TotalMarks = Science + English + Nepali + Computer + HPE + Maths + Account + Social;
            double Percentage = (TotalMarks / 8) * 100;
            String query = "INSERT INTO Result (StudentCode,Science,English,Nepali,Computer,HPE,Maths,Account,Social,TotalMarks,Percentage) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
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
            prestmt.setInt(10, TotalMarks);
            prestmt.setDouble(11, 20);

            int rows = prestmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Course Added Successfully");
                con.close();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void del() {
        try {
            Connection con = DBConnection.getConnection();
            int CourseCode = Input.getInt("Enter Course Code To Delete:");
            String query = "DELETE FROM Course WHERE CourseCode = (?)";
            PreparedStatement prestmt = con.prepareStatement(query);
            prestmt.setInt(1, CourseCode);

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
            String query = "SELECT * FROM Course";
            PreparedStatement prestmt = con.prepareStatement(query);
            ResultSet rs = prestmt.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getInt("CourseCode") + " " + rs.getString("CourseName") + " " + rs.getInt("RemainingSeats"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void update() {
        try {
            Connection con = DBConnection.getConnection();
            int CourseId = Input.getInt("Enter The Course Id To Update:");
            String CourseName = Input.getString("Enter Course Name:");
            String query = "UPDATE Course SET CourseName = ? WHERE CourseCode = ?";
            PreparedStatement prestmt = con.prepareStatement(query);
            prestmt.setString(1, CourseName);
            prestmt.setInt(2, CourseId);

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