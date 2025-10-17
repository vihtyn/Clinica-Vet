package org.example.ucb.dao;

import org.example.ucb.control.RepositorioDeDono;
import org.example.ucb.model.Dono;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate; // Importar LocalDate
import java.util.ArrayList;
import java.util.List;

public class RepositorioDeDonoSQL implements RepositorioDeDono {

    // Remover a lista estática DadosDosDonos, não é necessária aqui.

    @Override
    public void salvar(Dono dono) {
        // CORREÇÃO: Usar a tabela 'dono'
        String sql = "INSERT INTO dono (CPF, Nome, Endereco, data_nasc) VALUES (?, ?, ?, ?)"; // Nome da coluna data_nasc como no SQL

        try (Connection conn = new ConexaoMySQL().obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dono.getCPF());
            stmt.setString(2, dono.getNome());
            stmt.setString(3, dono.getEndereco());
            // CORREÇÃO: Usar setObject para LocalDate
            stmt.setObject(4, dono.getDataNascimento());
            stmt.executeUpdate();
            System.out.println("Dono salvo no banco de dados com sucesso!");

        } catch (Exception e) {
            System.err.println("Erro ao salvar dono no banco de dados: " + e.getMessage());
            // Remover e.printStackTrace() se não quiser ver o erro completo
        }
    }


    @Override
    public Dono BuscarPorCPF(String cpf) { // Renomear parâmetro para clareza
        // CORREÇÃO: Usar a tabela 'dono'
        String sql = "SELECT * FROM dono WHERE CPF = ?";
        Dono donoEncontrado = null;

        try (Connection conn = new ConexaoMySQL().obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String cpfEncontrado = rs.getString("CPF"); // Pegar o CPF do resultado
                    String nome = rs.getString("Nome");         // Usar 'Nome' como no SQL
                    String endereco = rs.getString("Endereco"); // Usar 'Endereco' como no SQL
                    // CORREÇÃO: Usar toLocalDate() para ler do banco
                    LocalDate dataNascimento = rs.getDate("data_nasc").toLocalDate(); // Usar 'data_nasc' como no SQL

                    donoEncontrado = new Dono(cpfEncontrado, dataNascimento, endereco, nome);
                }
            }
        } catch (Exception e) { // Capturar Exception genérica é mais simples por enquanto
             System.err.println("Erro ao buscar dono por CPF: " + e.getMessage());
             // Remover throws desnecessários se não for tratar especificamente SQLException
        }
        return donoEncontrado;
    }

    @Override
    public List<Dono> ListarDono() {
        String sql = "SELECT * FROM dono";
        List<Dono> donos = new ArrayList<>();

        try (Connection conn = new ConexaoMySQL().obterConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String cpf = rs.getString("CPF");
                String nome = rs.getString("Nome");
                String endereco = rs.getString("Endereco");
                 // CORREÇÃO: Usar toLocalDate()
                LocalDate dataNascimento = rs.getDate("data_nasc").toLocalDate();

                donos.add(new Dono(cpf, dataNascimento, endereco, nome));
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar donos: " + e.getMessage());
        }
        return donos;
    }

    @Override
    public List<Dono> BuscarPorAnimal(int id) {
         // Esta função é mais complexa, precisa de um JOIN com a tabela Animal.
         // Deixando como não implementada por enquanto.
        System.out.println("Atenção: Método BuscarPorAnimal ainda não implementado!");
        return new ArrayList<>(); // Retorna lista vazia
    }

    @Override
    // Remover @org.jetbrains.annotations.NotNull se não estiver usando a biblioteca
    public void atualizar(Dono dono) {
        String sql = "UPDATE dono SET Nome = ?, Endereco = ?, data_nasc = ? WHERE CPF = ?";

        try (Connection conn = new ConexaoMySQL().obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Usar try-with-resources aqui também

            stmt.setString(1, dono.getNome());
            stmt.setString(2, dono.getEndereco());
             // CORREÇÃO: Usar setObject para LocalDate
            stmt.setObject(3, dono.getDataNascimento());
            stmt.setString(4, dono.getCPF()); // O WHERE vem por último

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Dono atualizado com sucesso!");
            } else {
                // Mudar para System.err para erros
                System.err.println("Nenhum dono encontrado com o CPF " + dono.getCPF() + " para atualizar.");
            }

        } catch (Exception e) {
             System.err.println("Erro ao atualizar dono: " + e.getMessage());
        }
    }

    @Override
    public boolean deletarDono(String cpf) { // Renomear parâmetro
        String sql = "DELETE FROM dono WHERE CPF = ?";

        try (Connection conn = new ConexaoMySQL().obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            int linhasAfetadas = stmt.executeUpdate();
            // Adicionar mensagem de sucesso ou falha aqui também
            if(linhasAfetadas > 0) {
                System.out.println("Dono com CPF " + cpf + " deletado com sucesso.");
            } else {
                 System.err.println("Nenhum dono encontrado com o CPF " + cpf + " para deletar.");
            }
            return linhasAfetadas > 0;

        } catch (Exception e) {
            System.err.println("Erro ao deletar dono: " + e.getMessage());
            return false;
        }
    }
}
