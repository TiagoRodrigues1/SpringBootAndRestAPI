package pt.ufp.inf.esof.projeto.modelos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TarefaPrevista {
    private Long id;
    private String nome;
    private Projeto projeto;
    private Empregado empregado;
    private TarefaEfetiva tarefaEfetiva;
    private float tempoPrevistoConlusao;

    public float calcularCustoTarefa() {
        if(this.getEmpregado() != null) {
            return this.getEmpregado().getCargo().valorHora;
        }
        return 0F;
    }

    public void adicionaTarefa(TarefaEfetiva tarefaEfetiva) {
        setTarefaEfetiva(tarefaEfetiva);
        tarefaEfetiva.setTarefaPrevista(this);
    }


}
