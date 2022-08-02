package com.example.firstscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firstscreen.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding activityRegisterBinding; // RegisterActivity Binding
    private WarningDialog warningDialog; // 경고 다이얼로그
    private InformationDialog informationDialog; // 안내 다이얼로그
    private FirebaseAuth firebaseAuth; // 파이어베이스 인증
    private DatabaseReference databaseReference; // 실시간 데이터베이스
    private EditText registerEmail, registerPw, registerPwCheck; // 회원가입 입력 필드 (아이디, 비밀번호, 비밀번호 확인)
    private Button registerButton; // 회원가입 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(activityRegisterBinding.getRoot());

        // 초기화
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        registerEmail = activityRegisterBinding.registerEmailText;
        registerPw = activityRegisterBinding.registerPwText;
        registerPwCheck = activityRegisterBinding.registerPwCheckText;
        registerButton = activityRegisterBinding.registerButton;


        // 회원가입 버튼 클릭 후 회원가입 처리 시작
        registerButton.setOnClickListener(view -> {
            String email = registerEmail.getText().toString();
            String password = registerPw.getText().toString();

            // 이메일과 비밀번호가 비어있을 경우 경고
            if(email.equals("") && password.equals("")){
                warningDialog = new WarningDialog(RegisterActivity.this, 1);
                warningDialog.show();
            }

            // 비밀번호가 6글자 이상 넘지 않으면 경고
            else if (password.length()<6){
                warningDialog = new WarningDialog(RegisterActivity.this, 2);
                warningDialog.show();
            }

            else{
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // task는 실제로 회원가입 처리를 한 후의 결과값이다.
                        if(task.isSuccessful()){
                            // 방금 회원가입 처리가 된 user를 가져온다.
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            UserAccount userAccount = new UserAccount();
                            userAccount.setIdToken(firebaseUser.getUid());
                            userAccount.setUserEmail(firebaseUser.getEmail());
                            userAccount.setUserPw(password);

                            // database에 user 정보 삽입
                            databaseReference.child("UserAccount").child(firebaseUser.getUid()).setValue(userAccount);

                            // 회원가입 성공 다이얼로그
                            informationDialog = new InformationDialog(RegisterActivity.this, 1);
                            informationDialog.show();

                            //Toast.makeText(RegisterActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                            Log.e("회원가입 성공", userAccount.getIdToken()+" "+userAccount.getUserEmail()+" "+userAccount.getUserPw());

                        }
                        else {
                            // 회원가입 실패 다이얼로그
                            warningDialog = new WarningDialog(RegisterActivity.this, 4);
                            warningDialog.show();

                            //Toast.makeText(RegisterActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
                            Log.e("회원가입 실패","");
                        }
                    }
                });
            }
        });
    }
}