package org.example.ucb.control;

import org.example.ucb.model.Consulta;
import java.util.List;

public interface RepositorioDeConsulta {
    void salvar(Consulta consulta);
    Consulta BuscarConsulta(int id);
    List<Consulta> ListarConsulta();
    List<Consulta> BuscarPorAnimal(int id);
    void atualizarConsulta(Consulta consulta);
    boolean deletarConsulta(int id);
}
