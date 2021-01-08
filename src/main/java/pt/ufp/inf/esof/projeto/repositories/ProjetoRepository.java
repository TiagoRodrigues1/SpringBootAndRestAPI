package pt.ufp.inf.esof.projeto.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.inf.esof.projeto.modelos.Projeto;

import java.util.Optional;

@Repository
public interface ProjetoRepository extends CrudRepository<Projeto,Long> {
    Optional<Projeto> findByNome (String nome);
}
