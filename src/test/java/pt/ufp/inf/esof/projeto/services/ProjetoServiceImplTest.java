package pt.ufp.inf.esof.projeto.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.inf.esof.projeto.modelos.*;
import pt.ufp.inf.esof.projeto.repositories.ProjetoRepository;
import pt.ufp.inf.esof.projeto.repositories.TarefaEfetivaRepository;
import pt.ufp.inf.esof.projeto.repositories.TarefaPrevistaRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ProjetoServiceImpl.class)
class ProjetoServiceImplTest {

    @Autowired
    private ProjetoService projetoService;

    @MockBean
    private ProjetoRepository projetoRepository;
    @MockBean
    private TarefaPrevistaRepository tarefaPrevistaRepository;
    @MockBean
    private TarefaEfetivaRepository tarefaEfetivaRepository;

    @Test
    void criarProjeto() {
        Projeto projeto = new Projeto();
        projeto.setNome("Plataforma");

        when(projetoRepository.save(projeto)).thenReturn(projeto);
        assertTrue(projetoService.criarProjeto(projeto).isPresent());

        when(projetoRepository.findByNome(projeto.getNome())).thenReturn(Optional.of(projeto));
        assertTrue(projetoService.criarProjeto(projeto).isEmpty());

    }

    @Test
    void adicionaTarefa() {
        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        tarefaPrevista.setNome("TarefaPrevista1");
        TarefaEfetiva tarefaEfetiva = new TarefaEfetiva();
        tarefaEfetiva.setNome("TesteEfetiva");

        Projeto projeto = new Projeto();
        projeto.setNome("Plataforma");

        Tarefas tarefas = new Tarefas();
        tarefas.setTarefaEfetiva(tarefaEfetiva);
        tarefas.setTarefaPrevista(tarefaPrevista);

        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));
        when(tarefaPrevistaRepository.findByNome(tarefaPrevista.getNome())).thenReturn(Optional.of(tarefaPrevista));
        assertTrue(projetoService.adicionaTarefa(1L,tarefaPrevista.getNome()).isPresent());
        assertTrue(projetoService.adicionaTarefa(2L,tarefaPrevista.getNome()).isEmpty());
    }

    @Test
    void adicionaCliente() {
        Cliente cliente = new Cliente();
        cliente.setUsername("Pedro123");
        cliente.setNome("Pedro");
        cliente.setPassword("1234");

        Projeto projeto = new Projeto();
        projeto.setNome("Plataforma");

        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));
        assertTrue(projetoService.adicionaCliente(1L,cliente).isPresent());
        assertTrue(projetoService.adicionaCliente(2L,cliente).isEmpty());
    }
}