package pt.ufp.inf.esof.projeto.dtos.conversores;

import org.springframework.stereotype.Component;
import pt.ufp.inf.esof.projeto.dtos.ClienteCreateDTO;
import pt.ufp.inf.esof.projeto.dtos.ProjetoRespondeDTO;
import pt.ufp.inf.esof.projeto.dtos.TarefaPrevistaCreateDTO;
import pt.ufp.inf.esof.projeto.modelos.Projeto;

import java.util.stream.Collectors;

@Component
public class ConverterProjetoParaDTO implements Conversor<ProjetoRespondeDTO, Projeto> {
    @Override
    public ProjetoRespondeDTO converter (Projeto projeto) {
        ProjetoRespondeDTO respondeDTO = new ProjetoRespondeDTO();
        respondeDTO.setNome(projeto.getNome());
        if(projeto.getCliente() != null) { //caso o projeto não tenha cliente
            ClienteCreateDTO clienteCreateDTO = new ClienteCreateDTO();
            clienteCreateDTO.setNome(projeto.getCliente().getNome());
            clienteCreateDTO.setUsername(projeto.getCliente().getUsername());
            respondeDTO.setCliente(clienteCreateDTO);
        }
        respondeDTO.setTarefas(projeto.getTarefas().stream().map(tarefa -> {
            TarefaPrevistaCreateDTO previstaCreateDTO = new TarefaPrevistaCreateDTO();
            previstaCreateDTO.setNome(tarefa.getNome());
            previstaCreateDTO.setDias((short)tarefa.getTempoPrevistoConlusao().toDaysPart());
            previstaCreateDTO.setHoras((short)tarefa.getTempoPrevistoConlusao().toHoursPart());
            previstaCreateDTO.setMinutos((short)tarefa.getTempoPrevistoConlusao().toMinutesPart());
            return previstaCreateDTO;
        }).collect(Collectors.toList()));

        return respondeDTO;
    }
}