package com.example.gabriel.vigilantesurbanos;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Map;

public class AdapterVigilante extends RecyclerView.Adapter<ViewHolderVigilante> {
    ArrayList<String> dados;
    Context context;
    public AdapterVigilante(Map<String,?> stringMap,Context context)
    {
        this.context = context;
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
    public void onBindViewHolder(final ViewHolderVigilante viewHolderVigilante, int position)
    {
        Incidentes incidentes = new Incidentes();
        incidentes.formatar(dados.get(position));
        if (incidentes != null) {
            viewHolderVigilante.comentarioOapvalor.setText(incidentes.getComentário());
            viewHolderVigilante.descricaovalor.setText(incidentes.getDescricao());
            viewHolderVigilante.tipovalor.setText(incidentes.getTipo());
            viewHolderVigilante.localvalor.setText(incidentes.getLocalizacao());
            viewHolderVigilante.dataenviovalor.setText(incidentes.getData());
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("Beneficios",0);
        Beneficios beneficios = new Beneficios();
        beneficios.formatar(sharedPreferences.getString(incidentes.getIdVigilante(),""));
        if (incidentes != null && beneficios != null && incidentes.getDinheiro().equals("true")) {
            viewHolderVigilante.beneficiosadquiridosvalor.setText("R$" + beneficios.getDinheiro());
        }
        else if (incidentes != null && beneficios != null && incidentes.getVigicoin().equals("true"))
        {
            viewHolderVigilante.beneficiosadquiridosvalor.setText("V$" + beneficios.getVigiCoin());
        }
        else if (incidentes != null && beneficios != null && incidentes.getDesconto().equals("true"))
        {
            viewHolderVigilante.beneficiosadquiridosvalor.setText("R$" + beneficios.getValordesconto());
        }
        else
        {
            viewHolderVigilante.beneficiosadquiridosvalor.setText("Incidente não avalido pelo OAP!");
        }
        if (incidentes != null) {
            FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
            StorageReference storageReference = firebaseStorage.getReference().child("incidentes/" + incidentes.getId());
            Glide.with(context).load(storageReference).into(viewHolderVigilante.incidente);
        }
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
