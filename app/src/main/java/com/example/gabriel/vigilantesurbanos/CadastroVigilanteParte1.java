package com.example.gabriel.vigilantesurbanos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CadastroVigilanteParte1 extends AppCompatActivity {
    Spinner spinner;
    String sexo;
    boolean selecionou = false;
    public static Activity parte1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrarvigilante);
        parte1 = this;
        spinner = findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();
        list.add("Masculino");
        list.add("Feminino");
        list.add("Sexo*");
        final int tamanholista = list.size() - 1;
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item,list)
        {
            @Override
            public int getCount()
            {
                return(tamanholista);
            }
        };
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(tamanholista);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position != 2) {
                        sexo = (String) parent.getItemAtPosition(position);
                        selecionou = true;
                    }
                    else
                    {
                        selecionou = false;
                    }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void continuar (View view)
    {
        final Intent intent = new Intent(this, CadastroVigilanteParte2.class);
        EditText editText = findViewById(R.id.editText23);
        EditText editText1 = findViewById(R.id.editText24);
        EditText editText2 = findViewById(R.id.editText25);
        EditText editText3 = findViewById(R.id.editText26);
        EditText editText4 = findViewById(R.id.editText27);
        EditText editText5 = findViewById(R.id.editText28);
        EditText editText6 = findViewById(R.id.editText29);
        String nome = editText.getText().toString();
        String telefone = editText1.getText().toString();
        String email = editText2.getText().toString();
        String rg = editText3.getText().toString();
        String nacionalidade = editText4.getText().toString();
        String cep = editText5.getText().toString();
        String endereco = editText6.getText().toString();
        intent.putExtra("nome",nome);
        intent.putExtra("telefone",telefone);
        intent.putExtra("email",email);
        intent.putExtra("rg",rg);
        intent.putExtra("nacionalidade",nacionalidade);
        intent.putExtra("cep",cep);
        intent.putExtra("endereco",endereco);
        intent.putExtra("sexo",sexo);
        if (nome.equals("")||telefone.equals("")||email.equals("")||rg.equals("")||nacionalidade.equals("")||cep.equals("")||endereco.equals(""))
        {
                Toast.makeText(this, "Todos os campos são obrigatórios!", Toast.LENGTH_LONG).show();
        }
        else if (selecionou) {
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"Sexo não selecionado!",Toast.LENGTH_LONG).show();
        }
    }
}
