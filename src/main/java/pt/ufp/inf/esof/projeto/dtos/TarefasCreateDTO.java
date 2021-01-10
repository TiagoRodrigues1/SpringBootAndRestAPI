package pt.ufp.inf.esof.projeto.dtos;

import lombok.Data;
import pt.ufp.inf.esof.projeto.modelos.Tarefas;

@Data
public class TarefasCreateDTO implements CreateDTO<Tarefas> {
    private TarefaPrevistaCreateDTO tarefaPrevistaCreateDTO;
    private TarefasEfetivaCreateDTO tarefasEfetivaCreateDTO;

    @Override
    public Tarefas converter() {
        Tarefas tarefas = new Tarefas();
        tarefas.setTarefaEfetiva(this.getTarefasEfetivaCreateDTO().converter());
        tarefas.setTarefaPrevista(this.getTarefaPrevistaCreateDTO().converter());
        return tarefas;
    }
}
