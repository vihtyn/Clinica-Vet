package org.example.ucb.control;

import org.example.ucb.model.Dono;

import java.util.List;

public interface RepositorioDeDono {
    void salvar(Dono dono);
    Dono BuscarPorCPF(String Cpf);
    List<Dono> ListarDono();
    List<Dono> BuscarPorAnimal(int id);
    void atualizar(Dono dono);
    boolean deletarDono(String Cpf);

}
