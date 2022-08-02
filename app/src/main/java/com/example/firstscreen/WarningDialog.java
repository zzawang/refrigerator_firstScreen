package com.example.firstscreen;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.example.firstscreen.databinding.DialogWarningBinding;

public class WarningDialog extends Dialog {

    private DialogWarningBinding dialogWarningBinding;
    private Context myContext;
    private int myMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogWarningBinding = DialogWarningBinding.inflate(getLayoutInflater());
        setContentView(dialogWarningBinding.getRoot());

        Button okButton = dialogWarningBinding.warningDialogbuttonOk;
        ImageView warningText = dialogWarningBinding.warningDialogText;

        // 이메일과 비밀번호가 비어있는 경우
        if(myMode == 1){
            warningText.setImageResource(R.drawable.no_empty_email_password);
        }
        // 비밀번호가 6글자를 넘지 않는 경우
        else if(myMode == 2){
            warningText.setImageResource(R.drawable.write_six_password);
        }
        // 냉장고 이름이 비어있는 경우
        else if(myMode == 3){
            warningText.setImageResource(R.drawable.no_empty_refrigerator);
        }
        // 회원가입 실패시
        else if(myMode == 4){
            warningText.setImageResource(R.drawable.retry_register);
        }
        // mode가 설정되어있지 않는 경우
        else{
            warningText.setImageResource(R.drawable.empty_text);
            Log.e("warningDialog의 ","drawble mode 설정이 되어있지 않음");
        }


        // 버튼 리스너 설정
        okButton.setOnClickListener(view -> {
            Log.e("warningDialog의 ","OK 버튼이 눌림");
            dismiss();
        });

    }

    public WarningDialog(Context context, int mode) {
        super(context);
        this.myContext = context;
        this.myMode = mode;
    }

}