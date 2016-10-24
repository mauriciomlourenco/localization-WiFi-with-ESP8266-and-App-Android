package com.example.mauri.appcoleira;

/**
 * Created by mauri on 23/10/2016.
 */

public class IDColeiraCadastrado {
    int id;
    String IDColeira;

    public IDColeiraCadastrado(int id, String IDColeira) {
        this.id = id;
        this.IDColeira = IDColeira;
    }

    public IDColeiraCadastrado(String IDColeira) {
        this.IDColeira = IDColeira;
    }

    public IDColeiraCadastrado() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIDColeira() {
        return IDColeira;
    }

    public void setIDColeira(String IDColeira) {
        this.IDColeira = IDColeira;
    }
}
