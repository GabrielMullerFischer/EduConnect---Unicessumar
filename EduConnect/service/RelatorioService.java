package service;

import model.Aluno;
import model.Curso;
import model.Professor;
import repository.AlunoRepositorio;
import repository.CursoRepositorio;
import repository.ProfessorRepositorio;


public class RelatorioService {
    private AlunoRepositorio alunoRepositorio;
    private ProfessorRepositorio professorRepositorio;
    private CursoRepositorio cursoRepositorio;



    public RelatorioService(AlunoRepositorio alunoRepositorio, ProfessorRepositorio professorRepositorio, CursoRepositorio cursoRepositorio) {
        this.alunoRepositorio = alunoRepositorio;
        this.professorRepositorio = professorRepositorio;
        this.cursoRepositorio = cursoRepositorio;
    }

    public void gerarRelatorioAlunos() {
        System.out.println("\n - RELATORIO DE ALUNOS - ");
        if (alunoRepositorio.buscarTodos().isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        }
        for (Aluno aluno : alunoRepositorio.buscarTodos()) {
            aluno.gerarRelatorio();
        }
    }

    public void gerarRelatorioProfessores() {
        System.out.println("\n - RELATORIO DE PROFESSORES - ");
        if (professorRepositorio.buscarTodos().isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
        }
        for (Professor professor : professorRepositorio.buscarTodos()) {
            professor.gerarRelatorio();
        }
    }

    public void gerarRelatorioCurso() {
        System.out.println("\n - RELATORIO DE CURSOS - ");
        for(Curso curso : cursoRepositorio.buscarTodos()){
            curso.gerarRelatorio();
        }
    }

    public void gerarRelatorioGeral() {
        System.out.println("\n - RELATORIO GERAL - ");
        gerarRelatorioAlunos();
        gerarRelatorioProfessores();
        gerarRelatorioCurso();
    }
}
