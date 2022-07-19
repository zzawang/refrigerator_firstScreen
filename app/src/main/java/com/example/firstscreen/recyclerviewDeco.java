package com.example.firstscreen;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class recyclerviewDeco extends RecyclerView.ItemDecoration {

    private final int divWidth;

    public recyclerviewDeco(int divWidth) {
        this.divWidth = divWidth;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.left = divWidth;
        outRect.right = divWidth;
    }
}

