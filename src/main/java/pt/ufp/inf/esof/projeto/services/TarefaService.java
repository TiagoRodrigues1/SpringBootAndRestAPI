package pt.ufp.inf.esof.projeto.services;

import pt.ufp.inf.esof.projeto.modelos.Empregado;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;

import java.util.Optional;

public interface TarefaService {

    Optional<TarefaPrevista> criarTarefa (TarefaPrevista tarefaPrevista);
    Optional<TarefaPrevista> adicionaEmpregado(Long id, Empregado empregado);
}
