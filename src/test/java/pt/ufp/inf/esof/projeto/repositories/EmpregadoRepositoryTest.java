package pt.ufp.inf.esof.projeto.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pt.ufp.inf.esof.projeto.modelos.Empregado;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmpregadoRepositoryTest {
    @Autowired
    private EmpregadoRepository empregadoRepository;
    @Autowired
    private TarefaPrevistaRepository tarefaPrevistaRepository;

    @Test
    public void testaCriacaoEmpregado() {
        Empregado empregado = new Empregado();
        empregado.setCargo(Empregado.Cargo.DESENVOLVERDOR_JUNIOR);
        empregado.setEmail("Tiago@gmail.com");
        empregado.setNome("Tiago");
        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        tarefaPrevista.setNome("Tarefa1");
        TarefaPrevista tarefaPrevista1 = new TarefaPrevista();
        tarefaPrevista1.setNome("Tarefa2");
        empregado.adicionaTarefa(tarefaPrevista);
        empregado.adicionaTarefa(tarefaPrevista1);

        empregadoRepository.save(empregado);

        assertEquals(1,empregadoRepository.count());
        assertEquals(2,tarefaPrevistaRepository.count());
        assertTrue(empregadoRepository.findByEmail(empregado.getEmail()).isPresent());
        assertTrue(tarefaPrevistaRepository.findByNome(tarefaPrevista.getNome()).isPresent());
        assertTrue(tarefaPrevistaRepository.findByNome(tarefaPrevista1.getNome()).isPresent());
    }
}