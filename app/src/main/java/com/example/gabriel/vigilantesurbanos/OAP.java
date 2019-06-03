package com.example.gabriel.vigilantesurbanos;

import com.google.firebase.database.DatabaseReference;

public class OAP extends Usuarios {
    //atributos
    private String nomecompleto;
    private String email;

    /**
     * Construtor com parametros.
     * @param id do OAP
     * @param cpf do OAP
     * @param senha do OAP
     * @param nome do OAP
     */

    public OAP (String id,String cpf,String senha,String nome,String email)
    {
        super(id,"OAP",cpf,senha);
        this.nomecompleto = nome;
        this.email = email;
    }//end Construtor com parametros

    public OAP ()
    {

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

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
        databaseReference.child("usuarios").child("OAP").child(getID()).setValue(this);
    }//end inserir()

    public String toString ()
    {
        return (getID() + " " + getTipo() + " " + getCPF() + " " + getSenha() + " " + getNomecompleto() + " " + getEmail());
    }
}//end OAP
