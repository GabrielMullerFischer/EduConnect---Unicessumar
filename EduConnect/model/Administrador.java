package model;

public class Administrador extends Usuario implements Autenticacao{
    private String setor;

    public Administrador(String nome, String login, String senha, String setor) {
        super(nome, login, senha);
        this.setor = setor;
    }

    @Override
    public boolean autenticar(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }

    @Override
    public void gerarRelatorio() {
        System.out.println("\n Acesso restrito ");
    }

    public String getSetor() {
        return setor;
    }
}
