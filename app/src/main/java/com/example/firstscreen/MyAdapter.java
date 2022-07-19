package com.example.firstscreen;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
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
    private PlusDialog myDialog;

    public MyAdapter(MyViewModel viewModel){
        this.viewModel = viewModel;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        private TextView refriPlustext ;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.refriPlustext = view.findViewById(R.id.refri_plustext);
            //view.setOnCreateContextMenuListener((View.OnCreateContextMenuListener) this);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition(); // 냉장고 위치 (0 ~ ...)
                Log.d("test", "position = " + getAdapterPosition());
            });

            itemView.setOnCreateContextMenuListener(this);
            itemView.setOnLongClickListener(view1 -> {
                viewModel.userPos = getAdapterPosition();
                return false;
            });
        }


        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            ((Activity)view.getContext()).getMenuInflater().inflate(R.menu.refrigerator_menu, contextMenu);
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
