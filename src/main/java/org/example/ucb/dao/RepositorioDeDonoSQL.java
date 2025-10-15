package org.example.ucb.dao;

import org.example.ucb.control.RepositorioDeDono;
import org.example.ucb.model.Dono;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDeDonoSQL implements RepositorioDeDono {

    private static final List<Dono> DadosDosDonos = new ArrayList<>();

    @Override
    public void salvar(Dono dono) {
        String sql = "INSERT INTO veterinario (CPF, nome, endereco, dataNascimento) VALUES (?, ?, ?, ?)";

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
            e.printStackTrace(); // Imprime o erro detalhado no console
        }
    }


    @Override
    public Dono BuscarPorCPF(String Cpf) {
        String sql = "SELECT * FROM veterinario WHERE CPF = ?";
        Dono donoEncontrado = null;

        try (Connection conn = new ConexaoMySQL().obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, Cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String cpf = rs.getString("CPF");
                    String nome = rs.getString("nome");
                    String endereco = rs.getString("endereco");
                    Date dataNascimento = rs.getDate("dataNascimento");

                    donoEncontrado = new Dono(cpf, dataNascimento, endereco, nome);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
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
        } catch (SQLException e) {
            System.err.println("Erro ao listar donos.");
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return donos;
    }

    @Override
    public List<Dono> BuscarPorAnimal(int id) {
        return List.of();
    }

    @Override
    public void atualizar(@org.jetbrains.annotations.NotNull Dono dono) {
        String sql = "UPDATE dono SET nome = ?, endereco = ?, dataNascimento = ? WHERE CPF = ?";

        try (Connection conn = new ConexaoMySQL().obterConexao()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            {

                stmt.setString(1, dono.getNome());
                stmt.setString(2, dono.getEndereco());
                stmt.setDate(3, new java.sql.Date(dono.getDataNascimento().getTime()));
                stmt.setString(4, dono.getCPF());

                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Dono atualizado com sucesso!");
                } else {
                    System.out.println("Nenhum dono encontrado com esse CPF!");
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deletarDono(String Cpf) {
        String sql = "DELETE FROM dono WHERE CPF = ?";

        try (Connection conn = new ConexaoMySQL().obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, Cpf);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao deletar dono.");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}