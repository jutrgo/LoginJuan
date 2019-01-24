package es.ulpgc.juantrujillo.loginproyectosavanzados.register.presenter;

import es.ulpgc.juantrujillo.loginproyectosavanzados.register.events.RegisterEvent;
import es.ulpgc.juantrujillo.loginproyectosavanzados.register.model.IModelRegister;
import es.ulpgc.juantrujillo.loginproyectosavanzados.register.model.ModelRegister;
import es.ulpgc.juantrujillo.loginproyectosavanzados.register.view.IViewRegister;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class PresenterRegister implements IPresenterRegister{

    private IViewRegister view;
    private IModelRegister model;
    private EventBus eventBus;

    public PresenterRegister(IViewRegister activity) {
        this.view = activity;
        this.model = new ModelRegister();
        this.eventBus = EventBus.getDefault();
    }


    @Override
    public void onCreate(){
        eventBus.register(this);
    }

    @Override
    public void onStop() {
        eventBus.unregister(this);
    }

    @Override
    public void register(String email, String password, String name) {
        model.register(email, password, name);
    }


    @Subscribe
    @Override
    public void onEventLoginThread(RegisterEvent event) {
        view.hideProgress();
        switch (event.getEventType()){
            case RegisterEvent.ON_REGISTER_SUCESS:
                view.toMenu();
                break;
            case  RegisterEvent.ON_REGISTER_ERROR:
                view.showError();
                view.clearForm();
                break;
        }
    }
}