package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
/**
 * Lớp hỗ trợ quản lý cơ sở dữ liệu cho ứng dụng quản lý phòng trọ.
 * Kế thừa từ SQLiteOpenHelper để cung cấp các chức năng tạo và nâng cấp cơ sở dữ liệu.
 */
public class DbHelper extends SQLiteOpenHelper {
    // Tên cơ sở dữ liệu
    private static final String DB_NAME = "QLPT";
    // Phiên bản cơ sở dữ liệu
    private static final int DB_VERSION =19;

    // Lệnh SQL tạo bảng "Phong" (Thông tin phòng)
    static final String CREATE_TABLE_PHONG =
            "create table Phong (IdPhong INTEGER PRIMARY KEY AUTOINCREMENT,"+// Khóa chính tự động tăng
                    "SoPhong INTEGER NOT NULL ," +// Số phòng
                    "GiaPhong INTEGER  NOT NULL," +// Giá phòng
                    "GiaDien INTEGER  NOT NULL," +// Giá điện
                    "GiaNuoc INTEGER  NOT NULL," +// Giá nước
                    "GiaWifi INTEGER NOT NULL," +// Giá wifi
                    "TrangThai INTEGER NOT NULL)" ; // Trạng thái phòng (có thể là đang trống, đã thuê, v.v.)
    // Lệnh SQL tạo bảng "KhachThue" (Thông tin khách thuê)
    String createTableKhachThue = " create table KhachThue (" +
            "IdKhachThue INTEGER PRIMARY KEY autoincrement," +
            "HoTen TEXT NOT NULL," +
            "SoDienThoai TEXT NOT NULL," +
            "Cccd TEXT NOT NULL," +
            "IdPhong INTEGER REFERENCES Phong(IdPhong))";

    // Lệnh SQL tạo bảng "HOPDONG" (Thông tin khac thue)
    static final String CREATE_TABLE_HOP_DONG =
            "create table HopDong (" +
                    "IdHopDong INTEGER PRIMARY KEY AUTOINCREMENT, " +// Khóa chính tự động tăng
                    "NgayBatDau TEXT NOT NULL, " +
                    "NgayKetThuc TEXT NOT NULL," +
                    "SoNguoi INTEGER NOT NULL, " +
                    "SoLuongXe INTEGER NOT NULL, " +
                    "TienCoc INTEGER NOT NULL," +
                    "TrangThaiHD INTEGER NOT NULL," +
                    "IdKhachThue INTEGER REFERENCES KhachThue(IdKhachThue)," +
                    "IdPhong INTEGER REFERENCES Phong(IdPhong))";// Liên kết với IdPhong từ bảng "Phong"
    // Lệnh SQL tạo bảng "HoaDon" (Thông tin hóa đơn)
    static final String CREATE_TABLE_HOA_DON =
            "create table HoaDon (" +
                    "IdHoaDon INTEGER PRIMARY KEY AUTOINCREMENT, " +// Khóa chính tự động tăng
                    "TenHoaDon TEXT NOT NULL, " +
                    "Ngay DATE NOT NULL, " +
                    "SoDien INTEGER NOT NULL, " +
                    "SoNuoc INTEGER NOT NULL," +
                    "ChiPhiKhac INTERGER NOT NULL, " +
                    "Tong INTEGER NOT NULL," +
                    "TrangThai INTEGER NOT NULL," +
                    "GhiChu TEXT NOT NULL," +
                    "IdPhong INTEGER REFERENCES Phong(IdPhong)," +// Liên kết với IdPhong từ bảng "Phong"
                    "IdHopDong INTEGER REFERENCES HopDong(IdHopDong))";/// Liên kết với IdHopDong từ bảng "HopDong"

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tao bang Phong
        db.execSQL(CREATE_TABLE_PHONG);
        //Tao bang Khach Thue
        db.execSQL(createTableKhachThue);
        //Tao bang HopDong
        db.execSQL(CREATE_TABLE_HOP_DONG);
        //Tao bang Hoa Don
        db.execSQL(CREATE_TABLE_HOA_DON);
//        db.execSQL(INSERT_khach);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTablePhong = "drop table if exists Phong";
        db.execSQL(dropTablePhong);
        String dropTableKhachThue = "drop table if exists KhachThue";
        db.execSQL(dropTableKhachThue);
        String dropTableHopDong = "drop table if exists HopDong";
        db.execSQL(dropTableHopDong);
        String dropTableHoaDon = "drop table if exists HoaDon";
        db.execSQL(dropTableHoaDon);
        onCreate(db);
    }
}
