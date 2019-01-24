package es.ulpgc.juantrujillo.loginproyectosavanzados.main.presenter;

import es.ulpgc.juantrujillo.loginproyectosavanzados.main.events.MainEvent;

public interface IPresenterMain {
    void cerrarSesion();
    void onStart();
    void onStop();
    void onEventLoginThread(MainEvent event);
    void obtenerUsuario(String email);
    void eliminarUsuario(String id);
    void actualizarDatos(String id, String email, String username);

}
