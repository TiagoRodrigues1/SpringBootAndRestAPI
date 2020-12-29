package pt.ufp.inf.esof.projeto.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.ufp.inf.esof.projeto.modelos.TarefaEfetiva;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;

import java.util.Optional;

public interface TarefaEfetivaRepository extends CrudRepository<TarefaEfetiva,Long> {
    Optional<TarefaEfetiva> findByNome (String nome);

}
