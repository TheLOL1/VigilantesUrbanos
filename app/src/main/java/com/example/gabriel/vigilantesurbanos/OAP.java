package com.example.gabriel.vigilantesurbanos;

import com.google.firebase.database.DatabaseReference;

public class OAP extends Usuarios {
    //atributos
    private String nomecompleto;

    /**
     * Construtor com parametros.
     * @param id do OAP
     * @param cpf do OAP
     * @param senha do OAP
     * @param nome do OAP
     */

    public OAP (String id,String cpf,String senha,String nome)
    {
        super(id,'O',cpf,senha);
        this.nomecompleto = nome;
    }//end Construtor com parametros

    /**
     * Metodo que seta o nome completo do OAP
     * @param nomecompleto valor a ser atribuido
     */

    public void setNomecompleto(String nomecompleto) {
        this.nomecompleto = nomecompleto;
    }//end setNomeCompleto(String nomecompleto)

    /**
     * Metodo que retorna o nome completo do OAP.
     * @return string nomecompleto
     */

    public String getNomecompleto() {
        return nomecompleto;
    }//end getNomeCompleto()

    /**
     * Metodo que salva um OAP no banco de dados.
     */

    public void inserir()
    {
        DatabaseReference databaseReference = ConfiguracaoBancoDeDados.getDatabaseReference();
        databaseReference.child("usuarios").child(getID()).setValue(this);
    }//end inserir()
}//end OAP
