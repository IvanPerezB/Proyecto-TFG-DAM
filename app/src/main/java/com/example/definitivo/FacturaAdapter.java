package com.example.definitivo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FacturaAdapter extends RecyclerView.Adapter<FacturaAdapter.ViewHolder> { // Adaptador de la clase factura para las listas de factura

    public static List<Facturas> fac;
    Context ctx;


    public FacturaAdapter(List<Facturas> fac, Context ctx) {
        this.fac = fac;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public FacturaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        View view = layoutInflater.inflate(R.layout.plantilla_factura,parent,false);
        FacturaAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull FacturaAdapter.ViewHolder holder, int position) {

        Facturas facturas = fac.get(position);

        holder.tnombre.setText(facturas.getNombre());
        holder.tprecio.setText(String.valueOf(facturas.getPrecio() +" €"));
        holder.tcantidad.setText(String.valueOf(facturas.getCantidad()));
        holder.ttotal.setText(String.valueOf(facturas.getTotal() +" €"));
    }

    @Override
    public int getItemCount() {
        return fac.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{ // contenedores para la lista de factura (pedidos)
        TextView tnombre, tprecio,tcantidad,ttotal;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tnombre = itemView.findViewById(R.id.fnom);
            tprecio = itemView.findViewById(R.id.fpre);
            tcantidad = itemView.findViewById(R.id.fcan);
            ttotal = itemView.findViewById(R.id.ftot);

        }
    }
}