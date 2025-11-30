package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Aluno;

public class AlunoRepositorio {
    private List<Aluno> alunos = new ArrayList<>();
    private long proximoId = 1;

    public Aluno salvar(Aluno aluno) {
        aluno.setId(proximoId++);
        alunos.add(aluno);
        return aluno;
    }

    public Optional<Aluno> buscarPorId(long id) {
        return alunos.stream().filter(a -> a.getId() == id).findFirst();
    }

    public List<Aluno> buscarTodos() {
        return new ArrayList<>(alunos);
    }

    public Optional<Aluno> buscarPorMatricula(String matricula) {
        return alunos.stream().filter(a -> a.getMatricula().equalsIgnoreCase(matricula)).findFirst();
    }
}
