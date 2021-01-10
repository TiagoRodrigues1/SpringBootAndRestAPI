package pt.ufp.inf.esof.projeto.modelos;


import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjetoTest {

    @Test
    void calcularTempo() {
        Projeto p = new Projeto();
        TarefaPrevista t = new TarefaPrevista();
        TarefaPrevista t2 = new TarefaPrevista();
        TarefaEfetiva e = new TarefaEfetiva();
        TarefaEfetiva e1 = new TarefaEfetiva();

        Duration duration = Duration.ofHours(10).plusMinutes(30);
        Duration duration1 = Duration.ofHours(5);
        e.registarTempo(duration,5); //Realizou 5% da Tarefa em 10Hrs
        e1.registarTempo(duration1,100);//Realizou 100% da Tarefa em 5hrs quando estaria programado para 20hrs

        t.setTempoPrevistoConlusao(Duration.ofHours(10));
        t2.setTempoPrevistoConlusao(Duration.ofHours(20));

        Empregado emp = new Empregado();
        emp.setCargo(Empregado.Cargo.ANALISTA_SENIOR);
        Empregado empregado = new Empregado();
        empregado.setCargo(Empregado.Cargo.DESENVOLVEDOR_JUNIOR);

        t.setEmpregado(empregado);
        t2.setEmpregado(emp);

        t.adicionaTarefa(e);
        t2.adicionaTarefa(e1);

        p.adicionaTarefa(t);
        p.adicionaTarefa(t2);

        assertEquals(460,p.calcularTempo());

    }

    @Test
    void calcularCusto() {
        Projeto p = new Projeto();
        TarefaPrevista t = new TarefaPrevista();
        TarefaPrevista t2 = new TarefaPrevista();
        TarefaEfetiva e = new TarefaEfetiva();
        TarefaEfetiva e1 = new TarefaEfetiva();

        Duration duration = Duration.ofHours(10).plusMinutes(30);
        Duration duration1 = Duration.ofHours(5);
        e.registarTempo(duration,5); //Realizou 5% da Tarefa em 10Hrs
        e1.registarTempo(duration1,100);//Realizou 100% da Tarefa em 5hrs quando estaria programado para 20hrs

        t.setTempoPrevistoConlusao(Duration.ofHours(10));
        t2.setTempoPrevistoConlusao(Duration.ofHours(20));

        Empregado empregado = new Empregado();
        empregado.setCargo(Empregado.Cargo.DESENVOLVEDOR_JUNIOR);
        Empregado emp = new Empregado();
        emp.setCargo(Empregado.Cargo.ANALISTA_SENIOR);

        t.adicionaTarefa(e);
        t2.adicionaTarefa(e1);

        t.setEmpregado(empregado);
        t2.setEmpregado(emp);

        p.adicionaTarefa(t);
        p.adicionaTarefa(t2);
        assertEquals(20000.0,Double.parseDouble(new DecimalFormat("#.##").format(p.calcularCusto())));

    }
}