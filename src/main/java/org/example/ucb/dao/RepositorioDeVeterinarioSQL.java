package org.example.ucb.dao;

import org.example.ucb.control.RepositorioDeVeterinario;
import org.example.ucb.model.Veterinario;
import org.example.ucb.model.Certificacao;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class RepositorioDeVeterinarioSQL implements RepositorioDeVeterinario {

    @Override
    public void salvar(Veterinario veterinario) {
        String sql = "INSERT INTO veterinario (crmv, nome, idade, dataGraduacao) VALUES (?, ?, ?, ?)";

        try (Connection conn = new ConexaoMySQL().obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, veterinario.getCrmv());
            stmt.setString(2, veterinario.getNome());
            stmt.setInt(3, veterinario.getIdade());
            stmt.setDate(4, new java.sql.Date(veterinario.getDataGraduacao().getTime()));

            stmt.executeUpdate();
            System.out.println("Veterinário salvo no banco de dados com sucesso!");

        }
        catch (Exception e) {
            System.err.println("Erro ao salvar veterinário no banco de dados.");
        }
    }

    @Override
    public Veterinario BuscarVet(String crmv) {
        String sql = "SELECT * FROM veterinario WHERE crmv = ?";
        Veterinario vetEncontrado = null;

        try (Connection conn = new ConexaoMySQL().obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, crmv);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    int idade = rs.getInt("idade");
                    java.util.Date dataGraduacao = rs.getDate("dataGraduacao");

                    vetEncontrado = new Veterinario(crmv, nome, idade, dataGraduacao);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar veterinário por CRMV.");
        }
        return vetEncontrado;
    }

    @Override
    public List<Veterinario> ListarVet() {
    String sql = "SELECT * FROM veterinario";
    List<Veterinario> veterinarios = new ArrayList<>();

        try (Connection conn = new ConexaoMySQL().obterConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            String crmv = rs.getString("crmv");
            String nome = rs.getString("nome");
            int idade = rs.getInt("idade");
            java.util.Date dataGraduacao = rs.getDate("dataGraduacao");

            veterinarios.add(new Veterinario(crmv, nome, idade, dataGraduacao));
        }
    } catch (Exception e) {
        System.err.println("Erro ao listar veterinários.");
    }
        return veterinarios;
    }

    @Override
    public List<Veterinario> BuscarPorCertificacao(int numeroregistro) {
        return List.of();
    }

    @Override
    public void atualizar(Veterinario veterinario) {
        String sql = "UPDATE veterinario SET nome = ?, idade = ?, dataGraduacao = ? WHERE crmv = ?";

        try (Connection conn = new ConexaoMySQL().obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veterinario.getNome());
            stmt.setInt(2, veterinario.getIdade());
            stmt.setDate(3, new java.sql.Date(veterinario.getDataGraduacao().getTime()));
            stmt.setString(4, veterinario.getCrmv()); // O WHERE é o último

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Veterinário atualizado com sucesso!");
            } else {
                System.err.println("Nenhum veterinário encontrado com o CRMV informado para atualizar.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao atualizar veterinário.");
        }
    }

    @Override
    public boolean deletarVet(String crmv) {
        String sql = "DELETE FROM veterinario WHERE crmv = ?";

        try (Connection conn = new ConexaoMySQL().obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, crmv);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (Exception e) {
            System.err.println("Erro ao deletar veterinário.");
            return false;
        }
    }
}
