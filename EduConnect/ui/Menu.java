package ui;

import java.util.Scanner;

import service.RelatorioService;

public class Menu {
    private Scanner scanner;
    private RelatorioService relatorioService;

    public Menu(RelatorioService relatorioService) {
        this.scanner = new Scanner(System.in);
        this.relatorioService = relatorioService;
    }

    public void menuRelatoriosGerais() {
    int opcao;
        do{
            System.out.println("\n - Relatorios - ");
            System.out.println("1. Relatorio de Alunos");
            System.out.println("2. Relatorio de Professores");
            System.out.println("3. Relatorio de Cursos");
            System.out.println("4. Gerar Relatorio Geral");
            System.out.println("5. Sair");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(scanner.nextLine());

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
        }while(opcao != 5);
    }
}
