package org.example.ucb.cli;

import java.util.Scanner;
import java.util.List;

public class SistemaClinicaVet {
    private static final Scanner entrada = new Scanner(System.in);
    private static
    public static void main(String[] args) {

        try{
            configurarDependencias();
            exibirMenuPrincipal();
        } catch (Exception e) {
            System.err.println("Erro ao iniciar o sistema: " + e.getMessage());
        } finally {
            entrada.close();
        }
        private static void configurarDependencias(){

        }

    }
}