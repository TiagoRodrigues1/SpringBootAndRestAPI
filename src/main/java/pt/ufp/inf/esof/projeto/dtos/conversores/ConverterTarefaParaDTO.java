package pt.ufp.inf.esof.projeto.dtos.conversores;

import org.springframework.stereotype.Component;
import pt.ufp.inf.esof.projeto.dtos.EmpregadoCreateDTO;
import pt.ufp.inf.esof.projeto.dtos.ProjetoCreateDTO;
import pt.ufp.inf.esof.projeto.dtos.TarefaPrevistaResponseDTO;
import pt.ufp.inf.esof.projeto.dtos.TarefasEfetivaCreateDTO;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;

@Component
public class ConverterTarefaParaDTO implements Conversor <TarefaPrevistaResponseDTO,TarefaPrevista>{
    @Override
    public TarefaPrevistaResponseDTO converter (TarefaPrevista tarefaPrevista) {
        TarefaPrevistaResponseDTO respondeDTO = new TarefaPrevistaResponseDTO();
        respondeDTO.setNome(tarefaPrevista.getNome());
        if(tarefaPrevista.getEmpregado() != null) {
            EmpregadoCreateDTO empregadoCreateDTO = new EmpregadoCreateDTO();
            empregadoCreateDTO.setEmail(tarefaPrevista.getEmpregado().getEmail());
            empregadoCreateDTO.setCargo(tarefaPrevista.getEmpregado().getCargo());
            empregadoCreateDTO.setNome(tarefaPrevista.getEmpregado().getNome());
            respondeDTO.setEmpregado(empregadoCreateDTO);
        }
        respondeDTO.setTempoPrevistoConlusao(tarefaPrevista.getTempoPrevistoConlusao());
        if(tarefaPrevista.getProjeto() != null) {
            ProjetoCreateDTO projeto = new ProjetoCreateDTO();
            projeto.setNome(tarefaPrevista.getProjeto().getNome());
            respondeDTO.setProjeto(projeto);
        }
        if(tarefaPrevista.getTarefaEfetiva() != null) {
            TarefasEfetivaCreateDTO tarefasEfetivaCreateDTO = new TarefasEfetivaCreateDTO();
            tarefasEfetivaCreateDTO.setConcluida(tarefaPrevista.getTarefaEfetiva().isConcluida());
            tarefasEfetivaCreateDTO.setNome(tarefaPrevista.getTarefaEfetiva().getNome());
            tarefasEfetivaCreateDTO.setProgresso(tarefaPrevista.getTarefaEfetiva().getProgresso());
            tarefasEfetivaCreateDTO.setDias((short) tarefaPrevista.getTempoPrevistoConlusao().toDaysPart());
            tarefasEfetivaCreateDTO.setHoras((short)tarefaPrevista.getTempoPrevistoConlusao().toHoursPart());
            tarefasEfetivaCreateDTO.setMinutos((short)tarefaPrevista.getTempoPrevistoConlusao().toMinutesPart());
            respondeDTO.setTarefasEfetivaCreateDTO(tarefasEfetivaCreateDTO);
        }
        return respondeDTO;
    }
}