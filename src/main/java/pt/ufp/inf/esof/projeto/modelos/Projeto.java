package pt.ufp.inf.esof.projeto.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@ToString

public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDateTime deadline;
    @JsonIgnore
    @OneToMany(mappedBy = "projeto",cascade = CascadeType.ALL)
    private List<Tarefa> tarefas = new ArrayList<>();
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Cliente cliente;

    public Tarefa adicionaTarefa(Tarefa tarefa) {
        if (!this.tarefas.contains(tarefa)) {
            this.tarefas.add(tarefa);
            tarefa.setProjeto(this);
            return tarefa;
        }
        return null;
    }

    public Duration calcularTempo() {
        return Duration.between(LocalDateTime.now(),this.deadline);
    }

    public double calcularCusto() {
        double custo = 0.0;
        if (!tarefas.isEmpty()) {
            for (Tarefa t : tarefas) {
                custo += t.getEmpregado().getCargo().valorHora * t.tempoTarefa();
                System.out.println(t.tempoTarefa());
            }
        }
        return custo;
    }
}