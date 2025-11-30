package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Administrador;

public class AdministradorRepositorio {
    private List<Administrador> administradores = new ArrayList<>();
    private long proximoId = 1;

    public Administrador salvar(Administrador administrador) {
        administrador.setId(proximoId++);
        administradores.add(administrador);
        return administrador;
    }

    public Optional<Administrador> buscarPorId(long id) {
        return administradores.stream().filter(a -> a.getId() == id).findFirst();
    }

    public List<Administrador> buscarTodos() {
        return new ArrayList<>(administradores);
    }
}
