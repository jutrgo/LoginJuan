package es.ulpgc.juantrujillo.loginproyectosavanzados.register.events;


public class RegisterEvent {

    public final static int ON_REGISTER_SUCESS = 1;
    public final static int ON_REGISTER_ERROR = 2;
    private int eventType;

    public int getEventType(){
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

}
