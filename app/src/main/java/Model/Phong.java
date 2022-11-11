package Model;

public class Phong {
    private int IdPhong;
    private int SoPhong;
    private int GiaPhong;
    private int GiaDien;
    private int GiaNuoc;
    private int Wifi;

    public Phong() {
    }

    public Phong(int idPhong, int soPhong, int giaPhong, int giaDien, int giaNuoc, int wifi) {
        IdPhong = idPhong;
        SoPhong = soPhong;
        GiaPhong = giaPhong;
        GiaDien = giaDien;
        GiaNuoc = giaNuoc;
        Wifi = wifi;
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

    public int getWifi() {
        return Wifi;
    }

    public void setWifi(int wifi) {
        Wifi = wifi;
    }
}
