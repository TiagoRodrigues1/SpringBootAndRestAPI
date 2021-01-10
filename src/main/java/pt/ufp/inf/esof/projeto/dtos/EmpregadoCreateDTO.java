package pt.ufp.inf.esof.projeto.dtos;

import lombok.Data;
import pt.ufp.inf.esof.projeto.modelos.Empregado;

@Data
public class EmpregadoCreateDTO implements CreateDTO<Empregado> {
    private String email;
    private Empregado.Cargo cargo;
    private String nome;

    @Override
    public Empregado converter() {
        Empregado empregado = new Empregado();
        empregado.setEmail(this.email);
        empregado.setCargo(this.cargo);
        empregado.setNome(this.nome);
        return empregado;
    }

}