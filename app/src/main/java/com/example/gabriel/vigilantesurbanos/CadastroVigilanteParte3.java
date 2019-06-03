package com.example.gabriel.vigilantesurbanos;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import java.net.SocketException;
import java.util.Base64;

public class CadastroVigilanteParte3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrarvigilante3);
    }

    public void cadastrar (View view)
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        final Intent intent = getIntent();
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
        String sexo = intent.getStringExtra("sexo");
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
            progressDialog.dismiss();
            Toast.makeText(this,"Todos os campos são obrigatórios!",Toast.LENGTH_LONG);
        }
        else if (estaconectado())
        {
            final Intent intent1 = new Intent(this,Login.class);
            String iddadosbancarios = Base64.getEncoder().encodeToString(numerocartao.getBytes());
            final DadosBancarios dadosBancarios = new DadosBancarios(iddadosbancarios,banco,agencia,conta,numerocartao,data,cvv);
            final FirebaseAuth firebaseAuth = ConfiguracaoBancoDeDados.getFirebaseAuth();
            final String idvigilante = Base64.getEncoder().encodeToString(email.getBytes());
            final Vigilante vigilante = new Vigilante("0",idvigilante,cpf,senha,nome,telefone,email,rg,sexo,nacionalidade,iddadosbancarios,cep,endereco,numero,bairro,uf,cidade,complemento);
            OnCompleteListener onCompleteListener = new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        vigilante.inserir();
                        dadosBancarios.inserir();
                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VigilantesUrbanos",0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(idvigilante,vigilante.toString());
                        editor.commit();
                        firebaseAuth.signOut();
                        Beneficios beneficios = new Beneficios(idvigilante,"0","0","0","0","0");
                        beneficios.Inserir();
                        sharedPreferences = getApplicationContext().getSharedPreferences("Beneficios",0);
                        editor = sharedPreferences.edit();
                        editor.putString(beneficios.getIdvigilante(),beneficios.toString());
                        editor.apply();
                        Toast.makeText(CadastroVigilanteParte3.this,"Cadastro realizado com sucesso!",Toast.LENGTH_LONG).show();
                        CadastroVigilanteParte1.parte1.finish();
                        CadastroVigilanteParte2.parte2.finish();
                        CadastroVigilanteParte3.this.finish();
                        Login.fecharlogin.finish();
                        startActivity(intent1);
                    }
                    else
                    {
                        try
                        {
                            throw task.getException();
                        }
                        catch (FirebaseAuthWeakPasswordException e)
                        {
                            Toast.makeText(CadastroVigilanteParte3.this,"Senha precisa ter no minimo 6 caracteres!",Toast.LENGTH_LONG).show();
                        }
                        catch (FirebaseAuthInvalidCredentialsException e)
                        {
                            Toast.makeText(CadastroVigilanteParte3.this,"E-mail invalido!",Toast.LENGTH_LONG).show();
                        }
                        catch (FirebaseAuthUserCollisionException e)
                        {
                            Toast.makeText(CadastroVigilanteParte3.this,"Já existe uma conta cadastrada com este e-mail!",Toast.LENGTH_LONG).show();
                        }
                        catch (SocketException e)
                        {
                            e.printStackTrace();
                            Toast.makeText(CadastroVigilanteParte3.this,"Erro ao cadastrar tente novamente!",Toast.LENGTH_LONG).show();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }
            };
            firebaseAuth.createUserWithEmailAndPassword(vigilante.getEmail(),vigilante.getSenha()).addOnCompleteListener(onCompleteListener);
        }
        else
        {
            progressDialog.dismiss();
            Toast.makeText(this,"Falha de conexão!",Toast.LENGTH_LONG).show();
        }
    }

    public boolean estaconectado()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }//end estaconectado()
}
