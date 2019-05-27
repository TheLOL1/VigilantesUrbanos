package com.example.gabriel.vigilantesurbanos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolderOap extends RecyclerView.ViewHolder {
    TextView incidente;

    public ViewHolderOap (View view)
    {
        super(view);
        incidente = view.findViewById(R.id.textView4);
    }
}
