package com.example.firstscreen;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.firstscreen.databinding.ActivityFirstLoginBinding;

public class FirstLoginActivity extends AppCompatActivity {

    private ActivityFirstLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFirstLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

}