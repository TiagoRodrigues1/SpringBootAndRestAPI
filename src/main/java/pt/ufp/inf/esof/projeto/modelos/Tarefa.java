package pt.ufp.inf.esof.projeto.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.*;
import static java.time.temporal.ChronoUnit.MINUTES;

@Getter
@Setter
@ToString

@Entity
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Projeto projeto;
    @ManyToOne
    @JsonIgnore
    private Empregado empregado;
    private LocalDateTime diaInicio;
    private LocalDateTime diaFim;

    public float tempoTarefa() {
        //if (this.getDiaInicio() != null || this.getDiaFim() != null) {
            long minutos = 0;
            minutos = MINUTES.between(this.getDiaInicio(), this.getDiaFim());
            return (float) minutos / 60;
    }
}