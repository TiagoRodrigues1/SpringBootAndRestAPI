package pt.ufp.inf.esof.projeto.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@ToString
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    private String nome;
    @JsonIgnore
    @OneToMany(mappedBy = "projeto",cascade = CascadeType.ALL)
    private List<TarefaPrevista> tarefas = new ArrayList<>();
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
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

    public double calcularCusto() {
        double custo = 0.0;
        if (!tarefas.isEmpty()) {
            for (TarefaPrevista t : tarefas) {
                custo += t.calcularCustoTarefa() * t.getTempoPrevistoConlusao(); //Custo de Hora
            }
        }
        return custo;
    }
}