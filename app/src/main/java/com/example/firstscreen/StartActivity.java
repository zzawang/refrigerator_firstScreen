package com.example.firstscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.firstscreen.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity {

    private ActivityStartBinding activityStartBinding; // ActivityStartBinding binding
    private Button startButton; // 로그인 액티비티 이동 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityStartBinding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(activityStartBinding.getRoot());

        // 초기화
        startButton = activityStartBinding.startLoginButton;

        // 로그인 액티비티 이동 버튼 클릭
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, FirstLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}