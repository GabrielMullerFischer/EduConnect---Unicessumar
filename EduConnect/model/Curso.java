package model;

public class Curso implements Relatorio{
    private long id;
    private String nome;
    private String codigo;
    private int cargaHoraria;

    public Curso(String nome, String codigo, int cargaHoraria) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
    }

    public void detalharCurso() {
        System.out.println("\tDetalhes do Curso");
        System.out.println("Nome: " + nome);
        System.out.println("Codigo: " + codigo);
        System.out.println("Carga Horária: " + cargaHoraria + " horas");
    }

    public void gerarRelatorio(){
        System.out.println("\n - Relatorio do Curso - ");
        System.out.println("Nome: " + nome);
        System.out.println("Codigo: " + codigo);
        System.out.println("Carga Horaria: " + cargaHoraria + " horas");
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    @Override
    public String toString() {
        return "Curso [nome=" + nome + ", codigo=" + codigo + ", cargaHoraria=" + cargaHoraria + "]";
    }
}
