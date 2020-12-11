package pt.ufp.inf.esof.projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pt.ufp.inf.esof.projeto.modelos.*;
import pt.ufp.inf.esof.projeto.repositories.*;
import java.time.LocalDateTime;

@Component
public class Inicializacao implements ApplicationListener<ContextRefreshedEvent> {
    private final ClienteRepository clienteRepository; //Atributos podem ser finais devido ao construtor ser autowired
    private final EmpregadoRepository empregadoRepository;
    private final ProjetoRepository projetoRepository;
    private final TarefaRepository tarefaRepository;

    @Autowired
    public Inicializacao(ClienteRepository clienteRepository, EmpregadoRepository empregadoRepository, ProjetoRepository projetoRepository, TarefaRepository tarefaRepository) {
        this.clienteRepository = clienteRepository;
        this.empregadoRepository = empregadoRepository;
        this.projetoRepository = projetoRepository;
        this.tarefaRepository = tarefaRepository;
    }

    @Override
    public void onApplicationEvent   (ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("\n\n\n Inicializou \n\n\n");

        Projeto projeto = new Projeto();
        projeto.setNome("Plataforma Digital");
        projeto.setDeadline(LocalDateTime.now().plusDays(40));

        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao("Modelacao de Projeto");
        tarefa.setNome("Tarefa 1");

        tarefa.setDiaInicio(LocalDateTime.now().plusDays(1));
        tarefa.setDiaFim(LocalDateTime.now().plusDays(10));

        Empregado empregado = new Empregado();
        empregado.setNome("Joao");
        empregado.setEmail("Joao@gmail.co");

        Cliente cliente = new Cliente();
        cliente.setNome("Cliente 1");


        empregado.setCargo(Empregado.Cargo.ANALISTA_JUNIOR);
        cliente.adicionaProjeto(projeto);
        empregado.adicionaTarefa(tarefa);
        projeto.adicionaTarefa(tarefa);

        this.empregadoRepository.save(empregado);
        this.tarefaRepository.save(tarefa);
        this.projetoRepository.save(projeto);
        this.clienteRepository.save(cliente);

    }



}
