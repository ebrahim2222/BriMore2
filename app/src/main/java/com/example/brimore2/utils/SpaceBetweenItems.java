package com.example.brimore2.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class SpaceBetweenItems extends RecyclerView.ItemDecoration {
    int top , bottom ,right , left;

    public SpaceBetweenItems(int top, int bottom, int right, int left) {
        this.top = top;
        this.bottom = bottom;
        this.right = right;
        this.left = left;
    }

    @Override
    public void getItemOffsets(@NonNull @NotNull Rect outRect, @NonNull @NotNull View view, @NonNull @NotNull RecyclerView parent, @NonNull @NotNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.right = right;
        outRect.bottom = bottom;
        outRect.left = left;
        outRect.top = top;

    }


}
