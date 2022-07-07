package com.example.firstscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.firstscreen.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MyAdapter adapter;
    private String[] refrigerator_name = {"사용자 입력"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new MyAdapter(this, refrigerator_name);
        //adapter.setClickListener((MyAdapter.ItemClickListener) this);
        recyclerView.setAdapter(adapter);


        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        /*model.getUsers().observe(this, users -> {
            // update UI
        });
        */

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlusDialog myDialog = new PlusDialog(MainActivity.this);
                myDialog.show();
            }
        });

    }


}

