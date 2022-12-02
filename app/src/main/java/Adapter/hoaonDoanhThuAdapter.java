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
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import DAO.HoaDonDao;
import DAO.PhongDAO;
import Model.HoaDon;
import Model.Phong;
import longvtph16016.poly.appquanlyphongtro.R;

public class hoaonDoanhThuAdapter extends BaseAdapter {
    private Context context;
    private List<HoaDon> list;

    public hoaonDoanhThuAdapter(Context context, List<HoaDon> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if(list!=null)
            return list.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       MyViewHolder myViewHolder = null;
        if (view == null) {
            myViewHolder = new MyViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.iteam_hoadon, null);
            myViewHolder.tvHoadon = view.findViewById(R.id.tv_sohoadon);
            myViewHolder.tvHoadon.setText(list.get(i).getTenHoaDOn());
            view.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_bottom_hoadon);
                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);

                LinearLayout edt_xem_hd = dialog.findViewById(R.id.edt_xem_hd);
                HoaDon hoaDon = list.get(i);
                edt_xem_hd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editHoaDon(hoaDon);
                    }
                });
            }
        });


        return view;
    }
    public  class MyViewHolder {
        //khai báo các thành phần view có trong layoutitem
        private TextView tvHoadon,giaphong,giadien,gianuoc,giawifi,trangthai;
    }
    private void editHoaDon(HoaDon hoaDon) {
        final  Dialog dialog=new Dialog(context, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        dialog.requestWindowFeature((Window.FEATURE_NO_TITLE));
        dialog.setContentView(R.layout.dialog_xem_hoa_don);
        dialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        dialog.show();
        EditText Ed_NgayTao_HDon, Ed_NhapSoDien_HDon, Ed_NhapSoNuoc_HDon, Ed_ChiPhiKhac_HDon,Ed_TongTien_HDon,Ed_GhiChu_HDon,ed_tenhoaDon;
        TextView tvsophong;
        ImageView image_ngay;
        TextView tvtongtien;
        Button Btn_quayve;
        //ánh xạ
        Ed_NgayTao_HDon = dialog.findViewById(R.id.ed_NgayTao_HDon);
        ed_tenhoaDon = dialog.findViewById(R.id.ed_tenHoaDon);
        Ed_NhapSoDien_HDon = dialog.findViewById(R.id.ed_NhapSoDien_HDon);
        Ed_NhapSoNuoc_HDon = dialog.findViewById(R.id.ed_NhapSoNuoc_HDon);
        Ed_ChiPhiKhac_HDon = dialog.findViewById(R.id.ed_ChiPhiKhac_HDon);
        Ed_GhiChu_HDon = dialog.findViewById(R.id.ed_GhiChu_HDon);
        Btn_quayve=dialog.findViewById(R.id.btn_quayve);
        Btn_quayve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        tvtongtien=dialog.findViewById(R.id.tvtongtien);
        CheckBox checkBox=dialog.findViewById(R.id.checkboxTrangthai);

        ed_tenhoaDon.setText(hoaDon.getTenHoaDOn());
        Ed_NgayTao_HDon.setText(hoaDon.getNgay());
        Ed_NhapSoDien_HDon.setText(hoaDon.getSoDien()+"");
        Ed_NhapSoNuoc_HDon.setText(hoaDon.getSoNuoc()+"");
        Ed_ChiPhiKhac_HDon.setText(hoaDon.getChiPhiKhac()+"");
        Ed_GhiChu_HDon.setText(hoaDon.getGhiChu());
        int tongtien=hoaDon.getTong();
        tvtongtien.setText("Tổng Hóa Đơn: "+tongtien);
        if(hoaDon.getTrangThai()==1){
            checkBox.setChecked(false);
        }
        else {
            checkBox.setChecked(true);
        }

    }
}
