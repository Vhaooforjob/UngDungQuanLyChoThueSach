package com.example.appquanlychothuesach.Fragment.NhanVien;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.example.appquanlychothuesach.Adapter.ThuThuAdapter;
import com.example.appquanlychothuesach.DAO.ThuThuDAO;
import com.example.appquanlychothuesach.R;
import com.example.appquanlychothuesach.model.KhachHang;
import com.example.appquanlychothuesach.model.ThuThu;

import java.util.ArrayList;


public class NhanVienFragment extends Fragment {
    ListView lvNhanVien;
    ArrayList<ThuThu> list;
    static ThuThuDAO dao;
    ThuThuAdapter adapter;
    ThuThu item;

    Dialog dialog;
    EditText edMaTT, edTenTT, edPassword,edChucvu;
    Button btnsave, btncancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nhan_vien, container, false);

        lvNhanVien = v.findViewById(R.id.lvNhanVien);
        dao = new ThuThuDAO(getActivity());
        capNhatLv();

        lvNhanVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
        list = (ArrayList<ThuThu>) dao.getAll();
        adapter = new ThuThuAdapter(getActivity(), this, list);
        lvNhanVien.setAdapter(adapter);
    }

    public void xoa(final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("bạn có muốn xóa không ?");
        builder.setCancelable(true);

        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
                dao.detele(id);
                capNhatLv();
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
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.nhan_vien_dialog);

        edMaTT = dialog.findViewById(R.id.edMaTT);
        edTenTT = dialog.findViewById(R.id.edTenTT);
        edPassword = dialog.findViewById(R.id.edPassword);
//        edChucvu = dialog.findViewById(R.id.edChucvu);
        btncancel = dialog.findViewById(R.id.btnCancelTV);
        btnsave = dialog.findViewById(R.id.btnSaveTV);

        if (type != 0) {
            //cap nhat
            edMaTT.setText(String.valueOf(item.getMaTT()));
            edTenTT.setText(String.valueOf(item.getHoTen()));
//            edChucvu.setText(String.valueOf(item.getPhanQuyen()));
            edPassword.setText(String.valueOf(item.getMatKhau()));
        }
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new ThuThu();
                item.setMaTT(edMaTT.getText().toString());
                item.setHoTen(edTenTT.getText().toString());
//                item.setPhanQuyen(edChucvu.getText().toString());
                item.setMatKhau(edPassword.getText().toString());

                if (validate() > 0) {
                    if (type == 0) {
                        //==- thi them nguoi dung
                        if (dao.insert(item) > 0) {
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //khac kohn thi update data
                        item.setMaTT(String.valueOf(edMaTT.getText().toString()));
                        if (dao.update(item) > 0) {
                            Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
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
        if (edTenTT.getText().length() == 0 || edPassword.getText().length() == 0) {
            Toast.makeText(getContext(), "ban phai nhap day du thong tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check = 1;
    }
}
