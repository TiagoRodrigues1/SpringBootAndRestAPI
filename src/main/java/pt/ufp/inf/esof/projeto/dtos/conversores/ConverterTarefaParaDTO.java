package pt.ufp.inf.esof.projeto.dtos.conversores;

import org.springframework.stereotype.Component;
import pt.ufp.inf.esof.projeto.dtos.TarefaPrevistaResponseDTO;
import pt.ufp.inf.esof.projeto.dtos.TarefasEfetivasCreateDTO;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;

import java.util.stream.Collectors;

@Component
public class ConverterTarefaParaDTO implements Conversor <TarefaPrevistaResponseDTO,TarefaPrevista>{
    @Override
    public TarefaPrevistaResponseDTO converter (TarefaPrevista tarefaPrevista) {
        TarefaPrevistaResponseDTO respondeDTO = new TarefaPrevistaResponseDTO();
        respondeDTO.setNome(tarefaPrevista.getNome());
        //respondeDTO.setEmpregado(tarefaPrevista.getEmpregado());
        respondeDTO.setTempoPrevistoConlusao(tarefaPrevista.getTempoPrevistoConlusao());
        //respondeDTO.setProjeto(tarefaPrevista.getProjeto());
        /*respondeDTO.setTarefaEfetivas(tarefaPrevista.getTarefaEfetivas().stream().map(tarefaEfetiva -> {
            TarefasEfetivasCreateDTO tarefasEfetivasCreateDTO = new TarefasEfetivasCreateDTO();
            tarefasEfetivasCreateDTO.setNome(tarefaEfetiva.getNome());
            tarefasEfetivasCreateDTO.setProgresso(tarefaEfetiva.getProgresso());
            tarefasEfetivasCreateDTO.setConcluida(tarefaEfetiva.isConcluida());
            return tarefasEfetivasCreateDTO;
        }).collect(Collectors.toList()));
         */
        return respondeDTO;
    }
}
