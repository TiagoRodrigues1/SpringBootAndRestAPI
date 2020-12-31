package pt.ufp.inf.esof.projeto.services;

import pt.ufp.inf.esof.projeto.modelos.Empregado;

import java.util.Optional;

public interface EmpregadoService {
    Optional<Empregado> criarEmpregado (Empregado empregado);
}
