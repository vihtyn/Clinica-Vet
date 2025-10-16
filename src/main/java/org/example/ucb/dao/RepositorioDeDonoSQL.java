package org.example.ucb.dao;

import org.example.ucb.control.RepositorioDeDono;
import org.example.ucb.model.Dono;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDeDonoSQL implements RepositorioDeDono {
    @Override
    public void salvar(Dono dono) {
        String sql = "INSERT INTO dono (CPF, nome, endereco, dataNascimento) VALUES (?, ?, ?, ?)";

        try (Connection conn = new ConexaoMySQL().obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dono.getCPF());
            stmt.setString(2, dono.getNome());
            stmt.setString(3, dono.getEndereco());
            stmt.setDate(4, new java.sql.Date(dono.getDataNascimento().getTime()));

            stmt.executeUpdate();
            System.out.println("Dono salvo no banco de dados com sucesso!");

        } catch (Exception e) {
            System.err.println("Erro ao salvar dono no banco de dados.");
        }
    }

    @Override
    public Dono BuscarPorCPF(String cpf) {
        String sql = "SELECT * FROM dono WHERE CPF = ?";
        Dono donoEncontrado = null;

        try (Connection conn = new ConexaoMySQL().obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Monta o objeto 'Dono' com os dados que vieram do banco
                    String nome = rs.getString("nome");
                    String endereco = rs.getString("endereco");
                    java.util.Date dataNascimento = rs.getDate("dataNascimento");

                    donoEncontrado = new Dono(cpf, dataNascimento, endereco, nome);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar dono por CPF.");
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
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                java.util.Date dataNascimento = rs.getDate("dataNascimento");

                donos.add(new Dono(cpf, dataNascimento, endereco, nome));
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar donos.");
        }
        return donos;
    }

    @Override
    public void atualizar(Dono dono) {
        String sql = "UPDATE dono SET nome = ?, endereco = ?, dataNascimento = ? WHERE CPF = ?";

        try (Connection conn = new ConexaoMySQL().obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dono.getNome());
            stmt.setString(2, dono.getEndereco());
            stmt.setDate(3, new java.sql.Date(dono.getDataNascimento().getTime()));
            stmt.setString(4, dono.getCPF());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Dono atualizado com sucesso!");
            } else {
                System.err.println("Nenhum dono encontrado com o CPF informado para atualizar.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao atualizar dono.");
        }
    }

    @Override
    public boolean deletarDono(String cpf) {
        String sql = "DELETE FROM dono WHERE CPF = ?";

        try (Connection conn = new ConexaoMySQL().obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (Exception e) {
            System.err.println("Erro ao deletar dono.");
            return false;
        }
    }

    @Override
    public List<Dono> BuscarPorAnimal(int id) {
        System.out.println("Método BuscarPorAnimal ainda não foi implementado.");
        return new ArrayList<>(); // Retornando uma lista vazia por enquanto.
    }
}