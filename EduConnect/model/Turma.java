package model;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    private String codigo;
    private Curso curso;
    private Professor professor;
    private List<Aluno> alunos;

    public Turma(String codigo, Curso curso, Professor professor) {
        this.codigo = codigo;
        this.curso = curso;
        this.professor = professor;
        this.alunos = new ArrayList<>();
    }

    public void addAluno(Aluno aluno){
        alunos.add(aluno);
    }

    public void removeAluno(String matricula){
        boolean removido = alunos.removeIf(aluno -> aluno.getMatricula() != null && aluno.getMatricula().equalsIgnoreCase(matricula));

        if(removido){
            System.out.println("O aluno foi removido da turma");
        }else{
            System.out.println("O aluno nao foi encontrado.");
        }
    }

    public void mostrarResumo() {
        System.out.println("\nResumo da Turma: " + this.codigo);
        System.out.println("Curso: " + this.curso.getNome());
        System.out.println("Professor: " + this.professor.getNome());
        System.out.println("Quantidade de Alunos: " + this.alunos.size());
        System.out.println("Alunos:");
        for(Aluno aluno : alunos){
            System.out.println("Nome: " + aluno.getNome() + " - Matricula: " +  aluno.getMatricula());
        }
    }

    public void atribuirAvaliacao(String nomeAluno, Avaliacao avaliacao) {
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equalsIgnoreCase(nomeAluno)) {
                aluno.addAvaliacao(avaliacao);
                System.out.println("Nota atribuida.");
                return;
            }
        }
        System.out.println("Aluno nao encontrado!");
    }

    public void listarAvaliacoes() {
        for (Aluno aluno : alunos) {
            System.out.println("\nAluno: " + aluno.getNome());
            for (Avaliacao av : aluno.getAvaliacoes()) {
                System.out.println(" - " + av);
            }
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public Curso getCurso() {
        return curso;
    }

    public Professor getProfessor() {
        return professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }
}
