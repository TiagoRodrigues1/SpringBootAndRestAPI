package pt.ufp.inf.esof.projeto.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pt.ufp.inf.esof.projeto.modelos.TarefaEfetiva;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TarefaEfetivaRepositoryTest {
    @Autowired
    private TarefaEfetivaRepository tarefaEfetivaRepository;
    @Autowired
    private TarefaPrevistaRepository tarefaPrevistaRepository;

    @Test
    public void testaCriacaoTarefaEfetiva() {
        TarefaEfetiva tarefaEfetiva = new TarefaEfetiva();
        tarefaEfetiva.setNome("TarefaEfetiva1");
        tarefaEfetiva.setProgresso(80);
        tarefaEfetiva.setPeriodoTempoTrabalhado(10.5F);
        tarefaEfetiva.setConcluida(false);

        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        tarefaPrevista.setNome("TarefaPrevista");
        tarefaPrevista.setTempoPrevistoConlusao(20);
        tarefaPrevista.adicionaTarefa(tarefaEfetiva);
        tarefaEfetivaRepository.save(tarefaEfetiva);
        tarefaPrevistaRepository.save(tarefaPrevista);

        assertEquals(1,tarefaEfetivaRepository.count());
        assertEquals(1,tarefaPrevistaRepository.count());
        assertTrue(tarefaEfetivaRepository.findById(tarefaEfetiva.getId()).isPresent());
        assertTrue(tarefaPrevistaRepository.findByNome(tarefaPrevista.getNome()).isPresent());
    }

}