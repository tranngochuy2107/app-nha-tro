package DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Database.DbHelper;
import Model.HopDong;

public class HopDongDAO {
    private SQLiteDatabase db;

    public HopDongDAO(Context mContext) {
        DbHelper dbHelper = new DbHelper(mContext);
        db= dbHelper.getWritableDatabase();
    }
    //insert
    public long insertHopDong(HopDong obj){
        ContentValues values = new ContentValues();
        values.put("NgayBatDau",obj.getNgayBatDau());
        values.put("NgayKetThuc",obj.getNgayKetThuc());
        values.put("SoNguoi",obj.getSoNguoi());
        values.put("SoLuongXe",obj.getSoLuongXe());
        values.put("TiecCoc",obj.getTiecCoc());
        values.put("TrangThaiHD",obj.getTrangThaiHD());
        values.put("IdKhachThue",obj.getIdKhachThue());
        values.put("IdPhong",obj.getIdPhong());
        return db.insert("HopDong",null,values);
    }



    //update
    public int updateHopDong(HopDong obj){
        ContentValues values = new ContentValues();
        values.put("NgayBatDau",obj.getNgayBatDau());
        values.put("NgayKetThuc",obj.getNgayKetThuc());
        values.put("SoNguoi",obj.getSoNguoi());
        values.put("SoLuongXe",obj.getSoLuongXe());
        values.put("TiecCoc",obj.getTiecCoc());
        values.put("TrangThaiHD",obj.getTrangThaiHD());
        values.put("IdKhachThue",obj.getIdKhachThue());
        values.put("IdPhong",obj.getIdPhong());
        String Id = String.valueOf(obj.getIdHopDong());
        return db.update("HopDong",values,"IdHopDong=?",new String[]{Id});
    }
    //getAll
    public List<HopDong> getAll(){
        String sql="SELECT * FROM HopDong";
        return getData(sql);
    }
    //get user by id
    public HopDong getHopDongById(String Id){
        String sql="SELECT * FROM HopDong WHERE IdHopDong=?";
        List<HopDong> list = getData(sql,Id);
        if(list!=null){
            return list.get(0);
        }
        return null;
    }
    @SuppressLint("Range")
    public List<HopDong>getData(String sql, String...SelectArgs){
        List<HopDong> list= new ArrayList<>();
        Cursor cursor= db.rawQuery(sql,SelectArgs);
        while (cursor.moveToNext()){
            HopDong user= new HopDong();
            user.setIdHopDong(cursor.getInt(cursor.getColumnIndex("IdHopDong")));
            user.setIdPhong(cursor.getInt(cursor.getColumnIndex("IdPhong")));
            user.setIdKhachThue(cursor.getInt(cursor.getColumnIndex("IdKhachThue")));
            user.setNgayBatDau(String.valueOf(cursor.getInt(cursor.getColumnIndex("NgayBatDau"))));
            user.setNgayKetThuc(String.valueOf(cursor.getInt(cursor.getColumnIndex("NgayKetThuc"))));
            user.setSoNguoi(cursor.getInt(cursor.getColumnIndex("SoNguoi")));
            user.setSoLuongXe(cursor.getInt(cursor.getColumnIndex("SoLuongXe")));
            user.setTiecCoc(cursor.getInt(cursor.getColumnIndex("TienCoc")));
           // user.setTrangThaiHD(cursor.getInt(cursor.getColumnIndex("TrangThaiHD")));
            list.add(user);
        }
        if(list!=null||list.size()!=0){
            return list;
        }
        return null;
    }
}

