package es.ulpgc.juantrujillo.loginproyectosavanzados.login.presenter;

import es.ulpgc.juantrujillo.loginproyectosavanzados.login.events.LoginEvents;
import es.ulpgc.juantrujillo.loginproyectosavanzados.login.model.IModelLogin;
import es.ulpgc.juantrujillo.loginproyectosavanzados.login.model.ModelLogin;
import es.ulpgc.juantrujillo.loginproyectosavanzados.login.view.IViewLogin;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class PresenterLogin implements IPresenterLogin {

    private IViewLogin view;
    private IModelLogin model;
    private EventBus eventBus;

    public PresenterLogin(IViewLogin view){
        this.view = view;
        model = new ModelLogin();
        this.eventBus = EventBus.getDefault();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        //view = null;
        eventBus.unregister(this);
    }

    @Override
    public void toLogin(String email, String password) {
        model.doLogin(email, password);
    }

    @Override
    public void forgotPassword(String email) {
        model.forgotPassword(email);
    }

    @Subscribe
    @Override
    public void onEventLoginThread(LoginEvents events) {
        view.hideProgress();
        switch (events.getEventType()){
            case LoginEvents.ON_LOG_IN_SUCCESS:
                view.toMenu();
                break;
            case LoginEvents.ON_LOG_IN_ERROR:
                view.showErrorLogin();
                view.cleanForm();
                break;
            case LoginEvents.ON_RESET_PASSWORD_SUCESS:
                view.showPassSent();
                break;
            case LoginEvents.ON_RESET_PASSWORD_ERROR:
                view.showErrorPassSent();
                view.cleanForm();
                break;
        }
    }


}
