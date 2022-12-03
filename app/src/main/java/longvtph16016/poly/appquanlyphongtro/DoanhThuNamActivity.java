package longvtph16016.poly.appquanlyphongtro;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import Adapter.hoaonDoanhThuAdapter;
import DAO.HoaDonDao;
import Model.HoaDon;

public class DoanhThuNamActivity extends AppCompatActivity {
    private List<String> listthang,listnam;
    Button btn_xemDTN;
    Spinner namDoanThu;
    String nams;
    TextView textViewDoanhThu;
    ListView listView;
    List<HoaDon> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doanh_thu_nam);
        namDoanThu=findViewById(R.id.id_spinner_namDt);
        btn_xemDTN = findViewById(R.id.btn_xemDTN);
        listnam = new ArrayList<>();
        for (int i=2015;i<2100;i++){
            listnam.add(i+"");
        }
        textViewDoanhThu=findViewById(R.id.tvdoanhthu);
        listView=findViewById(R.id.listDoanhThunam);
        namDoanThu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nams=listnam.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter spinnerAdapter2 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listnam);
        namDoanThu.setAdapter(spinnerAdapter2);


        btn_xemDTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                String daunam=nams+"-01/01";
                String cuoinam=nams+"12/31";
                int doanhthu=0;
                HoaDonDao donDao=new HoaDonDao(getApplicationContext());
                list =donDao.gethoadonByNgay(daunam,cuoinam);


                    for(int i=0; i<list.size();i++){
                        doanhthu=doanhthu+list.get(i).getTong();
                    }

                textViewDoanhThu.setText("Doanh Thu Năm "+nams+": "+doanhthu+" đ");
                textViewDoanhThu.setVisibility(View.INVISIBLE);
                hoaonDoanhThuAdapter adapter=new hoaonDoanhThuAdapter(DoanhThuNamActivity.this,list);
                listView.setAdapter(adapter);
            }
        });

    }
}