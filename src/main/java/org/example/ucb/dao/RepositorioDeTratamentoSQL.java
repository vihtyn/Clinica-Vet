package org.example.ucb.dao;
import org.example.ucb.model.Consulta;

import org.example.ucb.control.RepositorioDeTratamento;
import org.example.ucb.model.Tratamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDeTratamentoSQL implements RepositorioDeTratamento {

    private final ConexaoBD conexaoBD;

    public RepositorioDeTratamentoSQL() {
        this.conexaoBD = new ConexaoMySQL();
    }

    @Override
    public void salvar(Tratamento tratamento) {

        String sql = "INSERT INTO tratamento (antibiotico, id_consulta, descricao_tratamento) VALUES (?, ?, ?)";


        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
    
            conexao = conexaoBD.obterConexao();

     
            stmt = conexao.prepareStatement(sql);

            stmt.setBoolean(1, tratamento.isAntibiotico());        
            stmt.setInt(2, tratamento.getConsulta().getid());     
            stmt.setString(3, tratamento.getDescricao());         

            stmt.executeUpdate();

            System.out.println("Tratamento salvo com sucesso!");

        } catch (Exception e) {
            System.err.println("Erro ao salvar tratamento: " + e.getMessage());
        
        } finally {
         
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexaoBD.fecharConexao(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    @Override
    
    public Tratamento BuscarTratamento(int id) {
    
        String sql = "SELECT * FROM tratamento WHERE id = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet resultado = null; 

        try {

            conexao = conexaoBD.obterConexao();

            stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, id);


            resultado = stmt.executeQuery();


            if (resultado.next()) {

                int tratamentoId = resultado.getInt("id");
                boolean antibiotico = resultado.getBoolean("antibiotico");
                String descricao = resultado.getString("descricao_tratamento");
                int idConsulta = resultado.getInt("id_consulta");


                Consulta consulta = new Consulta(idConsulta, null, null, null);


                return new Tratamento(tratamentoId, descricao, antibiotico, consulta);
            }

        } catch (Exception e) {
            System.err.println("Erro ao buscar tratamento por ID: " + e.getMessage());
        
        } finally {

            try {
                if (resultado != null) resultado.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexaoBD.fecharConexao(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return null;
    }
    
    @Override
    public List<Tratamento> BuscarPorConsulta(int idConsulta) {
      
        String sql = "SELECT * FROM tratamento WHERE id_consulta = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet resultado = null;
      
        List<Tratamento> tratamentos = new ArrayList<>();

        try {
            
            conexao = conexaoBD.obterConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idConsulta);

            
            resultado = stmt.executeQuery();

           
            while (resultado.next()) {
           
                int tratamentoId = resultado.getInt("id");
                boolean antibiotico = resultado.getBoolean("antibiotico");
                String descricao = resultado.getString("descricao_tratamento");

       
                Consulta consulta = new Consulta(idConsulta, null, null, null);

              
                Tratamento tratamento = new Tratamento(tratamentoId, descricao, antibiotico, consulta);

  
                tratamentos.add(tratamento);
            }

        } catch (Exception e) {
            System.err.println("Erro ao buscar tratamentos por consulta: " + e.getMessage());

        } finally {

            try {
                if (resultado != null) resultado.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexaoBD.
            }catch(Exception e){
                System.out.println("Erro ao ");
            }

