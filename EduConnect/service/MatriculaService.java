package service;

import repository.AlunoRepositorio;

public class MatriculaService {

    private AlunoRepositorio alunoRepositorio;

    public MatriculaService(AlunoRepositorio alunoRepositorio) {
        this.alunoRepositorio = alunoRepositorio;
    }
    public String gerarNovaMatricula() {
        long sequencial = 2500000L; // Começa a partir de 2500000
        String novaMatricula;
        do {
            novaMatricula = String.format("%07d", sequencial++); // Formata para 7 dígitos, preenchendo com zeros à esquerda se necessário
        } while (alunoRepositorio.buscarPorMatricula(novaMatricula).isPresent());
        return novaMatricula;
    }
}
