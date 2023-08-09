package com.example.appquanlychothuesach.Activity.SearchActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.appquanlychothuesach.Adapter.Search.SearchAdapter;
import com.example.appquanlychothuesach.DAO.SachDAO;
import com.example.appquanlychothuesach.Activity.DetailBookActivity;
import com.example.appquanlychothuesach.R;
import com.example.appquanlychothuesach.model.Sach;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView rvList;
    private ArrayList<Sach> listSach;
    private SearchAdapter adapter;
    private SearchView searchView;
    static SachDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        rvList = findViewById(R.id.rvSearch);
        searchView = findViewById(R.id.svSearch);

        searchView.requestFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Sach> filteredList = filter(listSach, newText);
                adapter.setData(filteredList);
                return true;
            }
        });

        dao = new SachDAO(this);
        listSach = (ArrayList<Sach>) dao.getAll();
        adapter = new SearchAdapter(listSach, this);
        adapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Sach product) {
                openDetailActivity(product);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(linearLayoutManager);
    }

    private ArrayList<Sach> filter(ArrayList<Sach> listSach, String query) {
        query = query.toLowerCase().trim();
        final ArrayList<Sach> filteredList = new ArrayList<>();
        for (Sach product : listSach) {
            String maSach = String.valueOf(product.getMaSach());
            String gia = String.valueOf(product.getGiaThue());

            if (product.getTenSach().toLowerCase().contains(query) ||
                    maSach.toLowerCase().contains(query) ||
                    gia.toLowerCase().contains(query)) {
                filteredList.add(product);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "Không tìm thấy bất kì sản phẩm nào", Toast.LENGTH_SHORT).show();
        }
        return filteredList;
    }

    private void openDetailActivity(Sach product) {
        Intent intent = new Intent(this, DetailBookActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("maSach", product.getMaSach());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
