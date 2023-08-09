package com.example.appquanlychothuesach.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquanlychothuesach.R;
import com.example.appquanlychothuesach.model.Sach;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.DonHangViewHolder> {

    private Context context;
    private List<Sach> list;
    private Map<Sach, Integer> numbers;

    public DonHangAdapter(Context context, List<Sach> list) {
        this.context = context;
        this.list = list;

        numbers = new HashMap<>();

        for (Sach sach : list) {
            numbers.put(sach, 0);
        }
    }

    @NonNull
    @Override
    public DonHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.donhang_item, parent, false);
        return new DonHangViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DonHangViewHolder holder, int position) {
        Sach sach = list.get(position);

        holder.sach = sach;
        holder.txtTitle.setText(sach.getTenSach());
        holder.txtPrice.setText(sach.getGiaThue() + "đ");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class DonHangViewHolder extends RecyclerView.ViewHolder {

        TextView txtX, txtTitle, txtNumber, txtPrice;
        ImageView imgPoster;
        ImageButton btnIncrease, btnDecrease;
        Sach sach;

        @SuppressLint("SetTextI18n")
        public DonHangViewHolder(@NonNull View itemView) {
            super(itemView);

            txtX = itemView.findViewById(R.id.txt_x);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtNumber = itemView.findViewById(R.id.txt_number);
            txtPrice = itemView.findViewById(R.id.txt_price);
            imgPoster = itemView.findViewById(R.id.img_poster);
            btnIncrease = itemView.findViewById(R.id.btn_increase);
            btnDecrease = itemView.findViewById(R.id.btn_decrease);

            btnIncrease.setOnClickListener(view -> {
                Integer value = numbers.get(sach);
                if (value == null) {
                    numbers.put(sach, 0);
                    txtX.setText(0 + "x");
                    txtNumber.setText(String.valueOf(0));
                } else if (value < sach.getSoluong()) {
                    numbers.put(sach, value + 1);
                    txtX.setText((value + 1) + "x");
                    txtNumber.setText(String.valueOf(value + 1));
                }
            });

            btnDecrease.setOnClickListener(view -> {
                Integer value = numbers.get(sach);
                if (value == null) {
                    numbers.put(sach, 0);
                    txtX.setText(0 + "x");
                    txtNumber.setText(String.valueOf(0));
                } else if (value > 0) {
                    numbers.put(sach, value - 1);
                    txtX.setText((value - 1) + "x");
                    txtNumber.setText(String.valueOf(value - 1));
                }
            });
        }
    }
}
