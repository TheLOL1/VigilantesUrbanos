package com.example.gabriel.vigilantesurbanos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class AdapterOap extends RecyclerView.Adapter<ViewHolderOap> {
    int posicaoselecionada = -1;
    ArrayList<String> dados;
    Context context;
    public AdapterOap(Map<String,?> stringMap, Context context)
    {
        this.context = context;
        dados = new ArrayList<>(0);
        for (Map.Entry<String,?> auxiliar : stringMap.entrySet())
        {
            dados.add(auxiliar.getValue().toString());
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
        viewHolderOap.incidente.setText(dados.get(position));
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
                  AlertDialog.Builder alertdialog = new AlertDialog.Builder(context,R.style.AlertDialogStyle);
                  Toolbar toolbar = new Toolbar(context);
                  toolbar.setBackgroundColor(Color.BLACK);
                  toolbar.setTitleTextColor(Color.WHITE);
                  toolbar.setTitle(dados.get(position));
                  TextView textView = new TextView(context);
                  textView.setTextColor(Color.WHITE);
                  textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                  textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                  textView.setText("Beneficios adquiridos");
                  TextView textView1 = new TextView(context);
                  textView1.setTextColor(Color.WHITE);
                  textView1.setText("Teste");
                  TextView textView2 = new TextView(context);
                  textView2.setTextColor(Color.WHITE);
                  textView2.setText("Data do Envio");
                  textView2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                  textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                  TextView textView3 = new TextView(context);
                  textView3.setTextColor(Color.WHITE);
                  textView3.setText("Teste2");
                  TextView textView4 = new TextView(context);
                  textView4.setTextColor(Color.WHITE);
                  textView4.setText("Local");
                  textView4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                  textView4.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                  TextView textView5 = new TextView(context);
                  textView5.setTextColor(Color.WHITE);
                  textView5.setText("Teste3");
                  TextView textView6 = new TextView(context);
                  textView6.setTextColor(Color.WHITE);
                  textView6.setText("Tipo");
                  textView6.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                  textView6.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                  TextView textView7 = new TextView(context);
                  textView7.setTextColor(Color.WHITE);
                  textView7.setText("Teste4");
                  TextView textView8 = new TextView(context);
                  textView8.setTextColor(Color.WHITE);
                  textView8.setText("Descrição");
                  textView8.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                  textView8.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                  TextView textView9 = new TextView(context);
                  textView9.setTextColor(Color.WHITE);
                  textView9.setText("Teste5");
                  TextView textView10 = new TextView(context);
                  textView10.setTextColor(Color.WHITE);
                  textView10.setText("Comentário OAP");
                  textView10.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                  textView10.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                  TextView textView11 = new TextView(context);
                  textView11.setTextColor(Color.WHITE);
                  textView11.setText("Teste6");
                  LinearLayout linearLayout = new LinearLayout(context);
                  linearLayout.setOrientation(LinearLayout.VERTICAL);
                  linearLayout.addView(textView);
                  linearLayout.addView(textView1);
                  linearLayout.addView(textView2);
                  linearLayout.addView(textView3);
                  linearLayout.addView(textView4);
                  linearLayout.addView(textView5);
                  linearLayout.addView(textView6);
                  linearLayout.addView(textView7);
                  linearLayout.addView(textView8);
                  linearLayout.addView(textView9);
                  linearLayout.addView(textView10);
                  linearLayout.addView(textView11);
                  alertdialog.setCancelable(false);
                  alertdialog.setView(linearLayout);
                  alertdialog.setNegativeButton("FECHAR", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
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
