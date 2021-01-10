package pt.ufp.inf.esof.projeto.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pt.ufp.inf.esof.projeto.dtos.TarefaPrevistaResponseDTO;
import pt.ufp.inf.esof.projeto.dtos.TarefasCreateDTO;
import pt.ufp.inf.esof.projeto.dtos.conversores.ConverterTarefaParaDTO;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;
import pt.ufp.inf.esof.projeto.services.TarefaService;

import java.util.Optional;

@Controller
@RequestMapping("/tarefa")
public class TarefaController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final TarefaService tarefaService;
    private final ConverterTarefaParaDTO converterTarefaParaDTO = new ConverterTarefaParaDTO();

    @Autowired//Optional
    public TarefaController (TarefaService tarefaService) { this.tarefaService = tarefaService;}

    @PostMapping
    public ResponseEntity<TarefaPrevistaResponseDTO> criarTarefa (@RequestBody TarefasCreateDTO tarefa) {
        this.logger.info("Post - criarTarefa");
        Optional<TarefaPrevista> optionalTarefaPrevista = tarefaService.criarTarefa(tarefa.converter());
        return optionalTarefaPrevista.map(value -> ResponseEntity.ok(converterTarefaParaDTO.converter(value))).orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @PatchMapping("/empregado/{id}")
    public ResponseEntity<TarefaPrevistaResponseDTO> adicionaEmpregado(@PathVariable Long id, @RequestBody String email) {
        this.logger.info("Patch - adicionaEmpregado");
        Optional<TarefaPrevista> optionalTarefaPrevista = tarefaService.adicionaEmpregado(id,email);
        return optionalTarefaPrevista.map(tarefaPrevista -> ResponseEntity.ok(converterTarefaParaDTO.converter(tarefaPrevista))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
