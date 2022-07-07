package com.example.firstscreen;


import androidx.lifecycle.ViewModel;


public class MyViewModel extends ViewModel {
    /*
    private MutableLiveData<List<User>> users;
    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<List<User>>();
            loadUsers();
        }
        return users;
    }
    */

    private void loadUsers() {
        // Do an asynchronous operation to fetch users.
    }
}
