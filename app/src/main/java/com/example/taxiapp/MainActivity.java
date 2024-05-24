package com.example.taxiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.taxiapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyLogs";
    SharedPreferences sPref;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Log.d(TAG, "MainAcitvity: onCreate");

        loadText(); // Загрузка сохраненных ранее данных пользователя
        // Если нет сохраненных данных пользователя
        if(binding.editTextPersonName.getText().toString().length() == 0) {
            binding.registrationBtn.setText("Регистрация");
        }
        // Если есть сохраненные данные пользователя
        else {
            binding.registrationBtn.setText("Вход");
        }

        Intent intent = new Intent(this, SecondActivity.class); //Обращаемся к second activity
        binding.registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveText(); //Сохраняем данные
                intent.putExtra("Phone", binding.editTextPhone.getText().toString());
                intent.putExtra("Name", binding.editTextPersonName.getText().toString());
                intent.putExtra("Surname", binding.editTextSurname.getText().toString());
                startActivity(intent); //Передаем данные
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy(); // метод родительского класса
        Log.d(TAG, "MainActivity: onDestroy"); // добавляем логи
    }

    private void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString("taxi_user_phone", binding.editTextPhone.getText().toString());
        editor.putString("taxi_user_name", binding.editTextPersonName.getText().toString());
        editor.putString("taxi_user_surname", binding.editTextSurname.getText().toString());
        editor.commit(); // сохранение данных
    }

    private void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        binding.editTextPhone.setText(sPref.getString("taxi_user_phone", ""));
        binding.editTextPersonName.setText(sPref.getString("taxi_user_name", ""));
        binding.editTextSurname.setText(sPref.getString("taxi_user_surname", ""));
    }

}