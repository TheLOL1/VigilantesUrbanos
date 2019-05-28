package com.example.gabriel.vigilantesurbanos;

import com.google.firebase.database.DatabaseReference;

public class Vigilante extends Usuarios {
    //atributos
    private String nomecompleto;
    private String telefone;
    private String email;
    private String RG;
    private char sexo;
    private String nacionalidade;

    /**
     * Construtor com parametros
     * @param id do vigilante
     * @param cpf do vigilante
     * @param senha do vigilante
     * @param nome do vigilante
     * @param telefone do vigilante
     * @param email do vigilante
     * @param rg do vigilante
     * @param sexo do vigilante
     * @param nacionalidade do vigilante
     */
    public Vigilante (String id, String cpf, String senha, String nome, String telefone, String email, String rg, char sexo, String nacionalidade)
    {
        super(id,'V',cpf,senha);
        this.nomecompleto = nome;
        this.telefone = telefone;
        this.email = email;
        this.RG = rg;
        this.sexo = sexo;
        this.nacionalidade = nacionalidade;
    }//end Construtor com parametros

    /**
     * Metodo que retorna o sexo de um Vigilante.
     * @return char sexo
     */

    public char getSexo() {
        return sexo;
    }//end getSexo()

    /**
     * Metodo que retorna o email de um Vigilante.
     * @return String email
     */

    public String getEmail() {
        return email;
    }//end getEmail()

    /**
     * Metodo que retorna a nacionalidade de um Vigilante.
     * @return String nacionalidade
     */

    public String getNacionalidade() {
        return nacionalidade;
    }//end getNacionalidade()

    /**
     * Metodo que retorna o nome completo de um Vigilante.
     * @return String nomecompleto
     */

    public String getNomecompleto() {
        return nomecompleto;
    }//end getNomecompleto()

    /**
     * Metodo que retorna o rg de um Vigilante.
     * @return String rg
     */

    public String getRG() {
        return RG;
    }//end getRG()

    /**
     * Metodo que retorna o telefone de um Vigilante.
     * @return String telefone
     */

    public String getTelefone() {
        return telefone;
    }//end getTelefone()

    /**
     * Metodo que seta o e-mail do Vigilante.
     * @param email valor a ser atribuido
     */

    public void setEmail(String email) {
        this.email = email;
    }//end setEmail(String email)

    /**
     * Metodo que seta a nacionalidade do Vigilante.
     * @param nacionalidade valor a ser atribuido
     */

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }//end setNacionalidade(String nacionalidade)

    /**
     * Metodo que seta o nome completo do Vigilante.
     * @param nomecompleto valor a ser atribuido
     */

    public void setNomecompleto(String nomecompleto) {
        this.nomecompleto = nomecompleto;
    }//end setNomecompleto(String nomecompleto)

    /**
     * Metodo que seta o rg do Vigilante.
     * @param RG valor a ser atribuido
     */

    public void setRG(String RG) {
        this.RG = RG;
    }//end setRG(String RG)

    /**
     * Metodo que seta o sexo do Vigilante.
     * @param sexo valor a ser atribuido
     */

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }//end setSexo(char sexo)

    /**
     * Metodo que seta o telefone do Vigilante.
     * @param telefone valor a ser atribuido
     */

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Metodo que salva um Vigilante no banco de dados.
     */

    public void inserir()
    {
        DatabaseReference databaseReference = ConfiguracaoBancoDeDados.getDatabaseReference();
        databaseReference.child("usuarios").child(getID()).setValue(this);
    }//end inserir()
}//end Vigilante
