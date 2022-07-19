package com.example.firstscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import com.example.firstscreen.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecyclerView myRecyclerView;
    private MyAdapter myAdapter;
    private MyViewModel myViewModel;
    private PlusDialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        myRecyclerView = binding.recyclerViewFirstScreen;
        myAdapter = new MyAdapter(myViewModel);

        myRecyclerView.setAdapter(myAdapter);
        myRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.addItemDecoration(new recyclerviewDeco(5));


        binding.addButton.setOnClickListener(view -> {
            myDialog = new PlusDialog(MainActivity.this, myViewModel);
            myDialog.show();
        });


        final Observer<ArrayList<String>> userObserver = strings -> {
            myAdapter.notifyDataSetChanged();
            Log.e("user observer", "myAdapter.notifyDataSetChanged()");
        };

        /*
        Observer<Integer> itemObserver = Integer -> {
            myDialog.show();
        };
        */

        registerForContextMenu(binding.recyclerViewFirstScreen);

        @Override
        public boolean onContextItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.information: // 정보 버튼 - 카테고리에는 필요 없으므로 삭제해야댐!!
                    Log.e("onContextItemSelected", "정보 버튼 클릭");
                    break;
                case R.id.delete: // 카테고리 삭제하기
                    Log.e("delete", "longClickPosition = " + viewModel.longClickPosition);
                    // 데이터베이스에도 삭제해야됨
                    String deleteName = viewModel.categorys.get(viewModel.longClickPosition);
                    Log.e("delete", "delete name = " + deleteName);
                    deleteCategory(viewModel.categorys.get(viewModel.longClickPosition));
                    viewModel.deleteItem(viewModel.longClickPosition);
                    break;
            }
            return true;
        }



        myViewModel.usersLivedata.observe(this, userObserver);
        //myViewModel.userClickEvent.observe(this,itemObserver);
    }


}

