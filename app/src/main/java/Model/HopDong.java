package Model;

public class HopDong {
    private int IdHopDong;
    private String NgayBatDau;
    private String NgayKetThuc;
    private int SoNguoi;
    private int SoLuongXe;
    private int TiecCoc;
    private int TrangThaiHD;
    private int IdPhong;
    private int IdKhachThue;

    public HopDong() {
    }

    public HopDong(int idHopDong, String ngayBatDau, String ngayKetThuc, int soNguoi, int soLuongXe, int tiecCoc, int trangThaiHD, int idPhong, int idKhachThue) {
        IdHopDong = idHopDong;
        NgayBatDau = ngayBatDau;
        NgayKetThuc = ngayKetThuc;
        SoNguoi = soNguoi;
        SoLuongXe = soLuongXe;
        TiecCoc = tiecCoc;
        TrangThaiHD = trangThaiHD;
        IdPhong = idPhong;
        IdKhachThue = idKhachThue;

    }


    public int getIdHopDong() {
        return IdHopDong;
    }

    public void setIdHopDong(int idHopDong) {
        IdHopDong = idHopDong;
    }

    public String getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        NgayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        NgayKetThuc = ngayKetThuc;
    }

    public int getSoNguoi() {
        return SoNguoi;
    }

    public void setSoNguoi(int soNguoi) {
        SoNguoi = soNguoi;
    }

    public int getSoLuongXe() {
        return SoLuongXe;
    }

    public void setSoLuongXe(int soLuongXe) {
        SoLuongXe = soLuongXe;
    }

    public int getTiecCoc() {
        return TiecCoc;
    }

    public void setTiecCoc(int tiecCoc) {
        TiecCoc = tiecCoc;
    }

    public int getTrangThaiHD() {
        return TrangThaiHD;
    }

    public void setTrangThaiHD(int trangThaiHD) {
        TrangThaiHD = trangThaiHD;
    }

    public int getIdPhong() {
        return IdPhong;
    }

    public void setIdPhong(int idPhong) {
        IdPhong = idPhong;
    }

    public int getIdKhachThue() {
        return IdKhachThue;
    }

    public void setIdKhachThue(int idKhachThue) {
        IdKhachThue = idKhachThue;
    }
}

