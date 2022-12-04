package Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Adapter.HopDongAdapter;
import DAO.HopDongDAO;
import Model.HopDong;
import longvtph16016.poly.appquanlyphongtro.MainActivity;
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
        checkData();
        hopDongDAO=new HopDongDAO(getContext());
          list=hopDongDAO.getAll();

        listViewHopDong=view.findViewById(R.id.rec_HopDong);
        hopDong_adapter= new HopDongAdapter(getContext(),list);
        listViewHopDong.setAdapter(hopDong_adapter);

    }
    private void addHopDong(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.tao_hop_dong, null);


        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
    }
    private void checkData(){
        hopDongDAO=new HopDongDAO(getContext());
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        String date = sdf.format(new Date());
        Log.d("sssss", "onCreate: "+date);


        List<HopDong> hopDongList=hopDongDAO.getAll();
        if(hopDongList.size()>0){
            for(int i=0;i<hopDongList.size();i++){
                String ngayhethan=hopDongList.get(i).getNgayKetThuc();
                Log.d("TAG", "checkData: "+ngayhethan);
                try {
                    Date date1=sdf.parse(date);
                    Date date2=sdf.parse(ngayhethan);
                    if(date2.compareTo(date1)<0){
                        if(hopDongList.get(i).getTrangThaiHD()==1){
                            hopDongList.get(i).setTrangThaiHD(2);
                            Log.d("TAG", "checkData: "+"đã hết hạn");
                            hopDongDAO.updateHopDong(hopDongList.get(i));
                        }

                    }
                    else {
                        Log.d("TAG", "checkData: "+hopDongList.get(i).getTrangThaiHD()+hopDongList.get(i).getIdHopDong());
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    Log.d("TAG", "checkData: "+"lỗi check");
                }
            }
        }

    }
}
