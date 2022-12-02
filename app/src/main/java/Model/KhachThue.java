package Model;

public class KhachThue extends Phong {
    private  int IdKhachThue;
    private  String hoTen;
    private  String Sdt;
    private  String Cccd;
    private int IdPhong;

    public KhachThue() {
    }

    public KhachThue(int idKhachThue, String hoTen, String sdt, String cccd, int idPhong) {
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

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
    }

    public String getCccd() {
        return Cccd;
    }

    public void setCccd(String cccd) {
        Cccd = cccd;
    }

    public int getIdPhong() {
        return IdPhong;
    }

    public void setIdPhong(int idPhong) {
        IdPhong = idPhong;
    }

}
