package es.ulpgc.juantrujillo.loginproyectosavanzados.main.model;

public class MainInteractorImpl implements IModelMain {

    private IRepositoryMain mainRepository;

    public MainInteractorImpl(){
        mainRepository =  new MainRepositoryImpl();
    }


    @Override
    public void cerrarSesion() {
        mainRepository.cerrarSesion();
    }

    @Override
    public void obtenerUsuario(String email){
        mainRepository.obtenerUsuario(email);
    }

    @Override
    public void eliminarUsuario(String id){
        mainRepository.eliminarUsuario(id);
    }

    @Override
    public void actualizarDatos(String id, String email, String username){
        mainRepository.actualizarDatos(id, email, username);
    }
}
