package org.example.ucb.dao;

import com.sun.source.tree.ArrayAccessTree;
import org.example.ucb.control.RepositorioDeAnimal;
import org.example.ucb.model.Animal;
import org.example.ucb.model.Pet;
import org.example.ucb.model.Exotico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class RepositorioDeAnimalSQL implements RepositorioDeAnimal {

    @Override
    public void salvar(Animal animal) {
        //"Instrução" para o SQL
        String sqlAnimal = "INSERT INTO Animal (Especie, CPF_Dono, Nome, Idade, Porte) VALUES (?, ?, ?, ?, ?)";
        //Tenta fazer a conexão com o banco
        try (Connection conexao = new ConexaoMySQL().obterConexao()) {

            try (PreparedStatement stmtAnimal = conexao.prepareStatement(sqlAnimal, Statement.RETURN_GENERATED_KEYS)) {
                //Inserção dos dados
                stmtAnimal.setString(1, animal.getEspecie());
                stmtAnimal.setString(2, animal.getDono().getCPF());
                stmtAnimal.setString(3, animal.getNome());
                stmtAnimal.setInt(4, animal.getIdade());
                stmtAnimal.setString(5, animal.getPorte());

                stmtAnimal.executeUpdate();

                try (ResultSet generatedKeys = stmtAnimal.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        animal.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Falha ao obter o ID do animal, inserção falhou");
                    }
                }
            }

            if(animal instanceof Pet){
                String sqlPet = "INSERT INTO Pet (animal_ID, RFID) VALUES (?, ?)";
                try (PreparedStatement stmtPet = conexao.prepareStatement(sqlPet)) {
                    stmtPet.setInt(1, animal.getId());
                    stmtPet.setString(2, ((Pet) animal).getrfid());
                    stmtPet.executeUpdate();
                }
            } else if (animal instanceof Exotico){
                String sqlExotico = "INSERT INTO Exotico (animal_ID, NotaFiscal, RFIDEX) VALUES (?, ?, ?)";
                try (PreparedStatement stmtExotico = conexao.prepareStatement(sqlExotico)) {
                    stmtExotico.setInt(1, animal.getId());
                    stmtExotico.setString(2, ((Exotico) animal).getNotaFiscal());
                    stmtExotico.setString(3, ((Exotico) animal).getRfidex());
                    stmtExotico.executeUpdate();
                }

            }

                System.out.println("Animal\n ID: " + animal.getId() + "Espécie: " + animal.getEspecie() + " Cpf do Dono: " + animal.getDono().getCPF() + " Nome: " + animal.getNome() + " Idade: " + animal.getIdade() + " Porte: " + animal.getPorte() + "\ncadastrado com sucesso!");
            } catch (Exception e) {
                System.err.println("Erro ao cadastrar animal: " + e.getMessage());
            }

    }

    @Override
    public Animal BuscarPorId(int id){
        String sql = "SELECT a.*, p.RFID, e.NotaFiscal, e.RFIDEX FROM Animal a LEFT JOIN Pet p ON a.ID = p.animal_ID LEFT JOIN Exotico e ON a.ID = e.animal_ID WHERE a.ID = ?";

        Animal animal = null;

        try (Connection conexao = new ConexaoMySQL().obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setInt(1, id);

            try( ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    String rfidPet = rs.getString(("RFID"));
                    String notaFiscalExotico = rs.getString("NotaFiscal");

                    if(rfidPet != null) {
                        animal = new Pet();
                        ((Pet) animal).setrfid(rfidPet);
                    } else if (notaFiscalExotico != null) {
                        animal = new Exotico();
                        ((Exotico) animal).setNotaFiscal(notaFiscalExotico);
                        ((Exotico) animal).setRfidex(rs.getString("RFIDEX"));
                    }

                    if (animal != null) {
                        animal.setId(rs.getInt("ID"));
                        animal.setNome(rs.getString("Nome"));
                        animal.setEspecie(rs.getString("Especie"));
                        animal.setIdade(rs.getInt("Idade"));
                        animal.setPorte(rs.getString("Porte"));
                    }
                }
            }
        } catch (Exception e){
            System.err.println("Erro ao buscar o animal pelo ID");
        }
        return animal;
    }

    @Override
    public List<Animal> ListarTodos() {
        List<Animal> animais = new ArrayList<>();

        String sql = SELECT
    }

    @Override
    public List<Animal> BuscarPorDono(String CpfDono) {
        return List.of();
    }

    @Override
    public boolean deletarAnimal(int id) {
        return false;
    }

    @Override
    public void atualizar(Animal animal) {

    }
}
