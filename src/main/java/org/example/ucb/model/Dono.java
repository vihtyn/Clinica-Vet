package org.example.ucb.model;

import java.time.LocalDate;
import java.util.Date;

public class Dono {

    //Declaração dos atributos
    private String CPF;
    private String nome;
    private String endereco;
    private LocalDate dataNascimento;

    //Construtores
    public Dono(String CPF, LocalDate dataNascimento, String endereco, String nome) {
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.nome = nome;
    }


    //Gets
    public String getCPF() {
        return CPF;
    }
    public String  getNome() {
        return nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    //Sets
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
