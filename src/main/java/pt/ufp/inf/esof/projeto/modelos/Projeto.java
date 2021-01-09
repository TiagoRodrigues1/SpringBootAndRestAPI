package pt.ufp.inf.esof.projeto.modelos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Projeto {
    private Long id;
    private String nome;
    private List<TarefaPrevista> tarefas = new ArrayList<>();
    private Cliente cliente;

    public void adicionaTarefa(TarefaPrevista tarefa) {
        if (!this.tarefas.contains(tarefa)) {
            this.tarefas.add(tarefa);
            tarefa.setProjeto(this);
        }
    }

    public double calcularTempo() {
        if(!this.tarefas.isEmpty()) {
            long tempoConclusao = 0;
            for (TarefaPrevista t : tarefas) {
                if (t.getTarefaEfetiva() != null) {
                    tempoConclusao += t.getTarefaEfetiva().calcularTempoPrevisto();
                }
                tempoConclusao += t.getTempoPrevistoConlusao().toMinutes();
            }
            return tempoConclusao / 60.0;
        }
        return 0D;
    }

    public double calcularCusto() {
        if(!this.tarefas.isEmpty()) {
            double custo = 0D;
            for (TarefaPrevista t : tarefas) {
                if (t.getTarefaEfetiva() != null) {
                    t.getTarefaEfetiva().calcularTempoPrevisto();
                    double tempo = (t.getTempoPrevistoConlusao().toMinutes()); //Passar o tempo para horas e minutos e não só minutos
                    custo += t.calcularCustoTarefa() * tempo;
                }
                custo += t.calcularCustoTarefa() * t.getTempoPrevistoConlusao().toMinutes();
            }
            return custo;

        }
        return 0D;
    }
}