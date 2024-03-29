package com.example.gabriel.vigilantesurbanos;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TelaInicialVigilante extends AppCompatActivity {
    Uri imagemvideo;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    RecyclerView recyclerView;
    AdapterVigilante adapterVigilante;
    boolean enviou = false;
    boolean filtrou = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicialvigilante);
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Incidentes",0);
        Map<String,?> dados = sharedPreferences.getAll();
        adapterVigilante = new AdapterVigilante(dados,TelaInicialVigilante.this);
        recyclerView.setAdapter(adapterVigilante);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL));
        TextView textView = findViewById(R.id.textView150);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(TelaInicialVigilante.this);
                TextView textView = new TextView(TelaInicialVigilante.this);
                textView.setText("FILTRAR");
                textView.setTextColor(Color.BLACK);
                textView.setGravity(Gravity.CENTER);
                final EditText editText = new EditText(TelaInicialVigilante.this);
                editText.setHint("Localização");
                editText.setHintTextColor(Color.BLACK);
                editText.setTextColor(Color.BLACK);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(20, 0, 30, 0);
                LinearLayout linearLayout = new LinearLayout(TelaInicialVigilante.this);
                linearLayout.addView(textView,params);
                linearLayout.addView(editText,params);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                alertdialog.setCancelable(true);
                alertdialog.setView(linearLayout);
                final SharedPreferences sharedPreferences = TelaInicialVigilante.this.getSharedPreferences("Incidentes",0);
                alertdialog.setPositiveButton("FILTRAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String localizacao = editText.getText().toString();
                        if ((!localizacao.equals(""))) {
                            Map<String, ?> map = sharedPreferences.getAll();
                            Map<String, String> map1 = new HashMap<>();
                            Incidentes incidentes = new Incidentes();
                            for (Map.Entry<String, ?> auxiliar : map.entrySet()) {
                                incidentes.formatar(auxiliar.getValue().toString());
                                if (incidentes.getLocalizacao() != null && (incidentes.getLocalizacao().equals(localizacao)|| incidentes.getLocalizacao().toLowerCase().equals(localizacao.toLowerCase()))|| incidentes.getLocalizacao().toUpperCase().equals(localizacao.toUpperCase())) {
                                    map1.put(auxiliar.getKey(), auxiliar.getValue().toString());
                                }
                            }
                            if (map1.size() > 0) {
                                adapterVigilante.dados.clear();
                                for (Map.Entry<String, String> auxiliar : map1.entrySet()) {
                                    adapterVigilante.dados.add(auxiliar.getValue());
                                }
                                filtrou = true;
                                adapterVigilante.notifyDataSetChanged();
                                recyclerView.smoothScrollToPosition(0);
                            } else {
                                Toast.makeText(TelaInicialVigilante.this, "Nenhum incidente localizado com essa localização", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
                alertdialog.setNeutralButton("LIMPAR FILTRO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (filtrou)
                        {
                            adapterVigilante = new AdapterVigilante(sharedPreferences.getAll(),TelaInicialVigilante.this);
                            recyclerView.setAdapter(adapterVigilante);
                            recyclerView.smoothScrollToPosition(0);
                            filtrou = false;
                        }
                    }
                });
                alertdialog.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertdialog.show();
            }
        });
    }

    public void InserirIncidente (View view)
    {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(this,R.style.AlertDialogStyle);
        Toolbar toolbar = new Toolbar(this);
        final Button button = new Button(this);
        final EditText editText = new EditText(this);
        final EditText editText3 = new EditText(this);
        final EditText editText1 = new EditText(this);
        toolbar.setTitle("Reportar Incidente");
        toolbar.setBackgroundColor(Color.BLACK);
        toolbar.setTitleTextColor(Color.WHITE);
        button.setText("ANEXAR FOTO");
        button.setTextColor(Color.WHITE);
        button.setBackgroundTintList(ContextCompat.getColorStateList(TelaInicialVigilante.this,R.color.butao));
        editText.setHint("Descrição*");
        editText.setHintTextColor(Color.WHITE);
        editText3.setHint("Tipo*");
        editText3.setHintTextColor(Color.WHITE);
        editText1.setHint("Localização*");
        editText1.setHintTextColor(Color.WHITE);
        editText.setTextColor(Color.WHITE);
        editText1.setTextColor(Color.WHITE);
        editText3.setTextColor(Color.WHITE);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(toolbar);
        linearLayout.addView(button);
        linearLayout.addView(editText);
        linearLayout.addView(editText3);
        linearLayout.addView(editText1);
        alertdialog.setView(linearLayout);
        alertdialog.setCancelable(false);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Intent galeria = new Intent(Intent.ACTION_GET_CONTENT);
                                          galeria.setType("image/*");
                                          startActivityForResult(Intent.createChooser(galeria,"Selecione imagem"),50);
                                      }
                                  });
                alertdialog.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        imagemvideo = null;
                    }
                });
        alertdialog.setPositiveButton("REPORTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String descricao = editText.getText().toString();
                String tipo = editText3.getText().toString();
                String localizacao = editText1.getText().toString();
                if (imagemvideo == null || descricao.equals("")||tipo.equals("")||localizacao.equals(""))
                {
                    Toast.makeText(TelaInicialVigilante.this,"Incidente não inserido (todos os campos são obrigatórios)",Toast.LENGTH_LONG).show();
                }
                else
                {
                   String path = imagemvideo.getPath();
                   int x = path.lastIndexOf("/");
                   String pathfinal = path.substring(x+1);
                   String id = Base64.getEncoder().encodeToString(pathfinal.getBytes());
                   FirebaseAuth firebaseAuth = ConfiguracaoBancoDeDados.getFirebaseAuth();
                   FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                   String idvigilante = Base64.getEncoder().encodeToString(firebaseUser.getEmail().getBytes());
                   Date data = Calendar.getInstance().getTime();
                   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                   Incidentes incidentes = new Incidentes(id,idvigilante,"-1",tipo,localizacao,path,"false",dateFormat.format(data),"",descricao);
                   uparImagem(incidentes.getId());
                   if (enviou) {
                       incidentes.Inserir();
                       SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Incidentes", 0);
                       SharedPreferences.Editor editor = sharedPreferences.edit();
                       editor.putString(incidentes.getId(), incidentes.toString());
                       editor.apply();
                       imagemvideo = null;
                       adapterVigilante = new AdapterVigilante(sharedPreferences.getAll(),TelaInicialVigilante.this);
                       recyclerView.setAdapter(adapterVigilante);
                       enviou = false;
                   }
                }
            }
        });
        alertdialog.show();
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == RESULT_OK)
        {
            if (requestCode == 50 && data != null && data.getData() != null)
            {
                imagemvideo = data.getData();
            }
        }
    }

    public void uparImagem(String incidente)
    {
        if (imagemvideo != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            try {
                enviou = true;
                progressDialog.setTitle("Upando...");
                progressDialog.show();
                StorageReference reference = storageReference.child("incidentes/" + incidente);
                reference.putFile(imagemvideo).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                        Toast.makeText(TelaInicialVigilante.this, "Imagem ou vídeo upado com sucesso!", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(TelaInicialVigilante.this, "Falha ao upar!" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progresso = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        progressDialog.setMessage((int) progresso + "%" + " Upado");
                    }
                });
            }
            catch (java.lang.SecurityException e)
            {
                progressDialog.dismiss();
                Toast.makeText(TelaInicialVigilante.this,"Arquivo invalido",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void abrirBeneficios (View view)
    {
        Intent intent = new Intent(this,TelaBeneficios.class);
        startActivity(intent);
    }

    public void sair (View view)
    {
        Intent intent = new Intent(this,Login.class);
        FirebaseAuth firebaseAuth = ConfiguracaoBancoDeDados.getFirebaseAuth();
        firebaseAuth.signOut();
        finish();
        startActivity(intent);
    }

    public void home (View view)
    {
        recyclerView.smoothScrollToPosition(0);
        adapterVigilante.notifyDataSetChanged();
    }
}
