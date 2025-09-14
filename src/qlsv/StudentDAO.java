package qlsv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.Date;  
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public void addStudent(Student s) throws Exception {
        String sql = "INSERT INTO students(ma_sv, ho_ten, ngay_sinh, nganh, diem_tb, lop) VALUES(?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getMaSV());
            ps.setString(2, s.getHoTen());
            ps.setDate(3, Date.valueOf(s.getNgaySinh())); // rõ ràng java.sql.Date
            ps.setString(4, s.getNganh());
            ps.setDouble(5, s.getDiemTB());
            ps.setString(6, s.getLop());
            ps.executeUpdate();
        }
    }

    public void updateStudent(Student s) throws Exception {
        String sql = "UPDATE students SET ho_ten=?, ngay_sinh=?, nganh=?, diem_tb=?, lop=? WHERE ma_sv=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getHoTen());
            ps.setDate(2, Date.valueOf(s.getNgaySinh()));
            ps.setString(3, s.getNganh());
            ps.setDouble(4, s.getDiemTB());
            ps.setString(5, s.getLop());
            ps.setString(6, s.getMaSV());
            ps.executeUpdate();
        }
    }


    public void deleteStudent(String maSV) throws Exception {
        String sql = "DELETE FROM students WHERE ma_sv=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maSV);
            ps.executeUpdate();
        }
    }

    public List<Student> getAllStudents() throws Exception {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Student(
                        rs.getString("ma_sv"),
                        rs.getString("ho_ten"),
                        rs.getDate("ngay_sinh").toLocalDate(),
                        rs.getString("nganh"),
                        rs.getDouble("diem_tb"),
                        rs.getString("lop")
                ));
            }
        }
        return list;
    }

    public List<Student> getByLop(String lop) throws Exception {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE lop=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, lop);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Student(
                        rs.getString("ma_sv"),
                        rs.getString("ho_ten"),
                        rs.getDate("ngay_sinh").toLocalDate(),
                        rs.getString("nganh"),
                        rs.getDouble("diem_tb"),
                        rs.getString("lop")
                ));
            }
        }
        return list;
    }

    public List<Student> getByNganh(String nganh) throws Exception {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE nganh=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nganh);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Student(
                        rs.getString("ma_sv"),
                        rs.getString("ho_ten"),
                        rs.getDate("ngay_sinh").toLocalDate(),
                        rs.getString("nganh"),
                        rs.getDouble("diem_tb"),
                        rs.getString("lop")
                ));
            }
        }
        return list;
    }
}
