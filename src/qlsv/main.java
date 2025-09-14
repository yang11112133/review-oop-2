package qlsv;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class main {
    private static final Scanner sc = new Scanner(System.in);
    private static final StudentDAO dao = new StudentDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Sửa sinh viên");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Xem tất cả sinh viên");
            System.out.println("5. Xem theo lớp");
            System.out.println("6. Xem theo ngành");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            int ch = Integer.parseInt(sc.nextLine());
            try {
                switch (ch) {
                    case 1 -> addStudent();
                    case 2 -> updateStudent();
                    case 3 -> deleteStudent();
                    case 4 -> showAll();
                    case 5 -> showByLop();
                    case 6 -> showByNganh();
                    case 0 -> System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }

    private static void addStudent() throws Exception {
        System.out.print("Mã SV: ");
        String ma = sc.nextLine();
        if (!(ma.matches("455105\\d{4}") || ma.matches("455109\\d{4}"))) {
            System.out.println("Mã SV không hợp lệ!");
            return;
        }

        System.out.print("Họ tên: ");
        String hoTen = normalizeName(sc.nextLine());

        System.out.print("Ngày sinh (yyyy-MM-dd): ");
        LocalDate ns = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);
        int age = LocalDate.now().getYear() - ns.getYear();
        if (age < 15 || age > 110) {
            System.out.println("Tuổi không hợp lệ!");
            return;
        }

        System.out.print("Ngành (CNTT/KTPM): ");
        String nganh = sc.nextLine();
        if (!(nganh.equals("CNTT") || nganh.equals("KTPM"))) {
            System.out.println("Ngành không hợp lệ!");
            return;
        }

        System.out.print("Điểm TB: ");
        double diem = Double.parseDouble(sc.nextLine());
        if (diem < 0.0 || diem > 10.0) {
            System.out.println("Điểm TB không hợp lệ!");
            return;
        }

        System.out.print("Lớp: ");
        String lop = sc.nextLine();

        Student s = new Student(ma, hoTen, ns, nganh, diem, lop);
        dao.addStudent(s);
        System.out.println("Thêm thành công!");
    }

    private static void updateStudent() throws Exception {
        System.out.print("Nhập mã SV cần sửa: ");
        String ma = sc.nextLine();
        System.out.print("Tên mới: ");
        String hoTen = normalizeName(sc.nextLine());
        System.out.print("Ngày sinh (yyyy-MM-dd): ");
        LocalDate ns = LocalDate.parse(sc.nextLine());
        System.out.print("Ngành: ");
        String nganh = sc.nextLine();
        System.out.print("Điểm TB: ");
        double diem = Double.parseDouble(sc.nextLine());
        System.out.print("Lớp: ");
        String lop = sc.nextLine();

        Student s = new Student(ma, hoTen, ns, nganh, diem, lop);
        dao.updateStudent(s);
        System.out.println("Cập nhật thành công!");
    }

    private static void deleteStudent() throws Exception {
        System.out.print("Nhập mã SV cần xóa: ");
        dao.deleteStudent(sc.nextLine());
        System.out.println("Đã xóa!");
    }

    private static void showAll() throws Exception {
        dao.getAllStudents().forEach(System.out::println);
    }

    private static void showByLop() throws Exception {
        System.out.print("Nhập lớp: ");
        dao.getByLop(sc.nextLine()).forEach(System.out::println);
    }

    private static void showByNganh() throws Exception {
        System.out.print("Nhập ngành: ");
        dao.getByNganh(sc.nextLine()).forEach(System.out::println);
    }

    private static String normalizeName(String name) {
        name = name.trim().toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (String word : name.split("\\s+")) {
            sb.append(Character.toUpperCase(word.charAt(0)))
              .append(word.substring(1)).append(" ");
        }
        return sb.toString().trim();
    }
}
