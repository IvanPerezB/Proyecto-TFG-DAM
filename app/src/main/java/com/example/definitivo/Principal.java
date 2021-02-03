package com.example.definitivo;



import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.loopj.android.http.*;
import org.json.JSONArray;
import cz.msebera.android.httpclient.Header;

public class Principal extends AppCompatActivity { // clase que controla la mesa seleccionada


    Button mesa1,mesa2,mesa3,mesa4,mesa5,mesa6, btn;

    Integer num_mesa=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        mesa1=findViewById(R.id.Mesa1);
        mesa2=findViewById(R.id.Mesa2);
        mesa3=findViewById(R.id.Mesa3);
        mesa4=findViewById(R.id.Mesa4);
        mesa5=findViewById(R.id.Mesa5);
        mesa6=findViewById(R.id.Mesa6);
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();




        mesa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_mesa = 1;
                startActivity(new Intent(Principal.this, Categorias.class));

            }
        });
        mesa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_mesa = 2;
                startActivity(new Intent(Principal.this, Categorias.class));

            }
        });
        mesa3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_mesa = 3;
                startActivity(new Intent(Principal.this, Categorias.class));

            }
        });
        mesa4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_mesa = 4;
                startActivity(new Intent(Principal.this, Categorias.class));

            }
        });
        mesa5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_mesa = 5;
                startActivity(new Intent(Principal.this, Categorias.class));

            }
        });
        mesa6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_mesa = 6;
                startActivity(new Intent(Principal.this, Categorias.class));

            }
        });



    }


}