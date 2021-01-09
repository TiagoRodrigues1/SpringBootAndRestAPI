package pt.ufp.inf.esof.projeto.modelos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
@EqualsAndHashCode
public class TarefaPrevista {
    private Long id;
    private String nome;
    private Projeto projeto;
    private Empregado empregado;
    private TarefaEfetiva tarefaEfetiva;
    private Duration tempoPrevistoConlusao;

    public float calcularCustoTarefa() {
        if(this.getEmpregado() != null) {
            return this.getEmpregado().getCargo().valorHora / 60F; //valor em minutos
        }
        return 0F;
    }

    public void adicionaTarefa(TarefaEfetiva tarefaEfetiva) {
        setTarefaEfetiva(tarefaEfetiva);
        tarefaEfetiva.setTarefaPrevista(this);
    }

}