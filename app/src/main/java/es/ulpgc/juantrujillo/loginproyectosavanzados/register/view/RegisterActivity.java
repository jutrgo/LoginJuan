package es.ulpgc.juantrujillo.loginproyectosavanzados.register.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import es.ulpgc.juantrujillo.loginproyectosavanzados.R;
import es.ulpgc.juantrujillo.loginproyectosavanzados.main.view.MainActivity;
import es.ulpgc.juantrujillo.loginproyectosavanzados.register.presenter.IPresenterRegister;
import es.ulpgc.juantrujillo.loginproyectosavanzados.register.presenter.PresenterRegister;


public class RegisterActivity extends AppCompatActivity implements IViewRegister{

    private EditText email, name, password, repeatPassword;
    private ProgressBar loading;
    private Button register;
    private IPresenterRegister presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        presenter =  new PresenterRegister(this);
        presenter.onCreate();

        email = findViewById(R.id.username);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        repeatPassword = findViewById(R.id.repeatPassword);
        loading = findViewById(R.id.loading);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress();
                presenter.register(email.getText().toString(), password.getText().toString(), name.getText().toString());
            }
        });

    }


    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onBackPressed(){
        toLogin();
    }

    @Override
    public void toLogin() {
        finish();
    }

    @Override
    public void toMenu() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("EXTRA_EMAIL", email.getText().toString());
        startActivity(intent);
        finish();
    }



    @Override
    public void showProgress() {
        loading.setVisibility(View.VISIBLE);
        email.setClickable(false);
        email.setEnabled(false);
        password.setClickable(false);
        password.setEnabled(false);
        repeatPassword.setClickable(false);
        repeatPassword.setEnabled(false);
        register.setClickable(false);
        register.setEnabled(false);
    }

    @Override
    public void hideProgress() {
        loading.setVisibility(View.GONE);
        email.setClickable(true);
        email.setEnabled(true);
        password.setClickable(true);
        password.setEnabled(true);
        repeatPassword.setClickable(true);
        repeatPassword.setEnabled(true);
        register.setClickable(true);
        register.setEnabled(true);
    }

    @Override
    public void clearForm() {
        email.setText("");
        password.setText("");
        repeatPassword.setText("");
    }

    @Override
    public void showError() {
        Toast.makeText(getApplicationContext(), "Error al crear cuenta",Toast.LENGTH_SHORT).show();
    }


}