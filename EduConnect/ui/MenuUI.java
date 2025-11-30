package ui;

import java.util.Scanner;

import model.*;
import repository.*;
import service.*;

public class MenuUI {
    
    private Scanner scanner;
    private TurmaService turmaService;
    private RelatorioService relatorioService;
    private MatriculaService matriculaService;
    private AlunoRepositorio alunoRepositorio;
    private ProfessorRepositorio professorRepositorio;
    private CursoRepositorio cursoRepositorio;
    private AdministradorRepositorio administradorRepositorio;
    private TurmaRepositorio turmaRepositorio;


    private Usuario usuarioLogado = null;

    public MenuUI(TurmaService turmaService, RelatorioService relatorioService, MatriculaService matriculaService,
                  AlunoRepositorio alunoRepositorio, ProfessorRepositorio professorRepositorio,
                  CursoRepositorio cursoRepositorio,
                  AdministradorRepositorio administradorRepositorio,
                  TurmaRepositorio turmaRepositorio) {

        this.scanner = new Scanner(System.in);
        this.turmaService = turmaService;
        this.relatorioService = relatorioService;
        this.matriculaService = matriculaService;
        this.alunoRepositorio = alunoRepositorio;
        this.professorRepositorio = professorRepositorio;
        this.cursoRepositorio = cursoRepositorio;
        this.administradorRepositorio = administradorRepositorio;
        this.turmaRepositorio = turmaRepositorio;
    }

    private String lerTextoObrigatorio(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine();
            if (entrada != null && !entrada.isBlank()) {
                return entrada.trim();
            }
            System.err.println("Erro: Este campo e obrigatorio!");
        }
    }

    private int lerInteiro(String mensagem) {
    while (true) {
        try {
            System.out.print(mensagem);
            String entrada = scanner.nextLine();
            return Integer.parseInt(entrada.trim());   
        } catch (NumberFormatException e) {
            System.err.println("Erro: Digite um valor inteiro!");
        }
    }
}

    public void exibirMenu() {
        boolean executando = true;
        while (executando) {
            if (usuarioLogado == null) {
                // Menu de Login
                System.out.println("\n - EduConnect - Acesso ao Sistema - ");
                System.out.println("1. Login");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opcao: ");

                try {
                    int opcao = Integer.parseInt(scanner.nextLine());
                    if (opcao == 1) {
                        fazerLogin();
                    } else if (opcao == 0) {
                        executando = false;
                    } else {
                        System.err.println("Opção invalida.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Entrada invalida. Digite um numero!");
                }
            } else {
                exibirMenuPrincipalLogado();
            }
        }
        System.out.println("Sistema EduConnect encerrado. Ate a proxima! :)");
        scanner.close();
    }

    private void exibirMenuPrincipalLogado() {
        System.out.println("\n - Bem-vindo(a), " + usuarioLogado.getNome() + " - ");

        if (usuarioLogado instanceof Administrador) {
            menuAdministrador();
        } else if (usuarioLogado instanceof Professor) {
            menuProfessor();
        } else if (usuarioLogado instanceof Aluno) {
            menuAluno();
        } else {
            System.err.println("Erro: Perfil de usuario nao encontrado.");
            fazerLogout();
        }
    }

    private void fazerLogin() {
        System.out.println("\n - Autenticacao - ");
        System.out.print("Digite o login: ");
        String login = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        Usuario usuario = alunoRepositorio.buscarTodos().stream()
                .filter(a -> a.getLogin().equals(login))
                .findFirst().orElse(null);

        if (usuario != null && usuario.autenticar(login, senha)) {
            this.usuarioLogado = usuario;
            System.out.println("Aluno autenticado!");
            return;
        }

        usuario = professorRepositorio.buscarTodos().stream()
                .filter(p -> p.getLogin().equals(login))
                .findFirst().orElse(null);

        if (usuario != null && usuario.autenticar(login, senha)) {
            this.usuarioLogado = usuario;
            System.out.println("Professor autenticado!");
            return;
        }

        usuario = administradorRepositorio.buscarTodos().stream().filter(adm -> adm.getLogin().equals(login)).findFirst().orElse(null);
        if (usuario != null && usuario.autenticar(login, senha)) {
            this.usuarioLogado = usuario;
            System.out.println("Administrador autenticado!");
            return;
        }

        System.err.println("Login ou senha incorreto!");
    }

    private void fazerLogout() {
        System.out.println("Fazendo logout, " + usuarioLogado.getNome() + "...");
        this.usuarioLogado = null;
    }

    private void menuAdministrador() {
        System.out.println("\n - Menu do Administrador - ");
        System.out.println("1. Adicionar Aluno");
        System.out.println("2. Adicionar Professor");
        System.out.println("3. Adicionar Novo Curso (EAD/Presencial)");
        System.out.println("4. Iniciar Turma");
        System.out.println("5. Matricular Aluno em Turma");
        System.out.println("6. Gerar Relatorios (Geral)");
        System.out.println("9. Logout");
        System.out.print("Escolha: ");

        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            switch (opcao) {
                case 1: adicionarAluno(); break;
                case 2: adicionarProfessor(); break;
                case 3: adicionarCurso(); break;
                case 4: iniciarTurma(); break;
                case 5: matricularAlunoEmTurma(); break;
                case 6: menuRelatoriosGerais(); break;
                case 9: fazerLogout(); break;
                default: System.err.println("Opcao invalida.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Entrada invalida.");
        }
    }

    private void menuProfessor() {
        System.out.println("\n - Menu do Professor - ");
        System.out.println("1. Matricular Aluno em Turma");
        System.out.println("2. Ver Resumo de Turma");
        System.out.println("3. Ver Relatório de Cursos");        
        System.out.println("9. Logout");
        System.out.print("Escolha: ");

        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            switch (opcao) {
                case 1: matricularAlunoEmTurma(); break;
                case 2: verResumoTurma(); break;
                case 3: relatorioService.gerarRelatorioCurso(); break;                
                case 9: fazerLogout(); break;
                default: System.err.println("Opcao invalida.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Entrada invalida.");
        }
    }

    private void menuAluno() {
        System.out.println("\n - Portal do Aluno -");
        System.out.println("1. Ver Meu Relatório (Notas)");
        System.out.println("2. Ver Detalhes do Meu Curso");

        System.out.println("9. Logout");
        System.out.print("Escolha: ");
        
        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            Aluno alunoLogado = (Aluno) usuarioLogado;

            switch (opcao) {
                case 1:
                    alunoLogado.gerarRelatorio();
                    break;
                case 2:
                    alunoLogado.getCurso().detalharCurso();
                    break;
                case 9:
                    fazerLogout();
                    break;
                default:
                    System.err.println("Opcao invalida.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Entrada invalida.");
        }
    }

    private void adicionarCurso() {
        System.out.println("\n - Adicionar Novo Curso - ");
        try {
            String nome = lerTextoObrigatorio("Nome do Curso: ");
            String codigo = lerTextoObrigatorio("Código do Curso (ex: CC01): ");
            int cargaHoraria;
            do {
            cargaHoraria = lerInteiro("Carga Horária (horas): ");
            if (cargaHoraria <= 0) {
                System.err.println("Erro: A carga horária deve ser maior que zero.");
            }
            } while (cargaHoraria <= 0);

            System.out.println("Qual a modalidade do curso?");
            System.out.println("1. Presencial");
            System.out.println("2. EAD");

            int tipo;
            do {
            tipo = lerInteiro("Escolha (1 ou 2): ");
            if (tipo != 1 && tipo != 2) {
                System.err.println("Erro: Opção inválida. Digite apenas 1 ou 2.");
            }
            } while (tipo != 1 && tipo != 2);

            Curso novoCurso;

            if (tipo == 1) {
                String sala = lerTextoObrigatorio("Digite a Sala/Laboratório: ");
                novoCurso = new CursoPresencial(nome, codigo, cargaHoraria, sala);
            } else{
                String plataforma = lerTextoObrigatorio("Digite a Plataforma/Link: ");
                novoCurso = new CursoEAD(nome, codigo, cargaHoraria, plataforma);
            }

            cursoRepositorio.salvar(novoCurso);
            System.out.println("Curso " + nome + " (Modalidade: " + (tipo == 1 ? "Presencial" : "EAD") + ") cadastrado com sucesso!");

        } catch (Exception e) {
            System.err.println("Erro ao criar curso: " + e.getMessage());
        }
    }

    private void iniciarTurma() {
        System.out.println("\n - Iniciar Nova Turma - ");

        try {
            String codigoTurma = lerTextoObrigatorio("Defina o Código da Turma (ex: 2024-1-CC): ");
            
            System.out.println("--- Cursos Disponíveis ---");
            cursoRepositorio.buscarTodos().forEach(c -> System.out.println(c.getCodigo() + " - " + c.getNome()));
            
            String codigoCurso = lerTextoObrigatorio("Digite o Código do Curso para esta turma: ");
            Curso curso = cursoRepositorio.buscarPorCodigo(codigoCurso).orElseThrow(() -> new RuntimeException("Curso não encontrado. Crie o curso antes de iniciar a turma."));

            String registroProf = lerTextoObrigatorio("Digite o Registro do Professor Responsável: ");
            Professor professor = professorRepositorio.buscarPorRegistro(registroProf).orElseThrow(() -> new RuntimeException("Professor não encontrado."));

            Turma novaTurma = new Turma(codigoTurma, curso, professor);
            turmaRepositorio.salvar(novaTurma);
            
            System.out.println("Turma " + codigoTurma + " iniciada com sucesso!");
            System.out.println("Modalidade: " + (curso instanceof CursoEAD ? "EAD" : "Presencial"));

        } catch (Exception e) {
            System.err.println("Erro ao iniciar turma: " + e.getMessage());
        }
    }

    private void matricularAlunoEmTurma() {
        System.out.println("\tMatricular Aluno em Turma");
        try {
            System.out.print("Digite a matricula do aluno: ");
            String matricula = scanner.nextLine();
            System.out.print("Digite o codigo da turma: ");
            String codigoTurma = scanner.nextLine();

            turmaService.matricularAlunoEmTurma(matricula, codigoTurma);
        } catch (Exception e) {
            System.err.println("Erro ao matricular aluno: " + e.getMessage());
        }
    }

    private void verResumoTurma() {
        System.out.print("Digite o codigo da turma: ");
        String codigoTurma = scanner.nextLine();
        System.out.println(turmaService.consultarResumoTurma(codigoTurma));
    }

    private void adicionarAluno() {
        try {
            System.out.println("\n - Adicionar Novo Aluno - ");
            String nome = lerTextoObrigatorio("Nome: ");
            String login = lerTextoObrigatorio("Login: ");
            String senha = lerTextoObrigatorio("Senha: ");
            String codCurso = lerTextoObrigatorio("Codigo do Curso: ");

            Curso curso = cursoRepositorio.buscarPorCodigo(codCurso).orElseThrow(() -> new RuntimeException("Curso com codigo " + codCurso + " nao encontrado."));

            String novaMatricula = matriculaService.gerarNovaMatricula();

            Aluno novoAluno = new Aluno(nome, login, senha, novaMatricula, curso);
            alunoRepositorio.salvar(novoAluno);
            System.out.println("Aluno " + nome + " cadastrado com sucesso! Matricula gerada: " + novaMatricula);

        } catch (Exception e) {
            System.err.println("Erro ao cadastrar aluno: " + e.getMessage());
        }
    }

    private void adicionarProfessor() {
        try {
            System.out.println("\n - Adicionar Novo Professor - ");
            String nome = lerTextoObrigatorio("Nome: ");
            String login = lerTextoObrigatorio("Login: ");
            String senha = lerTextoObrigatorio("Senha: ");
            String especialidade = lerTextoObrigatorio("Especialidade: ");
            String registro = lerTextoObrigatorio("Registro: ");

            Professor novoProfessor = new Professor(nome, login, senha, especialidade, registro);
            professorRepositorio.salvar(novoProfessor);
            System.out.println("Professor " + nome + " cadastrado com sucesso!");

        } catch (Exception e) {
            System.err.println("Erro ao cadastrar professor: " + e.getMessage());
        }
    }

    private void menuRelatoriosGerais() {
        System.out.println("\n - Relatorios - ");
        System.out.println("1. Relatorio de Alunos");
        System.out.println("2. Relatorio de Professores");
        System.out.println("3. Relatorio de Cursos");
        System.out.println("4. Relatorio de Turmas");
        System.out.print("Escolha: ");
        int opcao = Integer.parseInt(scanner.nextLine());

        switch (opcao) {
            case 1:
                relatorioService.gerarRelatorioAlunos();
                break;
            case 2:
                relatorioService.gerarRelatorioProfessores();
                break;
            case 3:
                relatorioService.gerarRelatorioCurso();
                break;
            case 4:
                relatorioService.gerarRelatorioGeral();
                break;
            default:
                System.err.println("Opcao invalida.");
        }
    }
}
