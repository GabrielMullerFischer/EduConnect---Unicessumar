package model;

public class Avaliacao {
    private double nota;
    private String descricao;

    public Avaliacao(String descricao) {
        this.nota = 0.0;
        this.descricao = descricao;
    }

    public void atribuirNota(double valor){
        if(valor >= 0 && valor <= 10){
            nota = valor;
        }else{
            System.out.println("Nota invalida, por favor digite uma nota de 0 a 10!");
        }
    }

    public double getNota() {
        return nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "\nAvaliacao\nDescricao: " + descricao + "\nNota: " + nota + "\n";
    }
}
