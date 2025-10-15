package org.example.ucb.control;

import org.example.ucb.model.Tratamento;
import java.util.List;

public interface RepositorioDeTratamento {
    void salvar(Tratamento tratamento);
    Tratamento BuscarTratamento(int id);
    List<Tratamento> BuscarPorConsulta(int id);
}
