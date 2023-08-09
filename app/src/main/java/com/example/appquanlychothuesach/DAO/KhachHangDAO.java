package com.example.appquanlychothuesach.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appquanlychothuesach.database.Dbhelper;
import com.example.appquanlychothuesach.model.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {
    private SQLiteDatabase db;

    public KhachHangDAO(Context context) {
        Dbhelper dbhelper = new Dbhelper(context);
        db = dbhelper.getWritableDatabase();
    }

    public long insert(KhachHang obj) {
        ContentValues values = new ContentValues();
        values.put("cccd", obj.getCccd());
        values.put("hoTen", obj.getHoTen());
        values.put("namSinh", obj.getNamSinh());
        return db.insert("KhachHang", null, values);
    }

    public int update(KhachHang obj) {
        ContentValues values = new ContentValues();
        values.put("cccd", obj.getCccd());
        values.put("hoTen", obj.getHoTen());
        values.put("namSinh", obj.getNamSinh());
        return db.update("KhachHang", values, "maKH=?", new String[]{String.valueOf(obj.getMaKH())});
    }

    public int detele(String id) {
        return db.delete("KhachHang", "maKH=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<KhachHang> getData(String sql, String... selectionArgs) {
        List<KhachHang> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            KhachHang obj = new KhachHang();
            obj.setMaKH(Integer.parseInt(c.getString(c.getColumnIndex("maKH"))));
            obj.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            obj.setNamSinh(c.getString(c.getColumnIndex("namSinh")));
            obj.setCccd(c.getString(c.getColumnIndex("cccd")));
            list.add(obj);
        }
        return list;
    }

    public List<KhachHang> getAll() {
        String sql = "SELECT * FROM KhachHang";
        return getData(sql);
    }

    public KhachHang getID(String id) {
        String sql = "SELECT * FROM KhachHang WHERE maKH=?";
        List<KhachHang> list = getData(sql, id);
        return list.get(0);
    }
    public KhachHang getKhachHangByCCCD(String cccd) {
        String sql = "SELECT * FROM KhachHang WHERE cccd=?";
        List<KhachHang> list = getData(sql, cccd);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
