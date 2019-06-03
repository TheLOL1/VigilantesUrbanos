package com.example.gabriel.vigilantesurbanos;


public class Usuarios {
    // atributos
    private String ID;
    private String Tipo;
    private String CPF;
    private String Senha;

    /**
     * Construtor inicial
     */

    public Usuarios()
    {

    }

    /**
     * Construtor com parametros.
     * @param id define id do usuario
     * @param tipo define tipo do usuario
     * @param cpf define cpf do usuario
     * @param senha define senha do usuario
     */

    public Usuarios (String id, String tipo, String cpf, String senha)
    {
        this.ID = id;
        this.Tipo = tipo;
        this.CPF = cpf;
        this.Senha = senha;
    }//end Construtor da classe Usuarios

    /**
     * Retorna o tipo de uma instancia de Usuarios.
     * @return tipo do Usuarios
     */

    public String getTipo() {
        return Tipo;
    }//end getTipo()

    /**
     * Retorna o cpf de uma instancia de Usuarios.
     * @return cpf do Usuarios
     */

    public String getCPF() {
        return CPF;
    }//end getCPF()

    /**
     * Retorna o id de uma instancia de Usuarios.
     * @return id do Usuarios
     */

    public String getID() {
        return ID;
    }//end getID()

    /**
     * Retorna a senha de uma instancia de Usuarios.
     * @return senha do Usuarios
     */

    public String getSenha() {
        return Senha;
    }//end getSenha()

    /**
     * Seta o cpf de um Usuarios.
     * @param CPF valor a ser atribuido
     */

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }//end setCPF(String CPF)

    /**
     * Seta o id de um Usuarios.
     * @param ID valor a ser atribuido
     */

    public void setID(String ID) {
        this.ID = ID;
    }//end setID(String ID)

    /**
     * Seta a senha de um Usuarios.
     * @param senha valor a ser atribuido
     */

    public void setSenha(String senha) {
        Senha = senha;
    }//end setSenha(String senha)

    /**
     * Seta o tipo de um Usuarios.
     * @param tipo valor a ser atribuido
     */

    public void setTipo(String tipo) {
        Tipo = tipo;
    }//end setTipo(char tipo)

    /**
     * Metodo que retorna uma string com os valores dos atributos da instancia de Usuarios.
     * @return string com os valores dos atributos
     */

    @Override
    public String toString() {
        return super.toString();
    }//end toString()
}//end Usuarios
