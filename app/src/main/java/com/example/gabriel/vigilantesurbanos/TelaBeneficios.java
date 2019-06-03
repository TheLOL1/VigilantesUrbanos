package com.example.gabriel.vigilantesurbanos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TelaBeneficios extends AppCompatActivity {
    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficios);
        TextView textView = findViewById(R.id.textView7);
        textView.setText("0");
        textView = findViewById(R.id.textView19);
        textView.setText("0");
        textView = findViewById(R.id.textView20);
        textView.setText("0");
        textView = findViewById(R.id.textView22);
        textView.setText("0");
        textView = findViewById(R.id.textView24);
        textView.setText("0");
    }
}
