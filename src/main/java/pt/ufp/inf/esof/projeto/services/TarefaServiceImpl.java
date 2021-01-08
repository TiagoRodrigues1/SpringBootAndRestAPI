package pt.ufp.inf.esof.projeto.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ufp.inf.esof.projeto.modelos.Empregado;
import pt.ufp.inf.esof.projeto.modelos.TarefaEfetiva;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;
import pt.ufp.inf.esof.projeto.repositories.EmpregadoRepository;
import pt.ufp.inf.esof.projeto.repositories.TarefaEfetivaRepository;
import pt.ufp.inf.esof.projeto.repositories.TarefaPrevistaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaServiceImpl implements TarefaService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final TarefaPrevistaRepository tarefaPrevistaRepository;
    private final TarefaEfetivaRepository tarefaEfetivaRepository;
    private final EmpregadoRepository empregadoRepository;

    @Autowired
    public TarefaServiceImpl (TarefaPrevistaRepository tarefaPrevistaRepository, TarefaEfetivaRepository tarefaEfetivaRepository, EmpregadoRepository empregadoRepository) {
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
        this.tarefaEfetivaRepository = tarefaEfetivaRepository;
        this.empregadoRepository = empregadoRepository;
    }

    @Override
    public Optional<TarefaPrevista> criarTarefa (TarefaPrevista tarefaPrevista) {
        this.logger.info("A criar nova Tarefa" + tarefaPrevista.getNome());
        Optional<TarefaPrevista> optionalTarefa = tarefaPrevistaRepository.findByNome(tarefaPrevista.getNome());
        if(optionalTarefa.isEmpty()) {
            this.logger.info("Tarefa criado com sucesso");
            tarefaPrevistaRepository.save(tarefaPrevista);
            return Optional.of(tarefaPrevistaRepository.save(tarefaPrevista));
            /*
            List<TarefaEfetiva> tarefaEfetivas = new ArrayList<>();
            tarefaPrevista.getTarefaEfetivas().forEach(tarefaEfetiva -> {
                Optional<TarefaEfetiva> optionalTarefaEfetiva = tarefaEfetivaRepository.findByNome(tarefaEfetiva.getNome());
                if(optionalTarefaEfetiva.isPresent()) {
                    tarefaEfetivas.add(tarefaEfetiva);
                    tarefaEfetiva.setTarefaPrevista(tarefaPrevista);
                    tarefaEfetivaRepository.save(optionalTarefaEfetiva.get());
                }
            });
            tarefaPrevista.setTarefaEfetivas(tarefaEfetivas);
            */
        }
        this.logger.info("Tarefa já Existia");
        return Optional.empty();
    }

    @Override
    public Optional<TarefaPrevista> adicionaEmpregado(Long id, String email) { // acessa o repositorio do empregado
        this.logger.info("A adicionar Empregado com email" + email + "a Projeto");
        Optional<TarefaPrevista> optionalTarefa = this.tarefaPrevistaRepository.findById(id);
        Optional<Empregado> empregado = this.empregadoRepository.findByEmail(email);
        if(empregado.isPresent()) {
            if (optionalTarefa.isPresent()) {
                this.logger.info("Empregado adicionado com sucesso");
                TarefaPrevista tarefa = optionalTarefa.get();
                empregado.get().adicionaTarefa(tarefa);
                return Optional.of(tarefaPrevistaRepository.save(tarefa));
            }
        }
        this.logger.info("Adição de empregado a Tarefa Falhou");
        return Optional.empty();
    }
}