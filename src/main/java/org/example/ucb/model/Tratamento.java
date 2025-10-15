package org.example.ucb.model;

public class Tratamento {

    private int id;
    private String descricao;
    private boolean antibiotico;

    public Tratamento( int id, String descricao, boolean antibiotico) {
        this.id = id;
        this.descricao = descricao;
        this.antibiotico = antibiotico;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public boolean isAntibiotico() {
        return antibiotico;
    }
    public void setAntibiotico(boolean antibiotico) {
        this.antibiotico = antibiotico;
    }
}
