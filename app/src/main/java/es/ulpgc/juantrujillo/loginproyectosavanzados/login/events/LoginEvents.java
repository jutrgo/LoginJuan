package es.ulpgc.juantrujillo.loginproyectosavanzados.login.events;

public class LoginEvents {

    public final static int ON_LOG_IN_ERROR = 0;

    public final static int ON_LOG_IN_SUCCESS = 1;

    public final static int ON_RESET_PASSWORD_ERROR = 2;

    public final static int ON_RESET_PASSWORD_SUCESS = 3;

    private int eventType;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

}
