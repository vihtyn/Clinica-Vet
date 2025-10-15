package org.example.ucb.model;

import java.util.Date;

public class Certificacao {

    private int NumeroRegistro;
    private Date DataObtencao;
    private String InstituicaoCertificadora;
    private Veterinario veterinario;
    private Especialidade especialidade;

    public Certificacao(int NumeroRegistro, Date DataObtencao,  String InstituicaoCertificadora, Veterinario veterinario, Especialidade especialidade) {
        this.NumeroRegistro = NumeroRegistro;
        this.DataObtencao = DataObtencao;
        this.InstituicaoCertificadora = InstituicaoCertificadora;
        this.veterinario = veterinario;
        this.especialidade = epecialidade;
    }

    public int getNumeroRegistro() {
        return NumeroRegistro;
    }
    public void setNumeroRegistro(int NumeroRegistro) {
        this.NumeroRegistro = NumeroRegistro;
    }
    public Date getDataObtencao() {
        return DataObtencao;
    }
    public void setDataObtencao(Date DataObtencao) {
        this.DataObtencao = DataObtencao;
    }
    public String setInstituicaoCertificadora() {
        return InstituicaoCertificadora;
    }
    public void getInstituicaoCertificadora(String InstituicaoCertificadora) {
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
