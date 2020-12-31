package pt.ufp.inf.esof.projeto.dtos;

import lombok.Data;
import pt.ufp.inf.esof.projeto.dtos.CreateDTO;
import pt.ufp.inf.esof.projeto.modelos.TarefaEfetiva;

@Data
public class TarefasEfetivasCreateDTO implements CreateDTO<TarefaEfetiva> {
    private String nome;
    private float progresso;
    private float periodoTempoTrabalhado;
    private boolean concluida;

    @Override
    public TarefaEfetiva converter() {
        TarefaEfetiva tarefaEfetiva = new TarefaEfetiva();
        tarefaEfetiva.setProgresso(this.progresso);
        tarefaEfetiva.setNome(this.nome);
        tarefaEfetiva.setPeriodoTempoTrabalhado(this.periodoTempoTrabalhado);
        tarefaEfetiva.setConcluida(this.concluida);
        return tarefaEfetiva;
    }
}
