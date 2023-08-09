package com.example.appquanlychothuesach.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appquanlychothuesach.R;
import com.example.appquanlychothuesach.model.KhachHang;

import java.util.ArrayList;

public class KhachHangSpinerAdapter extends ArrayAdapter<KhachHang> {
    private Context context;
    private ArrayList<KhachHang> lists;
    TextView tvMaTV,tvTenTV;
    public KhachHangSpinerAdapter(@NonNull Context context, ArrayList<KhachHang> lists) {
        super(context,0, lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =convertView;
        if (v ==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.khachhang_item_spinner,null);

        }
        final KhachHang item = lists.get(position);
        if (item !=null){
            tvMaTV = v.findViewById(R.id.tvMaTVSp);
            tvMaTV.setText(item.getMaKH()+".");
            tvTenTV = v.findViewById(R.id.tvTenTVSp);
            tvTenTV.setText(item.getHoTen());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =convertView;
        if (v ==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.khachhang_item_spinner,null);

        }
        final KhachHang item = lists.get(position);
        if (item !=null){
            tvMaTV = v.findViewById(R.id.tvMaTVSp);
            tvMaTV.setText(item.getMaKH()+".");
            tvTenTV = v.findViewById(R.id.tvTenTVSp);
            tvTenTV.setText(item.getHoTen());
        }
        return v;
    }
}
