package pt.ufp.inf.esof.projeto.dtos;

import lombok.Data;

import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;

import java.time.Duration;

@Data
public class TarefaPrevistaCreateDTO implements CreateDTO<TarefaPrevista> {
    private String nome;
    private TarefasEfetivaCreateDTO tarefasEfetivaCreateDTO;
    private short dias;
    private short horas;
    private short minutos;

    @Override
    public TarefaPrevista converter() {
        TarefaPrevista tarefa = new TarefaPrevista();
        tarefa.setNome(this.getNome());
        tarefa.setTempoPrevistoConlusao(Duration.ofDays(this.getDias()).plusHours(this.getHoras()).plusMinutes(this.getMinutos()));
        tarefa.setTarefaEfetiva(this.getTarefasEfetivaCreateDTO().converter());
        return tarefa;
    }
}