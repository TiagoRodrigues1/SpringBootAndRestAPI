package pt.ufp.inf.esof.projeto.modelos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Cliente extends Utilizador {
  private Long id;
  private List<Projeto> projetos = new ArrayList<>();
  private String username;
  private String password;

  public void adicionaProjeto(Projeto projeto) {
    if (!this.projetos.contains(projeto)) {
      this.projetos.add(projeto);
      projeto.setCliente(this);
    }
  }

}
