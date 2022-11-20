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
import java.util.List;

import Adapter.HopDongAdapter;
import DAO.HopDongDAO;
import Model.HopDong;
import longvtph16016.poly.appquanlyphongtro.R;

public class HopDongFragment extends Fragment {
    HopDongAdapter hopDong_adapter;
    private HopDongDAO hopDongDAO;
    ListView listViewHopDong;
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
        fab = view.findViewById(R.id.fab_addHopDong);
        hopDongDAO=new HopDongDAO(getContext());
//        list=hopDongDAO.getAll();
        list.add(new HopDong(1,"22/11/2022","22/10/2023",3,3,300000,true,1,1));
        listViewHopDong=view.findViewById(R.id.rec_HopDong);
        hopDong_adapter= new HopDongAdapter(getContext(),list);
        listViewHopDong.setAdapter(hopDong_adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addHopDong(getContext());
            }
        });
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