package com.example.gabriel.vigilantesurbanos;

import com.google.firebase.database.DatabaseReference;

public class DadosBancarios {
    private String id;
    private String banco;
    private String agencia;
    private String contacorrente;
    private String numerocartao;
    private String datadevencimento;
    private String codigo;

    /**
     * Construtor com parametros
     * @param banco a ser atribuido
     * @param agencia a ser atribuida
     * @param contacorrente a ser atribuida
     * @param numerocartao a ser atribuido
     * @param datadevencimento a ser atribuido
     * @param codigo a ser atribuido
     */

    public DadosBancarios (String id, String banco, String agencia, String contacorrente, String numerocartao, String datadevencimento, String codigo)
    {
        this.banco = banco;
        this.agencia = agencia;
        this.contacorrente = contacorrente;
        this.numerocartao = numerocartao;
        this.datadevencimento = datadevencimento;
        this.codigo = codigo;
        this.id = id;
    }//end Construtor com parametros

    public DadosBancarios ()
    {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Metodo que retorna uma string com os valores dos atributos da instancia de DadosBancarios.
     * @return string com os valores dos atributos
     */

    @Override
    public String toString() {
        return super.toString();
    }//end toString()

    /**
     * Metodo que retorna o valor do atributo agencia.
     * @return String agencia
     */

    public String getAgencia() {
        return agencia;
    }//end getAgencia()

    /**
     * Metodo que retorna o valor do atributo banco.
     * @return String banco
     */

    public String getBanco() {
        return banco;
    }//end getBanco()

    /**
     * Metodo que retorna o valor do atributo codigo.
     * @return String codigo
     */

    public String getCodigo() {
        return codigo;
    }//end getCodigo()

    /**
     * Metodo que retorna o valor do atributo contacorrente.
     * @return String contacorrente
     */

    public String getContacorrente() {
        return contacorrente;
    }//end getContacorrente()

    /**
     * Metodo que retorna o valor do atributo agencia.
     * @return String agencia
     */

    public String getDatadevencimento() {
        return datadevencimento;
    }//end getDatadevencimento()

    /**
     * Metodo que retorna o valor do atributo numerocartao.
     * @return String numerocartao
     */

    public String getNumerocartao() {
        return numerocartao;
    }//end getNumerocartao()

    /**
     * Metodo que seta o valor do atributo agencia.
     * @param agencia valor a ser atribuido
     */

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }//end setAgencia(String agencia)

    /**
     * Metodo que seta o valor do atributo banco.
     * @param banco String
     */

    public void setBanco(String banco) {
        this.banco = banco;
    }//end setBanco(String banco)

    /**
     * Metodo que seta o valor do atributo codigo.
     * @param codigo String
     */

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }//end setCodigo(String codigo)

    /**
     * Metodo que seta o valor do atributo contacorrente.
     * @param contacorrente String
     */

    public void setContacorrente(String contacorrente) {
        this.contacorrente = contacorrente;
    }//end setContacorrente(String contacorrente)

    /**
     * Metodo que seta o valor do atributo datadevencimento.
     * @param datadevencimento String
     */

    public void setDatadevencimento(String datadevencimento) {
        this.datadevencimento = datadevencimento;
    }//end setDatadevencimento(String datadevencimento)

    /**
     * Metodo que seta o valor do atributo numerocartao.
     * @param numerocartao String
     */

    public void setNumerocartao(String numerocartao) {
        this.numerocartao = numerocartao;
    }//end setNumeroCartao(String numerocartao)

    /**
     * Metodo que salva os dados bancarios no banco de dados.
     */

    public void inserir()
    {
        DatabaseReference databaseReference = ConfiguracaoBancoDeDados.getDatabaseReference();
        databaseReference.child("dados bancarios").child(getId()).setValue(this);
    }//end inserir()
}//end DadosBancarios
