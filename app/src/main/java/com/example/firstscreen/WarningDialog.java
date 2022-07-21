package com.example.firstscreen;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.firstscreen.databinding.DialogWarningBinding;

public class WarningDialog extends Dialog {

    private DialogWarningBinding dialogWarningBinding;
    private Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogWarningBinding = DialogWarningBinding.inflate(getLayoutInflater());
        setContentView(dialogWarningBinding.getRoot());

        Button okButton = dialogWarningBinding.warningDialogbuttonOk;

        // 버튼 리스너 설정
        okButton.setOnClickListener(view -> {
            Log.e("warningDialog의 ","OK 버튼이 눌림");
            dismiss();
        });

    }

    public WarningDialog(Context mContext) {
        super(mContext);
        this.myContext = mContext;
    }

}