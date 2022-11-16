package Model;

public class HoaDon {
    private  int IdHoaDon;
    private String Ngay;
    private  int SoDien;
    private  int SoNuoc;
    private  int Tong;
    private String ChiPhiKhac;
    private String GhiChu;

    public HoaDon() {
    }

    public HoaDon(int idHoaDon, String ngay, int soDien, int soNuoc, int tong, String chiPhiKhac, String ghiChu) {
        IdHoaDon = idHoaDon;
        Ngay = ngay;
        SoDien = soDien;
        SoNuoc = soNuoc;
        Tong = tong;
        ChiPhiKhac = chiPhiKhac;
        GhiChu = ghiChu;
    }

    public int getIdHoaDon() {
        return IdHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        IdHoaDon = idHoaDon;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String ngay) {
        Ngay = ngay;
    }

    public int getSoDien() {
        return SoDien;
    }

    public void setSoDien(int soDien) {
        SoDien = soDien;
    }

    public int getSoNuoc() {
        return SoNuoc;
    }

    public void setSoNuoc(int soNuoc) {
        SoNuoc = soNuoc;
    }

    public int getTong() {
        return Tong;
    }

    public void setTong(int tong) {
        Tong = tong;
    }

    public String getChiPhiKhac() {
        return ChiPhiKhac;
    }

    public void setChiPhiKhac(String chiPhiKhac) {
        ChiPhiKhac = chiPhiKhac;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }
}
