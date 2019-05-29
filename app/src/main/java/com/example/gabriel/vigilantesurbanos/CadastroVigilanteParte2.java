package com.example.gabriel.vigilantesurbanos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroVigilanteParte2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrarvigilante2);
    }

    public void continuar (View view)
    {
        Intent intent = getIntent();
        String nome = intent.getStringExtra("nome");
        String telefone = intent.getStringExtra("telefone");
        String email = intent.getStringExtra("email");
        String rg = intent.getStringExtra("rg");
        String nacionalidade = intent.getStringExtra("nacionalidade");
        String cep = intent.getStringExtra("cep");
        String endereco = intent.getStringExtra("endereco");
        char sexo = intent.getCharExtra("sexo",' ');
        intent.setClass(this,CadastroVigilanteParte3.class);
        EditText editText = findViewById(R.id.editText23);
        EditText editText2 = findViewById(R.id.editText24);
        EditText editText3 = findViewById(R.id.editText25);
        EditText editText4 = findViewById(R.id.editText26);
        EditText editText5 = findViewById(R.id.editText27);
        EditText editText6 = findViewById(R.id.editText28);
        EditText editText7 = findViewById(R.id.editText29);
        EditText editText8 = findViewById(R.id.editText30);
        String numero = editText.getText().toString();
        String bairro = editText2.getText().toString();
        String uf = editText3.getText().toString();
        String cidade = editText4.getText().toString();
        String complemento = editText5.getText().toString();
        String cpf = editText6.getText().toString();
        String senha = editText7.getText().toString();
        String confirmarsenha = editText8.getText().toString();
        if (numero.equals("")||bairro.equals("")||uf.equals("")||cidade.equals("")||complemento.equals("")||cpf.equals("")||senha.equals("")||confirmarsenha.equals(""))
        {
            Toast.makeText(this,"Todos os campos são obrigatórios!",Toast.LENGTH_LONG).show();
        }
        else
        {
            if (senha.equals(confirmarsenha))
            {
                intent.putExtra("numero",numero);
                intent.putExtra("bairro",bairro);
                intent.putExtra("uf",uf);
                intent.putExtra("cidade",cidade);
                intent.putExtra("complemento",complemento);
                intent.putExtra("cpf",cpf);
                intent.putExtra("senha",senha);
                intent.putExtra("nome",nome);
                intent.putExtra("telefone",telefone);
                intent.putExtra("email",email);
                intent.putExtra("rg",rg);
                intent.putExtra("nacionalidade",nacionalidade);
                intent.putExtra("cep",cep);
                intent.putExtra("endereco",endereco);
                intent.putExtra("sexo",sexo);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this,"Confirmar senha não é igual a senha!",Toast.LENGTH_LONG).show();
            }
        }
    }
}
