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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Map;

public class TelaInicialOAP extends AppCompatActivity {
    Uri imagemvideo;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    RecyclerView recyclerView;
    AdapterOap adapterOap;
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
    }

    public void AvaliarIncidente (View view)
    {
        if (adapterOap.posicaoselecionada != -1) {
            AlertDialog.Builder alertdialog = new AlertDialog.Builder(this, R.style.AlertDialogStyle);
            Toolbar toolbar = new Toolbar(this);
            toolbar.setTitle("Avaliar Incidente");
            toolbar.setBackgroundColor(Color.BLACK);
            toolbar.setTitleTextColor(Color.WHITE);
            Button button = new Button(this);
            button.setText("ANEXAR FOTO OU VÍDEO");
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
            final LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            editText.setTextColor(Color.WHITE);
            editText1.setTextColor(Color.WHITE);
            linearLayout.addView(toolbar);
            linearLayout.addView(button);
            linearLayout.addView(editText);
            linearLayout.addView(textView);
            linearLayout.addView(radioGroup);
            linearLayout.addView(editText1);
            alertdialog.setView(linearLayout);
            alertdialog.setCancelable(false);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent galeria = new Intent(Intent.ACTION_GET_CONTENT);
                    galeria.setType("*/*");
                    startActivityForResult(Intent.createChooser(galeria, "Selecione imagem ou vídeo"), 50);
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
                        Toast.makeText(TelaInicialOAP.this, "Incidente não reportado (todos os campos são obrigatórios)", Toast.LENGTH_LONG).show();
                    } else {
                        imagemvideo = null;
                    }
                }
            });
            alertdialog.show();
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
            progressDialog.setTitle("Upando...");
            progressDialog.show();
            StorageReference reference = storageReference.child("incidentes/").child("respostas/" + incidente);
            reference.putFile(imagemvideo).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(TelaInicialOAP.this,"Imagem ou vídeo upado com sucesso!",Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(TelaInicialOAP.this,"Falha ao upar!" + e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progresso = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage((int)progresso+"%" + " Upado");
                }
            });
        }
    }
}
