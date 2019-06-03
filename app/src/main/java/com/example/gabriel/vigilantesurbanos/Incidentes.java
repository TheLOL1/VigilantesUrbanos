package com.example.gabriel.vigilantesurbanos;


import com.google.firebase.database.DatabaseReference;

public class Incidentes {
    private String id;
    private String idVigilante;
    private String idOAP;
    private String tipo;
    private String localizacao;
    private String arquivo;
    private String notificacao;

    public Incidentes (String id,String idVigilante,String idOAP,String tipo,String localizacao,String arquivo,String notificacao)
    {
        this.id = id;
        this.idVigilante = idVigilante;
        this.idOAP = idOAP;
        this.tipo = tipo;
        this.localizacao = localizacao;
        this.arquivo = arquivo;
        this.notificacao = notificacao;
    }

    public Incidentes ()
    {

    }

    public String getNotificacao() {
        return notificacao;
    }

    public void setNotificacao(String notificacao) {
        this.notificacao = notificacao;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdOAP(String idOAP) {
        this.idOAP = idOAP;
    }

    public void setIdVigilante(String idVigilante) {
        this.idVigilante = idVigilante;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getArquivo() {
        return arquivo;
    }

    public String getId() {
        return id;
    }

    public String getIdOAP() {
        return idOAP;
    }

    public String getIdVigilante() {
        return idVigilante;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public String getTipo() {
        return tipo;
    }

    public String toString ()
    {
        return (getId() + " " + getArquivo() + " " + getIdOAP() + " " + getIdVigilante() + " " + getLocalizacao() + " " + getTipo());
    }

    public void Inserir ()
    {
        DatabaseReference databaseReference = ConfiguracaoBancoDeDados.getDatabaseReference();
        databaseReference.child("incidentes").child(getId()).setValue(this);
    }
}
