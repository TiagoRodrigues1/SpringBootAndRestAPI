package pt.ufp.inf.esof.projeto.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pt.ufp.inf.esof.projeto.modelos.Empregado;
import pt.ufp.inf.esof.projeto.modelos.TarefaEfetiva;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmpregadoRepositoryTest {
    @Autowired
    private EmpregadoRepository empregadoRepository;
    @Autowired
    private TarefaPrevistaRepository tarefaPrevistaRepository;
    @Autowired
    private TarefaEfetivaRepository tarefaEfetivaRepository;

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

        TarefaEfetiva tarefaEfetiva = new TarefaEfetiva();
        tarefaEfetiva.registarTempo(10,100);
        tarefaEfetiva.setNome("TarefaEfetiva");

        //tarefaPrevista.setTarefaEfetiva(tarefaEfetiva);
        //tarefaEfetiva.setTarefaPrevista(tarefaPrevista1);

        tarefaPrevista.adicionaTarefa(tarefaEfetiva);

        empregado.adicionaTarefa(tarefaPrevista);
        empregado.adicionaTarefa(tarefaPrevista1);


        tarefaEfetivaRepository.save(tarefaEfetiva);
        assertEquals(2,empregado.getTarefa().size());
        empregadoRepository.save(empregado);
        tarefaPrevistaRepository.save(tarefaPrevista);
        //tarefaEfetivaRepository.save(tarefaEfetiva);


        System.out.println();
        assertEquals(1,empregadoRepository.count());
        assertEquals(2,tarefaPrevistaRepository.count());
        assertEquals(1,tarefaEfetivaRepository.count());
        assertTrue(empregadoRepository.findByEmail(empregado.getEmail()).isPresent());
        assertTrue(tarefaPrevistaRepository.findByNome(tarefaPrevista.getNome()).isPresent());
        assertTrue(tarefaPrevistaRepository.findByNome(tarefaPrevista1.getNome()).isPresent());
        assertTrue(tarefaEfetivaRepository.findByNome(tarefaEfetiva.getNome()).isPresent());
    }
}