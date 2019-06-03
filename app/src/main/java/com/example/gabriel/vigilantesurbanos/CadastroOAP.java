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

public class CadastroOAP extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastraroap);
    }

    public void cadastrar (View view)
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        EditText editText = findViewById(R.id.editText8);
        EditText editText4 = findViewById(R.id.editText6);
        EditText editText1 = findViewById(R.id.editText7);
        EditText editText2 = findViewById(R.id.editText13);
        EditText editText3 = findViewById(R.id.editText14);
        String nome = editText.getText().toString();
        String email = editText4.getText().toString();
        String cpf = editText1.getText().toString();
        String senha = editText2.getText().toString();
        String confirmarsenha = editText3.getText().toString();

        if (nome.equals("")||email.equals("")||cpf.equals("")||senha.equals("")||confirmarsenha.equals(""))
        {
            progressDialog.dismiss();
            Toast.makeText(this,"Todos os campos são obrigatórios!",Toast.LENGTH_LONG).show();
        }
        else if (senha.equals(confirmarsenha))
        {
            if (estaconectado()) {
                final FirebaseAuth firebaseAuth = ConfiguracaoBancoDeDados.getFirebaseAuth();
                final String id = Base64.getEncoder().encodeToString(email.getBytes());
                final OAP oap = new OAP(id, cpf, senha, nome, email);
                OnCompleteListener onCompleteListener = new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            progressDialog.dismiss();
                            oap.inserir();
                            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VigilantesUrbanos",0);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(id,oap.toString());
                            editor.apply();
                            Toast.makeText(CadastroOAP.this,"Cadastro realizado com sucesso!",Toast.LENGTH_LONG).show();
                            firebaseAuth.signOut();
                            Intent intent = new Intent(CadastroOAP.this,Login.class);
                            finish();
                            Login.fecharlogin.finish();
                            startActivity(intent);
                        }
                        else
                        {
                            try
                            {
                                throw task.getException();
                            }
                            catch (FirebaseAuthWeakPasswordException e)
                            {
                                Toast.makeText(CadastroOAP.this,"Senha precisa ter no minimo 6 caracteres!",Toast.LENGTH_LONG).show();
                            }
                            catch (FirebaseAuthInvalidCredentialsException e)
                            {
                                Toast.makeText(CadastroOAP.this,"E-mail invalido!",Toast.LENGTH_LONG).show();
                            }
                            catch (FirebaseAuthUserCollisionException e)
                            {
                                Toast.makeText(CadastroOAP.this,"Já existe uma conta cadastrada com este e-mail!",Toast.LENGTH_LONG).show();
                            }
                            catch (SocketException e)
                            {
                                e.printStackTrace();
                                Toast.makeText(CadastroOAP.this,"Erro ao cadastrar tente novamente!",Toast.LENGTH_LONG).show();
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                            progressDialog.dismiss();
                        }
                    }
                };
                firebaseAuth.createUserWithEmailAndPassword(oap.getEmail(),oap.getSenha()).addOnCompleteListener(onCompleteListener);
            }
            else
            {
                progressDialog.dismiss();
                Toast.makeText(this,"Falha na conexão!", Toast.LENGTH_LONG).show();
            }

        }
        else
        {
            progressDialog.dismiss();
            Toast.makeText(this,"Confirmar senha não é igual a senha!",Toast.LENGTH_LONG).show();
        }
    }

    public boolean estaconectado()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }//end estaconectado()
}
