package es.ulpgc.juantrujillo.loginproyectosavanzados.main.events;

import es.ulpgc.juantrujillo.loginproyectosavanzados.user.User;


public class MainEvent {

    public final static int ON_LOG_OUT = 1;
    public final static int ON_OBTENER_DATOS = 2;
    public final static int ON_OBTENER_DATOS_ERROR = 3;

    public final static int ON_BORRAR_USUARIO_CORRECTO = 4;
    public final static int ON_BORRAR_USUARIO_ERROR = 5;
    public final static int ON_DATOS_ACTUALIZADOS_CORRECTO = 6;
    public final static int ON_DATOS_ACTUALIZADOS_ERROR = 7;







    private int eventType;
    private User usuario;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
}
