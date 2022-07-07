package com.example.firstscreen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.util.Objects;

public class PlusDialog extends Dialog {

    private EditText edit_refrigerator_name;
    private Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_plus);

        // 다이얼로그의 배경을 투명으로 만든다.
        //Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        edit_refrigerator_name = findViewById(R.id.refrigerator_name);
        Button okButton = findViewById(R.id.button_ok);
        Button cancelButton = findViewById(R.id.button_cancel);

        // 버튼 리스너 설정
        okButton.setOnClickListener(view -> {
            // 냉장고 추가
            edit_refrigerator_name.getText();
            dismiss();
        });
        cancelButton.setOnClickListener(view -> {
            dismiss();
        });

    }

    public PlusDialog(Context mContext) {
        super(mContext);
        this.myContext = mContext;
    }


}