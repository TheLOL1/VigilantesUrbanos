package com.example.gabriel.vigilantesurbanos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Map;

public class AdapterVigilante extends RecyclerView.Adapter<ViewHolderVigilante> {
    ArrayList<String> dados;
    public AdapterVigilante(Map<String,?> stringMap)
    {
        dados = new ArrayList<>(0);
        for (Map.Entry<String,?> auxiliar : stringMap.entrySet())
        {
            dados.add(auxiliar.getValue().toString());
        }
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
        viewHolderVigilante.comentarioOapvalor.setText("Teste");
        viewHolderVigilante.descricaovalor.setText("Teste2");
        viewHolderVigilante.tipovalor.setText("Teste3");
        viewHolderVigilante.localvalor.setText("Teste4");
        viewHolderVigilante.dataenviovalor.setText("Teste5");
        viewHolderVigilante.beneficiosadquiridosvalor.setText("Teste6");
    }

    @Override
    public int getItemCount()
    {
        int tamanho = 0;
        if (dados == null) {
            return (0);
        }
        else
        {
            tamanho = dados.size();
        }
        return (tamanho);
    }
}
