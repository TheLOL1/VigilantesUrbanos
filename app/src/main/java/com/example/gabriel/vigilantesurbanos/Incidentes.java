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
    private String data;

    public Incidentes (String id,String idVigilante,String idOAP,String tipo,String localizacao,String arquivo,String notificacao,String data)
    {
        this.id = id;
        this.idVigilante = idVigilante;
        this.idOAP = idOAP;
        this.tipo = tipo;
        this.localizacao = localizacao;
        this.arquivo = arquivo;
        this.notificacao = notificacao;
        this.data = data;
    }

    public Incidentes ()
    {

    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
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
        return (getId() + " 1 " + getArquivo() + " 2 " + getIdOAP() + " 3 " + getIdVigilante() + " 4 " + getLocalizacao() + " 5 " + getTipo() + " 6 " + getData());
    }

    public void formatar (String s)
    {
        int x = s.indexOf(" 1 ");
        setId(s.substring(0,x-1));
        int y = s.indexOf(" 2 ");
        setArquivo(s.substring(x+3,y-1));
        x = s.indexOf(" 3 ");
        setIdOAP(s.substring(y+3,x-1));
        y = s.indexOf(" 4 ");
        setIdVigilante(s.substring(x+3,y-1));
        x = s.indexOf(" 5 ");
        setLocalizacao(s.substring(y+3,x-1));
        y = s.indexOf(" 6 ");
        setTipo(s.substring(x+3,y-1));
        setData(s.substring(y+3));
    }

    public void Inserir ()
    {
        DatabaseReference databaseReference = ConfiguracaoBancoDeDados.getDatabaseReference();
        databaseReference.child("incidentes").child(getId()).setValue(this);
    }
}
