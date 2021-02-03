package com.example.definitivo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class Entrantes extends AppCompatActivity { // igual que en la clase Refrescos

    private TextView txtnom, txtpre;
    private Button btnalm,btnPedido;
    private EditText txtcan;

    private AsyncHttpClient cliente;
    RecyclerView lvPro;
    ProductoAdapter adapter;
    ArrayList<Producto> listap = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        txtnom = (TextView) findViewById(R.id.txtnom);
        txtpre = (TextView) findViewById(R.id.txtpre);
        txtcan = (EditText) findViewById(R.id.txtcan);
        btnalm = (Button)findViewById(R.id.btnalm);
        btnPedido = (Button) findViewById(R.id.IrAFactura);

        lvPro = findViewById(R.id.idRecycler);
        cliente = new AsyncHttpClient();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lvPro.setHasFixedSize(true);
        lvPro.setLayoutManager(linearLayoutManager);
        lvPro.setAdapter(adapter);
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();
        obtenerProductos();










        btnalm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Producto p = new Producto();



                for (int i=0; i< listap.size();i++) {
                    if(adapter.pro.get(i).getCantidad()>0) {
                        p.getId();
                        p.setNombre(adapter.pro.get(i).getNombre().replaceAll(" ","%20"));
                        p.setPrecio(adapter.pro.get(i).getPrecio());
                        p.setCantidad(adapter.pro.get(i).getCantidad());
                        p.setTotal(p.getCantidad() * p.getPrecio());


                        agregarProducto(p);
                    }
                    Toast.makeText(Entrantes.this,"Producto almacenado correctamente",Toast.LENGTH_SHORT).show();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }

            }

        });

        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Entrantes.this.startActivity(new Intent(Entrantes.this, Factura.class));
            }
        });

    }




    private void agregarProducto(Producto p) {
        String url = "https://pedidostpv.000webhostapp.com/insertarProducto.php?";

        String parametros ="&Id=" + p.getId() + "&Nombre=" + p.getNombre() + "&Precio=" + p.getPrecio() + "&Cantidad=" + p.getCantidad() + "&Total=" + p.getTotal();

        cliente.post(url + parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {


                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    } // Cierra el metodo agregarProducto.


    private void obtenerProductos() {



        final ProgressDialog progressDialog = new ProgressDialog(Entrantes.this);
        progressDialog.setMessage("Cargando Datos");
        progressDialog.show();
        String url = "https://pedidostpv.000webhostapp.com/obtenerEntrantes.php";
        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    progressDialog.dismiss();
                    try {
                        JSONArray jsonarray = new JSONArray(new String(responseBody));
                        for (int i = 0; i < jsonarray.length(); i++) {
                            Producto p = new Producto();
                            p.setId(Integer.parseInt(jsonarray.getJSONObject(i).getString("id_producto")));
                            p.setNombre(jsonarray.getJSONObject(i).getString("nombrep"));
                            p.setPrecio(Double.parseDouble(jsonarray.getJSONObject(i).getString("preciop")));
                            p.setCantidad(Integer.parseInt(jsonarray.getJSONObject(i).getString("cantidadp")));

                            listap.add(p);
                        }

                        lvPro.setAdapter(new ProductoAdapter(listap, getApplicationContext()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    } // Cierra el metodo obtenerProductos.






}

