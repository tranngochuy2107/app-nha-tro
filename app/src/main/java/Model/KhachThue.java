package Model;

public class KhachThue extends Phong {
    private  int IdKhachThue;
    private  String hoTen;
    private  int Sdt;
    private  int Cccd;
    private int IdPhong;

    public KhachThue() {
    }

    public KhachThue(int idKhachThue, String hoTen, int sdt, int cccd, int idPhong) {
        IdKhachThue = idKhachThue;
        this.hoTen = hoTen;
        Sdt = sdt;
        Cccd = cccd;
        IdPhong = idPhong;
    }

    public int getIdKhachThue() {
        return IdKhachThue;
    }

    public void setIdKhachThue(int idKhachThue) {
        IdKhachThue = idKhachThue;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getSdt() {
        return Sdt;
    }

    public void setSdt(int sdt) {
        Sdt = sdt;
    }

    public int getCccd() {
        return Cccd;
    }

    public void setCccd(int cccd) {
        Cccd = cccd;
    }

    public int getIdPhong() {
        return IdPhong;
    }

    public void setIdPhong(int idPhong) {
        IdPhong = idPhong;
    }

}
