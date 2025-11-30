package repository;

import model.Professor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfessorRepositorio {
    private List<Professor> professores = new ArrayList<>();
    private long proximoId = 1;

    public Professor salvar(Professor professor) {
        professor.setId(proximoId++);
        professores.add(professor);
        return professor;
    }

    public List<Professor> buscarTodos() {
        return new ArrayList<>(professores);
    }

    public Optional<Professor> buscarPorRegistro(String registro) {
        return professores.stream().filter(p -> p.getRegistro().equalsIgnoreCase(registro)).findFirst();
    }
}
