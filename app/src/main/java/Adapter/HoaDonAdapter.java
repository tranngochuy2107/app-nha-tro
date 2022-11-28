package Adapter;

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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DAO.HoaDonDao;
import DAO.KhachThueDAO;
import DAO.PhongDAO;
import Model.HoaDon;
import Model.KhachThue;
import Model.Phong;
import longvtph16016.poly.appquanlyphongtro.R;
import longvtph16016.poly.appquanlyphongtro.interfaceDeleteClickdistioner;

public class HoaDonAdapter extends BaseAdapter {
    private Context context;
    private List<HoaDon> list;
    private longvtph16016.poly.appquanlyphongtro.interfaceDeleteClickdistioner interfaceDeleteClickdistioner;

    // biến tạo hóa đơn
    String tenhoadon,ngaytao,ghiChu;
    int sodien,sonuoc,chiphikhac,tongtien;



    public HoaDonAdapter(Context context, interfaceDeleteClickdistioner interfaceDeleteClickdistioner) {
        this.context = context;
        this.interfaceDeleteClickdistioner = interfaceDeleteClickdistioner;
    }

    public void setData(List<HoaDon> arrayList){
        this.list= arrayList;
        notifyDataSetChanged();// có tác dụng refresh lại data
    }

    public final class MyViewHolder {
        //khai báo các thành phần view có trong layoutitem
        private TextView tv_sohoadon, Ngay,SoDien,SoNuoc,Tong,ChiPhiKhac, GhiChu;

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
            view = inflater.inflate(R.layout.iteam_hoadon, null);
            myViewHolder.tv_sohoadon = view.findViewById(R.id.tv_sohoadon);
            view.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        LinearLayout ln_item_dv = view.findViewById(R.id.ln_menu_hoadon);
        myViewHolder.tv_sohoadon.setText(list.get(i).getTenHoaDOn());

        ln_item_dv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_bottom_hoadon);

                LinearLayout editLayout = dialog.findViewById(R.id.edt_update_hd);
                LinearLayout edt_xem_hd = dialog.findViewById(R.id.edt_xem_hd);
                LinearLayout delete_layout = dialog.findViewById(R.id.edt_delete_hd);

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);

                editLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editHoaDon(i);
                    }
                });


                delete_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        interfaceDeleteClickdistioner.OnClickDelete(i);
                    }
                });

                edt_xem_hd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ThongTinHoaDon(i);
                        Log.d("ssssssssssss", "onClick: "+list.get(i).getTrangThai());
                    }
                });
            }
        });


        return view;
    }

    private void editHoaDon(int i) {
        final  Dialog dialog=new Dialog(context, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        dialog.requestWindowFeature((Window.FEATURE_NO_TITLE));
        dialog.setContentView(R.layout.dialog_sua_hoa_don);
        dialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        dialog.show();
        EditText  Ed_NgayTao_HDon, Ed_NhapSoDien_HDon, Ed_NhapSoNuoc_HDon, Ed_ChiPhiKhac_HDon,Ed_TongTien_HDon,Ed_GhiChu_HDon,ed_tenhoaDon;
        TextView tvsophong;
        ImageView image_ngay;
        TextView tvtongtien;
        Button Btn_huy_HDon, Btn_sua_HDon,btn_tongtien;
        //ánh xạ
        Ed_NgayTao_HDon = dialog.findViewById(R.id.ed_NgayTao_HDon);
        ed_tenhoaDon = dialog.findViewById(R.id.ed_tenHoaDon);
        Ed_NhapSoDien_HDon = dialog.findViewById(R.id.ed_NhapSoDien_HDon);
        Ed_NhapSoNuoc_HDon = dialog.findViewById(R.id.ed_NhapSoNuoc_HDon);
        Ed_ChiPhiKhac_HDon = dialog.findViewById(R.id.ed_ChiPhiKhac_HDon);
        Ed_GhiChu_HDon = dialog.findViewById(R.id.ed_GhiChu_HDon);
        image_ngay= dialog.findViewById(R.id.image_NgayTao_HDon);
        Btn_sua_HDon= dialog.findViewById(R.id.btn_sua_HDon);
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
                        Ed_NgayTao_HDon.setText(d + "/" + (m + 1) + "/" + y);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        HoaDonDao hoaDonDao = new HoaDonDao(context);
        btn_tongtien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check vavidate
                tenhoadon=ed_tenhoaDon.getText().toString();
                ngaytao=Ed_NgayTao_HDon.getText().toString();
                ghiChu=Ed_GhiChu_HDon.getText().toString();
                sodien= Integer.parseInt(Ed_NhapSoDien_HDon.getText().toString());
                sonuoc= Integer.parseInt(Ed_NhapSoNuoc_HDon.getText().toString());
                chiphikhac= Integer.parseInt(Ed_ChiPhiKhac_HDon.getText().toString());
                PhongDAO phongDAO = new PhongDAO(context);
                Phong phong=phongDAO.getUserById(String.valueOf(list.get(i).getIdPhong()));
                int giadien=phong.getGiaDien();
                int giaphong=phong.getGiaPhong();
                int gianuoc=phong.getGiaNuoc();
                int giawifi=phong.getGiaWifi();
                tongtien=(giadien*sodien)+(gianuoc*sonuoc)+giaphong+giawifi+chiphikhac;
                tvtongtien.setText("Tổng Hóa Đơn: "+tongtien);
            }
        });

        Btn_sua_HDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tongtien==0){
                    Toast.makeText(context, "hãy tính tổng", Toast.LENGTH_SHORT).show();
                    return;
                }

                //update
                tenhoadon=ed_tenhoaDon.getText().toString();
                ngaytao=Ed_NgayTao_HDon.getText().toString();
                ghiChu=Ed_GhiChu_HDon.getText().toString();
                sodien= Integer.parseInt(Ed_NhapSoDien_HDon.getText().toString());
                sonuoc= Integer.parseInt(Ed_NhapSoNuoc_HDon.getText().toString());
                chiphikhac= Integer.parseInt(Ed_ChiPhiKhac_HDon.getText().toString());

            //    HoaDon hoaDon=new HoaDon();

//                hoaDon.setTenHoaDOn(tenhoadon);
//                hoaDon.setGhiChu(ghiChu);
//                hoaDon.setChiPhiKhac(chiphikhac);
//                hoaDon.setNgay(ngaytao);
//                hoaDon.setSoDien(sodien);
//                hoaDon.setSoNuoc(sonuoc);

                list.get(i).setTenHoaDOn(tenhoadon);
                list.get(i).setNgay(ngaytao);
                list.get(i).setSoDien(sodien);
                list.get(i).setSoNuoc(sonuoc);
                list.get(i).setChiPhiKhac(chiphikhac);
                list.get(i).setTong(tongtien);

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
//                hoaDon.setTrangThai(trangTHai);
//                hoaDon.setTong(tongtien);
                list.get(i).setTrangThai(trangTHai);
                list.get(i).setGhiChu(ghiChu);

                long err=hoaDonDao.updateHoaDon(list.get(i));
                if(err>0){
                    Toast.makeText(context,"Sua thanh cong",Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    notifyDataSetChanged();

                }
                else {
                    Toast.makeText(context,"Sua khong thanh cong",Toast.LENGTH_LONG).show();
                }
            }
        });

        Btn_huy_HDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }



    private void ThongTinHoaDon(int pos) {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_xemchitiet_hoadon);

        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
    }


}

