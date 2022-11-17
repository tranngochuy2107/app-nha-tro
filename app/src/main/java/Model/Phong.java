package Model;

public class Phong {
    private int IdPhong;
    private int SoPhong;
    private int GiaPhong;
    private int GiaDien;
    private int GiaNuoc;
    private int GiaWifi;
    private String TrangThai;

    public Phong() {
    }

    public Phong(int idPhong, int soPhong, int giaPhong, int giaDien, int giaNuoc, int giaWifi, String trangThai) {
        IdPhong = idPhong;
        SoPhong = soPhong;
        GiaPhong = giaPhong;
        GiaDien = giaDien;
        GiaNuoc = giaNuoc;
        GiaWifi = giaWifi;
        TrangThai = trangThai;

    }

    public int getIdPhong() {
        return IdPhong;
    }

    public void setIdPhong(int idPhong) {
        IdPhong = idPhong;
    }

    public int getSoPhong() {
        return SoPhong;
    }

    public void setSoPhong(int soPhong) {
        SoPhong = soPhong;
    }

    public int getGiaPhong() {
        return GiaPhong;
    }

    public void setGiaPhong(int giaPhong) {
        GiaPhong = giaPhong;
    }

    public int getGiaDien() {
        return GiaDien;
    }

    public void setGiaDien(int giaDien) {
        GiaDien = giaDien;
    }

    public int getGiaNuoc() {
        return GiaNuoc;
    }

    public void setGiaNuoc(int giaNuoc) {
        GiaNuoc = giaNuoc;
    }

    public int getGiaWifi() {
        return GiaWifi;
    }

    public void setGiaWifi(int giaWifi) {
        GiaWifi = giaWifi;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }


}
