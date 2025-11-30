package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Turma;

public class TurmaRepositorio {
    private List<Turma> turmas = new ArrayList<>();
    private long proximoId = 1;

    public Turma salvar(Turma turma) {
        turma.setId(proximoId++);
        turmas.add(turma);
        return turma;
    }

    public Optional<Turma> buscarPorId(long id) {
        return turmas.stream().filter(t -> t.getId() == id).findFirst();
    }

    public List<Turma> buscarTodos() {
        return new ArrayList<>(turmas);
    }

    public Optional<Turma> buscarPorCodigo(String codigo) {
        return turmas.stream().filter(t -> t.getCodigo().equalsIgnoreCase(codigo)).findFirst();
    }
}
