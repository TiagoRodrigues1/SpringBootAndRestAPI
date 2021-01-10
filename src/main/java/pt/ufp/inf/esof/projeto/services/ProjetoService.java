package pt.ufp.inf.esof.projeto.services;

import pt.ufp.inf.esof.projeto.modelos.Cliente;
import pt.ufp.inf.esof.projeto.modelos.Projeto;

import java.util.Optional;

public interface ProjetoService {

    Optional<Projeto> adicionaTarefa (Long projetoID, String email);
    Optional<Projeto> criarProjeto (Projeto projeto);
    double getProjetoByIdVal (Long projetoID);
    double getProjetoByIdTempo (Long projetoID);
    Optional<Projeto> adicionaCliente(Long projetoID,  Cliente cliente);
}
