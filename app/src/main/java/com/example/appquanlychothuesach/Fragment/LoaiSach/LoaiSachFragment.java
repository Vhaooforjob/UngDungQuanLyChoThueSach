package com.example.appquanlychothuesach.Fragment.LoaiSach;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.appquanlychothuesach.Activity.SearchActivity.SearchloaiActivity;
import com.example.appquanlychothuesach.Adapter.LoaiSachAdapter;
import com.example.appquanlychothuesach.DAO.LoaiSachDAO;
import com.example.appquanlychothuesach.R;
import com.example.appquanlychothuesach.model.LoaiSach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LoaiSachFragment extends Fragment {
    ListView lvLS;
    LoaiSachAdapter adapter;

    Dialog dialog;
    EditText edMaLS, edTenLS;
    Button btnSavels, btnCancells;
    ArrayList<LoaiSach> list;
    static LoaiSachDAO dao;
    LoaiSach item;
    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_loai_sach, container, false);
        lvLS = v.findViewById(R.id.lvLoaiSach);
        fab = v.findViewById(R.id.fabLS);
        v.findViewById(R.id.tvSearch).setOnClickListener(view -> {
            startActivity(new Intent(getContext(), SearchloaiActivity.class));
        });
        dao = new LoaiSachDAO(getActivity());
        capNhatLV();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvLS.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        return v;
    }

    void capNhatLV() {
        list = (ArrayList<LoaiSach>) dao.getAll();
        adapter = new LoaiSachAdapter(getActivity(), this, list);
        lvLS.setAdapter(adapter);
    }

    public void xoa(final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không ?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.detele(id);
                Toast.makeText(getContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
                capNhatLV();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }

    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.loai_sach_dialog);

        edMaLS = dialog.findViewById(R.id.edMaLS);
        edTenLS = dialog.findViewById(R.id.edTenLS);

        btnSavels = dialog.findViewById(R.id.btnSaveLS);
        btnCancells = dialog.findViewById(R.id.btnCancelLS);

        edMaLS.setEnabled(false);

        if (type != 0) {
            edMaLS.setText(String.valueOf(item.getMaLoai()));
            edTenLS.setText(String.valueOf(item.getTenLoai()));
        }

        btnCancells.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSavels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new LoaiSach();
                item.setTenLoai(edTenLS.getText().toString());

                if (validate() > 0) {
                    if (type == 0) {
                        if (dao.insert(item) > 0) {
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMaLoai(Integer.parseInt(edMaLS.getText().toString()));
                        if (dao.update(item) > 0) {
                            Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLV();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public int validate() {
        int check = 1;
        if (edTenLS.getText().length() == 0) {
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}
