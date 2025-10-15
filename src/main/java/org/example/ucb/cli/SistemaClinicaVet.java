package org.example.ucb.cli;

import java.util.Scanner;
import java.util.List;
import org.example.ucb.control.RepositorioDeTratamento; //partedovitor
import org.example.ucb.dao.RepositorioDeTratamentoSQL; //partedovitor

public class SistemaClinicaVet {
    private static final Scanner entrada = new Scanner(System.in);
    private static RepositorioDeTratamento repositorioDeTratamento; //partedovitor
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
        repositorioDeTratamento = new RepositorioDeTratamentoSQL(); //partedovitor
        }

        // PARTE DO VÍTOR - TRATAMENTOS;
        private static void exibirMenuTratamentos() {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n--- Menu de Gerenciamento de Tratamentos ---");
            System.out.println("1. Adicionar novo tratamento a uma consulta");
            System.out.println("2. Buscar tratamento por ID");
            System.out.println("3. Listar tratamentos de uma consulta");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            int opcao = entrada.nextInt();
            entrada.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                     System.out.println("\n--- Adicionar Novo Tratamento ---");

                    System.out.print("Digite o ID da consulta à qual este tratamento pertence: ");
                    int idConsulta = entrada.nextInt();
                    entrada.nextLine(); 

                    System.out.print("Digite a descrição do tratamento: ");
                    String descricao = entrada.nextLine();

                    System.out.print("O tratamento utiliza antibiótico? (s/n): ");
                    String usaAntibioticoInput = entrada.nextLine();
                    boolean usaAntibiotico = usaAntibioticoInput.equalsIgnoreCase("s");

                    Consulta consulta = new Consulta(idConsulta, null, null, null);


                    Tratamento novoTratamento = new Tratamento(0, descricao, usaAntibiotico, consulta);

                    repositorioDeTratamento.salvar(novoTratamento);

                    break;
                case 2:
                    System.out.println("\n--- Buscar Tratamento por ID ---");
                    System.out.print("Digite o ID do tratamento que deseja buscar: ");
                    int idParaBuscar = entrada.nextInt();
                    entrada.nextLine(); // Limpa o buffer
.
                    Tratamento tratamentoEncontrado = repositorioDeTratamento.BuscarTratamento(idParaBuscar);

                    if (tratamentoEncontrado != null) {

                        System.out.println("\n--- Tratamento Encontrado ---");
                        System.out.println("ID do Tratamento: " + tratamentoEncontrado.getId());
                        System.out.println("ID da Consulta Associada: " + tratamentoEncontrado.getConsulta().getid());
                        System.out.println("Descrição: " + tratamentoEncontrado.getDescricao());
                        System.out.println("Usa Antibiótico: " + (tratamentoEncontrado.isAntibiotico() ? "Sim" : "Não"));
                        System.out.println("-----------------------------");
                    } else {

                        System.out.println("\nTratamento com o ID " + idParaBuscar + " não encontrado.");
                    }
                    break;
                case 3:
                    System.out.println("\n--- Listar Tratamentos por Consulta ---");
                    System.out.print("Digite o ID da consulta para ver os tratamentos associados: ");
                    int idConsultaParaListar = entrada.nextInt();
                    entrada.nextLine(); // Limpa o buffer

                    List<Tratamento> tratamentosDaConsulta = repositorioDeTratamento.BuscarPorConsulta(idConsultaParaListar);

                    if (tratamentosDaConsulta != null && !tratamentosDaConsulta.isEmpty()) {
                        System.out.println("\n--- Tratamentos Encontrados para a Consulta ID: " + idConsultaParaListar + " ---");
  
                        for (Tratamento tratamento : tratamentosDaConsulta) {
                            System.out.println("-----------------------------");
                            System.out.println("ID do Tratamento: " + tratamento.getId());
                            System.out.println("Descrição: " + tratamento.getDescricao());
                            System.out.println("Usa Antibiótico: " + (tratamento.isAntibiotico() ? "Sim" : "Não"));
                        }
                        System.out.println("-----------------------------");
                    } else {

                        System.out.println("\nNenhum tratamento encontrado para a consulta com o ID " + idConsultaParaListar + ".");
                    }

                    break;
                case 0:
                    sair = true;
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }
    }

    }
}
