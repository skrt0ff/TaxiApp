package com.example.taxiapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.taxiapp.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    private ActivitySecondBinding binding;
    private static final String TAG = "MyLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Log.d(TAG, "SecondActivity: onCreate");

        // извлечение данных из вызывающего родительского MainActivity
        Intent intentMain = getIntent();
        binding.textViewUserInfo.setText(intentMain.getStringExtra("Name")
                + " " + intentMain.getStringExtra("Surname") + "\n"
                + intentMain.getStringExtra("Phone"));

        // пока не задан маршут, кнопка вызова такси неактивна
        binding.btnCallTaxi.setEnabled(false);

        // Обработка результата ввода маршрута
        ActivityResultLauncher<Intent> startSetPathActivityForResult = registerForActivityResult
                (new ActivityResultContracts.StartActivityForResult(),
                        new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result) {
                                Intent intent = result.getData();
                                if (result.getResultCode() == Activity.RESULT_OK) {
                                    binding.textViewPathInfo.setText(intent.getStringExtra("Path"));
                                    binding.btnCallTaxi.setEnabled(true);

                                }
                                else {
                                    binding.textViewPathInfo.setText("Set Path!");
                                }
                            }
                        });

        // Вызов неявным методом SetPathActivity
        Intent intentThirdActivity = new Intent(this, ThirdActivity.class);
        binding.btnSetPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSetPathActivityForResult.launch(intentThirdActivity);
            }
        });

        // Вызов такси
        binding.btnCallTaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this, "Wait for a taxi! Good Luck!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "SecondActivity: onDestroy");
    }
}
