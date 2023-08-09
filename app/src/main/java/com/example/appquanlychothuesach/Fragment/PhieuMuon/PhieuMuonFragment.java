package com.example.appquanlychothuesach.Fragment.PhieuMuon;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.appquanlychothuesach.Adapter.PhieuMuonAdapter;
import com.example.appquanlychothuesach.Adapter.SachPsinerAdapter;
import com.example.appquanlychothuesach.Adapter.KhachHangSpinerAdapter;
import com.example.appquanlychothuesach.DAO.PhieuMuonDAO;
import com.example.appquanlychothuesach.DAO.SachDAO;
import com.example.appquanlychothuesach.DAO.KhachHangDAO;
import com.example.appquanlychothuesach.R;
import com.example.appquanlychothuesach.model.PhieuMuon;
import com.example.appquanlychothuesach.model.Sach;
import com.example.appquanlychothuesach.model.KhachHang;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class PhieuMuonFragment extends Fragment {
    ListView lvPhieuMuon;
    ArrayList<PhieuMuon> list;
    static PhieuMuonDAO dao;
    PhieuMuonAdapter adapter;
    PhieuMuon item;

    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaPM;
    Spinner spTV, spSach;
    TextView tvNgay, tvTienThue;
    CheckBox chkTraSach;
    Button btnSave, btnCancel;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    KhachHangSpinerAdapter khachHangSpinerAdapter;
    ArrayList<KhachHang> listKhachHang;
    KhachHangDAO khachHangDAO;
    KhachHang khachHang;
    int maKH;

    SachPsinerAdapter sachPsinerAdapter;
    ArrayList<Sach> listSach;
    SachDAO sachDAO;
    Sach sach;
    int maSach, tienThue;
    int positionTV, positionSach;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        lvPhieuMuon = v.findViewById(R.id.lvPhieuMuon);
        fab = v.findViewById(R.id.fabPM);
        dao = new PhieuMuonDAO(getActivity());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvPhieuMuon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1); // update
                return false;
            }
        });
        capNhatLv();

        return v;
    }

    void capNhatLv() {
        list = (ArrayList<PhieuMuon>) dao.getAll();
        adapter = new PhieuMuonAdapter(getActivity(), this, list);
        lvPhieuMuon.setAdapter(adapter);
    }

    protected void openDialog(final Context context, final int type) {
        //custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.phieu_muon_dialog);
        edMaPM = dialog.findViewById(R.id.edMaPM);
        spTV = dialog.findViewById(R.id.spMaTV);
        spSach = dialog.findViewById(R.id.spMaSach);
        tvNgay = dialog.findViewById(R.id.tvNgay);
        tvTienThue = dialog.findViewById(R.id.tvTienThue);
        chkTraSach = dialog.findViewById(R.id.chkTraSach);
        btnCancel = dialog.findViewById(R.id.btnCancelPM);
        btnSave = dialog.findViewById(R.id.btnSavePM);
        //set ngay thue,mac dinh ngay hien hanh
        tvNgay.setText("Ngày Thuê: " + sdf.format(new Date()));
        edMaPM.setEnabled(false);
        khachHangDAO = new KhachHangDAO(context);
        listKhachHang = new ArrayList<KhachHang>();
        listKhachHang = (ArrayList<KhachHang>) khachHangDAO.getAll();
        khachHangSpinerAdapter = new KhachHangSpinerAdapter(context, listKhachHang);
        spTV.setAdapter(khachHangSpinerAdapter);
        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maKH = listKhachHang.get(position).getMaKH();
                // Toast.makeText(context, "Chọn"+listThanhVien.get(position).getHoTen(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sachDAO = new SachDAO(context);
        listSach = new ArrayList<Sach>();
        listSach = (ArrayList<Sach>) sachDAO.getAll();
        sachPsinerAdapter = new SachPsinerAdapter(context, listSach);
        spSach.setAdapter(sachPsinerAdapter);
        // lay maLoaiSach
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = listSach.get(position).getMaSach();
                tienThue = listSach.get(position).getGiaThue();
                tvTienThue.setText("Tiền Thuê: " + tienThue);
                //  Toast.makeText(context, "Chọn"+listSach.get(position).getTenSach(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //edti set data nen form
        if (type != 0) {
            edMaPM.setText(String.valueOf(item.getMaPM()));
            for (int i = 0; i < listKhachHang.size(); i++)
                if (item.getMaKH() == (listKhachHang.get(i).getMaKH())) {
                    positionTV = i;
                }
            spTV.setSelection(positionTV);

            for (int i = 0; i < listSach.size(); i++)
                if (item.getMaSach() == (listSach.get(i).getMaSach())) {
                    positionSach = i;
                }
            spSach.setSelection(positionSach);
            tvNgay.setText("Ngày Thuê:" + sdf.format(item.getNgay()));
            tvTienThue.setText("Tiền Thuê:" + item.getTienThue());
            if (item.getTraSach() == 1) {
                chkTraSach.setChecked(true);
            } else {
                chkTraSach.setChecked(false);
            }
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
                item = new PhieuMuon();
                item.setMaSach(maSach);
                int soluong = sachDAO.getSoluong(String.valueOf(maSach));
                item.setMaKH(maKH);
                item.setNgay(new Date());
                item.setTienThue(tienThue);


                if (chkTraSach.isChecked()) {
                    Sach sach = sachDAO.getID(String.valueOf(maSach));
                    sach.setSoluong(soluong + 1);
                    sachDAO.update(sach);
                    item.setTraSach(1);

                } else {
                    if (soluong == 0) {
                        Toast.makeText(context, "Số lượng sách trong kho đã hết", Toast.LENGTH_SHORT).show();
                    } else {
                        if (type == 0) {
                            //type = 0(ínert)
                            if (dao.insert(item) > 0) {
                                Toast.makeText(context, "Thêm Thành Công Số lượng sách trong kho còn " + String.valueOf(soluong - 1), Toast.LENGTH_SHORT).show();
                                Sach sach = sachDAO.getID(String.valueOf(maSach));
                                sach.setSoluong(soluong - 1);
                                sachDAO.update(sach);
                            } else {
                                Toast.makeText(context, "Thêm Thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            //type = 1(update)
                            item.setMaPM((Integer.parseInt(edMaPM.getText().toString())));
                            if (dao.update(item) > 0) {
                                Toast.makeText(context, "Sửa Thành Công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "sửa Thất bại", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }

                }
                if (type == 1) {
                    //type = 1(update)
                    item.setMaPM((Integer.parseInt(edMaPM.getText().toString())));
                    if (dao.update(item) > 0) {
                        Toast.makeText(context, "Sửa Thành Công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "sửa Thất bại", Toast.LENGTH_SHORT).show();

                    }
                }

                capNhatLv();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void xoa(final String Id) {
        //Sử dụng Alẻt
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao.detele(Id);
                        Toast.makeText(getContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                        capNhatLv();
                        dialog.cancel();
                    }
                });
        builder.setNegativeButton("No"
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        builder.show();
    }
}