package com.example.gabriel.vigilantesurbanos;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Base64;

public class TelaBeneficios extends AppCompatActivity {
    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficios);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Beneficios",0);
        FirebaseAuth firebaseAuth = ConfiguracaoBancoDeDados.getFirebaseAuth();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        Beneficios beneficios = new Beneficios();
        beneficios.formatar(sharedPreferences.getString(Base64.getEncoder().encodeToString(firebaseUser.getEmail().getBytes()),""));
        TextView textView = findViewById(R.id.textView7);
        textView.setText(beneficios.getDinheiro());
        textView = findViewById(R.id.textView19);
        textView.setText(beneficios.getVigiCoin());
        textView = findViewById(R.id.textView20);
        if (beneficios.getPorcentagemdesconto().contains("%")) {
            textView.setText(beneficios.getPorcentagemdesconto());
        }
        else
        {
            textView.setText(beneficios.getPorcentagemdesconto() + "%");
        }
        textView = findViewById(R.id.textView22);
        textView.setText(beneficios.getTipoimposto());
        textView = findViewById(R.id.textView24);
        textView.setText(beneficios.getValordesconto());
    }
}
