package com.example.appquanlychothuesach.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "PNLIB";
    private static final int DB_VERSION = 1;

    public Dbhelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    static final String CREATE_TABLE_THU_THU =
            "CREATE TABLE ThuThu(" +
                    "maTT text PRIMARY KEY," +
                    "hoTen text NOT NULL," +
                    "matKhau text NOT NULL," +
                    "phanQuyen text NOT NULL" +
                    ")";

    static final String CREATE_TABLE_KHACH_HANG =
            "create table KhachHang (" +
                    "maKH INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "cccd CHAR(12)  NOT NULL UNIQUE," +
                    "hoTen TEXT NOT NULL," +
                    "anhTV TEXT," +
                    "namSinh TEXT NOT NULL)";

    ///
    static final String CREATE_TABLE_LOAI_SACH =
            "create table LoaiSach (" +
                    "maLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "tenLoai TEXT NOT NULL)";

    //
    static final String CREATE_TABLE_SACH =
            "create table Sach (" +
                    "maSach INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenSach TEXT NOT NULL," +
                    "giaThue INTEGER NOT NULL," +
                    "soluong INTEGER NOT NULL," +
                    "maLoai INTEGER REFERENCES LoaiSach(maLoai)," +
                    "img TEXT,"+
                    "Nhacungcap TEXT NOT NULL)";

    //
    static final String CREATE_TABLE_PHIEU_MUON =
            "create table PhieuMuon(" +
                    "maPM INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "maTT TEXT REFERENCES ThuThu(maTT)," +
                    "maKH INTEGER REFERENCES ThanhVien(maTV)," +
                    "maSach INTEGER REFERENCES Sach(maSach)," +
                    "tienThue INTEGER NOT NULL, " +
                    "ngay DATE NOT NULL," +
                    "traSach INTEGER NOT NULL)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_THU_THU);
        db.execSQL(CREATE_TABLE_KHACH_HANG);
        db.execSQL(CREATE_TABLE_LOAI_SACH);
        db.execSQL(CREATE_TABLE_SACH);
        db.execSQL(CREATE_TABLE_PHIEU_MUON);
        db.execSQL(Data_SQLite.INSERT_THUTHU);
        db.execSQL(Data_SQLite.INSERT_KHACHHANG);
        db.execSQL(Data_SQLite.INSERT_LOAISACH);
        db.execSQL(Data_SQLite.INSERT_SACH);
        db.execSQL(Data_SQLite.INSERT_PHIEUMUON);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTableLoaiThuThu = "drop table if exists ThuThu";
        db.execSQL(dropTableLoaiThuThu);
        //
        String dropTableKH = "drop table if exists KhachHang";
        db.execSQL(dropTableKH);
        //
        String dropTableLoaiSach = "drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);
        //
        String dropTableSach = "drop table if exists Sach";
        db.execSQL(dropTableSach);
        //
        String dropTablePhieuMuon = "drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);
        //
        onCreate(db);

    }
}
