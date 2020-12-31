package pt.ufp.inf.esof.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ufp.inf.esof.projeto.modelos.Empregado;
import pt.ufp.inf.esof.projeto.modelos.TarefaEfetiva;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;
import pt.ufp.inf.esof.projeto.repositories.TarefaEfetivaRepository;
import pt.ufp.inf.esof.projeto.repositories.TarefaPrevistaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaServiceImpl implements TarefaService {

    private final TarefaPrevistaRepository tarefaPrevistaRepository;
    private final TarefaEfetivaRepository tarefaEfetivaRepository;

    @Autowired
    public TarefaServiceImpl (TarefaPrevistaRepository tarefaPrevistaRepository, TarefaEfetivaRepository tarefaEfetivaRepository) {
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
        this.tarefaEfetivaRepository = tarefaEfetivaRepository;
    }

    @Override
    public Optional<TarefaPrevista> criarTarefa (TarefaPrevista tarefaPrevista) {
        Optional<TarefaPrevista> optionalTarefa = tarefaPrevistaRepository.findByNome(tarefaPrevista.getNome());
        if(optionalTarefa.isEmpty()) {
            tarefaPrevistaRepository.save(tarefaPrevista);
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
            return Optional.of(tarefaPrevistaRepository.save(tarefaPrevista));
        }
        return Optional.empty();
    }

    @Override
    public Optional<TarefaPrevista> adicionaEmpregado(Long id, Empregado empregado) { // acessa o repositorio do empregado
        Optional<TarefaPrevista> optionalTarefa = this.tarefaPrevistaRepository.findById(id);
        if(optionalTarefa.isPresent()) {
            TarefaPrevista tarefa = optionalTarefa.get();
            empregado.adicionaTarefa(tarefa);
            return Optional.of(tarefaPrevistaRepository.save(tarefa));
        }
        return Optional.empty();
    }




}
