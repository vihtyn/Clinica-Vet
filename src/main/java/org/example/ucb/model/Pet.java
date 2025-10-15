package org.example.ucb.model;

import java.util.Date;

public class Pet extends Animal {

    private String rfid;

    public Pet(){
        super();
    }

    public Pet(int id, String nome,String porte, int idade, String especie, String rfid, Dono dono) {
        super(id, nome, porte, idade, especie, dono);
        this.rfid = rfid;
    }

    public String getrfid(){
        return rfid;
    }
    public void setrfid(String rfid){
        this.rfid = rfid;
    }

}
