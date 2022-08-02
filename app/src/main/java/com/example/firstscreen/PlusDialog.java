package com.example.firstscreen;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.firstscreen.databinding.DialogPlusBinding;

public class PlusDialog extends Dialog {

    private DialogPlusBinding dialogPlusBinding;
    private MyViewModel viewModel;
    private EditText edit_refrigerator_name;
    private Context myContext;
    private WarningDialog warningDialog;
    private int itemPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogPlusBinding = DialogPlusBinding.inflate(getLayoutInflater());
        setContentView(dialogPlusBinding.getRoot());

        edit_refrigerator_name = dialogPlusBinding.plusDialogEditText;
        Button okButton = dialogPlusBinding.pludDialogbuttonOk;
        Button cancelButton = dialogPlusBinding.plusDialogbuttonCancel;



        // 버튼 리스너 설정
        okButton.setOnClickListener(view -> {
            Log.e("dialog add user", edit_refrigerator_name.getText().toString());
            if(itemPos<=-1){
                if(!edit_refrigerator_name.getText().toString().equals("")){
                    Log.e(edit_refrigerator_name.getText().toString(),"은 null값이 아님");
                    viewModel.addUsers(edit_refrigerator_name.getText().toString());
                }
                else{
                    warningDialog = new WarningDialog(myContext, 3);
                    warningDialog.show();
                    return;
                }
            }
            else{
                viewModel.updateUsers(itemPos, edit_refrigerator_name.getText().toString());
            }
            dismiss();
        });

        cancelButton.setOnClickListener(view -> {
            dismiss();
        });

    }

    public PlusDialog(Context context, MyViewModel viewModel, int pos) {
        super(context);
        this.myContext = context;
        this.viewModel = viewModel;
        this.itemPos = pos;
    }

}