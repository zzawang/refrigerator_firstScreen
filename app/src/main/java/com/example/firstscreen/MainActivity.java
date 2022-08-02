package com.example.firstscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.firstscreen.databinding.ActivityMainBinding;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private InformationDialog informationDialog; // 안내 다이얼로그
    private DatabaseReference mPostReference;
    private FirebaseAuth firebaseAuth; // 파이어베이스 인증
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private MyViewModel viewModel;
    private PlusDialog plusDialog;
    private ImageButton addButton;
    private ImageView logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        recyclerView = activityMainBinding.mainRecyclerView;
        adapter = new MyAdapter(viewModel);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new RecyclerviewDeco(5));

        addButton = activityMainBinding.mainAddButton;
        logoutButton = activityMainBinding.mainLogOutButton;

        addButton.setOnClickListener(view -> {
            plusDialog = new PlusDialog(MainActivity.this, viewModel, -1);
            plusDialog.show();
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 로그아웃 다이얼로그
                informationDialog = new InformationDialog(MainActivity.this, 2);
                informationDialog.show();

            }
        });


        final Observer<ArrayList<String>> userObserver = strings -> {
            adapter.notifyDataSetChanged();
            Log.e("user observer", "myAdapter.notifyDataSetChanged()");
        };

        registerForContextMenu(recyclerView);
        viewModel.usersLivedata.observe(this, userObserver);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                int position = viewModel.userPos;
                plusDialog = new PlusDialog(MainActivity.this, viewModel, position);
                plusDialog.show();
                break;
            case R.id.delete:
                viewModel.deleteUsers(viewModel.userPos);
                break;
        }
        return true;
    }
}