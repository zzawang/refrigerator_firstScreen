package com.example.firstscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.firstscreen.databinding.ActivityStartBinding;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private ActivityStartBinding activityStartBinding; // ActivityStartBinding binding
    private FirebaseAuth firebaseAuth; // 파이어베이스 인증
    private FirebaseAuth.AuthStateListener authStateListener;
    private Button startButton; // 로그인 액티비티 이동 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityStartBinding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(activityStartBinding.getRoot());

        // 초기화
        firebaseAuth = FirebaseAuth.getInstance();
        startButton = activityStartBinding.startLoginButton;

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    // 로그인 유지시 메인엑티비티로이동
                    String userUid = firebaseUser.getUid();
                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    intent.putExtra("user의 UID", userUid);
                    StartActivity.this.startActivity(intent);
                    StartActivity.this.finish();
                } else {
                    // 로그아웃
                }
            }
        };

        // 로그인 액티비티 이동 버튼 클릭
        startButton.setOnClickListener(view -> {
            Intent intent = new Intent(StartActivity.this, FirstLoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
    @Override
    protected void onStop(){
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}