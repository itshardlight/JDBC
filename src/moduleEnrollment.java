import java.sql.Connection;
import java.sql.PreparedStatement;
import db.DBConnection;

public class moduleEnrollment {
    public static void enroll(int CourseCode) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "UPDATE Course SET RemainingSeats = RemainingSeats -1 WHERE CourseCode = ?";
            PreparedStatement prestmt = con.prepareStatement(query);
            prestmt.setInt(1, CourseCode);
            prestmt.executeUpdate();
            System.out.println("Enrolled into " + CourseCode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void withdraw(int CourseCode) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "UPDATE Course SET RemainingSeats = RemainingSeats +1 WHERE CourseCode = ?";
            PreparedStatement prestmt = con.prepareStatement(query);
            prestmt.setInt(1, CourseCode);
            prestmt.executeUpdate();
            System.out.println("Withdrawed from " + CourseCode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
