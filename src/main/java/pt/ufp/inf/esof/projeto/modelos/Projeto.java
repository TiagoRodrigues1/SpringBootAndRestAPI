package pt.ufp.inf.esof.projeto.modelos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Projeto {
    private Long id;
    private String nome;
    //@JsonIgnore
    private List<TarefaPrevista> tarefas = new ArrayList<>();
    //@JsonIgnore
    private Cliente cliente;

    public void adicionaTarefa(TarefaPrevista tarefa) {
        if (!this.tarefas.contains(tarefa)) {
            this.tarefas.add(tarefa);
            tarefa.setProjeto(this);
        }
    }

    public float calcularTempo() {
       float tempoConclusao = 0;
        for(TarefaPrevista t : tarefas) {
            tempoConclusao += t.getTempoPrevistoConlusao();
        }
        return tempoConclusao;
    }

    public float calcularCusto() {
        float custo = 0F;
        if (!tarefas.isEmpty()) {
            for (TarefaPrevista t : tarefas) {
                custo += t.calcularCustoTarefa() * t.getTempoPrevistoConlusao(); //Custo de Hora
            }
        }
        return custo;
    }
}