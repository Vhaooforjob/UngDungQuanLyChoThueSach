package com.example.appquanlychothuesach.model;

public class ThuThu {
    private String maTT;
    private String hoTen;
    private String matKhau;

//    public String getPhanQuyen() {
//        return phanQuyen;
//    }
//
//    public void setPhanQuyen(String phanQuyen) {
//        this.phanQuyen = phanQuyen;
//    }
//
//    private String phanQuyen;


    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public ThuThu() {
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }


    public ThuThu(String maTT, String hoTen, String matKhau) {
        this.maTT = maTT;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
//        this.phanQuyen = phanQuyen;
    }
}
