import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.protocol.Resultset;

import db.DBConnection;
import utils.Input;

public class moduleAttendance {
    public static void main(String[] args) {
        show();
        add();

    }

    public static Map<Integer, String> fetch() {
        Map<Integer, String> entry = new LinkedHashMap<>();

        try {
            Connection con = DBConnection.getConnection();
            String query = "Select StudentCode from Student";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int StudentCode = rs.getInt("StudentCode");
                String Entry = Input.getString("Enter The Attendance for " + StudentCode + ":");
                entry.put(StudentCode, Entry);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return entry;
    }

    public static void add() {
        try {
            Map<Integer, String> listStudent = fetch();
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO Attendance (StudentCode,Status) VALUES (?,?)";
            PreparedStatement prestmt = con.prepareStatement(query);
            for (Map.Entry<Integer, String> entry : listStudent.entrySet()) {
                prestmt.setInt(1, entry.getKey());
                prestmt.setString(2, entry.getValue());
                prestmt.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void del() {
        try {
            Connection con = DBConnection.getConnection();
            int StudentCode = Input.getInt("Enter Student Code To Delete:");
            String query = "DELETE FROM Attendance WHERE Students = (?) ";
            PreparedStatement prestmt = con.prepareStatement(query);
            prestmt.setInt(1, StudentCode);

            int rows = prestmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Attendance Deleted Successfully");
                con.close();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void show() {
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM Attendance";
            PreparedStatement prestmt = con.prepareStatement(query);
            ResultSet rs = prestmt.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getDate("Date") + " " + rs.getString("StudentCode") + " " + rs.getString("Status"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void update() {
        try {
            Connection con = DBConnection.getConnection();
            int StudentId = Input.getInt("Enter The Student Id To Update:");
            String status = Input.getString("Enter Attendance for " + StudentId + ":");
            String query = "UPDATE Attendance SET Status = ? WHERE StudentCode = ?";
            PreparedStatement prestmt = con.prepareStatement(query);
            prestmt.setString(1, status);
            prestmt.setInt(2, StudentId);

            int rows = prestmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Attendance Updated Successfully");
                con.close();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}