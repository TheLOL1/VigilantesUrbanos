package com.example.gabriel.vigilantesurbanos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DadosIncidenteOAP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_incidente_oap);
        Intent intent = getIntent();
        Incidentes incidentes = new Incidentes();
        incidentes.formatar(intent.getStringExtra("Incidentes"));
        Toolbar toolbar = findViewById(R.id.toolbar3);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(intent.getStringExtra("Toolbar"));
        ImageView imageView = findViewById(R.id.imageView100);
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference().child("incidentes/" + incidentes.getId());
        Glide.with(this).load(storageReference).into(imageView);
        TextView textView = findViewById(R.id.textView27);
        SharedPreferences sharedPreferences = this.getSharedPreferences("Beneficios",0);
        Beneficios beneficios = new Beneficios();
        beneficios.formatar(sharedPreferences.getString(incidentes.getIdVigilante(),""));
        if (incidentes.getDinheiro().equals("true"))
        {
            textView.setText("R$" + beneficios.getDinheiro());
        }
        else if (incidentes.getVigicoin().equals(true))
        {
            textView.setText("V$" + beneficios.getVigiCoin());
        }
        else if (incidentes.getDesconto().equals(true))
        {
            textView.setText("R$" + beneficios.getValordesconto());
        }
        else
        {
            textView.setText("Nenhum benefício enviado!");
        }
        textView = findViewById(R.id.textView29);
        textView.setText(incidentes.getData());
        textView = findViewById(R.id.textView31);
        textView.setText(incidentes.getLocalizacao());
        textView = findViewById(R.id.textView33);
        textView.setText(incidentes.getTipo());
        textView = findViewById(R.id.textView35);
        textView.setText(incidentes.getDescricao());
        textView = findViewById(R.id.textView37);
        textView.setText(incidentes.getComentário());
    }
}
