package pt.ufp.inf.esof.projeto.services;

import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.inf.esof.projeto.modelos.Projeto;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;
import pt.ufp.inf.esof.projeto.repositories.ProjetoRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ProjetoServiceImpl.class)
class ProjetoServiceImplTest {

    @Autowired
    private ProjetoService projetoService;

    @MockBean
    private ProjetoRepository projetoRepository;

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
        when(projetoRepository.findById(2L)).thenReturn(Optional.of(projeto));
        assertTrue(projetoService.adicionaTarefa(2L,tarefaPrevista).isPresent());
        assertFalse(projetoService.adicionaTarefa(2L,tarefaPrevista).isEmpty());
    }

    @Test
    void getProjetoByIdVal() {

    }

    @Test
    void getProjetoByIdTempo() {
    }
}
