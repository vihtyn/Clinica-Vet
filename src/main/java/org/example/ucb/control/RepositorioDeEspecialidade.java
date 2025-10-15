package org.example.ucb.control;

import org.example.ucb.model.Especialidade;
import java.util.List;

public interface RepositorioDeEspecialidade {
    void salvar(Especialidade especialidade);
    Especialidade BuscarEspecialidade(int id);
    List<Especialidade> ListarEspecialidade();
    List<Especialidade> BuscarEspPorVet(int crmv);
}
