package org.example.ucb.dao;

import org.example.ucb.control.RepositorioDeCertificacao;
import org.example.ucb.model.Certificacao;
import org.example.ucb.model.Especialidade;
import org.example.ucb.model.Veterinario;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class RepositorioDeCertificacaoSQL extends RepositorioDeCertificacao {

    @Override
    public void salvar(Certificacao certificacao) {
        String sql = "INSERT INTO Certificacao (NumeroRegistro, DataObtencao, InstituicaoCertificadora) VALUES (?, ?, ?)";

        try (Connection conexao = new ConexaoMySQL().obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, certificacao.getNumeroRegistro());
            stmt.setDate(4, new java.sql.Date(certificacao.getDataObtencao().getTime()));
            stmt.setString(3, certificacao.getInstituicaoCertificadora());

            stmt.executeUpdate();
            System.out.println("Certificacao cadastrada com sucesso!");
        } catch (Exception e) {
            System.err.println("Falha no cadastro da certificação: " + e.getMessage());
        }
    }

    @Override
    public Certificacao BuscarNumeroRegistro(int numeroregistro) {
        String sql = "SELECT * FROM Certificacao WHERE NumeroRegistro = ?";
        Certificacao certificacao = null;

        try (Connection conexao = new ConexaoMySQL().obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, numeroregistro);

            try (ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    int NumeroRegistro = rs.getInt("NumeroRegistro");
                    Date DataObtencao = rs.getDate("DataObtencao");
                    String InstituicaoCertificadora = rs.getString("InstituicaoCertificadora");

                    certificacao = new Certificacao(NumeroRegistro, DataObtencao, InstituicaoCertificadora);
                }
            }
        }

    }
}
