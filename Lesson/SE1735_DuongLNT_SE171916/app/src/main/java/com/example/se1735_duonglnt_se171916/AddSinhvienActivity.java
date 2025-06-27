package com.example.se1735_duonglnt_se171916;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.se1735_duonglnt_se171916.database.SQLiteHelper;
import com.example.se1735_duonglnt_se171916.model.Nganh;
import com.example.se1735_duonglnt_se171916.model.Sinhvien;

import java.util.ArrayList;
import java.util.List;

public class AddSinhvienActivity extends AppCompatActivity {
    EditText edtName, edtDate, edtGender, edtAddress, edtPhone;
    Spinner spinnerNganh;
    Button btnSave;
    SQLiteHelper db;
    List<Nganh> nganhList;
    Button btnPickContact;
    Button btnViewMap;

    private static final int REQUEST_CONTACT = 100;
    private static final int REQUEST_PERMISSION = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sinhvien);

        edtName = findViewById(R.id.edtName);
        edtDate = findViewById(R.id.edtDate);
        edtGender = findViewById(R.id.edtGender);
        edtAddress = findViewById(R.id.edtAddress);
        edtPhone = findViewById(R.id.edtPhone);
        spinnerNganh = findViewById(R.id.spinnerNganh);
        btnSave = findViewById(R.id.btnSaveSinhvien);

        btnViewMap = findViewById(R.id.btnViewMap);

        db = new SQLiteHelper(this);

        // Load ngành từ DB vào spinner
        nganhList = db.getAllNganh();
        List<String> nganhNames = new ArrayList<>();
        for (Nganh ng : nganhList) nganhNames.add(ng.getName());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nganhNames);
        spinnerNganh.setAdapter(adapter);

        btnSave.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String date = edtDate.getText().toString().trim();
            String gender = edtGender.getText().toString().trim();
            String address = edtAddress.getText().toString().trim();
            String phone = edtPhone.getText().toString().trim();
            int idNganh = nganhList.get(spinnerNganh.getSelectedItemPosition()).getId();

            if (!name.isEmpty()) {
                Sinhvien sv = new Sinhvien(0, name, date, gender, address, idNganh, phone);
                db.insertSinhvien(sv);
                Toast.makeText(this, "Đã thêm sinh viên", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnPickContact = findViewById(R.id.btnPickContact);

        btnPickContact.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_PERMISSION);
            } else {
                pickContact();
            }
        });

        //Nút Google Map
        btnViewMap.setOnClickListener(v -> {
            String address = edtAddress.getText().toString().trim();
            if (address.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập địa chỉ!", Toast.LENGTH_SHORT).show();
                return;
            }

            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//            mapIntent.setPackage("com.google.android.apps.maps");

            // Nếu người dùng có Google Maps
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            } else {
                Toast.makeText(this, "Không tìm thấy ứng dụng bản đồ phù hợp", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Hàm mở danh bạ
    private void pickContact() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(intent, REQUEST_CONTACT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CONTACT && resultCode == RESULT_OK && data != null) {
            Uri contactUri = data.getData();
            String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};

            Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(numberIndex);
                edtPhone.setText(number);
                cursor.close();
            }
        }
    }

    //Xử lý người dùng từ chối quyền
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickContact();
            } else {
                Toast.makeText(this, "Bạn cần cấp quyền đọc danh bạ để sử dụng tính năng này", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
