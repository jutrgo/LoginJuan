package es.ulpgc.juantrujillo.loginproyectosavanzados.main.presenter;

import android.util.Log;

import es.ulpgc.juantrujillo.loginproyectosavanzados.main.events.MainEvent;
import es.ulpgc.juantrujillo.loginproyectosavanzados.main.model.IModelMain;
import es.ulpgc.juantrujillo.loginproyectosavanzados.main.model.MainInteractorImpl;
import es.ulpgc.juantrujillo.loginproyectosavanzados.main.view.IViewMain;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class MainPresenterImpl implements IPresenterMain {

    private IModelMain mainInteractor;
    private IViewMain mainView;
    private EventBus eventBus;

    private static final String TAG = "ValorDesLogueo";


    public MainPresenterImpl(IViewMain mainView){
        mainInteractor = new MainInteractorImpl();
        this.mainView = mainView;
        this.eventBus = EventBus.getDefault();
    }

    @Override
    public void cerrarSesion() {
        mainInteractor.cerrarSesion();
    }

    @Override
    public void obtenerUsuario(String email){
        mainInteractor.obtenerUsuario(email);
    }

    @Override
    public void eliminarUsuario(String id){
        mainInteractor.eliminarUsuario(id);
    }

    @Override
    public void actualizarDatos(String id, String email, String username){
        mainInteractor.actualizarDatos(id, email, username);
    }

    @Override
    public void onStart() {
        eventBus.register(this);
    }

    @Override
    public void onStop() {
        //mainView = null;
        eventBus.unregister(this);
    }


    @Override
    @Subscribe
    public void onEventLoginThread(MainEvent event) {
        mainView.ocultarCargando();
        Log.d(TAG, "valor:" + event.toString());
        switch (event.getEventType()) {
            case MainEvent.ON_LOG_OUT:
                mainView.irALogin();
                break;
            case MainEvent.ON_OBTENER_DATOS:
                mainView.pintarUsuario(event.getUsuario());
                break;
            case MainEvent.ON_OBTENER_DATOS_ERROR:
                mainView.errorObtenerDatos();
                break;
            case MainEvent.ON_BORRAR_USUARIO_CORRECTO:
                mainView.eliminarUsuarioSatisfactorio();
                break;
            case MainEvent.ON_BORRAR_USUARIO_ERROR:
                mainView.eliminarUsuarioError();
                break;
            case MainEvent.ON_DATOS_ACTUALIZADOS_CORRECTO:
                mainView.mensajeActualizarDatos();
                mainView.pintarUsuario(event.getUsuario());
                break;
            case MainEvent.ON_DATOS_ACTUALIZADOS_ERROR:
                mainView.errorObtenerDatos();
                break;

        }
    }
}
