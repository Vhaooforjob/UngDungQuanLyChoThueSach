package com.example.appquanlychothuesach.model;

public class KhachHang {
    private int maKH;
    private String hoTen;
    private String namSinh;

    private String cccd;

    private String anhTV;

    public KhachHang() {
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }
    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }
    public String getAnhTV() {
        return anhTV;
    }

    public void setAnhTV(String anhTV) {
        this.anhTV = anhTV;
    }
    public KhachHang(int maKH, String hoTen, String namSinh, String cccd, String anhTV) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.cccd = cccd;
        this.anhTV = anhTV;
    }
}
