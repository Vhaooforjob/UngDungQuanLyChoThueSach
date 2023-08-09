package com.example.appquanlychothuesach.Activity.SearchActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.appquanlychothuesach.Adapter.Search.SearchLoaiAdapter;
import com.example.appquanlychothuesach.DAO.LoaiSachDAO;
import com.example.appquanlychothuesach.Activity.DetailBookActivity;
import com.example.appquanlychothuesach.R;
import com.example.appquanlychothuesach.model.LoaiSach;

import java.util.ArrayList;

public class SearchloaiActivity extends AppCompatActivity {
    private RecyclerView rvList;
    private ArrayList<LoaiSach> listLoaiSach;
    private SearchLoaiAdapter adapter;
    private SearchView searchView;
    static LoaiSachDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchloai);

        rvList=findViewById(R.id.rvSearch);
        searchView = findViewById(R.id.svSearch);

        searchView.requestFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<LoaiSach> filteredList = filter(listLoaiSach,newText);
                adapter.setData(filteredList);
                return true;
            }
        });


        dao = new LoaiSachDAO(this);
        listLoaiSach=(ArrayList<LoaiSach>) dao.getAll();
        adapter = new SearchLoaiAdapter(listLoaiSach,this);
        adapter.setOnItemClickListener(new SearchLoaiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(LoaiSach product) {
                openDetailActivity(product);
            }
        });

        //lay du lieu
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(linearLayoutManager);
    }

    private ArrayList<LoaiSach> filter(ArrayList<LoaiSach> listSach, String query) {
        query = query.toLowerCase().trim();
        final ArrayList<LoaiSach> filteredList = new ArrayList<>();
        for (LoaiSach product : listSach) {
            if (String.valueOf(product.getMaLoai()).toLowerCase().contains(query) ||
                    product.getTenLoai().toLowerCase().contains(query)) {
                filteredList.add(product);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "Không tìm thấy bất kì sản phẩm nào", Toast.LENGTH_SHORT).show();
        }
        return filteredList;
    }

    private void openDetailActivity(LoaiSach product) {
        Intent intent = new Intent(this, DetailBookActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("maLoai", product.getMaLoai());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}