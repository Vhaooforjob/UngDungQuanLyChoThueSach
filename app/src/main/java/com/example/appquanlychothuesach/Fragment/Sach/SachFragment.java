package com.example.appquanlychothuesach.Fragment.Sach;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.appquanlychothuesach.Adapter.LoaiSachSpinnerAdapter;
import com.example.appquanlychothuesach.Adapter.SachAdapter;
import com.example.appquanlychothuesach.DAO.LoaiSachDAO;
import com.example.appquanlychothuesach.DAO.SachDAO;
import com.example.appquanlychothuesach.R;
import com.example.appquanlychothuesach.Activity.SearchActivity.SearchActivity;
import com.example.appquanlychothuesach.model.LoaiSach;
import com.example.appquanlychothuesach.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;


public class SachFragment extends Fragment {
    ListView lvSach;
    SachDAO sachDAO;
    SachAdapter adapter;
    Sach item;
    List<Sach> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaSach, edTenSach, edGiaThue, edNhacc, etSoluong,edLinkAnh,edSL;
    Spinner spinner;
    Button btnSave, btnCancel;

    private ShapeableImageView shapeableImageView;
    LoaiSachSpinnerAdapter spinnerAdapter;
    ArrayList<LoaiSach> listLoaiSach;
    LoaiSachDAO loaiSachDAO;
    LoaiSach loaiSach;
    int maLoaiSach, position;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sach, container, false);
        lvSach = v.findViewById(R.id.lvSach);
        sachDAO = new SachDAO(getActivity());
//        shapeableImageView = v.findViewById(R.id.viewImg);

        capNhatLv();
        fab = v.findViewById(R.id.fabSach);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        v.findViewById(R.id.tvSearch).setOnClickListener(view -> {
            startActivity(new Intent(getContext(), SearchActivity.class));
        });
        lvSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        return v;
    }

    void capNhatLv() {

        list = (List<Sach>) sachDAO.getAll();
        for (Sach sach : list) {
            if (sach.getSoluong() == 0) {
                sachDAO.detele(String.valueOf(sach.getMaSach()));
            }
        }
        adapter = new SachAdapter(getActivity(), this, list);
        lvSach.setAdapter(adapter);
    }

    public void xoa(final String Id) {
        //Su dung Alert
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sachDAO.detele(Id);
                        Toast.makeText(getContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                        capNhatLv();
                        dialog.cancel();
                    }
                });
        builder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        builder.show();
    }

    protected void openDialog(final Context context, final int type) {
        //custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.sach_dialog);
        edMaSach = dialog.findViewById(R.id.edMaSach);
        edTenSach = dialog.findViewById(R.id.edTenSach);
        edGiaThue = dialog.findViewById(R.id.edGiaThue);
        edNhacc = dialog.findViewById(R.id.edNhacc);
        spinner = dialog.findViewById(R.id.spLoaiSach);
        btnCancel = dialog.findViewById(R.id.btnCancelSach);
        btnSave = dialog.findViewById(R.id.btnSaveSach);
        edLinkAnh = dialog.findViewById(R.id.edLinkAnh);
        edSL = dialog.findViewById(R.id.edSL);
//        shapeableImageView = dialog.findViewById(R.id.viewImg);
//        Glide.with(context)
//                .load(item.getImg())
//                .into(shapeableImageView);
        listLoaiSach = new ArrayList<LoaiSach>();
        loaiSachDAO = new LoaiSachDAO(context);
        listLoaiSach = (ArrayList<LoaiSach>) loaiSachDAO.getAll();
        spinnerAdapter = new LoaiSachSpinnerAdapter(context, listLoaiSach);
        spinner.setAdapter(spinnerAdapter);
        //lay maLoaiSach
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSach = listLoaiSach.get(position).getMaLoai();
                Toast.makeText(context, "Chọn" + listLoaiSach.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //kiem tra type insert 0 hay Update 1
        edMaSach.setEnabled(false);
        if (type != 0) {
            edMaSach.setText(String.valueOf(item.getMaSach()));
            edTenSach.setText(item.getTenSach());
            edGiaThue.setText(String.valueOf(item.getGiaThue()));
            edNhacc.setText(String.valueOf(item.getMaLoai()));
            edSL.setText(String.valueOf(item.getSoluong()));
            edLinkAnh.setText(String.valueOf(item.getImg()));
            for (int i = 0; i < listLoaiSach.size(); i++)
                if (item.getMaLoai() == (listLoaiSach.get(i).getMaLoai())) {
                    position = i;
                }
            Log.i("demo", "posSach" + position);
            spinner.setSelection(position);
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new Sach();
                item.setTenSach(edTenSach.getText().toString());
                item.setGiaThue(Integer.parseInt(edGiaThue.getText().toString()));
                item.setNhacungcap(edNhacc.getText().toString());
                item.setMaLoai(maLoaiSach);
                item.setSoluong(Integer.parseInt(edSL.getText().toString()));
                String imageLink = edLinkAnh.getText().toString();
                item.setImg(imageLink);
                // Kiểm tra số lượng sách có âm hay không
                int soLuong = Integer.parseInt(edSL.getText().toString());
                if (soLuong < 0) {
                    // Hiển thị thông báo lỗi khi số lượng âm
                    edSL.setError("Số lượng sách không được âm");
                    return;
                }
                else if(soLuong == 0)
                {
                    edSL.setError("số lượng sách không được bằng 0");
                }
                item.setSoluong(soLuong);
                if (validate() > 0) {
                    if (type == 0) {
                        //type = (insert)
                        if (sachDAO.insert(item) > 0) {
                            Toast.makeText(context, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        //type =1(update)
                        item.setMaSach(Integer.parseInt(edMaSach.getText().toString()));
                        if (sachDAO.update(item) > 0) {
                            Toast.makeText(context, "Sửa Thành Công", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(context, "Sửa Thất Bại", Toast.LENGTH_SHORT).show();

                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public int validate() {
        int check = 1;
        if (edTenSach.getText().length() == 0 || edGiaThue.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        else if (Integer.parseInt(edSL.getText().toString()) == 0) {
            Toast.makeText(getContext(), "Số lượng sách không được bằng 0", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    public void capNhatSoluong(String idSach) {
        //custom dialog
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.capnhat_dialog);
        etSoluong = dialog.findViewById(R.id.etSoluong);
        btnCancel = dialog.findViewById(R.id.btnCancelSach);
        btnSave = dialog.findViewById(R.id.btnSaveSach);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soluongStr = etSoluong.getText().toString();
                if (!soluongStr.isEmpty()) {
                    int soluong = Integer.parseInt(soluongStr);
                    if (soluong < 0) {
                        Toast.makeText(getContext(), "Số lượng sách không được âm", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        return;
                    }
                    if (!list.isEmpty()) { // Kiểm tra danh sách có rỗng hay không
                        Sach sach = sachDAO.getID(idSach);
                        int oldCount = sach.getSoluong();
                        sach.setSoluong(oldCount + soluong);
                        sachDAO.update(sach);
                        capNhatLv();
                        Toast.makeText(getContext(), "Cập nhật số lượng thành công", Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                } else {
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }


}