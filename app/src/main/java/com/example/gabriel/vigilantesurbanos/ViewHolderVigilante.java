package com.example.gabriel.vigilantesurbanos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolderVigilante extends RecyclerView.ViewHolder {
    ImageView incidente;
    TextView beneficiosadquiridos;
    TextView beneficiosadquiridosvalor;
    TextView dataenvio;
    TextView dataenviovalor;
    TextView local;
    TextView localvalor;
    TextView tipo;
    TextView tipovalor;
    TextView descricao;
    TextView descricaovalor;
    TextView comentarioOap;
    TextView comentarioOapvalor;

    public ViewHolderVigilante (View view)
    {
        super(view);
        incidente = view.findViewById(R.id.imageView50);
        beneficiosadquiridos = view.findViewById(R.id.textView);
        beneficiosadquiridosvalor = view.findViewById(R.id.textView15);
        dataenvio = view.findViewById(R.id.textView6);
        dataenviovalor = view.findViewById(R.id.textView16);
        local = view.findViewById(R.id.textView8);
        localvalor = view.findViewById(R.id.textView9);
        tipo = view.findViewById(R.id.textView10);
        tipovalor = view.findViewById(R.id.textView11);
        descricao = view.findViewById(R.id.textView13);
        descricaovalor = view.findViewById(R.id.textView14);
        comentarioOap = view.findViewById(R.id.textView17);
        comentarioOapvalor = view.findViewById(R.id.textView18);
    }
}
