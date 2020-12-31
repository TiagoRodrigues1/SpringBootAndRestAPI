package pt.ufp.inf.esof.projeto.dtos.conversores;

import pt.ufp.inf.esof.projeto.dtos.EmpregadoResponseDTO;
import pt.ufp.inf.esof.projeto.dtos.TarefaPrevistaCreateDTO;
import pt.ufp.inf.esof.projeto.modelos.Empregado;

import java.util.stream.Collectors;

public class ConverterEmpregadoParaDTO implements Conversor<EmpregadoResponseDTO, Empregado> {
    @Override
    public EmpregadoResponseDTO converter(Empregado empregado) {
        EmpregadoResponseDTO responseDTO = new EmpregadoResponseDTO();
        responseDTO.setCargo(empregado.getCargo());
        responseDTO.setEmail(empregado.getEmail());
        responseDTO.setTarefas(empregado.getTarefa().stream().map(tarefa ->{
            TarefaPrevistaCreateDTO tarefaPrevistaCreateDTO = new TarefaPrevistaCreateDTO();
            tarefaPrevistaCreateDTO.setNome(tarefa.getNome());
            tarefaPrevistaCreateDTO.setProjeto(tarefa.getProjeto());
            return tarefaPrevistaCreateDTO;
        }).collect(Collectors.toList()));
        return responseDTO;
    }
}
