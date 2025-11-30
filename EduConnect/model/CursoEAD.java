package model;

public class CursoEAD extends Curso{
    private String plataforma;

    public CursoEAD(String nome, String codigo, int cargaHoraria, String plataforma) {
        super(nome, codigo, cargaHoraria);
        this.plataforma = plataforma;
    }

    @Override
    public void detalharCurso() {
        super.detalharCurso();
        System.out.println("Modalidade: EAD");
        System.out.println("Sala: " + plataforma);
    }
}
