package com.example.gabriel.vigilantesurbanos;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class TelaInicialOAP extends AppCompatActivity {
    Uri imagemvideo;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    RecyclerView recyclerView;
    AdapterOap adapterOap;
    boolean enviou = false;
    boolean filtrou = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicialoap);
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        recyclerView = findViewById(R.id.recyclerView2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Incidentes",0);
        Map<String,?> dados = sharedPreferences.getAll();
        adapterOap = new AdapterOap(dados,TelaInicialOAP.this);
        recyclerView.setAdapter(adapterOap);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL));
        TextView textView = findViewById(R.id.textView150);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertdialog = new AlertDialog.Builder(TelaInicialOAP.this);
                TextView textView = new TextView(TelaInicialOAP.this);
                textView.setText("FILTRAR");
                textView.setTextColor(Color.BLACK);
                textView.setGravity(Gravity.CENTER);
                final EditText editText = new EditText(TelaInicialOAP.this);
                editText.setHint("Localização");
                editText.setHintTextColor(Color.BLACK);
                editText.setTextColor(Color.BLACK);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(20, 0, 30, 0);
                LinearLayout linearLayout = new LinearLayout(TelaInicialOAP.this);
                linearLayout.addView(textView,params);
                linearLayout.addView(editText,params);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                alertdialog.setCancelable(true);
                alertdialog.setView(linearLayout);
                final SharedPreferences sharedPreferences = TelaInicialOAP.this.getSharedPreferences("Incidentes",0);
                alertdialog.setPositiveButton("FILTRAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String localizacao = editText.getText().toString();
                        if ((!localizacao.equals(""))) {
                            Map<String, ?> map = sharedPreferences.getAll();
                            Map<String, String> map1 = new HashMap<>();
                            Map<String, String> map2 = new HashMap<>();
                            Incidentes incidentes = new Incidentes();
                            for (Map.Entry<String, ?> auxiliar : map.entrySet()) {
                                incidentes.formatar(auxiliar.getValue().toString());
                                if (incidentes.getLocalizacao() != null && (incidentes.getLocalizacao().equals(localizacao)|| incidentes.getLocalizacao().toLowerCase().equals(localizacao.toLowerCase()))|| incidentes.getLocalizacao().toUpperCase().equals(localizacao.toUpperCase())) {
                                    map1.put(auxiliar.getKey(), incidentes.getLocalizacao());
                                    map2.put(auxiliar.getKey(),auxiliar.getValue().toString());
                                }
                            }
                            if (map1.size() > 0 && map2.size() > 0) {
                                adapterOap.dados.clear();
                                adapterOap.dadosauxiliar.clear();
                                for (Map.Entry<String, String> auxiliar : map1.entrySet()) {
                                    adapterOap.dados.add(auxiliar.getValue());
                                }
                                for (Map.Entry<String,String> auxiliar : map2.entrySet())
                                {
                                    adapterOap.dadosauxiliar.add(auxiliar.getValue());
                                }
                                Log.d("Teste",Integer.toString(adapterOap.dados.size()));
                                Log.d("Teste",Integer.toString(adapterOap.dadosauxiliar.size()));
                                filtrou = true;
                                adapterOap.notifyDataSetChanged();
                                recyclerView.smoothScrollToPosition(0);
                            } else {
                                Toast.makeText(TelaInicialOAP.this, "Nenhum incidente localizado com essa localização", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
                alertdialog.setNeutralButton("LIMPAR FILTRO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (filtrou)
                        {
                            adapterOap = new AdapterOap(sharedPreferences.getAll(),TelaInicialOAP.this);
                            recyclerView.setAdapter(adapterOap);
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

    public void AvaliarIncidente (View view)
    {
        if (adapterOap.posicaoselecionada != -1) {
            String s = adapterOap.dadosauxiliar.get(adapterOap.posicaoselecionada);
            final Incidentes incidentes = new Incidentes();
            incidentes.formatar(s);
            if (incidentes != null && incidentes.getIdOAP().equals("-1")) {
                final SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Incidentes",0);
                final SharedPreferences sharedPreferences1 = getApplicationContext().getSharedPreferences("Beneficios",0);
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(this, R.style.AlertDialogStyle);
                Toolbar toolbar = new Toolbar(this);
                toolbar.setTitle("Avaliar Incidente");
                toolbar.setBackgroundColor(Color.BLACK);
                toolbar.setTitleTextColor(Color.WHITE);
                Button button = new Button(this);
                button.setText("ANEXAR FOTO");
                button.setTextColor(Color.WHITE);
                button.setBackgroundTintList(ContextCompat.getColorStateList(TelaInicialOAP.this, R.color.butao));
                final EditText editText = new EditText(this);
                editText.setHint("Comentário");
                editText.setHintTextColor(Color.WHITE);
                TextView textView = new TextView(this);
                textView.setText("Tipo de bonificação");
                textView.setTextColor(Color.WHITE);
                final RadioGroup radioGroup = new RadioGroup(this);
                final RadioButton radioButton = new RadioButton(this);
                RadioButton radioButton2 = new RadioButton(this);
                RadioButton radioButton3 = new RadioButton(this);
                radioButton.setText("Dinheiro");
                radioButton.setTextColor(Color.WHITE);
                radioGroup.addView(radioButton);
                radioButton2.setText("VigiCoin");
                radioButton2.setTextColor(Color.WHITE);
                radioGroup.addView(radioButton2);
                radioButton3.setTextColor(Color.WHITE);
                radioButton3.setText("Desconto Imposto");
                radioGroup.addView(radioButton3);
                final EditText editText1 = new EditText(this);
                editText1.setHint("Valor Bonificação");
                editText1.setHintTextColor(Color.WHITE);
                final EditText editText2 = new EditText(this);
                editText2.setHint("Porcentagem desconto");
                editText2.setHintTextColor(Color.WHITE);
                final EditText editText3 = new EditText(this);
                editText3.setHint("Tipo imposto");
                editText3.setHintTextColor(Color.WHITE);
                final LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                editText.setTextColor(Color.WHITE);
                editText1.setTextColor(Color.WHITE);
                editText2.setTextColor(Color.WHITE);
                editText3.setTextColor(Color.WHITE);
                linearLayout.addView(toolbar);
                linearLayout.addView(button);
                linearLayout.addView(editText);
                linearLayout.addView(textView);
                linearLayout.addView(radioGroup);
                linearLayout.addView(editText1);
                linearLayout.addView(editText2);
                linearLayout.addView(editText3);
                alertdialog.setView(linearLayout);
                alertdialog.setCancelable(false);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent galeria = new Intent(Intent.ACTION_GET_CONTENT);
                        galeria.setType("image/*");
                        startActivityForResult(Intent.createChooser(galeria, "Selecione imagem"), 50);
                    }
                });
                alertdialog.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        imagemvideo = null;
                    }
                });
                alertdialog.setPositiveButton("ENVIAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String comentario = editText.getText().toString();
                        RadioButton radioButton4 = linearLayout.findViewById(radioGroup.getCheckedRadioButtonId());
                        String tipo = "";
                        if (radioButton4 != null) {
                            tipo = radioButton4.getText().toString();
                        }
                        String valor = editText1.getText().toString();
                        if (imagemvideo == null || tipo.equals("") || comentario.equals("") || valor.equals("")) {
                            Toast.makeText(TelaInicialOAP.this, "Incidente não avaliado (todos os campos são obrigatórios)", Toast.LENGTH_LONG).show();
                        } else {
                            if (tipo.equals("Dinheiro"))
                            {
                                FirebaseAuth firebaseAuth = ConfiguracaoBancoDeDados.getFirebaseAuth();
                                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                incidentes.setComentário(comentario);
                                incidentes.setIdOAP(Base64.getEncoder().encodeToString(firebaseUser.getEmail().getBytes()));
                                incidentes.setDinheiro("true");
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(incidentes.getId(),incidentes.toString());
                                Beneficios beneficios = new Beneficios();
                                beneficios.formatar(sharedPreferences1.getString(incidentes.getIdVigilante(),""));
                                beneficios.setDinheiro(valor);
                                SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                                editor1.putString(incidentes.getIdVigilante(),beneficios.toString());
                                uparImagem(incidentes.getId());
                                if (enviou) {
                                    editor1.apply();
                                    editor.apply();
                                    DatabaseReference databaseReference = ConfiguracaoBancoDeDados.getDatabaseReference();
                                    Map<String, Object> map = new HashMap<>();
                                    map.put(incidentes.getId(), incidentes);
                                    databaseReference.child("incidentes").updateChildren(map);
                                    map = new HashMap<>();
                                    map.put(beneficios.getIdvigilante(), beneficios);
                                    databaseReference.child("beneficios").updateChildren(map);
                                    imagemvideo = null;
                                    adapterOap = new AdapterOap(sharedPreferences.getAll(), TelaInicialOAP.this);
                                    recyclerView.setAdapter(adapterOap);
                                    enviou = false;
                                }
                            }
                            else if (tipo.equals("VigiCoin"))
                            {
                                FirebaseAuth firebaseAuth = ConfiguracaoBancoDeDados.getFirebaseAuth();
                                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                incidentes.setComentário(comentario);
                                incidentes.setIdOAP(Base64.getEncoder().encodeToString(firebaseUser.getEmail().getBytes()));
                                incidentes.setVigicoin("true");
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(incidentes.getId(),incidentes.toString());
                                Beneficios beneficios = new Beneficios();
                                beneficios.formatar(sharedPreferences1.getString(incidentes.getIdVigilante(),""));
                                beneficios.setVigiCoin(valor);
                                SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                                editor1.putString(incidentes.getIdVigilante(),beneficios.toString());
                                uparImagem(incidentes.getId());
                                if (enviou) {
                                    editor.apply();
                                    editor1.apply();
                                    DatabaseReference databaseReference = ConfiguracaoBancoDeDados.getDatabaseReference();
                                    Map<String, Object> map = new HashMap<>();
                                    map.put(incidentes.getId(), incidentes);
                                    databaseReference.child("incidentes").updateChildren(map);
                                    databaseReference = ConfiguracaoBancoDeDados.getDatabaseReference();
                                    map = new HashMap<>();
                                    map.put(beneficios.getIdvigilante(), beneficios);
                                    databaseReference.child("beneficios").updateChildren(map);
                                    imagemvideo = null;
                                    adapterOap = new AdapterOap(sharedPreferences.getAll(), TelaInicialOAP.this);
                                    recyclerView.setAdapter(adapterOap);
                                    enviou = false;
                                }
                            }
                            else
                            {
                                FirebaseAuth firebaseAuth = ConfiguracaoBancoDeDados.getFirebaseAuth();
                                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                incidentes.setComentário(comentario);
                                incidentes.setIdOAP(Base64.getEncoder().encodeToString(firebaseUser.getEmail().getBytes()));
                                incidentes.setDesconto("true");
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(incidentes.getId(),incidentes.toString());
                                Beneficios beneficios = new Beneficios();
                                beneficios.formatar(sharedPreferences1.getString(incidentes.getIdVigilante(),""));
                                beneficios.setValordesconto(valor);
                                beneficios.setPorcentagemdesconto(editText2.getText().toString());
                                beneficios.setTipoimposto(editText3.getText().toString());
                                SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                                editor1.putString(incidentes.getIdVigilante(),beneficios.toString());
                                uparImagem(incidentes.getId());
                                if (enviou) {
                                    editor.apply();
                                    editor1.apply();
                                    DatabaseReference databaseReference = ConfiguracaoBancoDeDados.getDatabaseReference();
                                    Map<String, Object> map = new HashMap<>();
                                    map.put(incidentes.getId(), incidentes);
                                    databaseReference.child("incidentes").updateChildren(map);
                                    databaseReference = ConfiguracaoBancoDeDados.getDatabaseReference();
                                    map = new HashMap<>();
                                    map.put(beneficios.getIdvigilante(), beneficios);
                                    databaseReference.child("beneficios").updateChildren(map);
                                    imagemvideo = null;
                                    adapterOap = new AdapterOap(sharedPreferences.getAll(), TelaInicialOAP.this);
                                    recyclerView.setAdapter(adapterOap);
                                    enviou = false;
                                }
                            }
                        }
                    }
                });
                alertdialog.show();
            }
            else
            {
                Toast.makeText(TelaInicialOAP.this,"Incidente já avalido!",Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(TelaInicialOAP.this, "Selecione um incidente pra avaliar!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
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
                StorageReference reference = storageReference.child("incidentes/").child("respostas/" + incidente);
                reference.putFile(imagemvideo).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                        Toast.makeText(TelaInicialOAP.this, "Imagem ou vídeo upado com sucesso!", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(TelaInicialOAP.this, "Falha ao upar!" + e.getMessage(), Toast.LENGTH_LONG).show();
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
                Toast.makeText(TelaInicialOAP.this,"Arquivo invalido",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void sair (View view)
    {
        Intent intent = new Intent(this,Login.class);
        FirebaseAuth firebaseAuth = ConfiguracaoBancoDeDados.getFirebaseAuth();
        firebaseAuth.signOut();
        finish();
        startActivity(intent);
    }
}
