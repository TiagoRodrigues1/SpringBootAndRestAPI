package pt.ufp.inf.esof.projeto.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.inf.esof.projeto.modelos.Empregado;
import pt.ufp.inf.esof.projeto.repositories.EmpregadoRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = EmpregadoServiceImpl.class)
class EmpregadoServiceImplTest {

    @Autowired
    private EmpregadoService empregadoService;

    @MockBean
    private EmpregadoRepository empregadoRepository;

    @Test
    void criarEmpregado() {
        Empregado empregado = new Empregado();
        empregado.setNome("Joao");
        empregado.setEmail("joao@gmail.com");
        empregado.setCargo(Empregado.Cargo.DESENVOLVEDOR_JUNIOR);


        when(empregadoRepository.save(empregado)).thenReturn(empregado);
        assertTrue(empregadoService.criarEmpregado(empregado).isPresent());

        when(empregadoRepository.findByEmail(empregado.getEmail())).thenReturn(Optional.of(empregado));
        assertTrue(empregadoService.criarEmpregado(empregado).isEmpty());


    }
}
