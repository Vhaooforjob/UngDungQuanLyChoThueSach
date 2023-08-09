package com.example.appquanlychothuesach.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.appquanlychothuesach.DAO.SachDAO;
import com.example.appquanlychothuesach.R;
import com.example.appquanlychothuesach.model.Sach;

public class DetailBookActivity extends AppCompatActivity {
    private int proID;
    private TextView tvMaSach, tvTenSach, tvGiaThue, tvLoai, tvNhacungcap, tvSoluong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);

        // Ánh xạ các thành phần giao diện
        tvMaSach = findViewById(R.id.tvMaSach);
        tvTenSach = findViewById(R.id.tvTenSach);
        tvGiaThue = findViewById(R.id.tvGiaThue);
        tvLoai = findViewById(R.id.tvLoai);
        tvNhacungcap = findViewById(R.id.tvGNhacungcap);
        tvSoluong = findViewById(R.id.tvSoluong);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            proID = b.getInt("maSach");

            // Truy vấn thông tin sách từ cơ sở dữ liệu (hoặc danh sách đã có)
            SachDAO dao = new SachDAO(this);
            Sach sach = dao.getID(String.valueOf(proID));

            // Hiển thị thông tin lên giao diện
            if (sach != null) {
                tvMaSach.setText("Mã sách: " + sach.getMaSach());
                tvTenSach.setText("Tên sách: " + sach.getTenSach());
                tvGiaThue.setText("Giá thuê: " + sach.getGiaThue());
                tvLoai.setText("Loại: " + sach.getMaLoai());
                tvNhacungcap.setText("Nhà cung cấp: " + sach.getNhacungcap());
                tvSoluong.setText("Số lượng: " + sach.getSoluong());
                ImageView imgDeleteLS = findViewById(R.id.imgDeleteLS);
                String imgUrl = sach.getImg();
                ImageView imageView = findViewById(R.id.imgBookCover);
                Glide.with(this)
                        .load(imgUrl)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(imageView);
//                imgDeleteLS.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        int maSach = b.getInt("maSach");
//
//                        // Sử dụng phương thức deleteSach để xóa sách
//                        int rowsDeleted = SachDAO.deleteSach(String.valueOf(maSach));
//
//                        if (rowsDeleted > 0) {
//                            // Xóa thành công, thực hiện hành động cần thiết
//                            Toast.makeText(DetailBookActivity.this, "Xóa sách thành công", Toast.LENGTH_SHORT).show();
//                            // Đóng activity hoặc thực hiện các hành động khác tùy ý
//                            // Tải lại trang SearchActivity
//                            Intent intent = new Intent(DetailBookActivity.this, SearchActivity.class);
//                            startActivity(intent);
//                            finish(); // Đóng DetailBookActivity sau khi xóa
//                        } else {
//                            // Xóa thất bại hoặc không có sách để xóa
//                            Toast.makeText(DetailBookActivity.this, "Xóa sách thất bại", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
            }
        }
    }
}
