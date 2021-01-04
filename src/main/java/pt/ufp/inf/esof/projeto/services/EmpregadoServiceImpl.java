package pt.ufp.inf.esof.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.ufp.inf.esof.projeto.modelos.Empregado;
import pt.ufp.inf.esof.projeto.repositories.EmpregadoRepository;
import pt.ufp.inf.esof.projeto.repositories.TarefaPrevistaRepository;


import java.util.Optional;

@Service
public class EmpregadoServiceImpl implements EmpregadoService {
    private final EmpregadoRepository empregadoRepository;
    private final TarefaPrevistaRepository tarefaPrevistaRepository;

    @Autowired
    public EmpregadoServiceImpl(EmpregadoRepository empregadoRepository, TarefaPrevistaRepository tarefaPrevistaRepository) {
        this.empregadoRepository = empregadoRepository;
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
    }

    @Override
    public Optional<Empregado> criarEmpregado(Empregado empregado) {
        Optional<Empregado> optionalEmpregado = empregadoRepository.findByEmail(empregado.getEmail());
        if (optionalEmpregado.isEmpty()) {
            empregadoRepository.save(empregado);
            return Optional.of(empregadoRepository.save(empregado));
            /*List<TarefaPrevista> tarefas = new ArrayList<>();
            empregado.getTarefa().forEach(tarefa -> {
                Optional<TarefaPrevista> optionalTarefa = tarefaPrevistaRepository.findByNome(tarefa.getNome());
                if (optionalTarefa.isPresent()) {
                    tarefas.add(tarefa);
                    empregado.adicionaTarefa(tarefa);
                    tarefaPrevistaRepository.save(optionalTarefa.get());
                }
            });
            empregado.setTarefa(tarefas);
            return Optional.of(empregadoRepository.save(empregado));

             */
        }
        return Optional.empty();
    }
}