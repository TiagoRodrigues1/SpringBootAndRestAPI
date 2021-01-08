package pt.ufp.inf.esof.projeto.dtos.conversores;

import org.springframework.stereotype.Component;
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
        //respondeDTO.setCliente(projeto.getCliente());
        /*respondeDTO.setTarefas(projeto.getTarefas().stream().map(tarefa -> {
            TarefaPrevistaCreateDTO previstaCreateDTO = new TarefaPrevistaCreateDTO();
            previstaCreateDTO.setNome(tarefa.getNome());
            previstaCreateDTO.setProjeto(tarefa.getProjeto());
            previstaCreateDTO.setEmpregado(tarefa.getEmpregado());
            return previstaCreateDTO;
        }).collect(Collectors.toList()));
         */
        return respondeDTO;
    }
}
