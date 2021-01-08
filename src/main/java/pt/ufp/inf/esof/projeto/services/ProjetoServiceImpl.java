package pt.ufp.inf.esof.projeto.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.ufp.inf.esof.projeto.modelos.Cliente;
import pt.ufp.inf.esof.projeto.modelos.Projeto;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;
import pt.ufp.inf.esof.projeto.repositories.ProjetoRepository;

import java.util.Optional;

@Service
public class ProjetoServiceImpl implements ProjetoService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProjetoRepository projetoRepository;

    @Autowired
    public ProjetoServiceImpl(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    @Override
    public Optional<Projeto> criarProjeto(Projeto projeto) {
        this.logger.info("A criar novo Projeto" + projeto.getNome());
        Optional<Projeto> optionalProjeto = projetoRepository.findByNome(projeto.getNome());
        if (optionalProjeto.isEmpty()) {
            this.logger.info("Projeto criado com sucesso");
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
        this.logger.info("Projeto já Existia");
        return Optional.empty();
    }

    @Override
    public Optional<Projeto> adicionaTarefa(Long projetoID, TarefaPrevista tarefa) {
        this.logger.info("A adicionar Tarefa " + tarefa.getNome() + " a Projeto");
        Optional<Projeto> optionalProjeto = projetoRepository.findById(projetoID);
        if (optionalProjeto.isPresent()) {
            this.logger.info("Tarefa adicionada com sucesso");
            Projeto projeto = optionalProjeto.get();
            projeto.adicionaTarefa(tarefa);
            projetoRepository.save(projeto);
            return Optional.of(projeto);
        }
        this.logger.info("Adição de Tarefa a Projeto falhou");
        return Optional.empty();
    }

    @Override
    public float getProjetoByIdVal(Long projetoID) {
        this.logger.info("A calcular valor do projeto com id" + projetoID);
        Optional<Projeto> projeto = projetoRepository.findById(projetoID);
        return projeto.map(Projeto::calcularCusto).orElse(0F);
    }

    @Override
    public float getProjetoByIdTempo(Long projetoID) {
        this.logger.info("A calcular tempo do projeto com id" + projetoID);
        Optional<Projeto> projeto = projetoRepository.findById(projetoID);
        return projeto.map(Projeto::calcularTempo).orElse(0F);
    }

    @Override
    public Optional<Projeto> adicionaCliente(Long projetoID,  Cliente cliente) {
        this.logger.info("A adicionar Cliente " + cliente.getNome() + " a Projeto");
        Optional<Projeto> optionalProjeto = projetoRepository.findById(projetoID);
        if(optionalProjeto.isPresent()) {
            Projeto projeto = optionalProjeto.get();
            if(projeto.getCliente() == null) {
                this.logger.info("Cliente adicionado com sucesso");
                projeto.setCliente(cliente);
                projetoRepository.save(projeto);
                return Optional.of(projeto);
            }
        }
        this.logger.info("Adição de Cliente a Projeto falhou");
        return Optional.empty();
    }
}