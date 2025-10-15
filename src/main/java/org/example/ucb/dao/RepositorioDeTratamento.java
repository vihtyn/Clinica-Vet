package org.example.ucb.dao;

import org.example.ucb.control.RepositorioDeTratamento;
import org.example.ucb.model.Tratamento;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositorioDeTratamentoSQL implements RepositorioDeTratamento {

    private final ConexaoBD conexaoBD;

    public RepositorioDeTratamentoSQL() {
        this.conexaoBD = new ConexãoMySQL(); 
    }

    @Override
    public void salvar(Tratamento tratamento) {
    // Implementar a lógica para salvar o tratamento no banco de dados aqui.
    }

    @Override
    public Tratamento BuscarTratamento(int id) {
    // Implementar a lógica para buscar um tratamento pelo seu ID aqui.
      return null; 
    }

    @Override
    public List<Tratamento> BuscarPorConsulta(int idConsulta) {
    // Implementar a lógica para buscar os tratamentos de uma consulta aqui.
      return null;
    }
}
