package pt.ufp.inf.esof.projeto.services;

import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;
import pt.ufp.inf.esof.projeto.modelos.Tarefas;

import java.util.Optional;

public interface TarefaService {

    Optional<TarefaPrevista> criarTarefa (Tarefas tarefas);
    Optional<TarefaPrevista> adicionaEmpregado(Long id, String email);
}