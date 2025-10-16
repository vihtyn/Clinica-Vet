package org.example.ucb.cli;

import java.util.Scanner;
import java.util.List;
import org.example.ucb.control.RepositorioDeTratamento; //partedovitor
import org.example.ucb.dao.RepositorioDeTratamentoSQL; //partedovitor
import org.example.ucb.control.RepositorioDeEspecialidade; //partedovitor2
import org.example.ucb.dao.RepositorioDeEspecialidadeSQL; //partedovitor2
import org.example.ucb.model.Especialidade;
import org.example.ucb.model.Consulta;
import org.example.ucb.model.Tratamento;


public class SistemaClinicaVet {
    private static final Scanner entrada = new Scanner(System.in);
    private static RepositorioDeTratamento repositorioDeTratamento; //partedovitor
    private static RepositorioDeEspecialidade repositorioDeEspecialidade;  //partedovitor2
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
        repositorioDeEspecialidade = new RepositorioDeEspecialidadeSQL(); //partedovitor2
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

        // PARTE DO VÍTOR - ESPECIALIDADES;
        private static void exibirMenuEspecialidades() {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n--- Menu de Gerenciamento de Especialidades ---");
            System.out.println("1. Cadastrar nova especialidade");
            System.out.println("2. Buscar especialidade por ID");
            System.out.println("3. Listar todas as especialidades");
            System.out.println("4. Listar especialidades de um veterinário");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            int opcao = entrada.nextInt();
            entrada.nextLine(); // Limpa o buffer
                switch (opcao) {
                case 1:
            
                    System.out.println("\n--- Cadastrar Nova Especialidade ---");
                    System.out.print("Digite o nome da nova especialidade: ");
                    String nomeEspecialidade = entrada.nextLine();

                    Especialidade novaEspecialidade = new Especialidade(0, nomeEspecialidade);

                    repositorioDeEspecialidade.salvar(novaEspecialidade);

                    break;
                case 2:
                    System.out.println("\n--- Buscar Especialidade por ID ---");
                    System.out.print("Digite o ID da especialidade que deseja buscar: ");
                    int idParaBuscar = entrada.nextInt();
                    entrada.nextLine(); // Limpa o buffer

                    Especialidade especialidadeEncontrada = repositorioDeEspecialidade.BuscarEspecialidade(idParaBuscar);

                    if (especialidadeEncontrada != null) {

                        System.out.println("\n--- Especialidade Encontrada ---");
                        System.out.println("ID: " + especialidadeEncontrada.getId());
                        System.out.println("Nome: " + especialidadeEncontrada.getNome());
                        System.out.println("--------------------------------");
                    } else {

                        System.out.println("\nEspecialidade com o ID " + idParaBuscar + " não encontrada.");
                    }
                    break;
                case 3:
                    System.out.println("\n--- Lista de Todas as Especialidades ---");
                    List<Especialidade> todasAsEspecialidades = repositorioDeEspecialidade.ListarEspecialidade();

                    if (todasAsEspecialidades != null && !todasAsEspecialidades.isEmpty()) {
                        for (Especialidade especialidade : todasAsEspecialidades) {
                            System.out.println("ID: " + especialidade.getId() + " | Nome: " + especialidade.getNome());
                        }
                        System.out.println("--------------------------------------");
                    } else {
                        System.out.println("\nNenhuma especialidade cadastrada no sistema.");
                    }
                    break;
                case 4:
                    System.out.println("\n--- Listar Especialidades por Veterinário ---");
                    System.out.print("Digite o CRMV do veterinário (ex: CRMV-DF 12345): ");
                    String crmvParaBuscar = entrada.nextLine();
                    List<Especialidade> especialidadesDoVet = repositorioDeEspecialidade.BuscarEspPorVet(crmvParaBuscar);

                    if (especialidadesDoVet != null && !especialidadesDoVet.isEmpty()) {
                        System.out.println("\n--- Especialidades Encontradas para o CRMV: " + crmvParaBuscar + " ---");
                        for (Especialidade especialidade : especialidadesDoVet) {
                            System.out.println("ID: " + especialidade.getId() + " | Nome: " + especialidade.getNome());
                        }
                        System.out.println("----------------------------------------------------");
                    } else {
                        System.out.println("\nNenhuma especialidade encontrada para o veterinário com o CRMV " + crmvParaBuscar + ".");
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
