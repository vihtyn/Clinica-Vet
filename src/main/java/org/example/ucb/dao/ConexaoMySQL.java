package org.example.ucb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL implements ConexaoBD{

    private static final String URL = "jdbc:mysql://localhost:3306/clinica";
    private static final String USUARIO = "root";
    private static final String SENHA = "senha";


    @Override
    public Connection obterConexao() throws Exception {
        try {
            Class.forName ("com.mysql.cj.jdbc.Driver");

            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexao com o banco de dados estabelecida com sucesso!");
            return conexao;
        } catch (ClassNotFoundException e) {
            throw new Exception ("Driver JDBC do MySQL nao encontrado: " + e.getMessage());
        } catch (SQLException e) {
            throw new Exception ("erro ao conecta ao bando de dados: " + e.getMessage());
        }
    }

    @Override
    public void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexao fechada com sucesso");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexao: " + e.getMessage());
            }
        }
    }

}
