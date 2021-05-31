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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private DatabaseReference dbRef;
    private EditText email, name, surname, password;
    private Button accept, login;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_view);

        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference().child("Users");

        accept = (Button) findViewById(R.id.register_accept_btn_ID);
        accept.setOnClickListener(this);

        login = (Button) findViewById(R.id.login_btn_ID);
        login.setOnClickListener(this);

        email = (EditText) findViewById(R.id.email_register_ID);
        name = (EditText) findViewById(R.id.name_register_ID);
        surname = (EditText) findViewById(R.id.surname_register_ID);
        password = (EditText) findViewById(R.id.password_register_ID);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn_ID:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.register_accept_btn_ID:
                registerUser();
        }
    }

    private void registerUser() {
        String emailStr = email.getText().toString().trim();
        String passwordStr = password.getText().toString().trim();
        String nameStr = name.getText().toString().trim();
        String surnameStr = surname.getText().toString().trim();

        if(emailStr.isEmpty()){
            email.setError("Email jest wymagany!");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()){
            email.setError("To nie jest poprawny adress email!");
            email.requestFocus();
            return;
        }

        if(nameStr.isEmpty()){
            name.setError("Imię jest wymagane!");
            name.requestFocus();
            return;
        }

        if(surnameStr.isEmpty()){
            surname.setError("Nazwisko jest wymagane!");
            surname.requestFocus();
            return;
        }


        if(passwordStr.isEmpty()){
            password.setError("Hasło jest wymagane!");
            password.requestFocus();
            return;
        }

        if(passwordStr.length() < 6){
            password.setError("Hasło powinno mieć minimum 6 znaków!");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(emailStr, passwordStr)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
<<<<<<< HEAD
<<<<<<< HEAD
                            UserDB user = new UserDB(mAuth.getUid(),nameStr,surnameStr,emailStr,"",false,"administrator");
=======
                            UserObject user = new UserObject(nameStr,surnameStr,emailStr,passwordStr);
>>>>>>> parent of 94818ab (Added creating groups)
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
=======
                            UserDB user = new UserDB(nameStr,surnameStr,emailStr,passwordStr);
                            dbRef.push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
>>>>>>> Sprint
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Register.this, "Użytkownik został zarejestrowany!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        FirebaseUser FBuser = FirebaseAuth.getInstance().getCurrentUser();
                                        UserProfileChangeRequest userUpdate = new UserProfileChangeRequest.Builder().setDisplayName(user.getName() + " " + user.getSurname()).build();
                                        FBuser.updateProfile(userUpdate);
                                        startActivity(new Intent(Register.this, Login.class));
                                    }else{
                                        Toast.makeText(Register.this, "Nie udało się zarejestrować! Spróbuj jeszcze raz!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(Register.this, "Nie udało się zarejestrować! Spróbuj jeszcze raz!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}