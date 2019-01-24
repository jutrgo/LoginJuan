package es.ulpgc.juantrujillo.loginproyectosavanzados.login.presenter;


import es.ulpgc.juantrujillo.loginproyectosavanzados.login.events.LoginEvents;

public interface IPresenterLogin {

    void onCreate();
    void onDestroy();
    void toLogin(String email, String password);
    void forgotPassword(String email);
    void onEventLoginThread(LoginEvents events);
}