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

import com.example.appquanlychothuesach.Fragment.NhanVien.NhanVienFragment;
import com.example.appquanlychothuesach.R;
import com.example.appquanlychothuesach.model.ThuThu;

import java.util.ArrayList;

public class ThuThuAdapter extends ArrayAdapter<ThuThu> {
    private Context context;
    NhanVienFragment fragment;
    private ArrayList<ThuThu> list;
    TextView tvMaTT, tvTenTT,tvChucvu;
    ImageView imgDel;


    public ThuThuAdapter(@NonNull Context context, NhanVienFragment fragment, @NonNull ArrayList<ThuThu> list) {
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
            v = inflater.inflate(R.layout.nhan_vien_item, null);
        }
        final ThuThu item = list.get(position);
        if (item != null) {
            tvMaTT = v.findViewById(R.id.tvMaTT);
            tvMaTT.setText("Mã nhân viên: " + item.getMaTT());

            tvTenTT = v.findViewById(R.id.tvTenTT);
            tvTenTT.setText("Tên nhân viên: " + item.getHoTen());

//            tvChucvu= v.findViewById(R.id.tvChucvu);
//            tvChucvu.setText("Chức vụ: " + item.getPhanQuyen());

            imgDel = v.findViewById(R.id.imgDeleteTT);
            imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.xoa(String.valueOf(item.getMaTT()));
                }
            });
        }
        return v;
    }
}
