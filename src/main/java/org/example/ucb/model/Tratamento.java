package org.example.ucb.model;

public class Tratamento {

    private int id;
    private String descricao;
    private boolean antibiotico;
    private Consulta consulta;

    public Tratamento( int id, String descricao, boolean antibiotico, Consulta consulta) {
        this.id = id;
        this.descricao = descricao;
        this.antibiotico = antibiotico;
        this.consulta = consulta;
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
    public Consulta getConsulta() {
        return consulta;
    }
    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
}
