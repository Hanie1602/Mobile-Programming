package com.example.lab10;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab10.api.TraineeRepository;
import com.example.lab10.api.TraineeService;
import com.example.lab10.model.Trainee;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TraineeService traineeService;
    EditText etname, etemail, etphone, etgioitinh;
    Button btnSave;
    Button btnUpdate;
    Button btnDelete;
    Button btnViewAll;
    Spinner spIdList;
    ListView lvTrainees;
    ArrayList<String> allId = new ArrayList<>();
    ArrayList<String> traineeDisplayList = new ArrayList<>();
    ArrayList<Trainee> fullTraineeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Thêm
        etname = findViewById(R.id.name);
        etemail = findViewById(R.id.email);
        etphone = findViewById(R.id.phone);
        etgioitinh = findViewById(R.id.gender);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        traineeService = TraineeRepository.getTraineeService();
        spIdList = findViewById(R.id.spIdList);
        loadAllTrainees(); // ✅ Load danh sách ID sẵn vào Spinner

        //Xem danh sách
        btnViewAll = findViewById(R.id.btnViewAll);
        lvTrainees = findViewById(R.id.lvTrainees);
        btnViewAll.setOnClickListener(v -> loadAndShowTrainees());

        //Sửa
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(v -> updateTrainee());

        lvTrainees.setOnItemClickListener((parent, view, position, id) -> {
            Trainee selectedTrainee = fullTraineeList.get(position);

            // Đổ dữ liệu lên form
            etname.setText(selectedTrainee.getName());
            etemail.setText(selectedTrainee.getEmail());
            etphone.setText(selectedTrainee.getPhone());
            etgioitinh.setText(selectedTrainee.getGender());

            // Đồng bộ luôn ID vào Spinner để sửa đúng
            int spinnerIndex = allId.indexOf(String.valueOf(selectedTrainee.getId()));
            if (spinnerIndex >= 0) {
                spIdList.setSelection(spinnerIndex);
            }
        });

        //Nút xóa
        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(v -> deleteTrainee());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    //Lưu
    private void save() {
        String name = etname.getText().toString();
        String email = etemail.getText().toString();
        String phone = etphone.getText().toString();
        String gender = etgioitinh.getText().toString();

        // Tạo dữ liệu dạng Trainee (đối tượng Trainee)
        Trainee trainee = new Trainee(name, email, phone, gender);

        try {
            Call<Trainee> call = traineeService.createTrainees(trainee);
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if (response.body() != null) {
                        Toast.makeText(MainActivity.this, "Save successfully", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Save Fail", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Log.d("Lỗi", e.getMessage());
        }
    }

    //Xem toàn bộ danh sách
    private void loadAllTrainees() {
        Call<Trainee[]> call = traineeService.getAllTrainees();
        call.enqueue(new Callback<Trainee[]>() {
            @Override
            public void onResponse(Call<Trainee[]> call, Response<Trainee[]> response) {
                Trainee[] trainees = response.body();
                if (trainees == null) return;

                allId.clear(); // Xóa dữ liệu cũ
                for (Trainee trainee : trainees) {
                    allId.add(String.valueOf(trainee.getId())); // Lưu ID dạng String
                }

                ArrayAdapter adapter = new ArrayAdapter(
                        MainActivity.this,
                        android.R.layout.simple_spinner_dropdown_item,
                        allId
                );
                spIdList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Trainee[]> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Không load được danh sách", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Chức năng xem toàn bộ danh sách [Nút xem]
    private void loadAndShowTrainees() {
        Call<Trainee[]> call = traineeService.getAllTrainees();
        call.enqueue(new Callback<Trainee[]>() {
            @Override
            public void onResponse(Call<Trainee[]> call, Response<Trainee[]> response) {
                Trainee[] trainees = response.body();
                if (trainees == null) return;

                fullTraineeList.clear(); // xóa danh sách cũ
                traineeDisplayList.clear(); // hiển thị lại list
                for (Trainee trainee : trainees) {
                    fullTraineeList.add(trainee); // lưu object thật
                    String info = "ID: " + trainee.getId()
                            + "\nName: " + trainee.getName()
                            + "\nEmail: " + trainee.getEmail()
                            + "\nPhone: " + trainee.getPhone()
                            + "\nGender: " + trainee.getGender();
                    traineeDisplayList.add(info);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        traineeDisplayList
                );
                lvTrainees.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Trainee[]> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Không thể tải danh sách", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Chức năng sửa
    private void updateTrainee() {
        if (spIdList.getSelectedItem() == null) {
            Toast.makeText(this, "Vui lòng chọn học viên để sửa", Toast.LENGTH_SHORT).show();
            return;
        }

        String selectedId = spIdList.getSelectedItem().toString();

        String name = etname.getText().toString();
        String email = etemail.getText().toString();
        String phone = etphone.getText().toString();
        String gender = etgioitinh.getText().toString();

        // Tạo đối tượng mới
        Trainee updatedTrainee = new Trainee(name, email, phone, gender);

        Call<Trainee> call = traineeService.updateTrainees(selectedId, updatedTrainee);
        call.enqueue(new Callback<Trainee>() {
            @Override
            public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    loadAllTrainees();
                    loadAndShowTrainees();
                } else {
                    Toast.makeText(MainActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Trainee> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Lỗi kết nối khi cập nhật", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Hàm xóa
    private void deleteTrainee() {
        if (spIdList.getSelectedItem() == null) {
            Toast.makeText(this, "Vui lòng chọn học viên để xóa", Toast.LENGTH_SHORT).show();
            return;
        }

        String selectedId = spIdList.getSelectedItem().toString();

        Call<Void> call = traineeService.deleteTrainees(selectedId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    loadAllTrainees(); // làm mới Spinner
                    loadAndShowTrainees(); // làm mới ListView
                } else {
                    Toast.makeText(MainActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Lỗi kết nối khi xóa", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSave) {
            save();
        }
    }

}