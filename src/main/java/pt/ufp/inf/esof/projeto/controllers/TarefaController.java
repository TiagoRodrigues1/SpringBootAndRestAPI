package pt.ufp.inf.esof.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.inf.esof.projeto.dtos.EmpregadoCreateDTO;
import pt.ufp.inf.esof.projeto.dtos.TarefaPrevistaCreateDTO;
import pt.ufp.inf.esof.projeto.dtos.TarefaPrevistaResponseDTO;
import pt.ufp.inf.esof.projeto.dtos.conversores.ConverterTarefaParaDTO;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;
import pt.ufp.inf.esof.projeto.services.TarefaService;

import java.util.Optional;

@Controller
@RequestMapping("/tarefa")
public class TarefaController {
    private final TarefaService tarefaService;
    private final ConverterTarefaParaDTO converterTarefaParaDTO = new ConverterTarefaParaDTO();

    @Autowired//Optional
    public TarefaController (TarefaService tarefaService) { this.tarefaService = tarefaService;}

    @PostMapping
    public ResponseEntity<TarefaPrevistaResponseDTO> criarTarefa (@RequestBody TarefaPrevistaCreateDTO tarefa) {
        Optional<TarefaPrevista> optionalTarefaPrevista = tarefaService.criarTarefa(tarefa.converter());
        return optionalTarefaPrevista.map(value -> ResponseEntity.ok(converterTarefaParaDTO.converter(value))).orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @PatchMapping("/empregado/{id}")
    public ResponseEntity<TarefaPrevistaResponseDTO> adicionaEmpregado(@PathVariable Long id, @RequestBody EmpregadoCreateDTO empregado) {
        Optional<TarefaPrevista> optionalTarefaPrevista = tarefaService.adicionaEmpregado(id,empregado.converter());
        return optionalTarefaPrevista.map(tarefaPrevista -> ResponseEntity.ok(converterTarefaParaDTO.converter(tarefaPrevista))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
