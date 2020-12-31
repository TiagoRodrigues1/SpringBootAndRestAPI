package pt.ufp.inf.esof.projeto.services;

import pt.ufp.inf.esof.projeto.modelos.Projeto;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;
import java.util.Optional;

public interface ProjetoService {

    Optional<Projeto> adicionaTarefa (Long projetoID, TarefaPrevista tarefa);
    Optional<Projeto> criarProjeto (Projeto projeto);
    float getProjetoByIdVal (Long projetoID);
    float getProjetoByIdTempo (Long projetoID);
}
