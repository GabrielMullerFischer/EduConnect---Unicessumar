package repository;

import model.Aluno;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlunoRepositorio {
    private List<Aluno> alunos = new ArrayList<>();

    public Aluno salvar(Aluno aluno) {
        alunos.add(aluno);
        return aluno;
    }

    public void remove(Aluno aluno){
        alunos.remove(aluno);
    }

    public List<Aluno> buscarTodos() {
        return new ArrayList<>(alunos);
    }

    public Optional<Aluno> buscarPorMatricula(String matricula) {
        return alunos.stream().filter(a -> a.getMatricula().equalsIgnoreCase(matricula)).findFirst();
    }
}
