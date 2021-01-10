package pt.ufp.inf.esof.projeto.modelos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //se tirar causaria erro de StackOverflow, levaria um ciclo infinito entre a TarefaPrevista e Efetiva então TarefaPrevista não é incluida no EqualsandHashcode
public class TarefaEfetiva {
    @EqualsAndHashCode.Include
    private Long id;
    @EqualsAndHashCode.Include
    private String nome;
    @EqualsAndHashCode.Include
    private float progresso;
    @EqualsAndHashCode.Include
    private Duration periodoTempoTrabalhado;
    @EqualsAndHashCode.Include
    private boolean concluida;
    private TarefaPrevista tarefaPrevista;

    public void registarConclusao() {
        this.setProgresso(100);
        this.concluida = true;
    }

    public void registarTempo(Duration tempoTrabalhado, float progresso) {
        this.setPeriodoTempoTrabalhado(tempoTrabalhado);
        this.setProgresso(progresso);
        if(progresso == 100) { //se tiver a feito a tarefa toda
            this.registarConclusao();
        }
    }

    public double calcularTempoPrevisto() {
        double minutes = this.getPeriodoTempoTrabalhado().toMinutes(); //passar para minutos
        double x = (minutes * 100) / this.getProgresso(); //calcular o tempo previsto tendo em conta o progresso e tempo trabalhado
        if(x > this.tarefaPrevista.getTempoPrevistoConlusao().toMinutes()) {
            Duration duration = Duration.ofMinutes((long)x);
            this.tarefaPrevista.setTempoPrevistoConlusao(duration);
            return x;
        }
        return this.tarefaPrevista.getTempoPrevistoConlusao().toMinutes();
    }
}