package Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import Adapter.HopDongAdapter;
import DAO.HopDongDAO;
import Model.HopDong;
import longvtph16016.poly.appquanlyphongtro.R;

public class HopDongFragment extends Fragment {
    HopDongAdapter hopDong_adapter;
    private HopDongDAO hopDongDAO;
    ListView listViewHopDong;
//    private Button btnthemHopDong;
//    EditText edtNgayBatDau;
//    EditText edtNgayKetThuc;
//    EditText edtSoLuongXe;
//    EditText edtTienCoc;
//    EditText edtSoNguoi;


    FloatingActionButton fab;
    private List<HopDong> list = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hop_dong, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        hopDongDAO=new HopDongDAO(getContext());
          list=hopDongDAO.getAll();

        listViewHopDong=view.findViewById(R.id.rec_HopDong);
        hopDong_adapter= new HopDongAdapter(getContext(),list);
        listViewHopDong.setAdapter(hopDong_adapter);
//        btnthemHopDong = view.findViewById(R.id.btn_Tao_HopDong);
//        edtNgayBatDau = view.findViewById(R.id.edt_NgayBatDau_HopDong);
//        edtNgayKetThuc = view.findViewById(R.id.edt_NgayKetThuc_HopDong);
//        edtSoLuongXe = view.findViewById(R.id.edt_SoLuongXe_HopDong);
//        edtSoNguoi = view.findViewById(R.id.edt_SoNguoi_HopDong);
//        edtTienCoc = view.findViewById(R.id.edt_TienCoc_HopDong);

//        btnthemHopDong.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Boolean check = true;
//                String ngayBatDau = edtNgayBatDau.getText().toString();
//                String ngayKetThuc = edtNgayKetThuc.getText().toString();
//                String soLuongXe = edtSoLuongXe.getText().toString();
//                String soNguoi =    edtSoNguoi.getText().toString();
//                String tienCoc = edtTienCoc.getText().toString();
//
//                if(ngayBatDau.length()==0){
//                    edtNgayBatDau.requestFocus();
//                    Toast.makeText(getContext(), "Hãy nhập ngày bắt đầu", Toast.LENGTH_SHORT).show();
//                    check = false;
//                }
//
//                if(ngayKetThuc.length()==0){
//                    edtNgayKetThuc.requestFocus();
//                    Toast.makeText(getContext(), "Hãy nhập ngày kết thúc", Toast.LENGTH_SHORT).show();
//                    check = false;
//                }
//                if(!tienCoc.matches("[0-99999]*")){
//                    Toast.makeText(getContext(), "Nhập sai, vui lòng nhập từ 0 trở lên", Toast.LENGTH_SHORT).show();
//                    check = false;
//                }
//                if (tienCoc.length() >8) {
//                    Toast.makeText(getContext(), "Tiền cọc không vượt quá 8 ký tự", Toast.LENGTH_SHORT).show();
//                    check = false;
//                }
//                if(soNguoi.matches("[0-99999]*")){
//                    Toast.makeText(getContext(), "Nhập sai, vui lòng nhập từ 0 trở lên", Toast.LENGTH_SHORT).show();
//                    check = false;
//                }
//                if(soLuongXe.matches("[0-99999]*")){
//                    Toast.makeText(getContext(), "Nhập sai, vui lòng nhập từ 0 trở lên", Toast.LENGTH_SHORT).show();
//                    check = false;
//                }
//            }
//        });
    }
    private void addHopDong(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.tao_hop_dong, null);


        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
    }
}
