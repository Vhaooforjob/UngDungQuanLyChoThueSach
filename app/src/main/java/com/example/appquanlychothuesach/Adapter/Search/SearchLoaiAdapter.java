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
import com.example.appquanlychothuesach.model.LoaiSach;

import java.util.ArrayList;

public class SearchLoaiAdapter extends RecyclerView.Adapter<SearchLoaiAdapter.SearchViewHolder> implements Filterable {
    private ArrayList<LoaiSach> listLoaiSach;
    private ArrayList<LoaiSach> listLoaiSachFiltered;
    private Context context;
    private OnItemClickListener monItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        monItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(LoaiSach product);
    }

    public SearchLoaiAdapter(ArrayList<LoaiSach> listLoaiSach, Context context) {
        this.listLoaiSach = listLoaiSach;
        this.listLoaiSachFiltered = listLoaiSach;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View userView = inflater.inflate(R.layout.loaisach_item_search, parent, false);
        SearchViewHolder viewHolder = new SearchViewHolder(userView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        LoaiSach item = listLoaiSachFiltered.get(position);
        holder.maLoai.setText(String.valueOf(item.getMaLoai()));
        holder.tenLoai.setText(item.getTenLoai());
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
        return listLoaiSachFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                ArrayList<LoaiSach> filteredList = new ArrayList<>();
                if (strSearch.isEmpty()) {
                    filteredList.addAll(listLoaiSach);
                } else {
                    for (LoaiSach product : listLoaiSach) {
                        if (product.getTenLoai().toLowerCase().contains(strSearch.toLowerCase())) {
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
                listLoaiSachFiltered.clear();
                listLoaiSachFiltered.addAll((ArrayList<LoaiSach>) results.values);
                notifyDataSetChanged();
            }
        };
    }
    public void filterByMaLoai(String maLoai) {
        listLoaiSachFiltered.clear();
        if (maLoai.isEmpty()) {
            listLoaiSachFiltered.addAll(listLoaiSach);
        } else {
            for (LoaiSach product : listLoaiSach) {
                if (String.valueOf(product.getMaLoai()).toLowerCase().contains(maLoai.toLowerCase())) {
                    listLoaiSachFiltered.add(product);
                }
            }
        }
        notifyDataSetChanged();
    }
    class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView maLoai, tenLoai;
        LinearLayout itemBookCateSearch;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            maLoai = itemView.findViewById(R.id.tvSearchMaLoai);
            tenLoai = itemView.findViewById(R.id.tvSearchTenLoai);
            itemBookCateSearch = itemView.findViewById(R.id.itemBookCateSearch);

            itemBookCateSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        LoaiSach clickedItem = listLoaiSachFiltered.get(position);
                        OnclickgotoitemSearch(clickedItem);
                    }
                }
            });
        }
    }

    public void OnclickgotoitemSearch(LoaiSach item) {
        Intent intent = new Intent(context, DetailBookActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("loaisach", item);
        intent.putExtras(args);
        context.startActivity(intent);
    }

    public void setData(ArrayList<LoaiSach> filteredList) {
        this.listLoaiSachFiltered = filteredList;
        notifyDataSetChanged();
    }
}
