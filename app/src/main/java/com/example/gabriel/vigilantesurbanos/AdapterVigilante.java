package com.example.gabriel.vigilantesurbanos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class AdapterVigilante extends RecyclerView.Adapter<ViewHolderVigilante> {
    public AdapterVigilante()
    {

    }

    @Override
    public ViewHolderVigilante onCreateViewHolder (ViewGroup viewGroup, int x)
    {
        ViewHolderVigilante viewHolderVigilante = new ViewHolderVigilante(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_viewholdervigilante,viewGroup,false));
        return (viewHolderVigilante);
    }

    @Override
    public void onBindViewHolder(ViewHolderVigilante viewHolderVigilante, int position)
    {

    }

    @Override
    public int getItemCount()
    {
        return (0);
    }
}
