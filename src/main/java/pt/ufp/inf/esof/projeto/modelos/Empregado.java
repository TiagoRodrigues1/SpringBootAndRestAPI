package pt.ufp.inf.esof.projeto.modelos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Empregado extends Utilizador {
    public enum Cargo {
        DESENVOLVERDOR_JUNIOR(10), ANALISTA_JUNIOR(20),DESENVOLVEDOR_JUNIOR(40),ANALISTA_SENIOR(80);
        public int valorHora;
        Cargo(int valorhora) {
            valorHora = valorhora;
        }
    }
    private Long id;
    private List<TarefaPrevista> tarefa = new ArrayList<>();
    private Cargo cargo;
    private String email;

    public void adicionaTarefa(TarefaPrevista tarefa) {
        if(!this.tarefa.contains(tarefa)) {
            this.tarefa.add(tarefa);
            tarefa.setEmpregado(this);
        }
    }
}