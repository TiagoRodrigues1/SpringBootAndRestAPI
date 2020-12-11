package pt.ufp.inf.esof.projeto.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity

public class Empregado extends Utilizador {
    public enum Cargo {
        DESENVOLVERDOR_JUNIOR(10), ANALISTA_JUNIOR(20),DESENVOLVEDOR_JUNIOR(40),ANALISTA_SENIOR(80);
        public int valorHora;
        Cargo(int valorhora) {
            valorHora = valorhora;
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "empregado",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Tarefa> tarefa = new ArrayList<>();
    private Cargo cargo;
    private String email;

    public void adicionaTarefa(Tarefa tarefa) {
        if(!this.tarefa.contains(tarefa)) {
            this.tarefa.add(tarefa);
            tarefa.setEmpregado(this);
        }
    }
}