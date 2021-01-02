package pt.ufp.inf.esof.projeto.dtos;

import lombok.Data;
import pt.ufp.inf.esof.projeto.modelos.Empregado;
import pt.ufp.inf.esof.projeto.modelos.Projeto;

import java.util.ArrayList;
import java.util.List;

@Data //cria todos os getters setters hascode to string etc
public class EmpregadoResponseDTO {
    private String email;
    private Empregado.Cargo cargo;
    //private List<TarefaPrevistaCreateDTO> tarefas = new ArrayList<>();
    private Projeto projeto;
    private String nome;

}