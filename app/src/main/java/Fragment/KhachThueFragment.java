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
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Adapter.KhachThueAdapter;
import Adapter.PhongAdapter;
import DAO.KhachThueDAO;
import DAO.PhongDAO;
import Model.KhachThue;
import Model.Phong;
import longvtph16016.poly.appquanlyphongtro.R;

public class KhachThueFragment extends Fragment {
    FloatingActionButton fab;
    ListView rcv_khachThue;
    KhachThueAdapter khachThueAdapter;
    KhachThueDAO khachThueDAO;

    private ArrayList<KhachThue> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_khach_thue, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab = view.findViewById(R.id.fab_addKhachTHue);
        rcv_khachThue = view.findViewById(R.id.rec_KhachThue);
        list.add(new KhachThue(1,"dsadas",0333333333,33333333,3));
        list.add(new KhachThue(2,"dsada1ss",0333333333,33333333,3));
        list.add(new KhachThue(3,"ffffffs",0333333333,33333333,3));
        khachThueAdapter=new KhachThueAdapter(getContext(),list);
        khachThueDAO=new KhachThueDAO(getContext());
        rcv_khachThue.setAdapter(khachThueAdapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogAddKhachTHue();
            }
        });
    }

    private void openDialogAddKhachTHue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.themkhachthue_dialog, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
    }
}