package es.ulpgc.juantrujillo.loginproyectosavanzados.register.model;

public class ModelRegister implements IModelRegister{

    private IRepositoryRegister repository;

    public ModelRegister() {
        repository = new RepositoryRegister();
    }

    @Override
    public void register(String email, String password, String name) {
        repository.register(email, password, name);
    }
}