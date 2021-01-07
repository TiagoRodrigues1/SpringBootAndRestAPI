package pt.ufp.inf.esof.projeto.modelos;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProjetoTest {

    @Test
    void calcularTempo() {
        Projeto p = new Projeto();
        TarefaPrevista t = new TarefaPrevista();
        TarefaPrevista t2 = new TarefaPrevista();
        TarefaEfetiva e = new TarefaEfetiva();
        TarefaEfetiva e1 = new TarefaEfetiva();

        e.registarTempo(10,5); //Realizou 5% da Tarefa em 10Hrs
        e1.registarTempo(5,100);//Realizou 100% da Tarefa em 5hrs quando estaria programado para 20hrs

        t.setTempoPrevistoConlusao(10);
        t2.setTempoPrevistoConlusao(20);

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

        //System.out.println(p.calcularTempo());

        assertEquals(220,p.calcularTempo());

        //assertEquals(2375,p.calcularTempo());

    }

    @Test
    void calcularCusto() {
        Projeto p = new Projeto();
        TarefaPrevista t = new TarefaPrevista();
        TarefaPrevista t2 = new TarefaPrevista();
        TarefaEfetiva e = new TarefaEfetiva();
        TarefaEfetiva e1 = new TarefaEfetiva();

        e.registarTempo(10,5); //Realizou 5% da Tarefa em 10Hrs
        e1.registarTempo(5,100);//Realizou 100% da Tarefa em 5hrs quando estaria programado para 20hrs

        t.setTempoPrevistoConlusao(10);
        t2.setTempoPrevistoConlusao(20);

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

        assertEquals(9600,p.calcularCusto());



    }
}