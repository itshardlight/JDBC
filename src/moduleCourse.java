import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.protocol.Resultset;

import db.DBConnection;
import utils.Input;

public class moduleCourse {
    public static void main(String[] args) {
        show();
        del();
    }

    public static void add() {
        try {
            Connection con = DBConnection.getConnection();
            String CourseName = Input.getString("Enter Course Name:");
            String query = "INSERT INTO Course (CourseName) VALUES (?)";
            PreparedStatement prestmt = con.prepareStatement(query);
            prestmt.setString(1, CourseName);

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