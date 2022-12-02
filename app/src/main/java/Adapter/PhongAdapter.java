package Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import DAO.HoaDonDao;
import DAO.HopDongDAO;
import DAO.KhachThueDAO;
import DAO.PhongDAO;
import Model.HoaDon;
import Model.HopDong;
import Model.KhachThue;
import Model.Phong;
import longvtph16016.poly.appquanlyphongtro.R;
import longvtph16016.poly.appquanlyphongtro.interfaceDeleteClickdistioner;

public class PhongAdapter extends BaseAdapter {
    private Context context;
    KhachThueDAO khachThueDAO;
    HopDongDAO hopDongDAO;
    PhongDAO phongDAO;
    HoaDonDao hoaDonDao;
    // biến tạo hóa đơn
    String tenhoadon,ngaytao,ghiChu;
    int sodien,sonuoc,chiphikhac,tongtien;
    private List<Phong> list;
    private interfaceDeleteClickdistioner interfaceDeleteClickdistioner;



    public PhongAdapter(Context context, interfaceDeleteClickdistioner interfaceDeleteClickdistioner) {
        this.context = context;
        this.interfaceDeleteClickdistioner = interfaceDeleteClickdistioner;
    }

    public void setData(List<Phong> arrayList){
        this.list= arrayList;
        notifyDataSetChanged();// có tác dụng refresh lại data
    }

    public final class MyViewHolder {
        //khai báo các thành phần view có trong layoutitem
        private TextView tv_sophong,giaphong,giadien,gianuoc,giawifi,trangthai;

    }
    public int getCount() {
        if(list!=null)
            return list.size();
        return 0;
    }
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder myViewHolder = null;
        if (view == null) {
            myViewHolder = new MyViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_phong, null);
            myViewHolder.tv_sophong = view.findViewById(R.id.tv_ssophong);
            view.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        LinearLayout ln_item_dv = view.findViewById(R.id.ln_menu_phong);
        myViewHolder.tv_sophong.setText("Phòng"+": "+list.get(i).getSoPhong());

        ln_item_dv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_bottom_phong);

                LinearLayout editLayout = dialog.findViewById(R.id.edt_update_dv);
                LinearLayout detail = dialog.findViewById(R.id.edt_detailphong);
                LinearLayout themhopdong = dialog.findViewById(R.id.edt_ThemHopDong);
                LinearLayout themhoadon = dialog.findViewById(R.id.edt_ThemHoaDon);
                LinearLayout delete_layout = dialog.findViewById(R.id.edt_delete_dv);
                LinearLayout ThemKhachThue = dialog.findViewById(R.id.edt_ThemKhachThue);

                TextView textView = dialog.findViewById(R.id.tv_title);
                textView.setText("Phòng "+list.get(i).getSoPhong());

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ThongTinPhong(i);
                    }
                });
                ThemKhachThue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Phong phong=list.get(i);
                        if(phong.getTrangThai()!=1){
                            Toast.makeText(context, "Phòng đã có người thuê", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                        View view = inflater.inflate(R.layout.themkhachthue_dialog, null);
                        TextView edsophong = view.findViewById(R.id.tv_SoPhong_ThemKhachThu);
                        int idphong=list.get(i).getIdPhong();
                        EditText edtenKT = view.findViewById(R.id.edTenK);
                        EditText edSdt = view.findViewById(R.id.edSd);
                        EditText edCccd = view.findViewById(R.id.edCcc);
                        Button btnCancel = view.findViewById(R.id.btnCance);
                        Button btnSave = view.findViewById(R.id.btnSav);
                        edsophong.setText(""+list.get(i).getSoPhong());
                        builder.setView(view);
                        Dialog dialog = builder.create();
                        dialog.show();

                        btnSave.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                khachThueDAO = new KhachThueDAO(context);
                                KhachThue khach = new KhachThue();
                                khach.setIdPhong(idphong);
                                khach.setHoTen(edtenKT.getText().toString());
                                khach.setSdt(Integer.parseInt(edSdt.getText().toString()));
                                khach.setCccd(Integer.parseInt(edCccd.getText().toString()));
                                if (khachThueDAO.insertKhachThue(khach)>0){
                                    Toast.makeText(context, "thêm mới thành công", Toast.LENGTH_SHORT).show();
                                    phong.setTrangThai(2);
                                    phongDAO=new PhongDAO(context);
                                    phongDAO.updatePhong(phong);
                                    dialog.dismiss();
                                }else {
                                    Toast.makeText(context, "thêm mới k thành công", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        btnCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                });
                editLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                        View vieww = inflater.inflate(R.layout.dialog_sua_phong, null);
                        EditText edt_update_sophong = vieww.findViewById(R.id.edt_themsophong_update);
                        EditText edt_update_giaphong = vieww.findViewById(R.id.edt_themgiaphong_update);
                        EditText edt_update_giadien = vieww.findViewById(R.id.edt_themgiadien_update);
                        EditText edt_update_gianuoc = vieww.findViewById(R.id.edt_themgianuoc_update);
                        EditText edt_update_wifi = vieww.findViewById(R.id.edt_themgiawifi_update);
                        Button btn_Cancel = vieww.findViewById(R.id.btn_huy_phong_update);
                        Button btn_update = vieww.findViewById(R.id.btn_them_phong_update);

                        edt_update_sophong.setText(list.get(i).getSoPhong()+ "");
                        edt_update_giaphong.setText(list.get(i).getGiaPhong()+ "");
                        edt_update_giadien.setText(list.get(i).getGiaDien()+ "");
                        edt_update_gianuoc.setText(list.get(i).getGiaNuoc()+ "");
                        edt_update_wifi.setText(list.get(i).getGiaWifi()+ "");

                        builder.setView(vieww);
                        Dialog dialog = builder.create();
                        dialog.show();
                        btn_update.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                PhongDAO phongDAO = new PhongDAO(context);
                                Phong phong;
                                phong = list.get(i);
                                phong.setSoPhong(Integer.parseInt(edt_update_sophong.getText().toString()));
                                phong.setGiaPhong(Integer.parseInt(edt_update_giaphong.getText().toString()));
                                phong.setGiaDien(Integer.parseInt(edt_update_giadien.getText().toString()));
                                phong.setGiaNuoc(Integer.parseInt(edt_update_gianuoc.getText().toString()));
                                phong.setGiaWifi(Integer.parseInt(edt_update_wifi.getText().toString()));
                                if (phongDAO.updatePhong(phong)>0){
                                    Toast.makeText(context,"Sửa thành công",Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                    list.clear();
                                    list.addAll(phongDAO.getAll());
                                    notifyDataSetChanged();
                                }else {
                                    Toast.makeText(context,"Sửa ko thành công",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                });
                themhopdong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                        View view = inflater.inflate(R.layout.tao_hop_dong, null);
                        hopDongDAO=new HopDongDAO(context);
                        khachThueDAO=new KhachThueDAO(context);
                        int idphong =list.get(i).getIdPhong();

                        if(list.get(i).getTrangThai()==1){
                            Toast.makeText(context, "Phòng chưa add có người thuê", Toast.LENGTH_SHORT).show();
                            return;
                        }
                       else if(hopDongDAO.getHopDongByIdPhong(String.valueOf(idphong))!=null){
                            Toast.makeText(context, "Phòng đã tạo hợp đồng thành công", Toast.LENGTH_SHORT).show();
                            return;
                        }else {


                        khachThueDAO = new KhachThueDAO(context);
                        KhachThue khachThue = khachThueDAO.getUserByIdPhong(String.valueOf(idphong));

                        TextView edsophong = view.findViewById(R.id.edt_SoPhong_HopDong);
                        TextView edtTenKhachThue = view.findViewById(R.id.edt_TenKhachThue_HopDong);
                        EditText edt_ngaybatdau_hopdong = view.findViewById(R.id.edt_NgayBatDau_HopDong);
                        EditText edt_ngayketthuc_hopdong = view.findViewById(R.id.edt_NgayKetThuc_HopDong);
                        ImageView img_a = view.findViewById(R.id.img_ngay_bat_dau_HopDong);
                        ImageView img_b = view.findViewById(R.id.img_Ngay_Ket_Thuc_HopDong);
                        EditText edt_songuoi = view.findViewById(R.id.edt_SoNguoi_HopDong);
                        EditText edt_soluongxe = view.findViewById(R.id.edt_SoLuongXe_HopDong);
                        EditText edt_tiencoc = view.findViewById(R.id.edt_TienCoc_HopDong);
                        TextView edt_trangthai = view.findViewById(R.id.edt_TrangThai_HopDong);
                        Button btnCancel = view.findViewById(R.id.btn_huy_HopDong);
                        Button btnSavet = view.findViewById(R.id.btn_Tao_HopDong);

                        //----
                        Calendar calendar = Calendar.getInstance();//Lay time
                        final int year = calendar.get(Calendar.YEAR);
                        final int month = calendar.get(Calendar.MONTH);
                        final int day = calendar.get(calendar.DAY_OF_MONTH);

                        //datePicker
                        img_a.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int y, int m, int d) {
                                        edt_ngaybatdau_hopdong.setText(d + "/" + (m + 1) + "/" + y);
                                    }
                                }, year, month, day);
                                datePickerDialog.show();
                            }
                        });
                        //datePicker
                        img_b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int y, int m, int d) {
                                        edt_ngayketthuc_hopdong.setText(d + "/" + (m + 1) + "/" + y);

                                    }
                                }, year, month, day);
                                datePickerDialog.show();
                            }
                        });


                            edsophong.setText(""+list.get(i).getSoPhong());
                            edtTenKhachThue.setText(khachThue.getHoTen());
                            builder.setView(view);
                            Dialog dialog = builder.create();
                            dialog.show();


                            edt_trangthai.setText("đang thuê");
                            btnSavet.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                                    if( edt_ngaybatdau_hopdong.getText().toString().length()==0){
                                        Toast.makeText(context, "Ngày bắt đầu không được để trống", Toast.LENGTH_SHORT).show();
                                        return;
                                    }if( edt_ngayketthuc_hopdong .getText().toString().length()==0){
                                        Toast.makeText(context, "Ngày kết thúc không được để trống và ", Toast.LENGTH_SHORT).show();
                                        return;
                                    }if(edt_songuoi.getText().toString().length()==0){
                                        Toast.makeText(context, "Số người không được để trống", Toast.LENGTH_SHORT).show();
                                        return;
                                    }


                                    try {
                                        Date date1=sdf.parse(edt_ngaybatdau_hopdong.getText().toString());
                                        Date date2=sdf.parse(edt_ngayketthuc_hopdong.getText().toString());
                                        if(date1.compareTo(date2)<0){
                                            HopDong hopDong = new HopDong();
                                            hopDong.setIdPhong(idphong);
                                            hopDong.setIdKhachThue(khachThue.getIdKhachThue());
                                            hopDong.setNgayBatDau((edt_ngaybatdau_hopdong.getText().toString()));
                                            hopDong.setNgayKetThuc((edt_ngayketthuc_hopdong.getText().toString()));
                                            hopDong.setSoNguoi(Integer.parseInt(edt_songuoi.getText().toString()));
                                            hopDong.setSoLuongXe(Integer.parseInt(edt_soluongxe.getText().toString()));
                                            hopDong.setTiecCoc(Integer.parseInt(edt_tiencoc.getText().toString()));

                                            hopDong.setTrangThaiHD("đang thuê");



                                            if (hopDongDAO.insertHopDong(hopDong)>0){

                                                Toast.makeText(context, "thêm mới thành công", Toast.LENGTH_SHORT).show();
                                                dialog.dismiss();
                                            }else {
                                                Toast.makeText(context, "thêm mới k thành công", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        else {
                                            Toast.makeText(context, "Ngày kết thúc lớn hơn ngày bắt đầu", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                        Log.d("ssssssss", "onClick: "+e);
                                    }



                                }
                            });
                            btnCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                        }
                        }


                });
                themhoadon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ThemHoadon(i);
                    }
                });
                delete_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        interfaceDeleteClickdistioner.OnClickDelete(i);
                        dialog.dismiss();
                    }
                });
            }
        });
        return view;
    }

    private void ThemHoadon(int position) {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_tao_hoa_don);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
        EditText  Ed_NgayTao_HDon, Ed_NhapSoDien_HDon, Ed_NhapSoNuoc_HDon, Ed_ChiPhiKhac_HDon,Ed_TongTien_HDon,Ed_GhiChu_HDon,ed_tenhoaDon;
        TextView tvsophong;
        ImageView image_ngay;
        TextView tvtongtien;
        Button Btn_huy_HDon, Btn_them_HDon,btn_tongtien;
        //ánh xạ
        Ed_NgayTao_HDon = dialog.findViewById(R.id.ed_NgayTao_HDon);
        ed_tenhoaDon = dialog.findViewById(R.id.ed_tenHoaDon);
        Ed_NhapSoDien_HDon = dialog.findViewById(R.id.ed_NhapSoDien_HDon);
        Ed_NhapSoNuoc_HDon = dialog.findViewById(R.id.ed_NhapSoNuoc_HDon);
        Ed_ChiPhiKhac_HDon = dialog.findViewById(R.id.ed_ChiPhiKhac_HDon);
        tvsophong=dialog.findViewById(R.id.tvsophong_hd);
        tvsophong.setText("Phòng: "+list.get(position).getSoPhong());
        Ed_GhiChu_HDon = dialog.findViewById(R.id.ed_GhiChu_HDon);
        image_ngay= dialog.findViewById(R.id.image_NgayTao_HDon);
        Btn_them_HDon= dialog.findViewById(R.id.btn_them_HDon);
        Btn_huy_HDon= dialog.findViewById(R.id.btn_huy_HDon);
        btn_tongtien= dialog.findViewById(R.id.tinhtongtien);
        tvtongtien=dialog.findViewById(R.id.tvtongtien);
        CheckBox checkBox=dialog.findViewById(R.id.checkboxTrangthai);
        //---------------- an voa anh chon ngay
        Calendar calendar = Calendar.getInstance();//Lay time
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(calendar.DAY_OF_MONTH);
        image_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        Ed_NgayTao_HDon.setText( y+"-" + (m + 1) + "-"+d );
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        btn_tongtien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check vavidate
                 tenhoadon=ed_tenhoaDon.getText().toString();
                 ngaytao=Ed_NgayTao_HDon.getText().toString();
                 ghiChu=Ed_GhiChu_HDon.getText().toString();
                 sodien= Integer.parseInt(Ed_NhapSoDien_HDon.getText().toString());
                 sonuoc= Integer.parseInt(Ed_NhapSoDien_HDon.getText().toString());
                 chiphikhac= Integer.parseInt(Ed_NhapSoDien_HDon.getText().toString());
                 phongDAO=new PhongDAO(context);
                 Phong phong=phongDAO.getUserById(String.valueOf(list.get(position).getIdPhong()));
                 int giadien=phong.getGiaDien();
                 int giaphong=phong.getGiaPhong();
                 int gianuoc=phong.getGiaNuoc();
                 int giawifi=phong.getGiaWifi();
                 tongtien=(giadien*sodien)+(gianuoc*sonuoc)+giaphong+giawifi+chiphikhac;
                 tvtongtien.setText("Tổng Hóa Đơn: "+tongtien);
            }
        });
        Btn_them_HDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tongtien==0){
                    Toast.makeText(context, "hãy tính tổng", Toast.LENGTH_SHORT).show();
                    return;
                }
                //ínert
                hopDongDAO=new HopDongDAO(context);
                HopDong hopDong=hopDongDAO.getHopDongByIdPhong(String.valueOf(list.get(position).getIdPhong()));
                hoaDonDao=new HoaDonDao(context);
                HoaDon hoaDon=new HoaDon();
                hoaDon.setIdPhong(list.get(position).getIdPhong());
                hoaDon.setTenHoaDOn(tenhoadon);
                hoaDon.setGhiChu(ghiChu);
                hoaDon.setChiPhiKhac(chiphikhac);
                hoaDon.setNgay(ngaytao);
                hoaDon.setSoDien(sodien);
                hoaDon.setSoNuoc(sonuoc);
                hoaDon.setIdHopDong(hopDong.getIdHopDong());
                boolean trangthai=checkBox.isChecked();
                int trangTHai;
                Log.d("sssssss", "onClick: "+trangthai);
                if (trangthai){
                    //đã thanh toán
                   trangTHai=2;
                }
                else {
//                    chưa thanh toan
                    trangTHai=1;
                }
                hoaDon.setTrangThai(trangTHai);
                hoaDon.setTong(tongtien);

                if(hoaDonDao.insertHoaDon(hoaDon)){
                    Toast.makeText(context, "thêm mới thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else {
                    Toast.makeText(context, "thêm mới không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void ThongTinPhong(int pos) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_thongtinphong);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        TextView tvSophong=dialog.findViewById(R.id.tvsophong);
        TextView tvGiaPhong=dialog.findViewById(R.id.tvGiaPhong);
        TextView tvGiadien=dialog.findViewById(R.id.tvGiaDien);
        TextView tvGianuoc=dialog.findViewById(R.id.tvGiaNuoc);
        TextView tvWifi=dialog.findViewById(R.id.tvGiaWifi);
        TextView tvtrangthai=dialog.findViewById(R.id.tinhTrang);
        TextView tvnguoithue=dialog.findViewById(R.id.tvnguoiThue);
        Button button = dialog.findViewById(R.id.btn_dissmiss_phong);

        Phong phong=list.get(pos);
        tvSophong.setText("Số phòng: "+phong.getSoPhong());
        tvGiaPhong.setText("Giá Phòng: "+phong.getGiaPhong());
        tvGiadien.setText("Giá Điện: "+phong.getGiaDien());
        tvGianuoc.setText("Giá Nước: "+phong.getGiaNuoc());
        tvWifi.setText("Giá Wifi: "+phong.getGiaWifi());
        if(phong.getTrangThai()==1){
            tvtrangthai.setText("Trạng Thái: chưa ai thuê ");
        }
        else {
            tvtrangthai.setText("Trạng Thái: đã có người thuê ");
            khachThueDAO=new KhachThueDAO(context);
            KhachThue khachThue=khachThueDAO.getUserByIdPhong(String.valueOf(phong.getIdPhong()));
            tvnguoithue.setText("Người Thuê: "+khachThue.getHoTen());

        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}
