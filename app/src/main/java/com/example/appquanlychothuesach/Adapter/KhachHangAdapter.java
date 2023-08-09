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

import com.example.appquanlychothuesach.R;
import com.example.appquanlychothuesach.Fragment.KhachHang.KhachHangFragment;
import com.example.appquanlychothuesach.model.KhachHang;
import com.example.appquanlychothuesach.model.ThuThu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class KhachHangAdapter extends ArrayAdapter<KhachHang> {
    private Context context;
    KhachHangFragment fragment;
    private ArrayList<KhachHang> list;
    TextView tvMaKH, tvTenKH, tvNamSinh,tvCCCD;
    ImageView imgDel,imgTV;


    public KhachHangAdapter(@NonNull Context context, KhachHangFragment fragment, @NonNull ArrayList<KhachHang> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.khach_hang_item, null);
        }
        final KhachHang item = list.get(position);
        if (item != null) {
            tvMaKH = v.findViewById(R.id.tvMaKH);
            tvMaKH.setText("Mã khách hàng: " + item.getMaKH());

            tvCCCD = v.findViewById(R.id.tvCCCD);
            tvCCCD.setText("Mã CCCD: " + item.getCccd());

//            imgTV = v.findViewById(R.id.imgTV);
//            String anhTVLink = item.getAnhTV();
//            if (anhTVLink != null && !anhTVLink.isEmpty()) {
//                Picasso.get().load(anhTVLink).into(imgTV);
//            }
            tvTenKH = v.findViewById(R.id.tvTenKH);
            tvTenKH.setText("Tên khách hàng: " + item.getHoTen());

            tvNamSinh = v.findViewById(R.id.tvNamSinh);
            tvNamSinh.setText("Năm sinh: " + item.getNamSinh());

            imgDel = v.findViewById(R.id.imgDeleteKH);
            imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.xoa(String.valueOf(item.getMaKH()));
                }
            });
        }
        for (int i = 0; i <= list.size(); i++) {
            if (item.getMaKH() % 2 == 0) {
                tvMaKH.setTextColor(Color.RED);
            } else {
                tvMaKH.setTextColor(Color.BLUE);
            }
        }
        return v;
    }
}
