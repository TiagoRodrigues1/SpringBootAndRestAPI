package pt.ufp.inf.esof.projeto.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.inf.esof.projeto.modelos.Empregado;
import pt.ufp.inf.esof.projeto.services.EmpregadoService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmpregadoController.class)
class EmpregadoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmpregadoService empregadoService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void criarEmpregado() throws Exception {
        Empregado empregado = new Empregado();
        empregado.setEmail("email");
        //empregado.setCargo(Empregado.Cargo.ANALISTA_SENIOR);

        when(this.empregadoService.criarEmpregado(empregado)).thenReturn(Optional.of(empregado));
        String empregadoasJsonString = new ObjectMapper().writeValueAsString(empregado);
        mockMvc.perform(post("/empregado").content(empregadoasJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()); //testa a criaçao de um empregado e se recebe status ok

        Empregado empregadoExistente = new Empregado();
        empregado.setEmail("miguel@gmail.com");
        String empregadoExistenteAsString = new ObjectMapper().writeValueAsString(empregadoExistente);
        mockMvc.perform(post("/empregado").content(empregadoExistenteAsString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()); //testa se é possivel criar empregado com mesmo email

    }

    @Test
    void getEmpregadoById() throws Exception {
        Empregado empregado = new Empregado();
        when(empregadoService.findById(1L)).thenReturn(Optional.of(empregado));
        String httpResponse = mockMvc.perform(get("/empregado/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponse);

        mockMvc.perform(get("/empregado/2")).andExpect(status().isNotFound());
    }
}