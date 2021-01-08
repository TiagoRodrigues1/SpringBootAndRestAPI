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

    public float calcularTempo() {
        if(!this.tarefas.isEmpty()) {
            float tempoConclusao = 0;
            for (TarefaPrevista t : tarefas) {
                tempoConclusao += t.getTarefaEfetiva().calcularTempoPrevisto();
            }
            return tempoConclusao;
        }
        return 0F;
    }

    public float calcularCusto() {
        if(!this.tarefas.isEmpty()) {
            float custo = 0F;
            for (TarefaPrevista t : tarefas) {
                t.getTarefaEfetiva().calcularTempoPrevisto();
                custo += t.calcularCustoTarefa() * t.getTempoPrevistoConlusao(); //Custo de Hora
            }
            return custo;
        }
        return 0F;
    }
}