package com.example.submission2;

import android.view.View;

public class CustomOnItemClickListener implements View.OnClickListener {
    private int onClick;
    private OnItemClickCallback onItemClickCallback;

    public CustomOnItemClickListener(int onClick, OnItemClickCallback onItemClickCallback) {
        this.onClick = onClick;
        this.onItemClickCallback = onItemClickCallback;
    }
    @Override
    public void onClick(View view) {
        onItemClickCallback.onItemClicked(view, onClick);
    }
    public interface OnItemClickCallback {
        void onItemClicked(View view, int onClick);
    }
}
