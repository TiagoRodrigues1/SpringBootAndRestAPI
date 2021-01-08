package pt.ufp.inf.esof.projeto.services;

import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.inf.esof.projeto.modelos.Cliente;
import pt.ufp.inf.esof.projeto.modelos.Empregado;
import pt.ufp.inf.esof.projeto.modelos.Projeto;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;
import pt.ufp.inf.esof.projeto.repositories.ProjetoRepository;
import pt.ufp.inf.esof.projeto.repositories.TarefaPrevistaRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ProjetoServiceImpl.class)
class ProjetoServiceImplTest {

    @Autowired
    private ProjetoService projetoService;

    @MockBean
    private ProjetoRepository projetoRepository;

    @MockBean
    private TarefaPrevistaRepository tarefaPrevistaRepository;

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

        Projeto projeto = new Projeto();
        projeto.setNome("Plataforma");
        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));
        assertTrue(projetoService.adicionaTarefa(1L,tarefaPrevista).isPresent());
        assertTrue(projetoService.adicionaTarefa(2L,tarefaPrevista).isEmpty());
    }

    @Test
    void getProjetoByIdVal() {
        Projeto projeto = new Projeto();
        projeto.setNome("Teste");
        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        tarefaPrevista.setNome("Tarefa");
        Empregado empregado = new Empregado();
        empregado.setCargo(Empregado.Cargo.ANALISTA_SENIOR);
        empregado.setEmail("joao@gmail.com");
        tarefaPrevista.setTempoPrevistoConlusao(100);
        tarefaPrevista.setEmpregado(empregado);
        projeto.adicionaTarefa(tarefaPrevista);
        projeto.setId(1L);
        //System.out.println(projeto);
        //projetoRepository.save(projeto);
        System.out.println(projetoRepository.findById(1L));

        //System.out.println(when(projetoRepository.findById(1L)).thenReturn(Optional.of(new Projeto())));
        when(projetoRepository.save(projeto)).thenReturn(projeto);
        //assertEquals(projeto.calcularCusto(),projetoService.getProjetoByIdVal(1L));
    }

    @Test
    void getProjetoByIdTempo() {
        Projeto projeto = new Projeto();
        projeto.setNome("Teste");
        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        tarefaPrevista.setNome("Tarefa");
        Empregado empregado = new Empregado();
        empregado.setCargo(Empregado.Cargo.ANALISTA_SENIOR);
        empregado.setEmail("joao@gmail.com");
        tarefaPrevista.setTempoPrevistoConlusao(35);
        TarefaPrevista tarefaPrevista1 = new TarefaPrevista();
        tarefaPrevista1.setNome("teste");
        tarefaPrevista1.setTempoPrevistoConlusao(89);
        projeto.setId(1L);
        projeto.adicionaTarefa(tarefaPrevista1);

        tarefaPrevistaRepository.save(tarefaPrevista);
        tarefaPrevistaRepository.save(tarefaPrevista1);

        when(projetoRepository.save(projeto)).thenReturn(projeto);
        //assertEquals(projeto.calcularTempo(),projetoService.getProjetoByIdTempo(1L));


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