package com.example.gabriel.vigilantesurbanos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class AdapterOap extends RecyclerView.Adapter<ViewHolderOap> {
    public AdapterOap()
    {

    }

    @Override
    public ViewHolderOap onCreateViewHolder (ViewGroup viewGroup, int x)
    {
        ViewHolderOap viewHolderOap = new ViewHolderOap(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_viewholderoap,viewGroup,false));
        return (viewHolderOap);
    }

    @Override
    public void onBindViewHolder(ViewHolderOap viewHolderOap, int position)
    {

    }

    @Override
    public int getItemCount()
    {
        return (0);
    }
}
