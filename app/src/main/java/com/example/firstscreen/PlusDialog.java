package com.example.firstscreen;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.firstscreen.databinding.ActivityMainBinding;
import com.example.firstscreen.databinding.DialogPlusBinding;

public class PlusDialog extends Dialog {

    private DialogPlusBinding dialogPlusBinding;
    private MyViewModel myViewModel;
    private EditText edit_refrigerator_name;
    private Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogPlusBinding = DialogPlusBinding.inflate(getLayoutInflater());
        setContentView(dialogPlusBinding.getRoot());

        // 다이얼로그의 배경을 투명으로 만든다.
        //Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        edit_refrigerator_name = dialogPlusBinding.refrigeratorName;
        Button okButton = findViewById(R.id.buttonOK);
        Button cancelButton = findViewById(R.id.buttonCancel);

        // 버튼 리스너 설정
        okButton.setOnClickListener(view -> {
            // 냉장고 추가
            myViewModel.addUsers(dialogPlusBinding.refrigeratorName.getText().toString());
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