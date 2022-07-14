package com.example.firstscreen;

import android.app.Activity;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.firstscreen.databinding.FirstScreenItemBinding;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private MyViewModel viewModel;

    public MyAdapter(MyViewModel viewModel){
        this.viewModel = viewModel;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView refriPlustext ;
        public ViewHolder(@NonNull View view) {
            super(view);
            this.refriPlustext = view.findViewById(R.id.refri_plustext);
            view.setOnCreateContextMenuListener((View.OnCreateContextMenuListener) this);
        }

        public void onCreateContextMenu(ContextMenu a_menu, View a_view, ContextMenu.ContextMenuInfo a_menuInfo) {
            ((Activity) a_view.getContext()).getMenuInflater().inflate(R.menu.refrigerator_menu, a_menu);
        }

        void setContents(int position){
            refriPlustext.setText(viewModel.users.get(position));
        }
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.first_screen_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, int position) {
        viewHolder.setContents(position);
    }

    @Override
    public int getItemCount() {
        return viewModel.getItemSize();
    }

}
