package Model;

public class HopDong {
    private int IdHopDong;
    private String NgayBatDau;
    private String NgayKetThuc;
    private int SoNguoi;
    private int SoLuongXe;
    private int TiecCoc;
    private Boolean TrangThaiHD;

    public HopDong() {
    }

    public HopDong(int idHopDong, String ngayBatDau, String ngayKetThuc, int soNguoi, int soLuongXe, int tiecCoc, Boolean trangThaiHD) {
        IdHopDong = idHopDong;
        NgayBatDau = ngayBatDau;
        NgayKetThuc = ngayKetThuc;
        SoNguoi = soNguoi;
        SoLuongXe = soLuongXe;
        TiecCoc = tiecCoc;
        TrangThaiHD = trangThaiHD;
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

    public Boolean getTrangThaiHD() {
        return TrangThaiHD;
    }

    public void setTrangThaiHD(Boolean trangThaiHD) {
        TrangThaiHD = trangThaiHD;
    }
}

