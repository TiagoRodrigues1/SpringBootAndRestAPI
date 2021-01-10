package pt.ufp.inf.esof.projeto.modelos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Tarefas { //classe para conseguir fazer o mapeamento entre a tarefa efetiva e tarefa preevista
    TarefaPrevista tarefaPrevista;
    TarefaEfetiva tarefaEfetiva;
}
