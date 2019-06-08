package com.example.gabriel.vigilantesurbanos;

import com.google.firebase.database.DatabaseReference;

public class Beneficios {
    private String idvigilante;
    private String dinheiro;
    private String VigiCoin;
    private String porcentagemdesconto;
    private String tipoimposto;
    private String valordesconto;

    public Beneficios (String idvigilante,String dinheiro,String vigicoin,String porcentagemdesconto,String tipoimposto,String valordesconto)
    {
        this.idvigilante = idvigilante;
        this.dinheiro = dinheiro;
        this.VigiCoin = vigicoin;
        this.porcentagemdesconto = porcentagemdesconto;
        this.tipoimposto = tipoimposto;
        this.valordesconto = valordesconto;
    }

    public Beneficios ()
    {

    }

    public String getDinheiro() {
        return dinheiro;
    }

    public String getIdvigilante() {
        return idvigilante;
    }

    public String getPorcentagemdesconto() {
        return porcentagemdesconto;
    }

    public String getTipoimposto() {
        return tipoimposto;
    }

    public String getValordesconto() {
        return valordesconto;
    }

    public String getVigiCoin() {
        return VigiCoin;
    }

    public void setDinheiro(String dinheiro) {
        this.dinheiro = dinheiro;
    }

    public void setPorcentagemdesconto(String porcentagemdesconto) {
        this.porcentagemdesconto = porcentagemdesconto;
    }

    public void setIdvigilante(String idvigilante) {
        this.idvigilante = idvigilante;
    }

    public void setTipoimposto(String tipoimposto) {
        this.tipoimposto = tipoimposto;
    }

    public void setValordesconto(String valordesconto) {
        this.valordesconto = valordesconto;
    }

    public void setVigiCoin(String vigiCoin) {
        VigiCoin = vigiCoin;
    }

    public String toString ()
    {
        return (getIdvigilante() + " 1 " + getDinheiro() + " 2 " + getPorcentagemdesconto() + " 3 " + getTipoimposto() + " 4 " + getValordesconto() + " 5 " + getVigiCoin());
    }

    public void Inserir ()
    {
        DatabaseReference databaseReference = ConfiguracaoBancoDeDados.getDatabaseReference();
        databaseReference.child("beneficios").child(getIdvigilante()).setValue(this);
    }

    public void formatar (String s)
    {
        int x = s.indexOf(" 1 ");
        if (x != -1) {
            setIdvigilante(s.substring(0, x));
        }
        int y = s.indexOf(" 2 ");
        if (x != -1 && y != -1) {
            setDinheiro(s.substring(x + 3, y));
        }
        x = s.indexOf(" 3 ");
        if (y != -1 && x != -1) {
            setPorcentagemdesconto(s.substring(y + 3, x));
        }
        y = s.indexOf(" 4 ");
        if (x != -1 && y != -1) {
            setTipoimposto(s.substring(x + 3, y));
        }
        x = s.indexOf(" 5 ");
        if (x != -1 && y != -1) {
            setValordesconto(s.substring(y + 3, x));
            setVigiCoin(s.substring(x + 3));
        }
    }
}
