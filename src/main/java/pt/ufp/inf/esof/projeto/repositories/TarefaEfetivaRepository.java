package pt.ufp.inf.esof.projeto.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.inf.esof.projeto.modelos.TarefaEfetiva;

import java.util.Optional;

@Repository
public interface TarefaEfetivaRepository extends CrudRepository<TarefaEfetiva,Long> {
    Optional<TarefaEfetiva> findByNome (String nome);

}
