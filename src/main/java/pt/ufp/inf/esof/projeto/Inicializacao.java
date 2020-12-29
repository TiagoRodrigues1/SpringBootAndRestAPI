package pt.ufp.inf.esof.projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pt.ufp.inf.esof.projeto.modelos.*;
import pt.ufp.inf.esof.projeto.repositories.*;

@Component
public class Inicializacao implements ApplicationListener<ContextRefreshedEvent> {
    private final ClienteRepository clienteRepository; //Atributos podem ser finais devido ao construtor ser autowired
    private final EmpregadoRepository empregadoRepository;
    private final ProjetoRepository projetoRepository;
    private final TarefaPrevistaRepository tarefaPrevistaRepository;

    @Autowired
    public Inicializacao(ClienteRepository clienteRepository, EmpregadoRepository empregadoRepository, ProjetoRepository projetoRepository, TarefaPrevistaRepository tarefaPrevistaRepository) {
        this.clienteRepository = clienteRepository;
        this.empregadoRepository = empregadoRepository;
        this.projetoRepository = projetoRepository;
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
    }

    @Override
    public void onApplicationEvent (ContextRefreshedEvent contextRefreshedEvent) {

        System.out.println("\n\n\n Inicializou \n\n\n");

        Projeto projeto = new Projeto();
        projeto.setNome("Plataforma Digital");

        TarefaPrevista tarefa = new TarefaPrevista();
        tarefa.setNome("Tarefa 1");
        TarefaEfetiva tarefa2 = new TarefaEfetiva();
        tarefa2.setNome("TarefaEfetiva");
        tarefa2.registarTempoTrabalhado(10);
        TarefaEfetiva tarefaEfetiva = new TarefaEfetiva();
        tarefaEfetiva.setNome("TarefaEfetiva1");
        tarefaEfetiva.registarTempoTrabalhado(5);
        tarefa.adicionaTarefa(tarefa2);
        tarefa.adicionaTarefa(tarefaEfetiva);

        Empregado empregado = new Empregado();
        empregado.setNome("Joao");
        empregado.setEmail("Joao@gmail.com");

        Cliente cliente = new Cliente();
        cliente.setNome("Cliente 1");

        empregado.setCargo(Empregado.Cargo.ANALISTA_JUNIOR);
        cliente.adicionaProjeto(projeto);
        empregado.adicionaTarefa(tarefa);
        projeto.adicionaTarefa(tarefa);

        this.empregadoRepository.save(empregado);
        this.tarefaPrevistaRepository.save(tarefa);
        this.projetoRepository.save(projeto);
        this.clienteRepository.save(cliente);

    }



}
