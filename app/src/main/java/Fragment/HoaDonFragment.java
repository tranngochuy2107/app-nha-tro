package Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import Adapter.HoaDonAdapter;
import DAO.HoaDonDao;
import Model.HoaDon;
import longvtph16016.poly.appquanlyphongtro.R;
import longvtph16016.poly.appquanlyphongtro.interfaceDeleteClickdistioner;


public class HoaDonFragment extends Fragment implements interfaceDeleteClickdistioner {

    EditText  Ed_NgayTao_HDon, Ed_NhapSoDien_HDon, Ed_NhapSoNuoc_HDon, Ed_ChiPhiKhac_HDon,Ed_TongTien_HDon,Ed_GhiChu_HDon,ed_tenhoaDon;
    Spinner  Ed_ChonSoPhong;
    Button Btn_huy_HDon, Btn_them_HDon;
    FloatingActionButton fab;
    ListView rcv_hoadon;
    ImageView image_ngay;

    HoaDonAdapter HoaDonAdapter;
    private HoaDonDao hoaDonDao;
    private List<HoaDon> list = new ArrayList<>();
    Context context;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab = view.findViewById(R.id.fab_addHoaDon);
        rcv_hoadon = view.findViewById(R.id.rec_HoaDon);
        context = this.getActivity();
        hoaDonDao = new HoaDonDao(context);
        list = hoaDonDao.getAll();
        HoaDonAdapter = new HoaDonAdapter(context, this::OnClickDelete);
        HoaDonAdapter.setData(list);
        rcv_hoadon.setAdapter(HoaDonAdapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                openDialog(getContext());
            }
        });
    }

    private void openDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_tao_hoa_don, null);

        Ed_NgayTao_HDon = view.findViewById(R.id.ed_NgayTao_HDon);
        ed_tenhoaDon = view.findViewById(R.id.ed_tenHoaDon);
        Ed_NhapSoDien_HDon = view.findViewById(R.id.ed_NhapSoDien_HDon);
        Ed_NhapSoNuoc_HDon = view.findViewById(R.id.ed_NhapSoNuoc_HDon);
        Ed_ChiPhiKhac_HDon = view.findViewById(R.id.ed_ChiPhiKhac_HDon);

        Ed_GhiChu_HDon = view.findViewById(R.id.ed_GhiChu_HDon);
        image_ngay= view.findViewById(R.id.image_NgayTao_HDon);
        Btn_them_HDon= view.findViewById(R.id.btn_them_HDon);
        Btn_huy_HDon= view.findViewById(R.id.btn_huy_HDon);

        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

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

        String ngays = Ed_NgayTao_HDon.getText().toString();
        String tenhoadon = ed_tenhoaDon.getText().toString();
        String giadienS = Ed_NhapSoDien_HDon.getText().toString();
        String gianuocS = Ed_NhapSoNuoc_HDon.getText().toString();
        String chiphikhacs = Ed_ChiPhiKhac_HDon.getText().toString();
        int chiphikhac = 0;

        Btn_them_HDon.setOnClickListener(new View.OnClickListener() {
            HoaDon HoaDon;

            @Override
            public void onClick(View view) {

            }
        });


        Btn_huy_HDon
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hoa_don, container, false);
    }


    @Override
    public void OnClickDelete(int index) {
        deletedialog(index);
    }

    public void deletedialog(int index) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setTitle("bạn có chắc chắn muốn xóa không?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (hoaDonDao.deleteHoaDon(list.get(index)) > 0) {
                    list.remove(index);
                    HoaDonAdapter.setData(list);
                    Toast.makeText(context, "xóa thành công",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "xóa không thành công",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
        builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}
