package pt.ufp.inf.esof.projeto.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pt.ufp.inf.esof.projeto.modelos.Cliente;
import pt.ufp.inf.esof.projeto.modelos.Projeto;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClienteRepositoryTest {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProjetoRepository projetoRepository;


    @Test
    public void testaCriacaoCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Joao");
        cliente.setPassword("teste");
        cliente.setUsername("Joao");

        Projeto projeto = new Projeto();
        projeto.setNome("Plataforma Digital de Comra");

        cliente.adicionaProjeto(projeto);
        assertNull(cliente.getId());
        clienteRepository.save(cliente);
        projetoRepository.save(projeto);

        assertEquals(1,clienteRepository.count());
        assertEquals(1,projetoRepository.count());
        assertTrue(clienteRepository.findById(cliente.getId()).isPresent());
        assertTrue(projetoRepository.findByNome(projeto.getNome()).isPresent());

    }

}