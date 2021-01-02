package pt.ufp.inf.esof.projeto.dtos;

import lombok.Data;
import pt.ufp.inf.esof.projeto.modelos.Cliente;
import pt.ufp.inf.esof.projeto.modelos.Projeto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProjetoCreateDTO implements CreateDTO<Projeto> {
    private String nome;
    //private List<TarefaPrevistaCreateDTO> tarefas = new ArrayList<>();
    //private Cliente cliente;

    @Override
    public Projeto converter () {
        Projeto projeto = new Projeto();
        projeto.setNome(this.getNome());
        //projeto.setCliente(this.cliente);
        //projeto.setTarefas(this.tarefas.stream().map(TarefaPrevistaCreateDTO::converter).collect(Collectors.toList()));
        return projeto;
    }
}