package com.example.gabriel.vigilantesurbanos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Base64;

public class CadastroVigilanteParte3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrarvigilante3);
    }

    public void cadastrar (View view)
    {
        Intent intent = getIntent();
        String numero = intent.getStringExtra("numero");
        String bairro = intent.getStringExtra("bairro");
        String uf = intent.getStringExtra("uf");
        String cidade = intent.getStringExtra("cidade");
        String complemento = intent.getStringExtra("complemento");
        String cpf = intent.getStringExtra("cpf");
        String senha = intent.getStringExtra("senha");
        String nome = intent.getStringExtra("nome");
        String telefone = intent.getStringExtra("telefone");
        String email = intent.getStringExtra("email");
        String rg = intent.getStringExtra("rg");
        String nacionalidade = intent.getStringExtra("nacionalidade");
        String cep = intent.getStringExtra("cep");
        String endereco = intent.getStringExtra("endereco");
        char sexo = intent.getStringExtra("sexo").charAt(0);
        EditText editText = findViewById(R.id.editText3);
        EditText editText2 = findViewById(R.id.editText4);
        EditText editText3 = findViewById(R.id.editText5);
        EditText editText4 = findViewById(R.id.editText10);
        EditText editText5 = findViewById(R.id.editText11);
        EditText editText6 = findViewById(R.id.editText12);
        String banco = editText.getText().toString();
        String agencia = editText2.getText().toString();
        String conta = editText3.getText().toString();
        String numerocartao = editText4.getText().toString();
        String data = editText5.getText().toString();
        String cvv = editText6.getText().toString();
        if (banco.equals("")||agencia.equals("")||conta.equals("")||numerocartao.equals("")||data.equals("")||cvv.equals(""))
        {
            Toast.makeText(this,"Todos os campos são obrigatórios!",Toast.LENGTH_LONG);
        }
        else
        {
            String iddadosbancarios="";
            DadosBancarios dadosBancarios = new DadosBancarios(iddadosbancarios,banco,agencia,conta,numerocartao,data,cvv);
            FirebaseAuth firebaseAuth = ConfiguracaoBancoDeDados.getFirebaseAuth();
        }
    }
}
