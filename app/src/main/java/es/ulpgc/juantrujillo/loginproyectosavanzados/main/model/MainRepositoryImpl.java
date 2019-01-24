package es.ulpgc.juantrujillo.loginproyectosavanzados.main.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import org.greenrobot.eventbus.EventBus;

import es.ulpgc.juantrujillo.loginproyectosavanzados.main.events.MainEvent;
import es.ulpgc.juantrujillo.loginproyectosavanzados.user.User;

public class MainRepositoryImpl implements IRepositoryMain {

    private static final String TAG = "BorrarCuenta";

    private FirebaseUser user;
    private DatabaseReference userUpdate;


    public MainRepositoryImpl() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        userUpdate = FirebaseDatabase.getInstance().getReference();

    }


    @Override
    public void cerrarSesion(){
        FirebaseAuth.getInstance().signOut();
        postEvent2(MainEvent.ON_LOG_OUT);
    }

    @Override
    public void obtenerUsuario(final String email){
        final DatabaseReference usuarioRef = FirebaseDatabase.getInstance().getReference();
        usuarioRef.child("usuarios").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    User usuario = new User();
                    usuario.setEmail(datas.child("email").getValue().toString());
                    usuario.setName(datas.child("name").getValue().toString());
                    usuario.setId(datas.child("id").getValue().toString());
                    postEvent(MainEvent.ON_OBTENER_DATOS, usuario);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                    postEvent2(MainEvent.ON_OBTENER_DATOS_ERROR);
            }
        });
    }



    @Override
    public void eliminarUsuario(final String id){
        final DatabaseReference usuarioRef = FirebaseDatabase.getInstance().getReference().child("usuarios").child(id);
        usuarioRef.removeValue();
        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User account deleted.");
                            postEvent2(MainEvent.ON_BORRAR_USUARIO_CORRECTO);
                        }else{
                            postEvent2(MainEvent.ON_BORRAR_USUARIO_ERROR);
                        }
                    }
                });

    }

    @Override
    public void actualizarDatos(String id, String email, String username){
        final DatabaseReference usuarioRef = FirebaseDatabase.getInstance().getReference();
        usuarioRef.child("usuarios").orderByChild("id").equalTo(id);
        usuarioRef.child("usuarios").child(id).child("name").setValue(username);
        usuarioRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                postEvent2(MainEvent.ON_DATOS_ACTUALIZADOS_CORRECTO);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                postEvent2(MainEvent.ON_DATOS_ACTUALIZADOS_ERROR);
            }
        });



    }

    private void postEvent(int type, User usuario) {
        MainEvent mainEvent = new MainEvent();
        mainEvent.setEventType(type);
        mainEvent.setUsuario(usuario);
        EventBus.getDefault().post(mainEvent);
    }

    private void postEvent2(int type) {
        MainEvent mainEvent = new MainEvent();
        mainEvent.setEventType(type);
        EventBus.getDefault().post(mainEvent);
    }
}
