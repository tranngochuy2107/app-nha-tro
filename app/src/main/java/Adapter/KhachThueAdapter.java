package Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.List;

import DAO.PhongDAO;
import Model.KhachThue;
import Model.Phong;
import longvtph16016.poly.appquanlyphongtro.R;

public class KhachThueAdapter extends BaseAdapter {
    private Context context;
    private List<KhachThue> list;


    public KhachThueAdapter(Context context, List<KhachThue> list  ) {
        this.context = context;
        this.list = list;

    }

    public void setData(List<KhachThue> list){
        this.list= list;
        notifyDataSetChanged();// có tác dụng refresh lại data
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
        KhachThueAdapter.MyViewHolder myViewHolder = null;
        if (view == null) {
            myViewHolder = new MyViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_khachthue, null);
            myViewHolder.tv_KhachThue = view.findViewById(R.id.txt_item_khachThue);
            view.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        LinearLayout ln_item_dv = view.findViewById(R.id.ln_menu_khachthue);
        myViewHolder.tv_KhachThue.setText("Khách Thuê"+": "+list.get(i).getHoTen());
        ln_item_dv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.bottom_layout_khachthue);
                LinearLayout editLayout = dialog.findViewById(R.id.edit_layout_khachthue);
                LinearLayout detailLayout = dialog.findViewById(R.id.view_layout_khachthue);

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);

                detailLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        detail(i);
                    }
                });
            }
        });
        return view;
    }

    public  class MyViewHolder {
        //khai báo các thành phần view có trong layoutitem
        private TextView tv_KhachThue,giaphong,giadien,gianuoc,giawifi,trangthai;

    }
    public void detail(int i) {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.detail_khachthue);
        TextView tvname=dialog.findViewById(R.id.thongtinname);
        TextView tvsdt=dialog.findViewById(R.id.thongtinsdt);
        TextView tvcccd=dialog.findViewById(R.id.thongtincccd);
        TextView tvophong=dialog.findViewById(R.id.thongtinophong);
        tvname.setText("Họ và Tên: "+list.get(i).getHoTen());
        tvcccd.setText("Số Điện Thoại: 0"+list.get(i).getSdt());
        tvsdt.setText("Số CCCD: "+list.get(i).getCccd());
        PhongDAO phongDAO=new PhongDAO(context);
        Log.d("cccc", "detail: "+list.get(i).getIdKhachThue());
        Phong phong=phongDAO.getUserById(String.valueOf(list.get(i).getIdPhong()));
        if(phong==null){
            tvophong.setText("Khách Chưa Thuê Phòng nào");
        }else {
            tvophong.setText("Phòng Ở: "+phong.getSoPhong());
        }
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        dialog.show();
    }
}
