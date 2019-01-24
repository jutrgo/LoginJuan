package es.ulpgc.juantrujillo.loginproyectosavanzados.register.presenter;


import es.ulpgc.juantrujillo.loginproyectosavanzados.register.events.RegisterEvent;

public interface IPresenterRegister {

    void register(String email, String password, String name);

    void onCreate();
    void onStop();
    void onEventLoginThread(RegisterEvent event);
}
