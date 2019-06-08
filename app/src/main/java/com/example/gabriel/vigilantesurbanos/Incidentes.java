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
    private String comentário;
    private String descricao;
    private String dinheiro = "false";
    private String vigicoin = "false";
    private String desconto = "false";

    public Incidentes (String id,String idVigilante,String idOAP,String tipo,String localizacao,String arquivo,String notificacao,String data,String comentário,String descricao)
    {
        this.id = id;
        this.idVigilante = idVigilante;
        this.idOAP = idOAP;
        this.tipo = tipo;
        this.localizacao = localizacao;
        this.arquivo = arquivo;
        this.notificacao = notificacao;
        this.data = data;
        this.comentário = comentário;
        this.descricao = descricao;
    }

    public Incidentes ()
    {

    }

    public String getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(String dinheiro) {
        this.dinheiro = dinheiro;
    }

    public String getDesconto() {
        return desconto;
    }

    public void setDesconto(String desconto) {
        this.desconto = desconto;
    }

    public String getVigicoin() {
        return vigicoin;
    }

    public void setVigicoin(String vigicoin) {
        this.vigicoin = vigicoin;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getComentário() {
        return comentário;
    }

    public void setComentário(String comentário) {
        this.comentário = comentário;
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
        return (getId() + " 1 " + getArquivo() + " 2 " + getIdOAP() + " 3 " + getIdVigilante() + " 4 " + getLocalizacao() + " 5 " + getTipo() + " 6 " + getData() + " 7 " + getComentário() + " 8 " + getDescricao() + " 9 " + getDinheiro() + " 10 " + getVigicoin() + " 11 " + getDesconto());
    }

    public void formatar (String s)
    {
        int x = s.indexOf(" 1 ");
        if (x != -1) {
            setId(s.substring(0, x));
        }
        int y = s.indexOf(" 2 ");
        if (x != -1 && y != -1) {
            setArquivo(s.substring(x + 3, y));
        }
        x = s.indexOf(" 3 ");
        if (x != -1 && y != -1) {
            setIdOAP(s.substring(y + 3, x));
        }
        y = s.indexOf(" 4 ");
        if (x != -1 && y != -1) {
            setIdVigilante(s.substring(x + 3, y));
        }
        x = s.indexOf(" 5 ");
        if (x != -1 && y != -1) {
            setLocalizacao(s.substring(y + 3, x));
        }
        y = s.indexOf(" 6 ");
        if (x != -1 && y != -1) {
            setTipo(s.substring(x + 3, y));
        }
        x = s.indexOf(" 7 ");
        if (y != -1 && x != -1) {
            setData(s.substring(y + 3, x));
        }
        y = s.indexOf(" 8 ");
        if (x != -1 && y != -1) {
            setComentário(s.substring(x + 3, y));
        }
        x = s.indexOf(" 9 ");
        if (x != -1 && y != -1)
        {
            setDescricao(s.substring(y + 3,x));
        }
        y = s.indexOf(" 10 ");
        if (x != -1 && y != -1)
        {
            setDinheiro(s.substring(x+3,y));
        }
        x = s.indexOf(" 11 ");
        if (x != -1 && y != -1)
        {
            setVigicoin(s.substring(y+4,x));
            setDesconto(s.substring(x+4));
        }
    }

    public void Inserir ()
    {
        DatabaseReference databaseReference = ConfiguracaoBancoDeDados.getDatabaseReference();
        databaseReference.child("incidentes").child(getId()).setValue(this);
    }
}
