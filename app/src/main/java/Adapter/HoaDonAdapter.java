package Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import Model.HoaDon;
import longvtph16016.poly.appquanlyphongtro.R;
import longvtph16016.poly.appquanlyphongtro.interfaceDeleteClickdistioner;

public class HoaDonAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HoaDon> list;
    private longvtph16016.poly.appquanlyphongtro.interfaceDeleteClickdistioner interfaceDeleteClickdistioner;



    public HoaDonAdapter(Context context, interfaceDeleteClickdistioner interfaceDeleteClickdistioner) {
        this.context = context;
        this.interfaceDeleteClickdistioner = interfaceDeleteClickdistioner;
    }

    public void setData(ArrayList<HoaDon> arrayList){
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
        myViewHolder.tv_sohoadon.setText("Hoa Don Phòng"+": "+list.get(i).getIdHoaDon());

        ln_item_dv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_bottom_hoadon);

                LinearLayout editLayout = dialog.findViewById(R.id.edt_update_hd);
                LinearLayout delete_layout = dialog.findViewById(R.id.edt_delete_hd);

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);


                delete_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        interfaceDeleteClickdistioner.OnClickDelete(i);
                    }
                });
            }
        });


        return view;
    }
}

