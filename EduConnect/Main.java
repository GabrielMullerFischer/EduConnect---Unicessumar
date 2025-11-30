import model.*;
import repository.*;
import service.RelatorioService;
import ui.Menu;


public class Main {
    public static void main(String[] args) {
        AlunoRepositorio alunoRepositorio = new AlunoRepositorio();
        ProfessorRepositorio professorRepositorio = new ProfessorRepositorio();
        CursoRepositorio cursoRepositorio = new CursoRepositorio();

        RelatorioService relatorioService = new RelatorioService(alunoRepositorio, professorRepositorio, cursoRepositorio);
        Menu menu = new Menu(relatorioService);
        Curso cursoCC = new Curso("Engenharia de Computacao", "2212", 3200);
        Aluno aluno = new Aluno("Gabriel", "gabriel", "123", "203040", cursoCC);
        Aluno aluno2 = new Aluno("Pedro", "Pedro", "123", "203041", cursoCC);
        Professor professor = new Professor("Carlos", "carlos", "123", "Matematica", "123456");

        System.out.println(aluno);
        System.out.println(professor);
        System.out.println(cursoCC);

        Turma turma = new Turma("98765", cursoCC, professor);
        turma.addAluno(aluno);
        turma.addAluno(aluno2);
        turma.mostrarResumo();
        turma.removeAluno("203040");
        turma.mostrarResumo();
        System.out.println("\nAlunos da turma:");
        for(Aluno alunos : turma.getAlunos()){
            System.out.println(alunos);
        }

        Avaliacao prova1 = new Avaliacao("Prova 1");
        prova1.atribuirNota(9.5);

        turma.atribuirAvaliacao("Pedro", prova1);

        for(Avaliacao avaliacao : aluno2.getAvaliacoes()){
            System.out.println(avaliacao);
        }

        Administrador administrador = new Administrador("admin", "admin", "123", "Computacao");
        
        alunoRepositorio.salvar(aluno);
        alunoRepositorio.salvar(aluno2);
        professorRepositorio.salvar(professor);
        cursoRepositorio.salvar(cursoCC);


        menu.menuRelatoriosGerais();
    }
}
