package pt.ufp.inf.esof.projeto.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.inf.esof.projeto.dtos.TarefaPrevistaCreateDTO;
import pt.ufp.inf.esof.projeto.modelos.Projeto;
import pt.ufp.inf.esof.projeto.services.ProjetoService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjetoController.class)
class ProjetoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProjetoService projetoService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getProjetoByIdVal() {
    }

    @Test
    void criarProjeto() throws Exception {
        Projeto projeto = new Projeto();
        projeto.setNome("Projeto");

        when(this.projetoService.criarProjeto(projeto)).thenReturn(Optional.of(projeto));
        String projetoString = new ObjectMapper().writeValueAsString(projeto);
        mockMvc.perform(post("/projeto").content(projetoString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        Projeto projeto1 = new Projeto();
        projeto1.setNome("Plataforma Digital");
        String projeto1String = new ObjectMapper().writeValueAsString(projeto1);
        mockMvc.perform(post("/projeto").content(projeto1String).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());


    }

    @Test
    void getProjetoByIdTempo() {
    }

    @Test
    void adicionaTarefa() throws Exception {
        Projeto projeto = new Projeto();
        projeto.setNome("Projeto1");

        TarefaPrevistaCreateDTO tarefaPrevistaCreateDTO = new TarefaPrevistaCreateDTO();
        tarefaPrevistaCreateDTO.setNome("TesteTarefa");
        tarefaPrevistaCreateDTO.setTempoPrevistoConlusao(30);

        String tarefaJson = objectMapper.writeValueAsString(tarefaPrevistaCreateDTO);
        when(projetoService.adicionaTarefa(1L, tarefaPrevistaCreateDTO.converter())).thenReturn(Optional.of(projeto));

        mockMvc.perform(patch("/projeto/tarefa/1").contentType(tarefaJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        mockMvc.perform(patch("/projeto/tarefa/2").contentType(tarefaJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());


    }
}