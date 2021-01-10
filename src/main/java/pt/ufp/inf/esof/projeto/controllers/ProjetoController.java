package pt.ufp.inf.esof.projeto.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.inf.esof.projeto.dtos.*;
import pt.ufp.inf.esof.projeto.dtos.conversores.ConverterProjetoParaDTO;
import pt.ufp.inf.esof.projeto.modelos.Projeto;
import pt.ufp.inf.esof.projeto.services.ProjetoService;

import java.util.Optional;

@Controller
@RequestMapping("/projeto")
public class ProjetoController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProjetoService projetoService;
    private final ConverterProjetoParaDTO converterProjetoParaDTO = new ConverterProjetoParaDTO();
    @Autowired
    public ProjetoController (ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping("/{id}/valor")
    public ResponseEntity<Double> getProjetoByIdVal(@PathVariable Long id) {
        this.logger.info("Get - getProjetoByIdVal");
        double valor = projetoService.getProjetoByIdVal(id);
        if(valor != 0F) {
            return ResponseEntity.ok(valor);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProjetoRespondeDTO> criarProjeto (@RequestBody ProjetoCreateDTO projeto) {
        this.logger.info("Post - criarProjeto");
        Optional<Projeto> optionalProjeto = projetoService.criarProjeto(projeto.converter());
        return optionalProjeto.map(value -> ResponseEntity.ok(converterProjetoParaDTO.converter(value))).orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @GetMapping("/{id}/tempo")
    public ResponseEntity<Double> getProjetoByIdTempo(@PathVariable Long id) {
        this.logger.info("Get - getProjetoByIdTempo");
        double tempo = projetoService.getProjetoByIdTempo(id);
        if(tempo != 0D) {
            return ResponseEntity.ok(tempo);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/tarefa/{id}")
    public ResponseEntity<ProjetoRespondeDTO> adicionaTarefa (@PathVariable Long id, @RequestBody String nome) {
        this.logger.info("Patch - adicionaTarefa");
        Optional<Projeto> optionalTarefaPrevista = projetoService.adicionaTarefa(id,nome);
        return optionalTarefaPrevista.map(projeto -> ResponseEntity.ok(converterProjetoParaDTO.converter(projeto))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PatchMapping("/cliente/{id}")
    public ResponseEntity<ProjetoRespondeDTO> adicionaCliente(@PathVariable Long id, @RequestBody ClienteCreateDTO clienteCreateDTO) {
        this.logger.info("Patch - adicionaCliente");
        Optional<Projeto> optionalProjeto = projetoService.adicionaCliente(id,clienteCreateDTO.converter());
        return optionalProjeto.map(projeto -> ResponseEntity.ok(converterProjetoParaDTO.converter(projeto))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
