package com.example.appquanlychothuesach.Fragment.topTen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.appquanlychothuesach.Adapter.TopAdapter;
import com.example.appquanlychothuesach.DAO.PhieuMuonDAO;
import com.example.appquanlychothuesach.R;
import com.example.appquanlychothuesach.model.Top;

import java.util.ArrayList;


public class TopTenFragment extends Fragment {
ListView lv;
ArrayList<Top> list;
TopAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View v =inflater.inflate(R.layout.fragment_top_ten, container, false);
        lv = v.findViewById(R.id.lvTop);
        PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(getActivity());
        list = (ArrayList<Top>) phieuMuonDAO.getTop();
     adapter = new TopAdapter(getActivity(),this,list);
     lv.setAdapter(adapter);
      return v;
    }
}