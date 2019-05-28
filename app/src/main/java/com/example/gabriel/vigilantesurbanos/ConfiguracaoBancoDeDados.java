package com.example.gabriel.vigilantesurbanos;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoBancoDeDados {
    //atributos
    public static DatabaseReference databaseReference;
    public static FirebaseAuth firebaseAuth;

    /**
     * Seta a referencia do banco de dados.
     * @return databaseReference
     */

    public static DatabaseReference getDatabaseReference()
    {
        if (databaseReference == null)
        {
            databaseReference = FirebaseDatabase.getInstance().getReference();
        } //end if
        return (databaseReference);
    }//end getDatabaseReference()

    /**
     * Autentica no firebase.
     * @return firebaseAuth
     */

    public static FirebaseAuth getFirebaseAuth()
    {
        if (firebaseAuth == null)
        {
            firebaseAuth = firebaseAuth.getInstance();
        }
        return (firebaseAuth);
    }//end getFirebaseAuth()
}//end ConfiguracaoBancoDeDados
