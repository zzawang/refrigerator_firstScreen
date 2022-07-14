package com.example.firstscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;

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
        myRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        myRecyclerView.setHasFixedSize(true);

        myDialog = new PlusDialog(MainActivity.this, myViewModel,-1);

        binding.addButton.setOnClickListener(view -> {
            myDialog.show();
        });

        /*

        public boolean onContextItemSelected(MenuItem item) {

            switch (item.getItemId()) {
                case R.id.edit:

                    return true;
                case R.id.delete:

                    return true;
                default:
                    return super.onContextItemSelected(item);
            }
            return super.onContextItemSelected(item);
        }
         */


        /*
        public boolean onContextItemSelected(MenuItem item) {
            final int position = mRecyclerAdapter.getPosition();
            final PhRecyclerItem item = (PhRecyclerItem) mItemList.get(position);

            switch (a_item.getItemId()) {
                case R.id.action_insert:
                    Toast.makeText(this, item.getName() + " " + getString(R.string.insert), Toast.LENGTH_SHORT).show();
                    break;

                case R.id.action_delete:
                    Toast.makeText(this, item.getName() + " " + getString(R.string.delete), Toast.LENGTH_SHORT).show();
                    break;

                case R.id.action_modify:
                    Toast.makeText(this, item.getName() + " " + getString(R.string.modify), Toast.LENGTH_SHORT).show();
                    break;

                case R.id.action_info:
                    Toast.makeText(this, item.getName() + " " + getString(R.string.info), Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }

            return true;
        }
         */

        final Observer<ArrayList<String>> userObserver = strings -> {
            myAdapter.notifyDataSetChanged();
            Log.e("user observer", "myAdapter.notifyDataSetChanged()");
        };

        Observer<Integer> itemObserver = Integer -> {
            myDialog.show();
        };

        // registerForContextMenu(binding.recyclerView)

        myViewModel.usersLivedata.observe(this, userObserver);
        myViewModel.userClickEvent.observe(this,itemObserver);
    }


}

