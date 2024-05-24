package com.example.taxiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.taxiapp.databinding.ActivityThirdBinding;


public class ThirdActivity extends AppCompatActivity {

    private ActivityThirdBinding binding;
    private static final String TAG = "MyLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThirdBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Log.d(TAG, "ThirdActivity: onStart");

        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String stringPath = "";
                if (binding.editTextStreetFrom.getText().toString().length() *
                        binding.editTextHomeFrom.getText().toString().length() *
                        binding.editTextFlatFrom.getText().toString().length() *
                        binding.editTextStreetTo.getText().toString().length() *
                        binding.editTextHomeTo.getText().toString().length() *
                        binding.editTextFlatTo.getText().toString().length() != 0) {
                    stringPath = "Taxi will arrive at  " +
                            binding.editTextStreetFrom.getText().toString() + ", " +
                            binding.editTextHomeFrom.getText().toString() + ", " +
                            binding.editTextFlatFrom.getText().toString() +
                            " in 5 minutes and take you in " +
                            binding.editTextStreetTo.getText().toString() + ", " +
                            binding.editTextHomeTo.getText().toString() + ", " +
                            binding.editTextFlatTo.getText().toString() +
                            ". If you agree click \"CALL TAXI\".";
                    setResult(RESULT_OK, intent);
                }
                else {
                    setResult(RESULT_CANCELED, intent);
                }
                intent.putExtra("Path", stringPath);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "ThirdActivity: onDestroy");
    }
}