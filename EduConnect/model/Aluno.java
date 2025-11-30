package model;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends Usuario implements Autenticacao{
    private String matricula;
    private Curso curso;
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    public Aluno(String nome, String login, String senha, String matricula, Curso curso) {
        super(nome, login, senha);
        this.matricula = matricula;
        this.curso = curso;
    }

    public void addAvaliacao(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
    }

    @Override
    public boolean autenticar(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }

    @Override
    public void gerarRelatorio() {
        System.out.println("\n - Relatorio do Aluno - ");
        System.out.println("Nome: " + nome);
        System.out.println("Matricula: " + matricula);
        System.out.println("Curso: " + curso.getNome());
        for(Avaliacao avaliacao : avaliacoes){
            System.out.println(avaliacao.getDescricao() + ": " + avaliacao.getNota());
        }
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public String getMatricula() {
        return matricula;
    }

    public Curso getCurso() {
        return curso;
    }

    @Override
    public String toString() {
        return "Aluno [nome=" + nome + ", matricula=" + matricula + ", login=" + login + ", curso=" + curso + ", senha="
                + senha + "]";
    }
}
