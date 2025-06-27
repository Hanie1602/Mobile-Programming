package com.example.se1735_duonglnt_se171916.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.se1735_duonglnt_se171916.model.Nganh;
import com.example.se1735_duonglnt_se171916.model.Sinhvien;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QLSinhvien.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng Ngành
        db.execSQL("CREATE TABLE Nganh (" +
                "IDnganh INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nameNganh TEXT)");

        // Tạo bảng Sinhvien
        db.execSQL("CREATE TABLE Sinhvien (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "date TEXT," +
                "gender TEXT," +
                "address TEXT," +
                "idNganh INTEGER," +
                "phone TEXT," +
                "FOREIGN KEY(idNganh) REFERENCES Nganh(IDnganh))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Sinhvien");
        db.execSQL("DROP TABLE IF EXISTS Nganh");
        onCreate(db);
    }

    //lấy tất cả sinh viên
    public List<Sinhvien> getAllSinhVien() {
        List<Sinhvien> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Sinhvien", null);
        while (cursor.moveToNext()) {
            list.add(new Sinhvien(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getString(6)
            ));
        }
        cursor.close();
        return list;
    }

    //Lấy tất cả Ngành
    public List<Nganh> getAllNganh() {
        List<Nganh> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Nganh", null);
        while (cursor.moveToNext()) {
            list.add(new Nganh(cursor.getInt(0), cursor.getString(1)));
        }
        cursor.close();
        return list;
    }

    //Lấy id tên ngành
    public String getNganhNameById(int idNganh) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nameNganh FROM Nganh WHERE IDnganh = ?", new String[]{String.valueOf(idNganh)});
        if (cursor.moveToFirst()) {
            String name = cursor.getString(0);
            cursor.close();
            return name;
        }
        cursor.close();
        return "Không rõ";
    }

    //Thêm Sinh viên
    public void insertSinhvien(Sinhvien sv) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", sv.getName());
        values.put("date", sv.getDate());
        values.put("gender", sv.getGender());
        values.put("address", sv.getAddress());
        values.put("idNganh", sv.getIdNganh());
        values.put("phone", sv.getPhone());

        db.insert("Sinhvien", null, values);
        db.close();
    }

    //Thêm Ngành
    public void insertNganh(int id, String nameNganh) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nameNganh", nameNganh);

        db.insert("Nganh", null, values);
        db.close();
    }

    //Cập nhật Sinh viên
    public void updateSinhvien(Sinhvien sv) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", sv.getName());
        values.put("date", sv.getDate());
        values.put("gender", sv.getGender());
        values.put("address", sv.getAddress());
        values.put("idNganh", sv.getIdNganh());
        values.put("phone", sv.getPhone());

        db.update("Sinhvien", values, "ID=?", new String[]{String.valueOf(sv.getId())});
        db.close();
    }

    //Cập nhật ngành
    public void updateNganhName(int id, String newName) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nameNganh", newName);
        db.update("Nganh", values, "IDnganh=?", new String[]{String.valueOf(id)});
        db.close();
    }

    //Xóa Ngành
    public void deleteNganhById(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("Nganh", "IDnganh=?", new String[]{String.valueOf(id)});
        db.close();
    }

    //Xóa Sinh viên
    public void deleteSinhvienById(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("Sinhvien", "ID=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
