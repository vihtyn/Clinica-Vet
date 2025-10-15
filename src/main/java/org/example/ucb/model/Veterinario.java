package org.example.ucb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Veterinario {

    private List<Consulta> historicoConsultas;

    private String crmv;
    private String nome;
    private int idade;
    private Date dataGraduacao;
    
    public Veterinario(String crmv, String nome, int idade,  Date dataGraduacao) {
        this.crmv = crmv;
        this.nome = nome;
        this.idade = idade;
        this.dataGraduacao = dataGraduacao;
        this.historicoConsultas = new ArrayList<>();
    }

    public Veterinario(String crmv, String nome, int idade, Date dataGraduacao, Consulta consultaInicial) {
        this.crmv = crmv;
        this.nome = nome;
        this.idade = idade;
        this.dataGraduacao = dataGraduacao;
        this.historicoConsultas = new ArrayList<>();
        this.historicoConsultas.add(consultaInicial);
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
}
