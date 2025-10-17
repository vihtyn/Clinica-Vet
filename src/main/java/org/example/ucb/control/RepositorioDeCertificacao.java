package org.example.ucb.control;

import org.example.ucb.model.Certificacao;
import java.util.List;

public interface RepositorioDeCertificacao {
    void salvar(Certificacao certificacao);
    Certificacao BuscarNumeroRegistro(int numeroregistro);
    List<Certificacao> ListarCertificacao();
    List<Certificacao> BuscarPorVet(String crmv);
    boolean deletarCertificacao(int numeroRegistro);

}
