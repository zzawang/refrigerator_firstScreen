package com.example.firstscreen;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstscreen.databinding.FirstScreenItemBinding;


public class MyAdapter(MyViewModel viewModel) extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private MyViewModel myViewModel;

    class ViewHolder extends RecyclerView.ViewHolder {
        private FirstScreenItemBinding binding;
        private TextView refrigeratorName = binding.refriPlustext;

        public ViewHolder(FirstScreenItemBinding binding) {
            super(binding.getRoot());
        }

        void setContents(int position){
            refrigeratorName.setText(myViewModel.getUsers(position).getText());
        }
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        FirstScreenItemBinding binding = FirstScreenItemBinding.inflate(layoutInflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, int position) {
        viewHolder.setContents(position);
    }

    @Override
    public int getItemCount() {
        return myViewModel.getUsers(position).size();
    }

}
