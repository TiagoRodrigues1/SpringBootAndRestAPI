package pt.ufp.inf.esof.projeto.modelos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity

public class Cliente extends Utilizador {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
  private List<Projeto> projetos = new ArrayList<>();

  public Date consultaTempo() {return null;}
  public Float consultaPreco() {return null;}

  public void adicionaProjeto(Projeto projeto) {
    if(!this.projetos.contains(projeto)) {
      this.projetos.add(projeto);
      projeto.setCliente(this);
    }
  }
  public Projeto removeProjeto() {
    return null;
  }
}