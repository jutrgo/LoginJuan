package es.ulpgc.juantrujillo.loginproyectosavanzados.login.model;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import es.ulpgc.juantrujillo.loginproyectosavanzados.login.events.LoginEvents;

import org.greenrobot.eventbus.EventBus;

public class RepositoryLogin implements IRepositotyLogin {

    private FirebaseAuth mAuth;

    public RepositoryLogin() {
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void doLogin(String email, String password) {
        try {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        postEvent(LoginEvents.ON_LOG_IN_SUCCESS);
                    } else {
                        postEvent(LoginEvents.ON_LOG_IN_ERROR);
                    }
                }
            });
        }catch (Exception e){
            postEvent(LoginEvents.ON_LOG_IN_ERROR);
        }
    }

    @Override
    public void forgotPassword(String email) {
        try{
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        postEvent(LoginEvents.ON_RESET_PASSWORD_SUCESS);
                    } else {
                        postEvent(LoginEvents.ON_RESET_PASSWORD_ERROR);
                    }
                }
            });
        } catch (Exception e){
            postEvent(LoginEvents.ON_RESET_PASSWORD_ERROR);
        }
    }

    private void postEvent(int type){
        LoginEvents loginEvents = new LoginEvents();
        loginEvents.setEventType(type);
        EventBus.getDefault().post(loginEvents);
    }
}
