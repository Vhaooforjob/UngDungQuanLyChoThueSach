package com.example.appquanlychothuesach.database;

public class Data_SQLite {
    public static final String INSERT_THUTHU = "insert into ThuThu(maTT,hoTen,matKhau,phanQuyen) values" +
            "('admin', 'admin', 'admin', 'admin')," +
            "('nvdoailinh', 'Đỗ Ái Linh', '123456', 'nhanvien')," +
            "('nvtrlbinh', 'Trần Lê Bình', '123456', 'nhanvien')," +
            "('nvtrericaa', 'Nguyễn Thị Minh Thư', '123456', 'nhanvien')";
    public static  final String INSERT_KHACHHANG = "insert into KhachHang(cccd,hoTen,anhTV, namSinh) values" +
            "('241997012196','Lương Đỗ Anh Kha','https://i.pinimg.com/564x/9f/02/e6/9f02e63738bd356d9ae3983a6310a59c.jpg','2003')," +
            "('241997010196','Nguyễn Ngọc Hiền Thanh','https://i.pinimg.com/564x/9f/02/e6/9f02e63738bd356d9ae3983a6310a59c.jpg','2003')," +
            "('241997011196','Nguyễn Quí Ngọc','https://i.pinimg.com/564x/9f/02/e6/9f02e63738bd356d9ae3983a6310a59c.jpg','2003')," +
            "('241997013196','Lê Long Tân','https://i.pinimg.com/564x/9f/02/e6/9f02e63738bd356d9ae3983a6310a59c.jpg','2003')," +
            "('241997014196','Trịnh Kiết Tường','https://i.pinimg.com/564x/9f/02/e6/9f02e63738bd356d9ae3983a6310a59c.jpg','2003')";
    public static final String INSERT_LOAISACH = "insert into LoaiSach(tenLoai) values" +
            "('Tiếng Anh')," +
            "('Sách Khoa Học')," +
            "('Sách Lập Trình')," +
            "('Sách Đời Sống'),"+
            "('Sách Kinh Doanh'),"+
             "('Sách Chính Trị'),"+
            "('Truyện Tranh'),"+
            "('Phiêu Lưu'),"+
            "('Sách Kinh Doanh'),"+
            "('Sách Trí Tuệ')";
    public static final String INSERT_SACH = "insert into Sach(TenSach,GiaThue,MaLoai,Nhacungcap,soluong,img) values" +
            "('Học Lập trình Android','1000','1','Bộ Giáo Dục','20','https://img.baza.vn/upload/products-var-ltJmRqxf/KN94lxzTlarge.jpg?v=635427479803569438&width=500')," +
            "('Học Lập trình C#','2000','1','Bộ Giáo Dục','20','https://img.baza.vn/upload/products-var-ltJmRqxf/KN94lxzTlarge.jpg?v=635427479803569438&width=500')," +
            "('Pháp luật đại cương','3000','2','HUFLIT','20','https://img.baza.vn/upload/products-var-ltJmRqxf/KN94lxzTlarge.jpg?v=635427479803569438&width=500')," +
            "('Công nghệ phần mềm','3000','2','HUFLIT','20','https://img.baza.vn/upload/products-var-ltJmRqxf/KN94lxzTlarge.jpg?v=635427479803569438&width=500')," +
            "('OOP và những điều bạn cần biết','4000','1','Bộ Giáo Dục','20','https://vietbooks.info/attachments/upload_2022-11-7_21-53-2-png.16986/')";
    public static final String INSERT_PHIEUMUON = "insert into PhieuMuon(maTT,maKH,maSach,tienThue,ngay,traSach) values" +
            "('admin','1','1','2000','2023/07/15',1)," +
            "('admin','2','2','2500','2023/07/16',0)," +
            "('admin','3','3','3000','2023/07/17',1)" ;

}
