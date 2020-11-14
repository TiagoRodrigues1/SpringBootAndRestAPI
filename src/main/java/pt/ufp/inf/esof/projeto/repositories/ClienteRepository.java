package pt.ufp.inf.esof.projeto.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.inf.esof.projeto.modelos.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente,Long> {

}
