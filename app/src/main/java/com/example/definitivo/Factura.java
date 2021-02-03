package com.example.definitivo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;


import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;

import static com.loopj.android.http.AsyncHttpClient.*;

public class Factura extends AppCompatActivity {



    private AsyncHttpClient cliente;
    RecyclerView lvFac;
    FacturaAdapter adapter;
    TextView Total;
    ArrayList<Facturas> listaf = new ArrayList<>();
    Button volver, eliminar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);

        volver = findViewById(R.id.btnVolver);
        eliminar=findViewById(R.id.btnEliminar);

        cliente = new AsyncHttpClient();
        lvFac = findViewById(R.id.recyclerFactura);
        Total = findViewById(R.id.textView9);
        cliente = new AsyncHttpClient();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lvFac.setHasFixedSize(true);
        lvFac.setLayoutManager(linearLayoutManager);
        lvFac.setAdapter(adapter);
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();
        obtenerProductos();
        ObtenerTotalPedido();

        volver.setOnClickListener(new View.OnClickListener() { // boton para volver a las categorias y seleccionar mas productos
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Factura.this, Categorias.class);
                startActivity(i);
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() { // boton finalizar un pedido y asi poder iniciar uno nuevo
            @Override
            public void onClick(View v) {
                eliminarPedido();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                startActivity(new Intent(Factura.this, Principal.class)); // vuelve a seleccionar las mesas

            }
        });
    }

    private void obtenerProductos() { // obtenemos los productos que hemos añadido al producto final

        final ProgressDialog progressDialog = new ProgressDialog(Factura.this);
        progressDialog.setMessage("Cargando Datos");
        progressDialog.show();

        String url = "https://pedidostpv.000webhostapp.com/obtenerFacturas.php";
        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    progressDialog.dismiss();
                    try {
                        JSONArray jsonarray = new JSONArray(new String(responseBody));
                        for (int i = 0; i < jsonarray.length(); i++) {
                            Facturas f = new Facturas();
                            f.setNombre(jsonarray.getJSONObject(i).getString("nombref"));
                            f.setPrecio(Double.parseDouble(jsonarray.getJSONObject(i).getString("preciof")));
                            f.setCantidad(Integer.parseInt(jsonarray.getJSONObject(i).getString("cantidadf")));
                            f.setTotal(Double.parseDouble(jsonarray.getJSONObject(i).getString("totalf")));

                            listaf.add(f);
                        }

                        lvFac.setAdapter(new FacturaAdapter(listaf, getApplicationContext()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void ObtenerTotalPedido(){ // calcula el total del precio de todos los productos
        final ProgressDialog progressDialog = new ProgressDialog(Factura.this);
        progressDialog.setMessage("Cargando Datos");
        progressDialog.show();

        String url = "https://pedidostpv.000webhostapp.com/obtenerTotal.php";
        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println();
                if (statusCode == 200) {
                    progressDialog.dismiss();
                    String[] parts={};
                    try {
                        JSONArray jsonarray = new JSONArray(new String(responseBody));
                        for (int i = 0; i < jsonarray.length(); i++) {

                            System.out.println(jsonarray.getJSONObject(i));


                            String luisito = jsonarray.getJSONObject(i).toString();
                            parts = luisito.split("\"");
                            System.out.println(luisito);
                        }
                        System.out.println(parts.length);

                       if(parts.length <=4){
                           Total.setText("0 €");

                        }else {
                           Total.setText(parts[3] +" €");


                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    private void eliminarPedido() { // eliminamos la comanda entera para reiniciar una nueva



        final ProgressDialog progressDialog = new ProgressDialog(Factura.this);
        progressDialog.setMessage("Cargando Datos");
        progressDialog.show();
        String url = "https://pedidostpv.000webhostapp.com/eliminarFacturas.php";
        cliente.post(url , new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    progressDialog.dismiss();
                    Toast.makeText(Factura.this,"Pedido enviado",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


}