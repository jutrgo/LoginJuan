package es.ulpgc.juantrujillo.loginproyectosavanzados.login.model;

public class ModelLogin implements IModelLogin {


    private RepositoryLogin repository;


    public ModelLogin() {
        repository = new RepositoryLogin();

    }

    @Override
    public void doLogin(String username, String password) {
        repository.doLogin(username, password);
    }

    @Override
    public void forgotPassword(String username) {
        repository.forgotPassword(username);
    }
}
