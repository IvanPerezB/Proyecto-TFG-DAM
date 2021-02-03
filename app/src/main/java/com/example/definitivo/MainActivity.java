package com.example.definitivo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity { // clase que controla el login

    EditText user, pass;
    String usuario="admin" , contrasenia="12345"; // usuario y contraseña predeterminado




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.password);
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();




    }

    public void login(View v) {

        String lu = user.getText().toString();
        String lp = pass.getText().toString();

        if (lu.equals(usuario) && lp.equals(contrasenia)) {

            Toast.makeText(this, "Login Correcto", Toast.LENGTH_SHORT).show();


            Intent i =new Intent(MainActivity.this,Principal.class);
            startActivity(i);
        }
        else {
            Toast.makeText(this, "Login Fallido", Toast.LENGTH_SHORT).show();

        }


    }
}