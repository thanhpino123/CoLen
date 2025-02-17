



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
public class sach1 {
     private String maSach;  // Mã sách
    private String tenSach; // Tên sách
    private int soLuong;    // Số lượng
    private double donGia;  // Đơn giá

    // Constructor không tham số
    public sach1() {}

    // Constructor có tham số
    public sach1(String maSach, String tenSach, int soLuong, double donGia) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    // Getter và Setter
    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    // Phương thức tính chiết khấu
    public double tinhChietKhau() {
        double thanhTien = soLuong * donGia;
        if (soLuong > 10) {
            return 0.05 * thanhTien; // Chiết khấu 5% nếu số lượng > 10
        } else if (soLuong > 5) {
            return 0.03 * thanhTien; // Chiết khấu 3% nếu số lượng > 5
        }
        return 0; // Không có chiết khấu nếu số lượng <= 5
    }

    // Phương thức hiển thị thông tin sách
    @Override
    public String toString() {
        return "Sach{" +
                "maSach='" + maSach + '\'' +
                ", tenSach='" + tenSach + '\'' +
                ", soLuong=" + soLuong +
                ", donGia=" + donGia +
                ", chietKhau=" + tinhChietKhau() +
                '}';
    }
    
}
