package pt.ufp.inf.esof.projeto.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.ufp.inf.esof.projeto.modelos.Projeto;

import java.util.Optional;

public interface ProjetoRepository extends CrudRepository<Projeto,Long> {
    Optional<Projeto> findByNome (String nome);
}
