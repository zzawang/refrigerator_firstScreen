package com.example.firstscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.firstscreen.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecyclerView myRecyclerView;
    private MyAdapter myadapter;
    private MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myViewModel = new MyViewModel();
        myRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_firstScreen);
        myRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        myRecyclerView.setHasFixedSize(true);
        /* initiate adapter */
        myadapter = new MyAdapter(myViewModel);
        //myadapter.setClickListener(this);
        /* initiate recyclerview */
        myRecyclerView.setAdapter(myadapter);
        /* adapt data */



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

