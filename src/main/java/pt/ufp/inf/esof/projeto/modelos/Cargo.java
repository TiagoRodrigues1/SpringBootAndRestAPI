package pt.ufp.inf.esof.projeto.modelos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class Cargo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private int valorHora;
  @OneToMany
  private List<Empregado> empregados = new ArrayList<>();
  private String nome;
  public void adicionaEmpregado(Empregado empregado) {
    if(!this.empregados.contains(empregado)) {
      empregados.add(empregado);
      empregado.setCargo(this);
    }
  }

  public void removerEmpregado() {
  }

}