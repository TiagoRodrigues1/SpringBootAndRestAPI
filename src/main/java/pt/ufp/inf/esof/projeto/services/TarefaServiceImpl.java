package pt.ufp.inf.esof.projeto.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ufp.inf.esof.projeto.modelos.Empregado;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;
import pt.ufp.inf.esof.projeto.modelos.Tarefas;
import pt.ufp.inf.esof.projeto.repositories.EmpregadoRepository;
import pt.ufp.inf.esof.projeto.repositories.TarefaEfetivaRepository;
import pt.ufp.inf.esof.projeto.repositories.TarefaPrevistaRepository;

import java.util.Optional;

@Service
public class TarefaServiceImpl implements TarefaService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final TarefaPrevistaRepository tarefaPrevistaRepository;
    private final EmpregadoRepository empregadoRepository;
    private final TarefaEfetivaRepository tarefaEfetivaRepository;

    @Autowired
    public TarefaServiceImpl (TarefaPrevistaRepository tarefaPrevistaRepository, TarefaEfetivaRepository tarefaEfetivaRepository, EmpregadoRepository empregadoRepository) {
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
        this.empregadoRepository = empregadoRepository;
        this.tarefaEfetivaRepository = tarefaEfetivaRepository;
    }

    @Override
    public Optional<TarefaPrevista> criarTarefa (Tarefas tarefas) {
        this.logger.info("A criar nova Tarefa " );
        Optional<TarefaPrevista> optionalTarefa = tarefaPrevistaRepository.findByNome(tarefas.getTarefaPrevista().getNome());
        if(optionalTarefa.isEmpty()) {
            this.logger.info("Tarefa criado com sucesso");
            tarefas.getTarefaPrevista().adicionaTarefa(tarefas.getTarefaEfetiva());
            tarefaPrevistaRepository.save(tarefas.getTarefaPrevista());
            tarefaEfetivaRepository.save(tarefas.getTarefaEfetiva());
            return Optional.of(tarefaPrevistaRepository.save(tarefas.getTarefaPrevista()));
        }
        this.logger.info("Tarefa já Existia");
        return Optional.empty();
    }

    @Override
    public Optional<TarefaPrevista> adicionaEmpregado(Long id, String email) {
        this.logger.info("A adicionar Empregado com email " + email + " a Projeto");
        Optional<TarefaPrevista> optionalTarefa = this.tarefaPrevistaRepository.findById(id);
        Optional<Empregado> empregado = this.empregadoRepository.findByEmail(email); // acessa o repositorio do empregado
        if (optionalTarefa.isPresent() && empregado.isPresent()) {
            this.logger.info("Empregado adicionado com sucesso");
            TarefaPrevista tarefa = optionalTarefa.get(); //ir buscar a tarefa
            Empregado empregado1 = empregado.get();
            empregado1.adicionaTarefa(tarefa);
            empregadoRepository.save(empregado1);
            tarefaPrevistaRepository.save(tarefa);
            return Optional.of(tarefa);
        }
        this.logger.info("Adição de empregado a Tarefa Falhou");
        return Optional.empty();
    }
}