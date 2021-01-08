package pt.ufp.inf.esof.projeto.dtos;

import lombok.Data;
import pt.ufp.inf.esof.projeto.modelos.Empregado;
import pt.ufp.inf.esof.projeto.modelos.Projeto;

import java.util.ArrayList;
import java.util.List;

@Data
public class TarefaPrevistaResponseDTO {
    private String nome;
    private TarefasEfetivasCreateDTO tarefasEfetivasCreateDTO;
    private float tempoPrevistoConlusao;
    //private Projeto projeto;
    //private Empregado empregado;

}