package com.example.appquanlychothuesach.Adapter.Search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquanlychothuesach.Activity.DetailBookActivity;
import com.example.appquanlychothuesach.R;
import com.example.appquanlychothuesach.model.Sach;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> implements Filterable {
    private ArrayList<Sach> listSach;
    private Context context;
    private OnItemClickListener monItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        monItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Sach product);
    }

    public SearchAdapter(ArrayList<Sach> listSach, Context context) {
        this.listSach = listSach;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //nạp layout cho view biểu diễn phần tử user
        View userView = inflater.inflate(R.layout.sach_item_search, parent, false);
        //
        SearchViewHolder viewHolder = new SearchViewHolder(userView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Sach item = listSach.get(position);
        holder.maSach.setText(String.valueOf(item.getMaSach()));
        holder.tenSach.setText(item.getTenSach());
        holder.gia.setText(String.valueOf(item.getGiaThue()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (monItemClickListener != null) {
                    monItemClickListener.onItemClick(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSach.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                ArrayList<Sach> filteredList = new ArrayList<>();
                if (strSearch.isEmpty()) {
                    filteredList.addAll(listSach);
                } else {
                    for (Sach product : listSach) {
                        String maSach = String.valueOf(product.getMaSach());
                        String gia = String.valueOf(product.getGiaThue());

                        if (product.getTenSach().toLowerCase().contains(strSearch.toLowerCase()) ||
                                maSach.toLowerCase().contains(strSearch.toLowerCase()) ||
                                gia.toLowerCase().contains(strSearch.toLowerCase())) {
                            filteredList.add(product);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listSach.clear();
                listSach.addAll((ArrayList<Sach>) results.values);
                notifyDataSetChanged();
            }
        };
    }


    class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView maSach, tenSach, gia;
        LinearLayout itemBookSearch;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            maSach = itemView.findViewById(R.id.tvSearchMaSach);
            tenSach = itemView.findViewById(R.id.tvSearchTenSach);
            gia = itemView.findViewById(R.id.tvSearchGia);
            itemBookSearch = itemView.findViewById(R.id.itemBookSearch); // Initialize itemBookSearch

            itemBookSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Sach clickedItem = listSach.get(position);
                        OnclickgotoitemSearch(clickedItem);
                    }
                }
            });

        }
    }

    public void OnclickgotoitemSearch(Sach item) {
        Intent intent = new Intent(context, DetailBookActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("sach", item);
        intent.putExtras(args);
        context.startActivity(intent);
    }

    public void setData(ArrayList<Sach> filteredList) {
        this.listSach = filteredList;
        notifyDataSetChanged();
    }
}
//    public void OnclickgotoitemSearch(Sach item) {
//        Intent intent = new Intent(context, DetailBookActivity.class);
//        Bundle args = new Bundle();
//        args.putSerializable("sach", item);
//        intent.putExtras(args);
//        context.startActivity(intent);
//    }

