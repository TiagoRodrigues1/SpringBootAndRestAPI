package pt.ufp.inf.esof.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestBody;
import pt.ufp.inf.esof.projeto.modelos.Projeto;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;
import pt.ufp.inf.esof.projeto.repositories.ProjetoRepository;
import pt.ufp.inf.esof.projeto.repositories.TarefaPrevistaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoServiceImpl implements ProjetoService {
    private final ProjetoRepository projetoRepository;

    @Autowired
    public ProjetoServiceImpl(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    @Override
    public Optional<Projeto> criarProjeto(Projeto projeto) {
        Optional<Projeto> optionalProjeto = projetoRepository.findByNome(projeto.getNome());
        if (optionalProjeto.isEmpty()) {
            projetoRepository.save(projeto); // salvar o projeto
            return Optional.of(projetoRepository.save(projeto));
           /* List<TarefaPrevista> tarefas = new ArrayList<>();
            projeto.getTarefas().forEach(tarefa -> { //percorrer as tarefas daquele projeto
                Optional<TarefaPrevista> optionalTarefa = tarefaPrevistaRepository.findByNome(tarefa.getNome()); //procurar por id
                if (optionalTarefa.isPresent()) {
                    tarefas.add(tarefa);
                    tarefa.setProjeto(projeto);
                    tarefaPrevistaRepository.save(optionalTarefa.get()); //salvar na BD
                }
            });
            projeto.setTarefas(tarefas);
            return Optional.of(projetoRepository.save(projeto));
                        */
        }
        return Optional.empty();
    }

    @Override
    public Optional<Projeto> adicionaTarefa(Long projetoID, TarefaPrevista tarefa) { //acessar o rep da tarefa
        Optional<Projeto> optionalProjeto = projetoRepository.findById(projetoID);
        if (optionalProjeto.isPresent()) {
            Projeto projeto = optionalProjeto.get();
            projeto.adicionaTarefa(tarefa);
            projetoRepository.save(projeto);
            return Optional.of(projeto);
        }
        return Optional.empty();
    }

    @Override
    public float getProjetoByIdVal(Long projetoID) {
        Optional<Projeto> projeto = projetoRepository.findById(projetoID);
        return projeto.map(Projeto::calcularCusto).orElse(0F);
    }

    @Override
    public float getProjetoByIdTempo(Long projetoID) {
        Optional<Projeto> projeto = projetoRepository.findById(projetoID);
        return projeto.map(Projeto::calcularTempo).orElse(0F);
    }
}
