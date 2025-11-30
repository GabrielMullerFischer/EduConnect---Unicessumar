import model.*;
import repository.*;
import service.MatriculaService;
import service.RelatorioService;
import service.TurmaService;
import ui.MenuUI;


public class Main {
    public static void main(String[] args) {
        AlunoRepositorio alunoRepositorio = new AlunoRepositorio();
        ProfessorRepositorio professorRepositorio = new ProfessorRepositorio();
        CursoRepositorio cursoRepositorio = new CursoRepositorio();
        TurmaRepositorio turmaRepositorio = new TurmaRepositorio();
        AdministradorRepositorio administradorRepositorio = new AdministradorRepositorio();
        TurmaService turmaService = new TurmaService(turmaRepositorio, alunoRepositorio);
        MatriculaService matriculaService = new MatriculaService(alunoRepositorio);


        RelatorioService relatorioService = new RelatorioService(alunoRepositorio, professorRepositorio, cursoRepositorio);
        MenuUI menu = new MenuUI(turmaService, relatorioService, matriculaService, alunoRepositorio, professorRepositorio, cursoRepositorio, administradorRepositorio,turmaRepositorio );
        Curso cursoCC = new CursoEAD("Ciencia da Computacao", "2212", 3200, "EduConnect");
        Curso cursoEC = new CursoPresencial("Engenharia de Computacao", "2222", 3600, "B321"); 
        Aluno aluno = new Aluno("Gabriel", "gabriel", "123", "203040", cursoCC);
        Aluno aluno2 = new Aluno("Pedro", "Pedro", "123", "203041", cursoCC);
        Aluno aluno3 = new Aluno("Lucas", "lucas", "123", "203042", cursoEC);
        Aluno aluno4 = new Aluno("Mariana", "mariana", "123", "203043", cursoEC);
        Professor professor = new Professor("Carlos", "carlos", "123", "Matematica", "123456");
        Professor professor2 = new Professor("Fernanda", "fernanda", "123", "Fisica", "654987");

        System.out.println(aluno);
        System.out.println(professor);
        System.out.println(cursoCC);
        System.out.println(professor2);
        System.out.println(aluno3);
        System.out.println(aluno4);

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

        Administrador administrador = new Administrador("ADMIN", "admin", "admin", "Computacao");
        
        alunoRepositorio.salvar(aluno);
        alunoRepositorio.salvar(aluno2);        
        alunoRepositorio.salvar(aluno3);
        alunoRepositorio.salvar(aluno4);
        professorRepositorio.salvar(professor);
        professorRepositorio.salvar(professor2);
        cursoRepositorio.salvar(cursoCC);
        cursoRepositorio.salvar(cursoEC);
        administradorRepositorio.salvar(administrador);


        menu.exibirMenu();
    }
}
