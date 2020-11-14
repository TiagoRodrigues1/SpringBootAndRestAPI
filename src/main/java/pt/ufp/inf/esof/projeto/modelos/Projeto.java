package pt.ufp.inf.esof.projeto.modelos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity

public class Projeto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private DayOfWeek deadline;
  private LocalTime endHour;
  @OneToMany(mappedBy = "projeto",cascade = CascadeType.ALL)
  private List<Tarefa> tarefas = new ArrayList<>();
  @ManyToOne
  private Cliente cliente;

  public void adicionaTarefa(Tarefa tarefa) {
    if(!this.tarefas.contains(tarefa)) {
      this.tarefas.add(tarefa);
      tarefa.setProjeto(this);
    }
  }

  public void removeTarefa() {
  }

}