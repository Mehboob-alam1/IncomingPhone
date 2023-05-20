package com.mehboob.incomingphone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.mehboob.incomingphone.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
   private SharedPref sharedPref;
   private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPref = new SharedPref(this);


        binding.edittextApi.setText(sharedPref.fetchApi());
        binding.edittextHotelCode.setText(sharedPref.fetchRestaurantId());

        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},1);
        }

        binding.btnSave.setOnClickListener(v -> {
            if (binding.edittextApi.getText().toString().isEmpty())
                Toast.makeText(this, "Enter your api", Toast.LENGTH_SHORT).show();
            else if (binding.edittextHotelCode.getText().toString().isEmpty())
                Toast.makeText(this, "Enter restaurant id", Toast.LENGTH_SHORT).show();
            else{


                sharedPref.saveApi(binding.edittextApi.getText().toString());
                sharedPref.saveRestaurantId(binding.edittextHotelCode.getText().toString());
                Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show();
                binding.edittextHotelCode.setText("");
                binding.edittextApi.setText("");
            }
        });


    }
}