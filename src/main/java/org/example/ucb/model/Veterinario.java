package org.example.ucb.model;

import java.util.Date;

public class Veterinario {
    private String crmv;
    private String nome;
    private int idade;
    private Date dataGraduacao;
    private Consulta consulta;


    public Veterinario(String crmv, String nome, int idade,  Date dataGraduacao, Consulta consulta) {
        this.crmv = crmv;
        this.nome = nome;
        this.idade = idade;
        this.dataGraduacao = dataGraduacao;
        this.consulta = consulta;
    }

    public String getCrmv() {
        return crmv;
    }
    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public Date getDataGraduacao() {
        return dataGraduacao;
    }
    public void setDataGraduacao(Date dataGraduacao) {
        this.dataGraduacao = dataGraduacao;
    }
    public Consulta getConsulta() {
        return consulta;
    }
    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
}
