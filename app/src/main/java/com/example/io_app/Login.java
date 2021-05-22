package com.example.io_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button register, login;
    private EditText email, password;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);

        mAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.email_ID);
        password = (EditText) findViewById(R.id.password_ID);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        register = (Button) findViewById(R.id.register_btn_ID);
        register.setOnClickListener(this);

        login = (Button) findViewById(R.id.login_accept_btn_ID);
        login.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_btn_ID:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.login_accept_btn_ID:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String emailStr = email.getText().toString().trim();
        String passwordStr = password.getText().toString().trim();

        if(emailStr.isEmpty()){
            email.setError("Email jest wymagany!");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()){
            email.setError("Email jest niepoprawny! Spróbuj jeszcze raz!");
            email.requestFocus();
            return;
        }

        if(passwordStr.isEmpty()){
            password.setError("Hasło jest wymagane!");
            password.requestFocus();
            return;
        }

        if(passwordStr.length() < 6){
            password.setError("Haslo musi mieć co najniej 6 znaków!");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(emailStr, passwordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(Login.this, UserProfile.class));
                }else{
                    Toast.makeText(Login.this, "Logowanie niepowiodło się! Sprawdź podane dane.", Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}