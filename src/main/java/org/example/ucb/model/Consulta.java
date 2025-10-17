package org.example.ucb.model;

public class Consulta {

    private int id;
    private String diagnostico;
    private Animal animal;
    private Veterinario veterinario;

    public Consulta() {

    }

    public Consulta(int id, String diagnostico, Animal animal, Veterinario veterinario) {
        this.id = id;
        this.diagnostico = diagnostico;
        this.animal = animal;
        this.veterinario = veterinario;
    }

    public int getid(){
        return id;
    }
    public void setid(int id){
        this.id = id;
    }
    public String getdiagnostico(){
        return diagnostico;
    }
    public void setdiagnostico(String diagnostico){
        this.diagnostico = diagnostico;
    }
    public Animal getanimal(){
        return animal;
    }
    public void setanimal(Animal animal){
        this.animal = animal;
    }
    public Veterinario getveterinario(){
        return veterinario;
    }
    public void setveterinario(Veterinario veterinario){
        this.veterinario = veterinario;
    }
}
