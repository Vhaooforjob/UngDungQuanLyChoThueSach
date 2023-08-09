package com.example.appquanlychothuesach.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appquanlychothuesach.R;
import com.example.appquanlychothuesach.model.Top;
import com.example.appquanlychothuesach.Fragment.topTen.TopTenFragment;

import java.util.ArrayList;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    TopTenFragment topFragment;
    ArrayList<Top> lists;
    TextView tvSach,tvSoLuong;
    ImageView imDel;

    public TopAdapter(@NonNull Context context, TopTenFragment topFragment, ArrayList<Top> lists) {
        super(context, 0,lists);
        this.context = context;
        this.topFragment = topFragment;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v ==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.top_item,null);
        }
        final  Top item = lists.get(position);
        if (item != null){
            tvSach = v.findViewById(R.id.tvSach);
            tvSach.setText("Tên sách: "+item.getTenSach());

            tvSoLuong = v.findViewById(R.id.tvSL);
            tvSoLuong.setText("Số lượng: "+item.getSoLuong());
        }
        return v;
    }
}
