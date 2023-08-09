package com.example.appquanlychothuesach.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.appquanlychothuesach.DAO.ThuThuDAO;
import com.example.appquanlychothuesach.R;
import com.example.appquanlychothuesach.model.ThuThu;

public class ProfileActivity extends AppCompatActivity {

    ThuThuDAO thuThuDAO;
    private TextView username;
    private TextView tvUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvUser = findViewById(R.id.tvUser);
        username = findViewById(R.id.username);
        Intent intent = getIntent();
//        String user = intent.getStringExtra("user");
//        thuThuDAO = new ThuThuDAO(this);
//        ThuThu thuThu = thuThuDAO.getID(user);
//        String username1 = thuThu.getHoTen();
//        username.setText(username1);
        tvUser.setText("Welcome" + "!");
    }
}