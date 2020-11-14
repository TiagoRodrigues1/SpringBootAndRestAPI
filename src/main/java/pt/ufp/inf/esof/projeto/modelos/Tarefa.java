package pt.ufp.inf.esof.projeto.modelos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String descricao;
    @ManyToOne
    private Projeto projeto;
    @ManyToOne
    private Empregado empregado;


}