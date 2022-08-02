package com.example.firstscreen;


import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MyViewModel extends ViewModel {

    public MutableLiveData<ArrayList<String>> usersLivedata = new MutableLiveData<>();
    public ArrayList<String> users = new ArrayList<>();

    public int userPos = -1;

    private String userUid;
    private DatabaseReference mPostReference = FirebaseDatabase.getInstance().getReference();

    public MyViewModel(String uid) {
        this.userUid = uid;
    }

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
        createUserinDataBase(user);
        usersLivedata.setValue(users);
    }

    // 냉장고 사용자 삭제
    public void deleteUsers(int pos){
        Log.e("deleteUsers","deleteUsers");
        users.remove(pos);
        String user = users.get(pos);
        deleteUserinDataBase(user);
        usersLivedata.setValue(users);
    }

    // 냉장고 사용자 수정
    public void updateUsers(int pos, String user){
        Log.e("updateUsers","updateUsers");
        String beforeUser = users.get(pos);
        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put(beforeUser, user);
        users.remove(pos);
        users.add(pos, user);
        updateUserinDataBase(beforeUser, userMap);
        usersLivedata.setValue(users);
    }

    public void setUserUid(String uid){
        this.userUid = uid;
    }

    // 전체 사용자의 수
    public Integer getItemSize() {
        return users.size();
    }

    public void createUserinDataBase(String user){
        String users = user;
        mPostReference.child("냉장고").child(users).setValue("");
    }

    public void deleteUserinDataBase(String user) {
        mPostReference.child("냉장고").child(user).removeValue();
    }

    public Task<Void> updateUserinDataBase(String beforeUser, HashMap<String, Object> hashMap) {
        return mPostReference.child("냉장고").child(beforeUser).updateChildren(hashMap);
    }

    public String getUserUid(){
        return userUid;
    }
}
