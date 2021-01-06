package pt.ufp.inf.esof.projeto;

import lombok.SneakyThrows;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pt.ufp.inf.esof.projeto.modelos.*;
import pt.ufp.inf.esof.projeto.repositories.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class Inicializacao implements ApplicationListener<ContextRefreshedEvent> {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private  ClienteRepository clienteRepository; //Atributos podem ser finais devido ao construtor ser autowired
    @Autowired
    private  EmpregadoRepository empregadoRepository;
    @Autowired
    private  ProjetoRepository projetoRepository;
    @Autowired
    private  TarefaPrevistaRepository tarefaPrevistaRepository;
    @Autowired
    private  TarefaEfetivaRepository tarefaEfetivaRepository;

    @SneakyThrows
    @Override
    public void onApplicationEvent (ContextRefreshedEvent contextRefreshedEvent) {

        logger.info("\n\n\n Inicializou \n\n\n");

        Projeto projeto = new Projeto();
        projeto.setNome("Plataforma Digital");

        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        tarefaPrevista.setNome("TarefaPrevista");
        tarefaPrevista.setTempoPrevistoConlusao(10);
        TarefaPrevista tarefaPrevista1 = new TarefaPrevista();
        tarefaPrevista1.setNome("TarefaPrevista1");
        tarefaPrevista1.setTempoPrevistoConlusao(20);

        TarefaEfetiva tarefaEfetiva = new TarefaEfetiva();
        tarefaEfetiva.setNome("TarefaEfetive");
        tarefaEfetiva.setProgresso(10);

        //tarefaPrevista.setTarefaEfetiva(tarefaEfetiva);

        Empregado empregado = new Empregado();
        empregado.setEmail("teste@teste.pt");
        empregado.setNome("Tiago");
        empregado.setCargo(Empregado.Cargo.ANALISTA_JUNIOR);
        //tarefaPrevista.adicionaEmpregado(empregado);
        empregado.adicionaTarefa(tarefaPrevista);

        Empregado empregado1 = new Empregado();
        empregado1.setCargo(Empregado.Cargo.ANALISTA_SENIOR);
        empregado1.setNome("Miguel");
        empregado1.setEmail("miguel@gmail.com");
        //tarefaPrevista1.adicionaEmpregado(empregado1);
        empregado1.adicionaTarefa(tarefaPrevista1);

        this.empregadoRepository.save(empregado);
        this.empregadoRepository.save(empregado1);
        this.tarefaEfetivaRepository.save(tarefaEfetiva);
        this.tarefaPrevistaRepository.save(tarefaPrevista);
        this.tarefaPrevistaRepository.save(tarefaPrevista1);

        Cliente cliente = new Cliente();
        cliente.setNome("Tiago");
        cliente.setUsername("Tiago");
        cliente.setPassword("teste");

        cliente.adicionaProjeto(projeto);
        this.clienteRepository.save(cliente);

        projeto.adicionaTarefa(tarefaPrevista);
        projeto.adicionaTarefa(tarefaPrevista1);

        this.projetoRepository.save(projeto);
    }
}
