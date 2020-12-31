package pt.ufp.inf.esof.projeto.dtos;

import lombok.Data;
import pt.ufp.inf.esof.projeto.modelos.Empregado;
import pt.ufp.inf.esof.projeto.modelos.Projeto;

import java.util.ArrayList;
import java.util.List;

@Data
public class TarefaResponseDTO {
    private String nome;
    private Projeto projeto;
    private Empregado empregado;
    private List<TarefasEfetivasCreateDTO> tarefaEfetivas = new ArrayList<>();
    private float tempoPrevistoConlusao;
}
