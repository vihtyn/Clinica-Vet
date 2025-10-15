package org.example.ucb.dao;

import java.sql.Connection;

public interface ConexaoBD {
    Connection obterConexao() throws Exception;

    void fecharConexao (Connection conexao);
}
