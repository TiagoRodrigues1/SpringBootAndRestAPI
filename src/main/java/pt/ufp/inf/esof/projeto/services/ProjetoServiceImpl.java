package pt.ufp.inf.esof.projeto.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.ufp.inf.esof.projeto.modelos.Cliente;
import pt.ufp.inf.esof.projeto.modelos.Projeto;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;
import pt.ufp.inf.esof.projeto.repositories.ProjetoRepository;
import pt.ufp.inf.esof.projeto.repositories.TarefaPrevistaRepository;

import java.util.Optional;

@Service
public class ProjetoServiceImpl implements ProjetoService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProjetoRepository projetoRepository;
    private final TarefaPrevistaRepository tarefaPrevistaRepository;

    @Autowired
    public ProjetoServiceImpl(ProjetoRepository projetoRepository, TarefaPrevistaRepository tarefaPrevistaRepository) {
        this.projetoRepository = projetoRepository;
        this.tarefaPrevistaRepository = tarefaPrevistaRepository;
    }

    @Override
    public Optional<Projeto> criarProjeto(Projeto projeto) {
        this.logger.info("A criar novo Projeto" + projeto.getNome());
        Optional<Projeto> optionalProjeto = projetoRepository.findByNome(projeto.getNome());
        if (optionalProjeto.isEmpty()) {
            this.logger.info("Projeto criado com sucesso");
            projetoRepository.save(projeto); // salvar o projeto
            return Optional.of(projetoRepository.save(projeto));
        }
        this.logger.info("Projeto já Existia");
        return Optional.empty();
    }

    @Override
    public Optional<Projeto> adicionaTarefa(Long projetoID, String nome) {
        this.logger.info("A adicionar Tarefa a Projeto");
        Optional<Projeto> optionalProjeto = projetoRepository.findById(projetoID);
        Optional<TarefaPrevista> optionalTarefaPrevista = tarefaPrevistaRepository.findByNome(nome);
        if(optionalTarefaPrevista.isPresent() && optionalProjeto.isPresent()) {
                this.logger.info("Tarefa adicionada com sucesso");
                Projeto projeto = optionalProjeto.get();
                projeto.adicionaTarefa(optionalTarefaPrevista.get());
                projetoRepository.save(projeto);
                return Optional.of(projeto);
        }
        this.logger.info("Adição de Tarefa a Projeto falhou! Crie uma tarefa e depois adicione ao Projeto");
        return Optional.empty();
    }

    @Override
    public double getProjetoByIdVal(Long projetoID) {
        this.logger.info("A calcular valor do projeto com id" + projetoID);
        Optional<Projeto> projeto = projetoRepository.findById(projetoID);
        return projeto.map(Projeto::calcularCusto).orElse(0D);
    }

    @Override
    public double getProjetoByIdTempo(Long projetoID) {
        this.logger.info("A calcular tempo do projeto com id" + projetoID);
        Optional<Projeto> projeto = projetoRepository.findById(projetoID);
        return projeto.map(Projeto::calcularTempo).orElse(0D);
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