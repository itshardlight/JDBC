import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.protocol.Resultset;

import db.DBConnection;
import utils.Input;

public class moduleStudent {
    public static void main(String[] args) {
        show();
        del();

    }

    public static void add() {
        try {
            Connection con = DBConnection.getConnection();
            String StudentName = Input.getString("Enter Student Name:");
            String StudentGmail = Input.getString("Enter Student Gmail:");
            String StudentPhone = Input.getString("Enter Student Phone:");
            int CourseCode = Input.getInt("Enter Course Code To Enroll:");
            moduleEnrollment.enroll(CourseCode);

            String query = "INSERT INTO Student (StudentName,StudentGmail,StudentPhone,CourseCode) VALUES (?,?,?,?)";
            PreparedStatement prestmt = con.prepareStatement(query);
            prestmt.setString(1, StudentName);
            prestmt.setString(2, StudentGmail);
            prestmt.setString(3, StudentPhone);
            prestmt.setInt(4, CourseCode);

            int rows = prestmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Student Added Successfully");
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
            int CourseCode = Input.getInt("Enter Course Code To Withdraw:");
            moduleEnrollment.withdraw(CourseCode);
            String query = "DELETE FROM Student WHERE StudentCode = (?)";
            PreparedStatement prestmt = con.prepareStatement(query);
            prestmt.setInt(1, StudentCode);

            int rows = prestmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Student Deleted Successfully");
                con.close();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void show() {
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM Student";
            PreparedStatement prestmt = con.prepareStatement(query);
            ResultSet rs = prestmt.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getInt("StudentCode") + " " + rs.getString("StudentName") + " "
                                + rs.getString("StudentGmail")
                                + " " + rs.getString("StudentPhone") + " " + rs.getInt("CourseCode"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void update() {
        try {
            Connection con = DBConnection.getConnection();
            int StudentCode = Input.getInt("Enter The Student Id To Update:");
            String StudentName = Input.getString("Enter Student Name To Update:");
            String StudentGmail = Input.getString("Enter Student Gmail To Update:");
            String StudentPhone = Input.getString("Enter Student Phone To Update:");
            int oldCourse = Input.getInt("Enter Old Course Code To Withdraw:");
            moduleEnrollment.withdraw(oldCourse);
            int newCourse = Input.getInt("Enter New Course Code To Enroll:");
            moduleEnrollment.enroll(newCourse);
            String query = "UPDATE Student SET StudentName = ? ,StudentGmail = ? , StudentPhone = ?, CourseCode = ? WHERE StudentCode = ?";
            PreparedStatement prestmt = con.prepareStatement(query);
            prestmt.setString(1, StudentName);
            prestmt.setString(2, StudentGmail);
            prestmt.setString(3, StudentPhone);
            prestmt.setInt(4, newCourse);
            prestmt.setInt(5, StudentCode);

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