package pt.ufp.inf.esof.projeto.modelos;


import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class ProjetoTest {

    @Test
    void calcularTempo() {
        Projeto p = new Projeto();
        TarefaPrevista t = new TarefaPrevista();
        TarefaPrevista t2 = new TarefaPrevista();
        TarefaEfetiva e = new TarefaEfetiva();
        TarefaEfetiva e1 = new TarefaEfetiva();
        TarefaEfetiva e2 = new TarefaEfetiva();
        e.registarTempoTrabalhado(10);
        e1.registarTempoTrabalhado(15);
        e2.registarTempoTrabalhado(20);
        t.setTempoPrevistoConlusao(10);
        t2.setTempoPrevistoConlusao(20);
        Empregado emp = new Empregado();
        emp.setCargo(Empregado.Cargo.ANALISTA_SENIOR);
        t2.setEmpregado(emp);
        t.adicionaTarefa(e);
        t2.adicionaTarefa(e2);
        t.adicionaTarefa(e1);
        p.adicionaTarefa(t);
        p.adicionaTarefa(t2);
        System.out.println(p.calcularTempo());
        System.out.println(p.calcularCusto());
        assertEquals(30,p.calcularTempo());

        //assertEquals(2375,p.calcularTempo().toHours());
        /*
        System.out.println(p.calcularTempo().toDaysPart());
        System.out.println(p.calcularTempo().toHoursPart());
        System.out.println(p.calcularTempo().toMinutesPart());
        */
    }

    @Test
    void calcularCusto() {
        Projeto p = new Projeto();
        TarefaPrevista t2 = new TarefaPrevista();
        TarefaPrevista t = new TarefaPrevista();
        t2.setTempoPrevistoConlusao(20);

        Empregado empregado = new Empregado();
        empregado.setCargo(Empregado.Cargo.DESENVOLVERDOR_JUNIOR);
        t.setEmpregado(empregado);
        Empregado emp = new Empregado();
        emp.setCargo(Empregado.Cargo.ANALISTA_SENIOR);
        t2.setEmpregado(emp);
        t.setTempoPrevistoConlusao(10);
        p.adicionaTarefa(t);
        p.adicionaTarefa(t2);
        assertEquals(1700,p.calcularCusto());

    }
}