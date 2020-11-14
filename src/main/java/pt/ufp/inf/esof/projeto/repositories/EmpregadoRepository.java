package pt.ufp.inf.esof.projeto.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.inf.esof.projeto.modelos.Empregado;

@Repository
public interface EmpregadoRepository extends CrudRepository<Empregado,Long> {

}
