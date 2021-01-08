package pt.ufp.inf.esof.projeto.dtos;

import lombok.Data;

import pt.ufp.inf.esof.projeto.modelos.Empregado;
import pt.ufp.inf.esof.projeto.modelos.Projeto;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;


@Data
public class TarefaPrevistaCreateDTO implements CreateDTO<TarefaPrevista> {
    private String nome;
    //private Projeto projeto;
    private TarefasEfetivasCreateDTO tarefasEfetivasCreateDTO;
    private float tempoPrevistoConlusao;
    //private Empregado empregado;

    @Override
    public TarefaPrevista converter() {
        TarefaPrevista tarefa = new TarefaPrevista();
        tarefa.setNome(this.nome);
        tarefa.setTempoPrevistoConlusao(this.tempoPrevistoConlusao);
        tarefa.setTarefaEfetiva(this.tarefasEfetivasCreateDTO.converter());
        //tarefa.setProjeto(this.projeto);
        //tarefa.setEmpregado(this.empregado);
        return tarefa;
    }
}