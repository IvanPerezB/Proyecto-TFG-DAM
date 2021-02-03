package com.example.definitivo;



import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Categorias extends AppCompatActivity { // clase donde seleccionamos las categorias

    Button Refrescos,Conchas,Pescados,Carnes,Licores,Cervezas,Cafes,Postres,VinoB,VinoT,Entrantes,Pasta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        Refrescos= findViewById(R.id.btnRefrescos);
        Conchas = findViewById(R.id.btnConchas);
        Pescados = findViewById(R.id.btnPescados);
        Carnes = findViewById(R.id.btnCarnes);
        Licores = findViewById(R.id.btnLicores);
        Cervezas = findViewById(R.id.btnCervezas);
        Cafes = findViewById(R.id.btnCafes);
        Postres = findViewById(R.id.btnPostres);
        VinoB = findViewById(R.id.btnVinoB);
        VinoT = findViewById(R.id.btnVinoT);
        Entrantes = findViewById(R.id.btnEntrantes);
        Pasta = findViewById(R.id.btnPastas);
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();



        Refrescos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categorias.this.startActivity(new Intent(Categorias.this, Refrescos.class));
            }
        });

        Conchas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categorias.this.startActivity(new Intent(Categorias.this, Conchas.class));
            }
        });
        Pescados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categorias.this.startActivity(new Intent(Categorias.this, Pescados.class));
            }
        });
        Carnes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categorias.this.startActivity(new Intent(Categorias.this, Carnes.class));
            }
        });
        Licores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categorias.this.startActivity(new Intent(Categorias.this, Licores.class));
            }
        });
        Cervezas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categorias.this.startActivity(new Intent(Categorias.this, Cervezas.class));
            }
        });
        Cafes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categorias.this.startActivity(new Intent(Categorias.this, Cafes.class));
            }
        });
        Postres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categorias.this.startActivity(new Intent(Categorias.this, Postres.class));
            }
        });
        VinoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categorias.this.startActivity(new Intent(Categorias.this, VinosBlancos.class));
            }
        });
        VinoT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categorias.this.startActivity(new Intent(Categorias.this, VinosTintos.class));
            }
        });
        Entrantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categorias.this.startActivity(new Intent(Categorias.this, Entrantes.class));
            }
        });
        Pasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categorias.this.startActivity(new Intent(Categorias.this, Pastas.class));
            }
        });

    }


}