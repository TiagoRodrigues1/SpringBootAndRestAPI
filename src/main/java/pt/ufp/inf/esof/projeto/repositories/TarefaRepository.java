package pt.ufp.inf.esof.projeto.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.ufp.inf.esof.projeto.modelos.Tarefa;

import java.util.Optional;

public interface TarefaRepository extends CrudRepository<Tarefa,Long> {
    Optional<Tarefa> findByNome (String nome);
}
