package com.example.appquanlychothuesach.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appquanlychothuesach.database.Dbhelper;
import com.example.appquanlychothuesach.model.PhieuMuon;
import com.example.appquanlychothuesach.model.Sach;
import com.example.appquanlychothuesach.model.Top;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {
private Context context;
private SQLiteDatabase db;
SimpleDateFormat sdf =new SimpleDateFormat("yyyy/MM/dd");
    public PhieuMuonDAO(Context context) {
        this.context = context;
        Dbhelper dbHelper = new Dbhelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(PhieuMuon obj) {
        if (getTotalBooksBorrowedByCustomer(obj.getMaKH()) < 2) {
            ContentValues values = new ContentValues();
            values.put("maTT", obj.getMaTT());
            values.put("maKH", obj.getMaKH());
            values.put("maSach", obj.getMaSach());
            values.put("ngay", sdf.format(obj.getNgay()));
            values.put("tienThue", obj.getTienThue());
            values.put("traSach", obj.getTraSach());
            return db.insert("PhieuMuon", null, values);
        } else {
            // Khách hàng đã mượn quá 2 cuốn sách, không thể mượn thêm
            return -1;
        }
    }
    public int update(PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("maTT", obj.getMaTT());
        values.put("maKH", obj.getMaKH());
        values.put("maSach", obj.getMaSach());
        values.put("ngay", sdf.format(obj.getNgay()));
        values.put("tienThue", obj.getTienThue());
        values.put("traSach", obj.getTraSach());
        return db.update("PhieuMuon",values,"maPM=?",new String[]{String.valueOf(obj.getMaPM())});
    }
    public int detele(String id){
        return db.delete("PhieuMuon","maPM=?",new String[]{id});
    }
    @SuppressLint("Range")
    public List<PhieuMuon> getData(String sql, String...selectionArgs){
        List<PhieuMuon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            PhieuMuon obj = new PhieuMuon();
            obj.setMaPM(Integer.parseInt(c.getString(c.getColumnIndex("maPM"))));
            obj.setMaTT(c.getString(c.getColumnIndex("maTT")));
            obj.setMaSach(Integer.parseInt(c.getString(c.getColumnIndex("maSach"))));
            obj.setMaKH(Integer.parseInt(c.getString(c.getColumnIndex("maKH"))));
            obj.setTienThue(Integer.parseInt(c.getString(c.getColumnIndex("tienThue"))));

            try {
                obj.setNgay(sdf.parse(c.getString(c.getColumnIndex("ngay"))));
            }catch (ParseException e){
                e.printStackTrace();;
            }
            obj.setTraSach(Integer.parseInt(c.getString(c.getColumnIndex("traSach"))));
            list.add(obj);
        }
        return list;
    }
    public List<PhieuMuon> getAll(){
        String sql = "SELECT * FROM PhieuMuon";
        return getData(sql);
    }
    public PhieuMuon getID(String id)   {
        String sql = "SELECT * FROM   PhieuMuon WHERE maPM=?" ;
        List<PhieuMuon> list = getData(sql,id);
        return list.get(0);
    }
    //thong ke top 10
    @SuppressLint("Range")
    public List<Top> getTop(){
        String sqlTop  = "SELECT maSach,count(maSach) as soLuong FROM PhieuMuon GROUP BY maSach ORDER BY soLuong DESC LIMIT 10";
        List<Top> list = new ArrayList<Top>();
        SachDAO sachDAO = new SachDAO(context);
        Cursor c = db.rawQuery(sqlTop,null);

        while (c.moveToNext()){
            Top top = new Top();

            Sach sach = sachDAO.getID(c.getString(c.getColumnIndex("maSach")));
            top.setTenSach(sach.getTenSach());
            top.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("soLuong"))));
            list.add(top);
        }
        return list;
    }
    @SuppressLint("Range")
    public int getDanhThu(String tuNgay, String denNgay){
        String sqlDoanhThu = "SELECT SUM(tienThue) as doanhThu FROM PhieuMuon WHERE ngay BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<Integer>();
        Cursor c = db.rawQuery(sqlDoanhThu,new String[]{tuNgay,denNgay});

        while (c.moveToNext()){
            try {
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));

            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }
    public int getTotalBooksBorrowedByCustomer(int maKH) {
        String sql = "SELECT COUNT(*) as total FROM PhieuMuon WHERE maKH = ?";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(maKH)});
        if (c.moveToFirst()) {
            return c.getInt(c.getColumnIndex("total"));
        }
        return 0;
    }
}
