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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import DAO.HopDongDAO;
import DAO.KhachThueDAO;
import Model.HopDong;
import Model.KhachThue;
import Model.Phong;
import longvtph16016.poly.appquanlyphongtro.R;
import longvtph16016.poly.appquanlyphongtro.interfaceDeleteClickdistioner;

public class PhongAdapter extends BaseAdapter {
    private Context context;
    KhachThueDAO khachThueDAO;
    private ArrayList<Phong> list;
    private interfaceDeleteClickdistioner interfaceDeleteClickdistioner;



    public PhongAdapter(Context context, interfaceDeleteClickdistioner interfaceDeleteClickdistioner) {
        this.context = context;
        this.interfaceDeleteClickdistioner = interfaceDeleteClickdistioner;
    }

    public void setData(ArrayList<Phong> arrayList){
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
                LinearLayout themhopdong = dialog.findViewById(R.id.edt_ThemHopDong);
                LinearLayout delete_layout = dialog.findViewById(R.id.edt_delete_dv);
                LinearLayout ThemKhachThue = dialog.findViewById(R.id.edt_ThemKhachThue);

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);

                ThemKhachThue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        if(!list.get(i).getTrangThai().equalsIgnoreCase("chưa thuê")){
//                            Toast.makeText(context, "đã cho thuê", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
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

                themhopdong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                        View view = inflater.inflate(R.layout.tao_hop_dong, null);

                        int idphong =list.get(i).getIdPhong();
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

                                HopDongDAO hopDongDAO = new HopDongDAO(context);
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
                        });
                        btnCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
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
}
