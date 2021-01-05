package pt.ufp.inf.esof.projeto.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.inf.esof.projeto.modelos.Empregado;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;
import pt.ufp.inf.esof.projeto.repositories.EmpregadoRepository;
import pt.ufp.inf.esof.projeto.repositories.TarefaEfetivaRepository;
import pt.ufp.inf.esof.projeto.repositories.TarefaPrevistaRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TarefaServiceImpl.class)
class TarefaServiceImplTest {

    @Autowired
    private TarefaService tarefaService;

    @MockBean
    private TarefaPrevistaRepository tarefaPrevistaRepository;

    @MockBean
    private TarefaEfetivaRepository tarefaEfetivaRepository;

    @MockBean
    private EmpregadoRepository empregadoRepository;

    @Test
    void criarTarefa() {
        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        tarefaPrevista.setNome("Tarefa");
        tarefaPrevista.setTempoPrevistoConlusao(45);

        when(tarefaPrevistaRepository.save(tarefaPrevista)).thenReturn(tarefaPrevista);
        assertTrue(tarefaService.criarTarefa(tarefaPrevista).isPresent());

        when(tarefaPrevistaRepository.findByNome(tarefaPrevista.getNome())).thenReturn(Optional.of(tarefaPrevista));
        assertTrue(tarefaService.criarTarefa(tarefaPrevista).isEmpty());

    }

    @Test
    void adicionaEmpregado() {
        Empregado empregado = new Empregado();
        empregado.setEmail("teste@teste.com");
        empregado.setCargo(Empregado.Cargo.ANALISTA_JUNIOR);
        empregado.setNome("teste");

        TarefaPrevista tarefa = new TarefaPrevista();
        tarefa.setNome("testesss");
        empregado.adicionaTarefa(tarefa);

        when(tarefaPrevistaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(empregadoRepository.save(any(Empregado.class))).thenReturn(empregado); //
        assertTrue(tarefaService.adicionaEmpregado(1L,empregado.getEmail()).isPresent()); // teste falha devido ao empregado ser preciso encontrar na BD através do email
        assertTrue(tarefaService.adicionaEmpregado(2L,empregado.getEmail()).isEmpty());


    }
}