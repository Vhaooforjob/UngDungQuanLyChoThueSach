package com.example.appquanlychothuesach.Fragment.KhachHang;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.appquanlychothuesach.Adapter.KhachHangAdapter;
import com.example.appquanlychothuesach.DAO.KhachHangDAO;
import com.example.appquanlychothuesach.R;
import com.example.appquanlychothuesach.model.KhachHang;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class KhachHangFragment extends Fragment {
    ListView lvKhachHang;
    ArrayList<KhachHang> list;
    static KhachHangDAO dao;
    KhachHangAdapter adapter;
    KhachHang item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaTV, edTenTV, ednamSinh,edCCCD,edAnh;
    Button btnsave, btncancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_khach_hang, container, false);

        lvKhachHang = v.findViewById(R.id.lvKhachHang);
        fab = v.findViewById(R.id.fabTV);
        dao = new KhachHangDAO(getActivity());
        capNhatLv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);//bang = thi insert
            }
        });
        lvKhachHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                item = list.get(position);
                openDialog(getActivity(), 1);//=1 thi update

                return false;
            }
        });
        return v;
    }

    void capNhatLv() {
        list = (ArrayList<KhachHang>) dao.getAll();
        adapter = new KhachHangAdapter(getActivity(), this, list);
        lvKhachHang.setAdapter(adapter);
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
        dialog.setContentView(R.layout.khach_hang_dialog);

        edMaTV = dialog.findViewById(R.id.edMaTV);
        edCCCD = dialog.findViewById(R.id.edCCCD);
        edTenTV = dialog.findViewById(R.id.edTenTV);
        ednamSinh = dialog.findViewById(R.id.edNamSinh);
        btncancel = dialog.findViewById(R.id.btnCancelTV);
        btnsave = dialog.findViewById(R.id.btnSaveTV);
//        edAnh = dialog.findViewById(R.id.edAnh);
        edMaTV.setEnabled(false);
        if (type != 0) {
            //cap nhat
            edMaTV.setText(String.valueOf(item.getMaKH()));
            edCCCD.setText(String.valueOf(item.getCccd()));
            edTenTV.setText(String.valueOf(item.getHoTen()));
            ednamSinh.setText(String.valueOf(item.getNamSinh()));
//            item.setAnhTV(edAnh.getText().toString());
        }
        // Thêm InputFilter cho edCCCD
        InputFilter inputFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isDigit(source.charAt(i))) {
                        return ""; // Loại bỏ các ký tự không phải số
                    }
                }
                return null;
            }
        };
        edCCCD.setFilters(new InputFilter[] { inputFilter });
        // Thêm TextWatcher cho edCCCD
        edCCCD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Không cần xử lý trước sự thay đổi văn bản
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Không cần xử lý khi văn bản thay đổi
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String cccd = editable.toString();
                if (cccd.length() != 12 || !cccd.matches("[0-9]+")) {
                    // Hiển thị thông báo lỗi khi CCCD không hợp lệ
                    edCCCD.setError("CCCD phải chứa đúng 12 số");
                } else {
                    // Xóa thông báo lỗi nếu CCCD hợp lệ
                    edCCCD.setError(null);
                }
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cccd = edCCCD.getText().toString();
                // Kiểm tra xem CCCD đã tồn tại trong CSDL chưa
                KhachHang existingKhachHang = dao.getKhachHangByCCCD(cccd);
                if (existingKhachHang != null) {
                    Toast.makeText(getContext(), "Khách hàng với CCCD đã tồn tại", Toast.LENGTH_SHORT).show();
                    return;
                }

                item = new KhachHang();
                item.setCccd(cccd);
                item.setHoTen(edTenTV.getText().toString());
                item.setNamSinh(ednamSinh.getText().toString());

                if (validate(item)) {
                    if (type == 0) {
                        if (dao.insert(item) > 0) {
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // ... (các dòng code khác)
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }

    public boolean validate(KhachHang khachHang) {
        if (khachHang.getHoTen().isEmpty() || khachHang.getNamSinh().isEmpty() || khachHang.getCccd().isEmpty()) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public int validate() {
        int check = 1;
        if (edTenTV.getText().length() == 0 || ednamSinh.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            KhachHang existingKhachHang = dao.getKhachHangByCCCD(edCCCD.getText().toString());
            if (existingKhachHang != null) {
                Toast.makeText(getContext(), "Khách hàng với CCCD đã tồn tại", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}