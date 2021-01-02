package pt.ufp.inf.esof.projeto.dtos;

import lombok.Data;
import pt.ufp.inf.esof.projeto.modelos.Empregado;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class EmpregadoCreateDTO  implements CreateDTO<Empregado> {
    private String email;
    //private List<TarefaPrevistaCreateDTO> tarefas = new ArrayList<>();
    private Empregado.Cargo cargo;
    private String nome;

    @Override
    public Empregado converter() {
        Empregado empregado = new Empregado();
        empregado.setEmail(this.email);
        empregado.setCargo(this.cargo);
        empregado.setNome(this.nome);
        //empregado.setTarefa(tarefas.stream().map(TarefaPrevistaCreateDTO::converter).collect(Collectors.toList()));
        return empregado;
    }

}