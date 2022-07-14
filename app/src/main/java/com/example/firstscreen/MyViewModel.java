package com.example.firstscreen;


import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;


public class MyViewModel extends ViewModel {

    public MutableLiveData<ArrayList<String>> usersLivedata = new MutableLiveData<>();
    public ArrayList<String> users = new ArrayList<>();

    public MutableLiveData<Integer> userClickEvent = new MutableLiveData<Integer>();
    public int userPos = -1;

    // 냉장고 사용자의 position
    public String getUsers(int pos) {
        Log.e("getUsers","getUsers");
        return users.get(pos);
    }

    // 냉장고 사용자 추가
    public void addUsers(String user){
        String text = user;
        Log.e("addUsers",text);
        users.add(user);
        usersLivedata.setValue(users);
    }

    // 냉장고 사용자 삭제
    public void deleteUsers(int pos){
        Log.e("deleteUsers","deleteUsers");
        users.remove(pos);
        usersLivedata.setValue(users);
    }

    // 냉장고 사용자 수정
    public void updateUsers(int pos, String user){
        Log.e("updateUsers","updateUsers");
        users.add(pos, user);
        usersLivedata.setValue(users);
    }

    // 전체 사용자의 수
    public Integer getItemSize() {
        return users.size();
    }


}
