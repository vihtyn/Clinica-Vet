package org.example.ucb.model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Veterinario {

    private List<Consulta> historicoConsultas;

    private String crmv;
    private String nome;
    private int idade;
    private LocalDate dataGraduacao;

    public Veterinario(){

    }
    
    public Veterinario(String crmv, String nome, int idade,  LocalDate dataGraduacao) {
        this.crmv = crmv;
        this.nome = nome;
        this.idade = idade;
        this.dataGraduacao = dataGraduacao;
        this.historicoConsultas = new ArrayList<>();
    }

    public Veterinario(String crmv, String nome, int idade, LocalDate dataGraduacao, Consulta consultaInicial) {
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
    public LocalDate getDataGraduacao() {
        return dataGraduacao;
    }
    public void setDataGraduacao(LocalDate dataGraduacao) {
        this.dataGraduacao = dataGraduacao;
    }
}
