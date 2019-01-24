package es.ulpgc.juantrujillo.loginproyectosavanzados.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import es.ulpgc.juantrujillo.loginproyectosavanzados.R;
import es.ulpgc.juantrujillo.loginproyectosavanzados.login.presenter.IPresenterLogin;
import es.ulpgc.juantrujillo.loginproyectosavanzados.login.presenter.PresenterLogin;
import es.ulpgc.juantrujillo.loginproyectosavanzados.main.view.MainActivity;
import es.ulpgc.juantrujillo.loginproyectosavanzados.register.view.RegisterActivity;


public class LoginActivity extends AppCompatActivity implements IViewLogin {


    private EditText username, password;
    private Button login;
    private TextView register, forgotPassword;
    private IPresenterLogin presenter;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new PresenterLogin(this);
        presenter.onCreate();
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        loading = findViewById(R.id.loading);
        forgotPassword = findViewById(R.id.forgotPassword);

        login.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                showProgress();
                presenter.toLogin(username.getText().toString(), password.getText().toString());
            }
        });

        register.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                toRegister();

            }
        });

        forgotPassword.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                presenter.forgotPassword(username.getText().toString());
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onDestroy();
    }


    @Override
    public void showProgress() {
        loading.setVisibility(android.view.View.VISIBLE);
        login.setClickable(false);
        login.setEnabled(false);
        register.setClickable(false);
        register.setEnabled(false);
        username.setClickable(false);
        username.setEnabled(false);
        password.setClickable(false);
        password.setEnabled(false);
}

    @Override
    public void hideProgress() {
        loading.setVisibility(android.view.View.GONE);
        login.setClickable(true);
        login.setEnabled(true);
        register.setClickable(true);
        register.setEnabled(true);
        username.setClickable(true);
        username.setEnabled(true);
        password.setClickable(true);
        password.setEnabled(true);
    }

    @Override
    public void showErrorLogin() {
        Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cleanForm() {
        username.setText("");
        password.setText("");
    }


    @Override
    public void toMenu() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("EXTRA_EMAIL", username.getText().toString());
        startActivity(intent);
        //finish();
    }

    @Override
    public void toRegister() {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void showPassSent() {
        Toast.makeText(getApplicationContext(), "Email enviado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorPassSent() {
        Toast.makeText(getApplicationContext(), "Email no encontrado", Toast.LENGTH_SHORT).show();
    }




}
