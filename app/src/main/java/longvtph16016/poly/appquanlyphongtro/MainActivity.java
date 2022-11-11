package longvtph16016.poly.appquanlyphongtro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import Fragment.PhongFragment;
import Fragment.*;

public class MainActivity extends AppCompatActivity {
    MaterialToolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolBar_);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Quản lý phòng");
        // Kết Thúc Xử Lí Toolbar
        // ánh xạ drawer vs navigation
        drawerLayout = findViewById(R.id.Draw_layout);
        navigationView = findViewById(R.id.Draw_nav);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_main, new PhongFragment()).commit();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_phongtro:
                        replaceFragment(new PhongFragment());
                        getSupportActionBar().setTitle("Quản Lý Phòng");
                        break;
                    case R.id.nav_khachthue:
                        replaceFragment(new KhachThueFragment());
                        getSupportActionBar().setTitle("Quản Lý Khách Thuê");
                        break;
                    case R.id.nav_hopdong:
                        replaceFragment(new HopDongFragment());
                        getSupportActionBar().setTitle("Quản Lý Hợp Đồng");
                        break;
                    case R.id.nav_hoadon:
                        replaceFragment(new HoaDonFragment());
                        getSupportActionBar().setTitle("Quản Lý Hóa Đơn");
                        break;
                    case R.id.nav_doanhthu:
                     //   replaceFragment(new DoanhThuFragment());
                        getSupportActionBar().setTitle("Doanh Thu");
                        break;
                }
                drawerLayout.closeDrawer(navigationView);
                return false;
            }
        });
    }
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main, fragment, fragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(navigationView);

        }

        return super.onOptionsItemSelected(item);
    }
}