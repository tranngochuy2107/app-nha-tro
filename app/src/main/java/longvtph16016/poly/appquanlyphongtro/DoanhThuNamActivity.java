package longvtph16016.poly.appquanlyphongtro;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class DoanhThuNamActivity extends AppCompatActivity {
    ImageView img_doanh_thu_nam;
    EditText ed_doanh_thu_nam;
    Button btn_xemDTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doanh_thu_nam);
        img_doanh_thu_nam = findViewById(R.id.img_doanh_thu_nam);
        ed_doanh_thu_nam = findViewById(R.id.ed_doanh_thu_nam);
        btn_xemDTN = findViewById(R.id.btn_xemDTN);

        //----------------
        Calendar calendar = Calendar.getInstance();//Lay time
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(calendar.DAY_OF_MONTH);
        //-----------------------------
        //datePicker
        img_doanh_thu_nam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DoanhThuNamActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        ed_doanh_thu_nam.setText(d + "/" + (m + 1) + "/" + y);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        btn_xemDTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean check = true;
                if (ed_doanh_thu_nam.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Không được để trống!", Toast.LENGTH_SHORT).show();
                    check = false;
                } else {
                    check = true;
                }
            }
        });

    }
}