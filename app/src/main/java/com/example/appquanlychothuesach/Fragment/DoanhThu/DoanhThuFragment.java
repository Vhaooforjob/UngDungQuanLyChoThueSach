package com.example.appquanlychothuesach.Fragment.DoanhThu;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.appquanlychothuesach.DAO.PhieuMuonDAO;
import com.example.appquanlychothuesach.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class DoanhThuFragment extends Fragment {
Button btnTuNgay,btnDenNgay,btnDoanhThu;
EditText edTuNgay,edDenNgay;
TextView tvDoanhThu;

SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
int mYear,mMonth,mDay;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_doanh_thu, container, false);
         edTuNgay = v.findViewById(R.id.edTuNgay);
         edDenNgay = v.findViewById(R.id.edDenNgay);
         tvDoanhThu = v.findViewById(R.id.tvDoanhThu);
         btnTuNgay = v.findViewById(R.id.btnTuNgay);
        btnDenNgay = v.findViewById(R.id.btnDenNgay);
        btnDoanhThu = v.findViewById(R.id.btnDoanhThu);
        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(),
                        0,mDateTuNgay,mYear,mMonth,mDay);
        d.show();
            }
        });
        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(),
                        0,mDateDenNgay,mYear,mMonth,mDay);
                d.show();
            }
        });
        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay = edTuNgay.getText().toString();
                String denNgay = edDenNgay.getText().toString();
                PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(getActivity());
                tvDoanhThu.setText("Doanh Thu:"+phieuMuonDAO.getDanhThu(tuNgay,denNgay)+"VNĐ");
            }
        });
        return v;
    }
    DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
            edTuNgay.setText(sdf.format(c.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
            edDenNgay.setText(sdf.format(c.getTime()));
        }
    };
}