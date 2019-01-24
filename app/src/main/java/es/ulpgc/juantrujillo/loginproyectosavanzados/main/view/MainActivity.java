package es.ulpgc.juantrujillo.loginproyectosavanzados.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import es.ulpgc.juantrujillo.loginproyectosavanzados.R;
import es.ulpgc.juantrujillo.loginproyectosavanzados.login.view.LoginActivity;
import es.ulpgc.juantrujillo.loginproyectosavanzados.main.presenter.IPresenterMain;
import es.ulpgc.juantrujillo.loginproyectosavanzados.main.presenter.MainPresenterImpl;
import es.ulpgc.juantrujillo.loginproyectosavanzados.user.User;

public class MainActivity extends AppCompatActivity implements IViewMain {

    private IPresenterMain mainPresenter;
    private Button botonGuardar;
    private Button botonLogOut;
    private Button botonEliminar;
    private TextView emailUsuario;
    private EditText nombreUsuario;
    private TextView IDUsuario;
    private ProgressBar pbCargando;
    private ImageView foto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenterImpl(this);
        initView();
        mostrarEmail();
        obtenerUsuario(emailUsuario.getText().toString());

    }

    @Override
    public void onStart() {
        super.onStart();
        mainPresenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mainPresenter.onStop();
    }


    private void initView(){

        botonLogOut = (Button) findViewById(R.id.botonLogOut);
        botonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarCargando();
                cerrarSesion();
            }
        });
        botonEliminar = (Button) findViewById(R.id.botonEliminar);
        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarUsuario(IDUsuario.getText().toString());
            }
        });
        botonGuardar = (Button) findViewById(R.id.botonGuardar);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarDatos(IDUsuario.getText().toString(), emailUsuario.getText().toString(), nombreUsuario.getText().toString());
            }
        });

        emailUsuario = (TextView) findViewById(R.id.emailUsuario);
        nombreUsuario = (EditText) findViewById(R.id.nombreUsuario);
        IDUsuario = (TextView) findViewById(R.id.IDUsuario);
        pbCargando = (ProgressBar) findViewById(R.id.pbCargando);



    }


    @Override
    public void cerrarSesion(){
        mainPresenter.cerrarSesion();
    }

    @Override
    public void irALogin() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void mostrarEmail() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String j =(String) extras.get("EXTRA_EMAIL");
        emailUsuario.setText(j);
    }

    @Override
    public void mostrarCargando() {
        pbCargando.setVisibility(View.VISIBLE);
        botonGuardar.setClickable(false);
        botonGuardar.setEnabled(false);
        botonLogOut.setClickable(false);
        botonLogOut.setEnabled(false);
        botonEliminar.setClickable(false);
        botonEliminar.setEnabled(false);
        emailUsuario.setClickable(false);
        emailUsuario.setEnabled(false);
        nombreUsuario.setClickable(false);
        nombreUsuario.setEnabled(false);
        IDUsuario.setClickable(false);
        IDUsuario.setEnabled(false);

    }

    @Override
    public void ocultarCargando() {
        pbCargando.setVisibility(View.GONE);
        botonGuardar.setClickable(true);
        botonGuardar.setEnabled(true);
        botonLogOut.setClickable(true);
        botonLogOut.setEnabled(true);
        botonEliminar.setClickable(true);
        botonEliminar.setEnabled(true);
        emailUsuario.setClickable(true);
        emailUsuario.setEnabled(true);
        nombreUsuario.setClickable(true);
        nombreUsuario.setEnabled(true);
        IDUsuario.setClickable(true);
        IDUsuario.setEnabled(true);

    }

    @Override
    public void obtenerUsuario(String email){
        mainPresenter.obtenerUsuario(email);
    }

    @Override
    public void eliminarUsuario(String id){
        mainPresenter.eliminarUsuario(id);
    }

    @Override
    public void actualizarDatos(String id, String email, String username){
        mainPresenter.actualizarDatos(id, email, username);
    }

    @Override
    public void eliminarUsuarioSatisfactorio(){
        Toast.makeText(getApplicationContext(), getString(R.string.mensaje_correcto_eliminar_usuario), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void eliminarUsuarioError(){
        Toast.makeText(getApplicationContext(), getString(R.string.mensaje_error_eliminar_usuario), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pintarUsuario(User usuario){
        emailUsuario.setText(usuario.getEmail());
        IDUsuario.setText(usuario.getId());
        nombreUsuario.setText(usuario.getName());
    }

    @Override
    public void errorObtenerDatos(){
        Toast.makeText(getApplicationContext(), getString(R.string.mensaje_error_obtener_datos_usuario), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void mensajeActualizarDatos(){
        Toast.makeText(getApplicationContext(), getString(R.string.mensaje_actualizar_datos_usuario), Toast.LENGTH_SHORT).show();
    }




}
