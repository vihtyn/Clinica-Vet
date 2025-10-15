package org.example.ucb.control;

import org.example.ucb.model.Veterinario;
import java.util.List;

public interface RepositorioDeVeterinario {
    void salvar(Veterinario veterinario);
    Veterinario BuscarVet (String crmv);
    List<Veterinario> ListarVet();
    List<Veterinario> BuscarPorCertificacao(int numeroregistro);
    void atualizar(Veterinario veterinario);
    boolean deletarVet(String crmv);
}
