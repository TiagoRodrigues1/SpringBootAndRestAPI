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

    public float calcularTempoPrevisto() {
        float x = 0;
        x = (this.getPeriodoTempoTrabalhado() * 100) / this.getProgresso(); //calcular o tempo previsto tendo em conta o progresso e tempo trabalhado
        if(x > this.tarefaPrevista.getTempoPrevistoConlusao()) {
            this.tarefaPrevista.setTempoPrevistoConlusao(x);
            return x;
        }
        return this.getTarefaPrevista().getTempoPrevistoConlusao();
    }
}
