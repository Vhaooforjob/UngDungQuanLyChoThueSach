package com.example.appquanlychothuesach.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.appquanlychothuesach.DAO.PhieuMuonDAO;
import com.example.appquanlychothuesach.DAO.ThuThuDAO;
import com.example.appquanlychothuesach.Fragment.DoanhThu.DoanhThuFragment;
import com.example.appquanlychothuesach.Fragment.DoiMk.DoiMKFragment;
import com.example.appquanlychothuesach.Fragment.LoaiSach.LoaiSachFragment;
import com.example.appquanlychothuesach.Fragment.NhanVien.NhanVienFragment;
import com.example.appquanlychothuesach.Fragment.PhieuMuon.PhieuMuonFragment;
import com.example.appquanlychothuesach.Fragment.Sach.SachFragment;
import com.example.appquanlychothuesach.Fragment.KhachHang.KhachHangFragment;
import com.example.appquanlychothuesach.Fragment.chaomung.ChaoMungFragment;
import com.example.appquanlychothuesach.Fragment.themUser.ThemUserFragment;
import com.example.appquanlychothuesach.Fragment.topTen.TopTenFragment;
import com.example.appquanlychothuesach.R;
import com.example.appquanlychothuesach.model.ThuThu;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private View headerView;
    private TextView username;
    private TextView tvUser;
    PhieuMuonDAO dao;
    ThuThuDAO thuThuDAO;
    TextView search;
    String username1;
    String user;
//    private int currentFragment = FRAGMENT_PHIEUMUON;

    private static final int FRAGMENT_PHIEUMUON = 0;
    private static final int FRAGMENT_SACH = 1;
    private static final int FRAGMENT_LOAISACH = 2;
    private static final int FRAGMENT_THANHVIEN = 3;
    private static final int FRAGMENT_TOP10 = 4;
    private static final int FRAGMENT_DOANHTHU = 5;
    private static final int FRAGMENT_THEMNGUOIDUNG = 6;
    private static final int FRAGMENT_DOIMK = 7;
    private static final int FRAGMENT_TRANGCHU = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.draw_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
//cũ
//        FragmentManager manager = getSupportFragmentManager();
//        ChaoMungFragment chaoMungFragment = new ChaoMungFragment(() -> {
//            drawerLayout.open();
//        });
        FragmentManager manager = getSupportFragmentManager();
        ChaoMungFragment chaoMungFragment = ChaoMungFragment.newInstance(user, username1, () -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);
        manager.beginTransaction()
                .replace(R.id.content_fame, chaoMungFragment)
                .commit();
        //show user header
        headerView = navigationView.getHeaderView(0);
        tvUser = headerView.findViewById(R.id.tvUser);
        username = headerView.findViewById(R.id.username);
        Intent intent = getIntent();
        user = intent.getStringExtra("user");
        thuThuDAO = new ThuThuDAO(this);
        ThuThu thuThu = thuThuDAO.getID(user);
        username1 = thuThu.getHoTen();
        username.setText(username1);
        tvUser.setText("Xin chào,");
//        ChaoMungFragment cHaoMungFragment = ChaoMungFragment.newInstance(user);

//        //search
//        search = findViewById(R.id.tvSearch);
//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(MainActivity.this, SearchActivity.class);
//                //i.putExtra("user", username);
//                startActivity(i);
//            }
//        });
        //admin co quyen add user
        if (user != null && user.equalsIgnoreCase("admin")) {
            navigationView.getMenu().findItem(R.id.sub_ThemUser).setVisible(true);
            navigationView.getMenu().findItem(R.id.navNhanVien).setVisible(true);
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                switch (item.getItemId()) {
                    case R.id.navPhieuMuon:
                        if (getSupportActionBar() != null) {
                            getSupportActionBar().show();
                        }
                        setTitle("Quản lý mượn sách");
                        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
                        manager.beginTransaction().replace(R.id.content_fame, phieuMuonFragment).commit();
                        break;

                    case R.id.navLoaiSach:
                        if (getSupportActionBar() != null) {
                            getSupportActionBar().show();
                        }
                        setTitle("Quản lý loại sách");
                        LoaiSachFragment loaiSachFragment = new LoaiSachFragment();
                        manager.beginTransaction().replace(R.id.content_fame, loaiSachFragment).commit();
                        break;

                    case R.id.navSach:
                        if (getSupportActionBar() != null) {
                            getSupportActionBar().show();
                        }
                        setTitle("Quản lý sách");
                        SachFragment sachFragment = new SachFragment();
                        manager.beginTransaction().replace(R.id.content_fame, sachFragment).commit();
                        break;

                    case R.id.navKhachHang:
                        if (getSupportActionBar() != null) {
                            getSupportActionBar().show();
                        }
                        setTitle("Quản lý khách hàng");
                        KhachHangFragment khachHangFragment = new KhachHangFragment();
                        manager.beginTransaction().replace(R.id.content_fame, khachHangFragment).commit();
                        break;

                    case R.id.sub_Top:
                        if (getSupportActionBar() != null) {
                            getSupportActionBar().show();
                        }
                        setTitle("Top sách mượn nhiều nhất");
                        TopTenFragment topTenFragment = new TopTenFragment();
                        manager.beginTransaction().replace(R.id.content_fame, topTenFragment).commit();
                        break;

                    case R.id.sub_DoanhThu:
                        if (getSupportActionBar() != null) {
                            getSupportActionBar().show();
                        }
                        setTitle("Doanh Thu");
                        DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                        manager.beginTransaction().replace(R.id.content_fame, doanhThuFragment).commit();
                        break;
                    case R.id.navNhanVien:
                        if (getSupportActionBar() != null) {
                            getSupportActionBar().show();
                        }
                        setTitle("Quản lý Nhân viên");
                        NhanVienFragment nhanVienFragment = new NhanVienFragment();
                        manager.beginTransaction().replace(R.id.content_fame, nhanVienFragment).commit();
                        break;
                    case R.id.sub_ThemUser:
                        if (getSupportActionBar() != null) {
                            getSupportActionBar().show();
                        }
                        setTitle("Thêm nhân viên");
                        ThemUserFragment themNguoiDungFragment = new ThemUserFragment();
                        manager.beginTransaction().replace(R.id.content_fame, themNguoiDungFragment).commit();
                        break;

                    case R.id.sub_DoiMK:
                        if (getSupportActionBar() != null) {
                            getSupportActionBar().show();
                        }
                        setTitle("Đổi mật khẩu");
                        DoiMKFragment changePassFragment = new DoiMKFragment();
                        manager.beginTransaction()
                                .replace(R.id.content_fame, changePassFragment)
                                .commit();
                        break;

                    case R.id.sub_DangXuat:
                        if (getSupportActionBar() != null) {
                            getSupportActionBar().show();
                        }
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                        break;

                    case R.id.navcHAO:
                        if (getSupportActionBar() != null) {
                            getSupportActionBar().hide();
                        }
                        setTitle("Trang Chủ");
                        ChaoMungFragment chaoMungFragment = new ChaoMungFragment(() -> {
                            drawerLayout.open();
                        });
                        manager.beginTransaction().replace(R.id.content_fame, chaoMungFragment).commit();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }
}