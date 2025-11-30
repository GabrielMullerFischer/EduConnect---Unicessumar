package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Curso;

public class CursoRepositorio {
    private List<Curso> cursos = new ArrayList<>();
    private long proximoId = 1;

    public Curso salvar(Curso curso) {
        curso.setId(proximoId++);
        cursos.add(curso);
        return curso;
    }

    public List<Curso> buscarTodos() {
        return new ArrayList<>(cursos);
    }

    public Optional<Curso> buscarPorCodigo(String codigo) {
        return cursos.stream().filter(c -> c.getCodigo().equalsIgnoreCase(codigo)).findFirst();
    }
}
