package org.example.ucb.model;

import java.util.Date;

public class Certificacao {

    private String NumeroRegistro;
    private Date DataObtencao;
    private String InstituicaoCertificadora;
    private Veterinario veterinario;
    private Especialidade especialidade;

    public Certificacao(){

    }

    public Certificacao(String NumeroRegistro, Date DataObtencao,  String InstituicaoCertificadora, Veterinario veterinario) {
        this.NumeroRegistro = NumeroRegistro;
        this.DataObtencao = DataObtencao;
        this.InstituicaoCertificadora = InstituicaoCertificadora;
        this.veterinario = veterinario;
    }

    public String getNumeroRegistro() {
        return NumeroRegistro;
    }

    public void setNumeroRegistro(String NumeroRegistro) {
        this.NumeroRegistro = NumeroRegistro;
    }

    public Date getDataObtencao() {
        return DataObtencao;
    }

    public void setDataObtencao(Date DataObtencao) {
        this.DataObtencao = DataObtencao;
    }

    public String getInstituicaoCertificadora() {
        return InstituicaoCertificadora;
    }

    public void setInstituicaoCertificadora(String InstituicaoCertificadora) {
        this.InstituicaoCertificadora = InstituicaoCertificadora;
    }

    public Veterinario getVeterinario() {

        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
}
