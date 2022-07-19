package com.example.firstscreen;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.firstscreen.databinding.ActivityMainBinding;
import com.example.firstscreen.databinding.DialogPlusBinding;

public class PlusDialog extends Dialog {

    private DialogPlusBinding dialogPlusBinding;
    private MyViewModel viewModel;
    private EditText edit_refrigerator_name;
    private Context myContext;
    //private int itemPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogPlusBinding = DialogPlusBinding.inflate(getLayoutInflater());
        setContentView(dialogPlusBinding.getRoot());

        edit_refrigerator_name = dialogPlusBinding.refrigeratorName;
        Button okButton = dialogPlusBinding.buttonOK;
        Button cancelButton = dialogPlusBinding.buttonCancel;



        // 버튼 리스너 설정
        okButton.setOnClickListener(view -> {
            Log.e("dialog add user", edit_refrigerator_name.getText().toString());
            viewModel.addUsers(edit_refrigerator_name.getText().toString());
            dismiss();
        });

        cancelButton.setOnClickListener(view -> {
            dismiss();
        });

    }

    public PlusDialog(Context mContext, MyViewModel myViewModel) {
        super(mContext);
        this.myContext = mContext;
        this.viewModel = myViewModel;
        //this.itemPos = pos;
    }

}