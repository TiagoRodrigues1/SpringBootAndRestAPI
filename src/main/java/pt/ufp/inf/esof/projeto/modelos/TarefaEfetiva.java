package pt.ufp.inf.esof.projeto.modelos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TarefaEfetiva {
    private Long id;
    private String nome;
    private float progresso;
    private float periodoTempoTrabalhado;
    private boolean concluida;
    private TarefaPrevista tarefaPrevista;

    public void registarTempoTrabalhado(float tempo) {
        this.setPeriodoTempoTrabalhado(tempo);
    }

    public void registarConclusao() {
        this.setProgresso(100);
        this.concluida = true;
    }

    /*
    public float registarTem() {
        //if (this.getDiaInicio() != null || this.getDiaFim() != null) {
        long minutos = 0;
        minutos = MINUTES.between(this.getDiaInicio(), this.getDiaFim());
        return (float) minutos / 60;
    }
    */
}
