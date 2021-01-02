package pt.ufp.inf.esof.projeto.dtos;

import lombok.Data;

import pt.ufp.inf.esof.projeto.modelos.Empregado;
import pt.ufp.inf.esof.projeto.modelos.Projeto;
import pt.ufp.inf.esof.projeto.modelos.TarefaEfetiva;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TarefaPrevistaCreateDTO implements CreateDTO<TarefaPrevista> {
    private String nome;
    //private Projeto projeto;
    //private List<TarefasEfetivasCreateDTO> tarefaEfetivas = new ArrayList<>();
    private float tempoPrevistoConlusao;
    //private Empregado empregado;

    @Override
    public TarefaPrevista converter() {
        TarefaPrevista tarefa = new TarefaPrevista();
        tarefa.setNome(this.nome);
        //tarefa.setProjeto(this.projeto);
        tarefa.setTempoPrevistoConlusao(this.tempoPrevistoConlusao);
        //tarefa.setEmpregado(this.empregado);
        //tarefa.setTarefaEfetivas(this.tarefaEfetivas.stream().map(TarefasEfetivasCreateDTO::converter).collect(Collectors.toList()));
        return tarefa;
    }
}
