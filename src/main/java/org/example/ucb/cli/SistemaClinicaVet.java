package org.example.ucb.cli;

import java.util.Scanner;
import java.util.List;
import org.example.ucb.control.RepositorioDeTratamento; //partedovitor
import org.example.ucb.dao.RepositorioDeTratamentoSQL; //partedovitor
import org.example.ucb.model.Tratamento; //partedovitor
import org.example.ucb.control.RepositorioDeEspecialidade; //partedovitor2
import org.example.ucb.dao.RepositorioDeEspecialidadeSQL; //partedovitor2
import org.example.ucb.model.Especialidade; //partedovitor2
import org.example.ucb.control.RepositorioDeAnimal; //partedorenan
import org.example.ucb.dao.RepositorioDeAnimalSQL; //partedorenan
import org.example.ucb.model.Animal; //partedorenan
import org.example.ucb.control.RepositorioDeDono;
import org.example.ucb.dao.RepositorioDeDonoSQL;
import org.example.ucb.model.Pet;
import org.example.ucb.model.Exotico;
import org.example.ucb.model.Dono;
import org.example.ucb.model.Consulta;



public class SistemaClinicaVet {
    private static final Scanner entrada = new Scanner(System.in);
    private static RepositorioDeTratamento repositorioDeTratamento; //partedovitor
    private static RepositorioDeEspecialidade repositorioDeEspecialidade;  //partedovitor2
    private static RepositorioDeAnimal repositorioDeAnimal; //partedorenan
    private static RepositorioDeDono repositorioDeDono;
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
        repositorioDeAnimal = new RepositorioDeAnimalSQL(); //partedorenan
        repositorioDeDono = new RepositorioDeDonoSQL();
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

        //PARTE DO RENAN - ANIMAL;
        private static void exibirMenuAnimais() {
        boolean sair = false;
        
        while (!sair) {
            System.out.println("\n--- Menu de Animais ---");
            System.out.println("1. Cadastrar Animal");
            System.out.println("2. Listar Todos os Animais");
            System.out.println("3. Buscar Animal por ID");
            System.out.println("4. Atualizar Animal");
            System.out.println("5. Excluir Animal");
            System.out.println("6. Buscar Animais por Dono (CPF)");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int opcao = entrada.nextInt();
            entrada.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    System.out.println("--- Cadastrar Novo Animal ---");
                    System.out.print("Digite o CPF do Dono: ");
                    String cpfDono = entrada.nextLine();
                    Dono dono = repositorioDeDono.BuscarPorCPF(cpfDono); 

                    if (dono == null) {
                        System.out.println("Erro: Dono com CPF " + cpfDono + " não encontrado.");
                        System.out.println("Por favor, cadastre o dono antes de cadastrar o animal.");
                        break;
                    }
                    System.out.println("Dono encontrado: " + dono.getNome()); 

                    System.out.println("Qual o tipo de animal?");
                    System.out.println("1. Pet (Doméstico)");
                    System.out.println("2. Animal Exótico");
                    System.out.print("Opção: ");
                    int tipo = entrada.nextInt();
                    entrada.nextLine(); 
                    System.out.print("Nome: ");
                    String nome = entrada.nextLine();
                    System.out.print("Espécie: ");
                    String especie = entrada.nextLine();
                    System.out.print("Porte (P, M, G): ");
                    String porte = entrada.nextLine();
                    System.out.print("Idade: ");
                    int idade = entrada.nextInt();
                    entrada.nextLine(); // Limpar buffer
                    Animal novoAnimal;

                    if (tipo == 1) { //Só pra indentificar se é pet ou exótico (Pet = 1, Exótico = 2)
                        System.out.print("RFID (Chip de identificação): ");
                        String rfid = entrada.nextLine();
                        Pet pet = new Pet(); 
                        pet.setrfid(rfid); 
                        novoAnimal = pet;

                    } else if (tipo == 2) {
                        System.out.print("RFIDEX (Identificação de exótico): ");
                        String rfidex = entrada.nextLine();
                        System.out.print("Nota Fiscal: ");
                        String notaFiscal = entrada.nextLine();
                        Exotico exotico = new Exotico(); 
                        exotico.setRfidex(rfidex); 
                        exotico.setNotaFiscal(notaFiscal); 
                        novoAnimal = exotico;

                    } else {
                        System.out.println("Tipo inválido. Cadastro cancelado.");
                        break; 
                    }
                    novoAnimal.setNome(nome); 
                    novoAnimal.setEspecie(especie); 
                    novoAnimal.setPorte(porte); 
                    novoAnimal.setIdade(idade); 
                    novoAnimal.setDono(dono); 
                    repositorioDeAnimal.salvar(novoAnimal); 
                    break;
                
                case 2:
                    System.out.println("--- Lista de Animais Cadastrados ---");
                    List<Animal> animais = repositorioDeAnimal.ListarTodos(); 
                
                    if (animais.isEmpty()) {
                        System.out.println("Nenhum animal cadastrado.");
                    } else {

                        for (Animal animal : animais) {
                            System.out.println("--------------------");
                            System.out.println("ID: " + animal.getId()); 
                            System.out.println("Nome: " + animal.getNome()); 
                            System.out.println("Espécie: " + animal.getEspecie()); 
                            System.out.println("Idade: " + animal.getIdade()); 
                            

                            System.out.println("Dono: " + animal.getDono().getNome()); 
                            if (animal instanceof Pet) {
                                System.out.println("Tipo: Pet");
                                System.out.println("RFID: " + ((Pet) animal).getrfid()); 
                            } else if (animal instanceof Exotico) {
                                System.out.println("Tipo: Exótico");
                                System.out.println("RFIDEX: " + ((Exotico) animal).getRfidex()); 
                                System.out.println("Nota Fiscal: " + ((Exotico) animal).getNotaFiscal()); 
                            }
                        }
                        System.out.println("--------------------");
                    }
                    break;
                
                case 3:
                    System.out.println("--- Buscar Animal por ID ---");
                    System.out.print("Digite o ID do animal: ");
                    int idBusca = entrada.nextInt();
                    entrada.nextLine(); // Limpa buffer
                    
                    Animal animalEncontrado = repositorioDeAnimal.BuscarPorId(idBusca); 
                    
                    if (animalEncontrado != null) {
                        System.out.println("Animal encontrado:");
                        System.out.println("ID: " + animalEncontrado.getId()); 
                        System.out.println("Nome: " + animalEncontrado.getNome()); 
                        System.out.println("Espécie: " + animalEncontrado.getEspecie()); 
                        System.out.println("Idade: " + animalEncontrado.getIdade()); 
                        
                        if (animalEncontrado instanceof Pet) {
                            System.out.println("Tipo: Pet");
                            System.out.println("RFID: " + ((Pet) animalEncontrado).getrfid()); 
                        } else if (animalEncontrado instanceof Exotico) {
                            System.out.println("Tipo: Exótico");
                            System.out.println("RFIDEX: " + ((Exotico) animalEncontrado).getRfidex()); 
                            System.out.println("Nota Fiscal: " + ((Exotico) animalEncontrado).getNotaFiscal()); 
                        }

                    } else {
                        System.out.println("Animal com o ID " + idBusca + " não encontrado.");
                    }
                    break;
                
                case 4:
                    System.out.println("--- Atualizar Animal ---");
                    System.out.print("Digite o ID do animal que deseja atualizar: ");
                    int idAtt = entrada.nextInt();
                    entrada.nextLine(); // Limpar buffer

                    Animal animalAtt = repositorioDeAnimal.BuscarPorId(idAtt); 

                    if (animalAtt == null) {
                        System.out.println("Animal não encontrado.");
                        break;
                    }
                    System.out.println("Animal encontrado: " + animalAtt.getNome()); 
                    System.out.println("Deixe o campo em branco para não alterar.");

                    System.out.print("Novo Nome (" + animalAtt.getNome() + "): "); 
                    String nomeAtt = entrada.nextLine();
                    if (!nomeAtt.trim().isEmpty()) {
                        animalAtt.setNome(nomeAtt);
                    }
                    System.out.print("Nova Espécie (" + animalAtt.getEspecie() + "): "); 
                    String especieAtt = entrada.nextLine();
                    if (!especieAtt.trim().isEmpty()) {
                        animalAtt.setEspecie(especieAtt); 
                    }
                    
                    System.out.print("Novo Porte (" + animalAtt.getPorte() + "): "); 
                    String porteAtt = entrada.nextLine();
                    if (!porteAtt.trim().isEmpty()) {
                        animalAtt.setPorte(porteAtt); 
                    }
                    
                    System.out.print("Nova Idade (" + animalAtt.getIdade() + "): "); 
                    String idadeAttStr = entrada.nextLine();
                    if (!idadeAttStr.trim().isEmpty()) {
                        animalAtt.setIdade(Integer.parseInt(idadeAttStr)); 
                    }
                    if (animalAtt instanceof Pet) {
                        System.out.print("Novo RFID (" + ((Pet) animalAtt).getrfid() + "): "); 
                        String rfidAtt = entrada.nextLine();
                        if (!rfidAtt.trim().isEmpty()) {
                            ((Pet) animalAtt).setrfid(rfidAtt); 
                        }
                    } else if (animalAtt instanceof Exotico) {
                        System.out.print("Novo RFIDEX (" + ((Exotico) animalAtt).getRfidex() + "): "); 
                        String rfidexAtt = entrada.nextLine();
                        if (!rfidexAtt.trim().isEmpty()) {
                            ((Exotico) animalAtt).setRfidex(rfidexAtt); 
                        }
                        
                        System.out.print("Nova Nota Fiscal (" + ((Exotico) animalAtt).getNotaFiscal() + "): "); 
                        String notaAtt = entrada.nextLine();
                        if (!notaAtt.trim().isEmpty()) {
                            ((Exotico) animalAtt).setNotaFiscal(notaAtt); 
                        }
                    }

                    repositorioDeAnimal.atualizar(animalAtt); 
                    System.out.println("Animal atualizado com sucesso!");
                    
                    break;

                case 5:
                    System.out.println("--- Excluir Animal ---");
                    System.out.print("Digite o ID do animal que deseja excluir: ");
                    int idDel = entrada.nextInt();
                    entrada.nextLine(); // Limpar buffer

                    Animal animalDel = repositorioDeAnimal.BuscarPorId(idDel); //
                    if (animalDel == null) {
                        System.out.println("Animal não encontrado.");
                        break;
                    }

                    System.out.println("Tem certeza que deseja excluir o animal: " + animalDel.getNome() + "? (S/N)"); //
                    String confirmacao = entrada.nextLine();

                    if (confirmacao.equalsIgnoreCase("S")) {
                        boolean deletado = repositorioDeAnimal.deletarAnimal(idDel); 
                        if (deletado) {
                            System.out.println("Animal excluído com sucesso.");
                        } else {
                             System.out.println("Erro ao excluir animal.");
                        }
                    } else {
                        System.out.println("Exclusão cancelada.");
                    }
                    break;
                
                case 6:
                    System.out.println("--- Buscar Animais por Dono ---");
                    System.out.print("Digite o CPF do Dono: ");
                    String cpfDonoBusca = entrada.nextLine();
                    
                    List<Animal> animaisDoDono = repositorioDeAnimal.BuscarPorDono(cpfDonoBusca); //
                    
                    if (animaisDoDono.isEmpty()) {
                        System.out.println("Nenhum animal encontrado para este dono.");
                    } else {
                        System.out.println("Animais encontrados para o CPF: " + cpfDonoBusca);
                        for (Animal animalDono : animaisDoDono) {
                            System.out.println("--------------------");
                            System.out.println("ID: " + animalDono.getId()); 
                            System.out.println("Nome: " + animalDono.getNome()); 
                            System.out.println("Espécie: " + animalDono.getEspecie()); 

                            
                            if (animalDono instanceof Pet) {
                                System.out.println("Tipo: Pet");
                                System.out.println("RFID: " + ((Pet) animalDono).getrfid()); 
                            } else if (animalDono instanceof Exotico) {
                                System.out.println("Tipo: Exótico");
                                System.out.println("RFIDEX: " + ((Exotico) animalDono).getRfidex()); 
                                System.out.println("Nota Fiscal: " + ((Exotico) animalDono).getNotaFiscal());                             }
                        }
                        System.out.println("--------------------");
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
