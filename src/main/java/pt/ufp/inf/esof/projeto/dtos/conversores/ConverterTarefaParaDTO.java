package pt.ufp.inf.esof.projeto.dtos.conversores;

import org.springframework.stereotype.Component;
import pt.ufp.inf.esof.projeto.dtos.EmpregadoCreateDTO;
import pt.ufp.inf.esof.projeto.dtos.ProjetoCreateDTO;
import pt.ufp.inf.esof.projeto.dtos.TarefaPrevistaResponseDTO;
import pt.ufp.inf.esof.projeto.modelos.Projeto;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;

@Component
public class ConverterTarefaParaDTO implements Conversor <TarefaPrevistaResponseDTO,TarefaPrevista>{
    @Override
    public TarefaPrevistaResponseDTO converter (TarefaPrevista tarefaPrevista) {
        TarefaPrevistaResponseDTO respondeDTO = new TarefaPrevistaResponseDTO();
        respondeDTO.setNome(tarefaPrevista.getNome());
        EmpregadoCreateDTO empregadoCreateDTO = new EmpregadoCreateDTO();
        empregadoCreateDTO.setEmail(tarefaPrevista.getEmpregado().getEmail());
        empregadoCreateDTO.setCargo(tarefaPrevista.getEmpregado().getCargo());
        empregadoCreateDTO.setNome(tarefaPrevista.getEmpregado().getNome());
        respondeDTO.setEmpregado(empregadoCreateDTO);
        respondeDTO.setTempoPrevistoConlusao(tarefaPrevista.getTempoPrevistoConlusao());
        ProjetoCreateDTO projeto = new ProjetoCreateDTO();
        projeto.setNome(tarefaPrevista.getProjeto().getNome());
        respondeDTO.setProjeto(projeto);
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