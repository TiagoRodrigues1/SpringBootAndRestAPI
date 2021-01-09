package pt.ufp.inf.esof.projeto.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProjetoRespondeDTO {
    private String nome;
    private List<TarefaPrevistaCreateDTO> tarefas = new ArrayList<>();
    private ClienteCreateDTO cliente;
}