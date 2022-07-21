package com.example.firstscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.firstscreen.databinding.ActivityMainBinding;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private MyViewModel viewModel;
    private PlusDialog plusDialog;
    private DatabaseReference mPostReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        recyclerView = binding.recyclerViewFirstScreen;
        adapter = new MyAdapter(viewModel);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new recyclerviewDeco(5));


        binding.addButton.setOnClickListener(view -> {
            plusDialog = new PlusDialog(MainActivity.this, viewModel, -1);
            plusDialog.show();
        });


        final Observer<ArrayList<String>> userObserver = strings -> {
            adapter.notifyDataSetChanged();
            Log.e("user observer", "myAdapter.notifyDataSetChanged()");
        };

        registerForContextMenu(binding.recyclerViewFirstScreen);
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
            case R.id.delete: // 카테고리 삭제하기
                viewModel.deleteUsers(viewModel.userPos);
                break;
        }
        return true;
    }
}