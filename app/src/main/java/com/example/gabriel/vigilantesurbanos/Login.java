package com.example.gabriel.vigilantesurbanos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.Base64;

public class Login extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    public static Activity fecharlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fecharlogin = this;
        TextView textView = findViewById(R.id.textView2);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irParaTelaCadastro();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        firebaseAuth = ConfiguracaoBancoDeDados.getFirebaseAuth();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null)
        {
            String email = firebaseUser.getEmail();
            String id = Base64.getEncoder().encodeToString(email.getBytes());
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VigilantesUrbanos",0);
            String usuario = sharedPreferences.getString(id,"");
        }
    }


    public void logar(View view) {
        firebaseAuth = ConfiguracaoBancoDeDados.getFirebaseAuth();
        EditText editText = findViewById(R.id.editText);
        EditText editText1 = findViewById(R.id.editText2);
        final String cpf = editText.getText().toString();
        String senha = editText1.getText().toString();
        RadioGroup radioGroup = findViewById(R.id.radiogroup);
        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        final Intent intent = new Intent(this,TelaInicialVigilante.class);
        final Intent intent1 = new Intent(this,TelaInicialOAP.class);
        if (radioButton != null) {
            final String tipo = radioButton.getText().toString();
            if (cpf.equals("") || senha.equals("") || tipo.equals("")) {
                Toast.makeText(this, "CPF ou senha não preenchido", Toast.LENGTH_LONG).show();
            } else if (estaconectado()) {
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.show();
                OnCompleteListener onCompleteListener = new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (tipo.equals("Vigilante")) {
                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VigilantesUrbanos",0);
                                String usuario = sharedPreferences.getString(Base64.getEncoder().encodeToString(cpf.getBytes()),"");
                                progressDialog.dismiss();
                                finish();
                                startActivity(intent);
                            } else {
                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VigilantesUrbanos",0);
                                String usuario = sharedPreferences.getString(Base64.getEncoder().encodeToString(cpf.getBytes()),"");
                                progressDialog.dismiss();
                                finish();
                                startActivity(intent1);
                            }
                        } else {
                            String erro;
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidUserException e) {
                                erro = getString(R.string.emailincorreto);
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                erro = getString(R.string.senhaincorreta);
                            } catch (Exception e) {
                                erro = getString(R.string.errologar);
                                e.printStackTrace();
                            }
                            progressDialog.dismiss();
                            Toast.makeText(Login.this, erro, Toast.LENGTH_LONG).show();
                        }
                    }
                };
                firebaseAuth.signInWithEmailAndPassword(cpf, senha).addOnCompleteListener(onCompleteListener);
            } else {
                Toast.makeText(this, "Erro de conexão!", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(this,"Tipo não selecionado!",Toast.LENGTH_LONG).show();
        }
    }//end logar(View view)

    public void irParaTelaCadastro() {
        RadioGroup radioGroup = findViewById(R.id.radiogroup);
        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        if (radioButton != null) {
            String tipo = radioButton.getText().toString();
            if (tipo.equals("Vigilante")) {
                Intent intent = new Intent(this, CadastroVigilanteParte1.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, CadastroOAP.class);
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, "Selecione o tipo de conta que deseja cadastrar!", Toast.LENGTH_LONG).show();
        }
    }

    public boolean estaconectado() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }//end estaconectado()
}
