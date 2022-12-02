package longvtph16016.poly.appquanlyphongtro;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Adapter.HoaDonAdapter;
import Adapter.hoaonDoanhThuAdapter;
import DAO.HoaDonDao;
import Model.HoaDon;

public class DoanhThuThangActivity extends AppCompatActivity {
    private List<String> listthang,listnam;
    Spinner spinnerThang,spinnerNam;
    Button btn_xemDTT;
    String thangs,nam;
    TextView textViewDoanhThu;
    ListView listView;
    List<HoaDon> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_doanh_thu_thang);
        textViewDoanhThu=findViewById(R.id.tvdoanhthu);
        listView=findViewById(R.id.listDoanhThuThang);
        btn_xemDTT = findViewById(R.id.btn_xemDTT);
        spinnerThang=findViewById(R.id.id_spinner_thang);
        spinnerNam=findViewById(R.id.id_spinner_nam);

        listthang = new ArrayList<>();
        listnam = new ArrayList<>();
        listthang.add("01");
        listthang.add("02");
        listthang.add("03");
        listthang.add("04");
        listthang.add("05");
        listthang.add("06");
        listthang.add("07");
        listthang.add("08");
        listthang.add("09");
        listthang.add("10");
        listthang.add("11");
        listthang.add("12");
        for (int i=2015;i<2100;i++){
            listnam.add(i+"");
        }
        ArrayAdapter spinnerAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listthang);

        ArrayAdapter spinnerAdapter2 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listnam);

        spinnerThang.setAdapter(spinnerAdapter);
        spinnerThang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                thangs=listthang.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerNam.setAdapter(spinnerAdapter2);
        spinnerNam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nam=listnam.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_xemDTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                String dauthang=nam+"-"+thangs+"-"+"01";
                String cuoithang=nam+"-"+thangs+"-"+"30";
                int doanhthu=0;
                    HoaDonDao donDao=new HoaDonDao(getApplicationContext());
                list =donDao.gethoadonByNgay(dauthang,cuoithang);
                if (list.size()<0){
                    textViewDoanhThu.setText("Doanh Thu Tháng "+thangs+"/"+nam+" = 0 đ");
                    return;
                }
                else{

                    for(int i=0; i<list.size();i++){
                        doanhthu=doanhthu+list.get(i).getTong();
                    }
                }
                textViewDoanhThu.setText("Doanh Thu Tháng"+thangs+"/"+nam+": "+doanhthu+" đ");
                hoaonDoanhThuAdapter adapter=new hoaonDoanhThuAdapter(DoanhThuThangActivity.this,list);
                listView.setAdapter(adapter);
            }
        });

    }

}