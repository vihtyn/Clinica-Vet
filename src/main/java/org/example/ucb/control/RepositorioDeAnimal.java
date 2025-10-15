package org.example.ucb.control;

import org.example.ucb.model.Animal;

import java.util.List;

public interface RepositorioDeAnimal {
    void salvar(Animal animal);
    Animal BuscarPorId(int id);
    List<Animal> ListarTodos();
    List<Animal> BuscarPorDono(String CpfDono);
}
