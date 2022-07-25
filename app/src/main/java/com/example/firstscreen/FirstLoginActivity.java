package com.example.firstscreen;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firstscreen.databinding.ActivityFirstLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirstLoginActivity extends AppCompatActivity {

    private ActivityFirstLoginBinding binding;
    private FirebaseAuth firebaseAuth; // 파이어베이스 인증
    private DatabaseReference databaseReference; // 실시간 데이터베이스
    private EditText firstLoginEmail, firstLoginPw; // 로그인 입력 필드 (아이디, 비밀번호)
    private Button loginButton, registerButton; // 로그인, 회원가입 버튼


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFirstLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        firstLoginEmail = binding.firstLoginEmailText;
        firstLoginPw = binding.firstLoginPwText;
        loginButton = binding.firstLoginButton;
        registerButton = binding.firstRegisterButton;

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 로그인 요청
                String email = firstLoginEmail.getText().toString();
                String password = firstLoginPw.getText().toString();

                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(FirstLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            // 로그인 성공
                            // MainActivity(냉장고 추가 화면)으로 넘어간다.
                            Intent intent = new Intent(FirstLoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            // 로그인 activity 종료
                            finish();
                            Toast.makeText(FirstLoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                            Log.e("로그인 성공", "email : "+email+" password :  "+password);
                        }
                        else {
                            // 로그인 실패
                            Toast.makeText(FirstLoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                            Log.e("로그인 실패", "email : "+email+" password :  "+password);
                        }
                    }
                });
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 회원가입 화면으로 이동
                Intent intent = new Intent(FirstLoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}