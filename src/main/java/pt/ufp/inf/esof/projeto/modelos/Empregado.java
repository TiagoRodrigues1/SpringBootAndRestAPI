package pt.ufp.inf.esof.projeto.modelos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity

public class Empregado extends Utilizador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "empregado",cascade = CascadeType.ALL)
    private List<Tarefa> tarefa = new ArrayList<>();
    @ManyToOne
    private Cargo cargo;

    public void adicionaTarefa(Tarefa tarefa) {
        if(!this.tarefa.contains(tarefa)) {
            this.tarefa.add(tarefa);
            tarefa.setEmpregado(this);
        }
    }

    public Tarefa removeTarefa(Long id) {
        return null;
    }
}