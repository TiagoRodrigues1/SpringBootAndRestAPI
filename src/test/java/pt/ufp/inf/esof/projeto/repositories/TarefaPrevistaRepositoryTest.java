package pt.ufp.inf.esof.projeto.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pt.ufp.inf.esof.projeto.modelos.Projeto;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TarefaPrevistaRepositoryTest {
    @Autowired
    private TarefaPrevistaRepository tarefaPrevistaRepository;
    @Autowired
    private ProjetoRepository projetoRepository;

    @Test
    public void testaCriacaoTarefaPrevista() {
        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        tarefaPrevista.setNome("TarefaPrevista");
        tarefaPrevista.setTempoPrevistoConlusao(20);

        Projeto projeto = new Projeto();
        projeto.setNome("Plataforma Digital de Comra");

        projeto.adicionaTarefa(tarefaPrevista);

        tarefaPrevistaRepository.save(tarefaPrevista);
        projetoRepository.save(projeto);

        assertEquals(1,tarefaPrevistaRepository.count());
        assertEquals(1,projetoRepository.count());
        assertTrue(projetoRepository.findByNome(projeto.getNome()).isPresent());
        assertTrue(tarefaPrevistaRepository.findByNome(tarefaPrevista.getNome()).isPresent());
    }
}
