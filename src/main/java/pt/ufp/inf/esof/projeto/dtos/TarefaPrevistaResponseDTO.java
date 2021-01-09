package pt.ufp.inf.esof.projeto.dtos;

import lombok.Data;

import java.time.Duration;

@Data
public class TarefaPrevistaResponseDTO {
    private String nome;
    private ProjetoCreateDTO projeto;
    private EmpregadoCreateDTO empregado;
    private TarefasEfetivaCreateDTO tarefasEfetivaCreateDTO;
    private Duration tempoPrevistoConlusao;
}