package pt.ufp.inf.esof.projeto.dtos;

import lombok.Data;
import pt.ufp.inf.esof.projeto.modelos.TarefaEfetiva;

import java.time.Duration;

@Data
public class TarefasEfetivaCreateDTO implements CreateDTO<TarefaEfetiva> {
    private String nome;
    private float progresso;
    private short dias;
    private short horas;
    private short minutos;
    private boolean concluida;

    @Override
    public TarefaEfetiva converter() {
        TarefaEfetiva tarefaEfetiva = new TarefaEfetiva();
        tarefaEfetiva.setProgresso(this.progresso);
        tarefaEfetiva.setNome(this.nome);
        tarefaEfetiva.setPeriodoTempoTrabalhado(Duration.ofDays(dias).plusHours(horas).plusMinutes(minutos));
        tarefaEfetiva.setConcluida(this.concluida);
        return tarefaEfetiva;
    }
}