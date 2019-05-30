package com.example.gabriel.vigilantesurbanos;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class Vigilante extends Usuarios {
    //atributos
    private String nomecompleto;
    private String telefone;
    private String email;
    private String RG;
    private char sexo;
    private String nacionalidade;
    private String IDDadosBancarios;
    private String cep;
    private String endereco;
    private String numero;
    private String bairro;
    private String uf;
    private String cidade;
    private String complemento;
    private String IDBeneficios;
    private ArrayList<String> incidentes;

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
    public Vigilante (String idbeneficios, String id, String cpf, String senha, String nome, String telefone, String email, String rg, char sexo, String nacionalidade, String iddadosbancarios,String cep, String endereco, String numero, String bairro, String uf, String cidade, String complemento)
    {
        super(id,'V',cpf,senha);
        this.nomecompleto = nome;
        this.telefone = telefone;
        this.email = email;
        this.RG = rg;
        this.sexo = sexo;
        this.nacionalidade = nacionalidade;
        this.IDDadosBancarios = iddadosbancarios;
        this.cep = cep;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.uf = uf;
        this.cidade = cidade;
        this.complemento = complemento;
        this.IDBeneficios = idbeneficios;
        this.incidentes = new ArrayList<>(0);
    }//end Construtor com parametros

    public ArrayList<String> getIncidentes() {
        return incidentes;
    }

    public String getIDBeneficios() {
        return IDBeneficios;
    }

    public void setIDBeneficios(String IDBeneficios) {
        this.IDBeneficios = IDBeneficios;
    }

    public void setIncidentes(String idincidente) {
        this.incidentes.add(idincidente);
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNumero() {
        return numero;
    }

    public String getUf() {
        return uf;
    }

    /**
     * Metodo que retorna o sexo de um Vigilante.
     * @return char sexo
     */

    public char getSexo() {
        return sexo;
    }//end getSexo()

    /**
     * Metodo que retorna o id correspondente de uma instancia de dados bancarios.
     * @return String IDDadosBancarios
     */

    public String getIDDadosBancarios() {
        return IDDadosBancarios;
    }

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
     * Metodo que seta uma referencia a uma instancia de dados bancarios.
     * @param IDDadosBancarios valor a ser atribuido
     */

    public void setIDDadosBancarios(String IDDadosBancarios) {
        this.IDDadosBancarios = IDDadosBancarios;
    }

    /**
     * Metodo que salva um Vigilante no banco de dados.
     */

    public void inserir()
    {
        DatabaseReference databaseReference = ConfiguracaoBancoDeDados.getDatabaseReference();
        databaseReference.child("usuarios").child("vigilante").child(getID()).setValue(this);
    }//end inserir()
}//end Vigilante
