package com.example.gabriel.vigilantesurbanos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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
            dados.add(incidentes.getLocalizacao());
            dadosauxiliar.add(auxiliar.getValue().toString());
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
                  FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
                  Incidentes incidentes = new Incidentes();
                  incidentes.formatar(dadosauxiliar.get(position));
                  StorageReference storageReference = firebaseStorage.getReference().child("incidentes/"+incidentes.getId());
                  SharedPreferences sharedPreferences = context.getSharedPreferences("Beneficios",0);
                  Beneficios beneficios = new Beneficios();
                  beneficios.formatar(sharedPreferences.getString(incidentes.getIdVigilante(),""));
                  posicaoselecionada = position;
                  AlertDialog.Builder alertdialog = new AlertDialog.Builder(context,R.style.AlertDialogStyle);
                  Toolbar toolbar = new Toolbar(context);
                  toolbar.setBackgroundColor(Color.BLACK);
                  toolbar.setTitleTextColor(Color.WHITE);
                  toolbar.setTitle(dados.get(position));
                  ImageView imageView = new ImageView(context);
                  Glide.with(context).load(storageReference).into(imageView);
                  TextView textView = new TextView(context);
                  textView.setTextColor(Color.WHITE);
                  textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                  textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                  textView.setText("Beneficios enviados");
                  TextView textView1 = new TextView(context);
                  textView1.setTextColor(Color.WHITE);
                  if (incidentes.getDinheiro().equals("true")) {
                      textView1.setText(beneficios.getDinheiro());
                  }
                  else if (incidentes.getVigicoin().equals("true"))
                  {
                      textView1.setText(beneficios.getVigiCoin());
                  }
                  else if (incidentes.getDesconto().equals("true"))
                  {
                      textView1.setText(beneficios.getValordesconto());
                  }
                  else
                  {
                      textView1.setText("Nenhum beneficio enviado");
                  }
                  TextView textView2 = new TextView(context);
                  textView2.setTextColor(Color.WHITE);
                  textView2.setText("Data do Envio");
                  textView2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                  textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                  TextView textView3 = new TextView(context);
                  textView3.setTextColor(Color.WHITE);
                  textView3.setText(incidentes.getData());
                  TextView textView4 = new TextView(context);
                  textView4.setTextColor(Color.WHITE);
                  textView4.setText("Local");
                  textView4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                  textView4.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                  TextView textView5 = new TextView(context);
                  textView5.setTextColor(Color.WHITE);
                  textView5.setText(incidentes.getLocalizacao());
                  TextView textView6 = new TextView(context);
                  textView6.setTextColor(Color.WHITE);
                  textView6.setText("Tipo");
                  textView6.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                  textView6.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                  TextView textView7 = new TextView(context);
                  textView7.setTextColor(Color.WHITE);
                  textView7.setText(incidentes.getTipo());
                  TextView textView8 = new TextView(context);
                  textView8.setTextColor(Color.WHITE);
                  textView8.setText("Descrição");
                  textView8.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                  textView8.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                  TextView textView9 = new TextView(context);
                  textView9.setTextColor(Color.WHITE);
                  textView9.setText(incidentes.getDescricao());
                  TextView textView10 = new TextView(context);
                  textView10.setTextColor(Color.WHITE);
                  textView10.setText("Comentário OAP");
                  textView10.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                  textView10.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                  TextView textView11 = new TextView(context);
                  textView11.setTextColor(Color.WHITE);
                  textView11.setText(incidentes.getComentário());
                  LinearLayout linearLayout = new LinearLayout(context);
                  linearLayout.setOrientation(LinearLayout.VERTICAL);
                  linearLayout.addView(toolbar);
                  linearLayout.addView(imageView);
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
                  int largura = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,400,context.getResources().getDisplayMetrics());
                  int altura = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,400,context.getResources().getDisplayMetrics());
                  LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(largura,altura);
                  linearLayout.setLayoutParams(layoutParams);
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
