package com.example.firstscreen;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class MyViewModel extends ViewModel {

    private MutableLiveData<ArrayList<String>> users;
    private Integer itemPos = -1;
    public MyViewModel(){

    }

    public String getUsers(int pos) {
        return
    }

    public void addUsers(String user){

    }
    public Integer getItemPos() {
        return itemPos;
    }

    public String getName(){

        return ;
    }
}
