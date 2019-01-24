package es.ulpgc.juantrujillo.loginproyectosavanzados.login.model;

public interface IRepositotyLogin {

    void doLogin(String email, String password);
    void forgotPassword(String email);
}
