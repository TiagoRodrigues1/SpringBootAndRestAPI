package pt.ufp.inf.esof.projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pt.ufp.inf.esof.projeto.modelos.*;
import pt.ufp.inf.esof.projeto.repositories.*;

@Component
public class Inicializacao implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private CargoRepository cargoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EmpregadoRepository empregadoRepository;
    @Autowired
    private ProjetoRepository projetoRepository;
    @Autowired
    private TarefaRepository tarefaRepository;

    @Override
    public void onApplicationEvent   (ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("\n\n\n Inicializou \n\n\n");
        Projeto projeto = new Projeto();
        projeto.setNome("Plataforma Digital");

        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao("Modelacao de Projeto");

        Empregado empregado = new Empregado();
        empregado.setNome("Tiago");
        Cargo cargo = new Cargo();
        cargo.setValorHora(10);
        cargo.setNome("Desenvolvedor Junior");
        empregado.setCargo(cargo);

        tarefa.setEmpregado(empregado);
        projeto.adicionaTarefa(tarefa);

        Cliente cliente = new Cliente();
        cliente.setNome("Cliente 1");
        projeto.setCliente(cliente);


    }

}
