package pt.ufp.inf.esof.projeto.modelos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class TarefaPrevista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ManyToOne
    private Projeto projeto;
    @ManyToOne
    private Empregado empregado;
    private float tempoPrevistoConlusao;
    @OneToMany(cascade = CascadeType.ALL)
    private List<TarefaEfetiva> tarefaEfetivas = new ArrayList<>();



    public float calcularCustoTarefa() {
        return this.empregado.getCargo().valorHora;
    }

    public void adicionaTarefa(TarefaEfetiva tarefaEfetiva) {
        if(!this.tarefaEfetivas.contains(tarefaEfetiva)) {
            this.tarefaEfetivas.add(tarefaEfetiva);
            tarefaEfetiva.setTarefaPrevista(this);
        }
    }
    public Empregado adicionaEmpregado(Empregado empregado) {
        if(this.empregado != null && !this.empregado.getTarefa().contains(this)) {
            this.empregado = empregado;
            this.empregado.adicionaTarefa(this);
            return this.empregado;
        }
        return null;
    }
}
