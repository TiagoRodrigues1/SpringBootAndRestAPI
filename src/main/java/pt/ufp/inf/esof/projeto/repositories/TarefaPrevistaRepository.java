package pt.ufp.inf.esof.projeto.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;

import java.util.Optional;

public interface TarefaPrevistaRepository extends CrudRepository<TarefaPrevista,Long> {
    Optional<TarefaPrevista> findByNome (String nome);
}
