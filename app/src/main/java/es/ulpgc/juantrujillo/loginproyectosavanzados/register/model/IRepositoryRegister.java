package es.ulpgc.juantrujillo.loginproyectosavanzados.register.model;

public interface IRepositoryRegister {

    void register(String email, String password, String name);
}
