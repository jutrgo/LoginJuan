package es.ulpgc.juantrujillo.loginproyectosavanzados.main.model;

public interface IRepositoryMain {
    void cerrarSesion();
    void obtenerUsuario(final String email);
    void eliminarUsuario(final String id);
    void actualizarDatos(String id, String email, String username);

}
