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
public class TarefaPrevista {
    private Long id;
    private String nome;
    private Projeto projeto;
    private Empregado empregado;
    private List<TarefaEfetiva> tarefaEfetivas = new ArrayList<>();
    private float tempoPrevistoConlusao;

    public float calcularCustoTarefa() {
        return this.getEmpregado().getCargo().valorHora;
    }

    public void adicionaTarefa(TarefaEfetiva tarefaEfetiva) {
        if(!this.tarefaEfetivas.contains(tarefaEfetiva)) {
            this.tarefaEfetivas.add(tarefaEfetiva);
            tarefaEfetiva.setTarefaPrevista(this);
        }
    }


}
