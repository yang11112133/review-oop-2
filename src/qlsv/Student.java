package qlsv;

import java.time.LocalDate;

public class Student {
    private String maSV;
    private String hoTen;
    private LocalDate ngaySinh;
    private String nganh;
    private double diemTB;
    private String lop;

    public Student(String maSV, String hoTen, LocalDate ngaySinh, String nganh, double diemTB, String lop) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.nganh = nganh;
        this.diemTB = diemTB;
        this.lop = lop;
    }

    // Getter/Setter
    public String getMaSV() { return maSV; }
    public String getHoTen() { return hoTen; }
    public LocalDate getNgaySinh() { return ngaySinh; }
    public String getNganh() { return nganh; }
    public double getDiemTB() { return diemTB; }
    public String getLop() { return lop; }

    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    public void setNgaySinh(LocalDate ngaySinh) { this.ngaySinh = ngaySinh; }
    public void setNganh(String nganh) { this.nganh = nganh; }
    public void setDiemTB(double diemTB) { this.diemTB = diemTB; }
    public void setLop(String lop) { this.lop = lop; }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %.2f | %s",
                maSV, hoTen, ngaySinh, nganh, diemTB, lop);
    }
}
