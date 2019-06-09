package com.example.gabriel.vigilantesurbanos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Map;

public class AdapterOap extends RecyclerView.Adapter<ViewHolderOap> {
    int posicaoselecionada = -1;
    ArrayList<String> dados;
    ArrayList<String> dadosauxiliar;
    Context context;
    public AdapterOap(Map<String,?> stringMap, Context context)
    {
        this.context = context;
        dados = new ArrayList<>(0);
        dadosauxiliar = new ArrayList<>(0);
        for (Map.Entry<String,?> auxiliar : stringMap.entrySet())
        {
            Incidentes incidentes = new Incidentes();
            incidentes.formatar(auxiliar.getValue().toString());
            if (incidentes != null) {
                dados.add(incidentes.getLocalizacao());
                dadosauxiliar.add(auxiliar.getValue().toString());
            }
        }
    }

    @Override
    public ViewHolderOap onCreateViewHolder (ViewGroup viewGroup, int x)
    {
        ViewHolderOap viewHolderOap = new ViewHolderOap(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_viewholderoap,viewGroup,false));
        return (viewHolderOap);
    }

    @Override
    public void onBindViewHolder(ViewHolderOap viewHolderOap, final int position)
    {
        viewHolderOap.incidente.setText("Novo incidente da cidade de " + dados.get(position));
      if (posicaoselecionada == position)
      {
          viewHolderOap.incidente.setBackgroundColor(Color.BLUE);
      }
      else
      {
          viewHolderOap.incidente.setBackgroundColor(Color.TRANSPARENT);
      }
      viewHolderOap.incidente.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if (posicaoselecionada == position)
              {
                  posicaoselecionada = -1;
              }
              else {
                  posicaoselecionada = position;
                  AlertDialog.Builder alertdialog = new AlertDialog.Builder(context);
                  alertdialog.setMessage("Deseja exibir os detalhes deste incidente? (Se deseja apenas selecionar o incidente para avaliar toque em NÃO)");
                  alertdialog.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialogInterface, int i) {
                                  Intent intent = new Intent(context, DadosIncidenteOAP.class);
                                  intent.putExtra("Incidentes",dadosauxiliar.get(position));
                                  intent.putExtra("Toolbar","Novo incidente da cidade de " + dados.get(position));
                                  context.startActivity(intent);
                              }
                          });
                  alertdialog.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialogInterface, int i) {
                      }
                  });
                  alertdialog.show();
              }
              notifyDataSetChanged();
          }
      });
    }

    @Override
    public int getItemCount()
    {
        int tamanho = 0;
        if (dados == null)
        {
            return 0;
        }
        else
        {
            tamanho = dados.size();
        }
        return (tamanho);
    }
}
