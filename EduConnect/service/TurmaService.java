package service;

import model.Aluno;
import model.Turma;
import repository.AlunoRepositorio;
import repository.TurmaRepositorio;

public class TurmaService {

    private TurmaRepositorio turmaRepositorio;
    private AlunoRepositorio alunoRepositorio;

    public TurmaService(TurmaRepositorio turmaRepositorio, AlunoRepositorio alunoRepositorio){
        this.turmaRepositorio = turmaRepositorio;
        this.alunoRepositorio = alunoRepositorio;
    }

    public void matricularAlunoEmTurma(String matriculaAluno, String codigoTurma) {
        Turma turma = turmaRepositorio.buscarPorCodigo(codigoTurma)
                .orElseThrow(() -> new RuntimeException("Turma com codigo " + codigoTurma + " nao encontrada!"));

        Aluno aluno = alunoRepositorio.buscarPorMatricula(matriculaAluno)
                .orElseThrow(() -> new RuntimeException("Aluno com matrícula " + matriculaAluno + " nao encontrado!"));

        if (turma.getAlunos().contains(aluno)) {
            throw new RuntimeException("Aluno " + aluno.getNome() + " ja esta matriculado nesta turma!");
        }

        turma.addAluno(aluno);
        System.out.println("Aluno " + aluno.getNome() + " matriculado na turma " + turma.getCodigo() + " com sucesso!");
    }

    public String consultarResumoTurma(String codigoTurma) {
        Turma turma = turmaRepositorio.buscarPorCodigo(codigoTurma)
                .orElseThrow(() -> new RuntimeException("Turma com codigo " + codigoTurma + " nao encontrada!"));
        
        turma.mostrarResumo();
        return ""; 
    }
}
