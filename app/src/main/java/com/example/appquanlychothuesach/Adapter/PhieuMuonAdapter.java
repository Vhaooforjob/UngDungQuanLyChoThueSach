package com.example.appquanlychothuesach.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appquanlychothuesach.DAO.SachDAO;
import com.example.appquanlychothuesach.DAO.KhachHangDAO;
import com.example.appquanlychothuesach.Fragment.PhieuMuon.PhieuMuonFragment;
import com.example.appquanlychothuesach.R;
import com.example.appquanlychothuesach.model.PhieuMuon;
import com.example.appquanlychothuesach.model.Sach;
import com.example.appquanlychothuesach.model.KhachHang;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    private Context context;
    PhieuMuonFragment fragment;
    private ArrayList<PhieuMuon> lists;
    TextView tvMaPM, tvTenTV, tvTenSach, tvTienThue, tvNgay, tvTraSach;
    ImageView imgDel;
    SachDAO sachDAO;
    KhachHangDAO khachHangDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public PhieuMuonAdapter(@NonNull Context context, PhieuMuonFragment fragment, ArrayList<PhieuMuon> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.phieumuon_item, null);
        }
        final PhieuMuon item = lists.get(position);
        if (item != null) {
            tvMaPM = v.findViewById(R.id.tvMaPM);
            tvMaPM.setText("Mã Phiếu: " + item.getMaPM());
            sachDAO = new SachDAO(context);
            Sach sach = sachDAO.getID(String.valueOf(item.getMaSach()));
            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên Sách: " + sach.getTenSach());
            khachHangDAO = new KhachHangDAO(context);
            KhachHang khachHang = khachHangDAO.getID(String.valueOf(item.getMaKH()));
            tvTenTV = v.findViewById(R.id.tvTenKH);
            tvTenTV.setText("Khách hàng: " + khachHang.getHoTen());
            tvTienThue = v.findViewById(R.id.tvTienThue);
            tvTienThue.setText("Tiền Thuê: " + item.getTienThue());
            tvNgay = v.findViewById(R.id.tvNgayPM);
            tvNgay.setText("Ngày Thuê: " + sdf.format(item.getNgay()));
            tvTraSach = v.findViewById(R.id.tvTraSach);
            if (item.getTraSach() == 1) {
                tvTraSach.setTextColor(Color.BLUE);
                tvTraSach.setText("Đã Trả Sách");
            } else {
                tvTraSach.setTextColor(Color.RED);
                tvTraSach.setText("Chưa Trả Sách");
            }
            imgDel = v.findViewById(R.id.imgDeleteLS);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gọi pt xóa
                fragment.xoa(String.valueOf(item.getMaPM()));
            }
        });

        return v;

    }
}
