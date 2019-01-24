package es.ulpgc.juantrujillo.loginproyectosavanzados.login.view;

public interface IViewLogin {

    void showProgress();
    void hideProgress();
    void showErrorLogin();
    void cleanForm();


    void toMenu();
    void showPassSent();
    void showErrorPassSent();
    void toRegister();
}
