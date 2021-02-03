package com.example.definitivo;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> { // adaptador de la clase producto junto con el recyclerview

    public static List<Producto> pro;
    Context context;

    public ProductoAdapter(List<Producto> pro, Context context) {
        this.pro = pro;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_view_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ViewHolder holder, int position) {
        Producto producto = pro.get(position);

        holder.tvnombre.setText(producto.getNombre());
        holder.tvprecio.setText(String.valueOf(producto.getPrecio() +" €"));
        holder.et_cantidad.setText(String.valueOf(producto.getCantidad()));

    }

    @Override
    public int getItemCount() {
        return pro.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{ // creamos los contenedores de las listas
        TextView tvnombre, tvprecio;
        EditText et_cantidad;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvnombre = itemView.findViewById(R.id.txtnom);
            tvprecio = itemView.findViewById(R.id.txtpre);
            et_cantidad = itemView.findViewById(R.id.txtcan);
            et_cantidad.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    pro.get(getAdapterPosition()).setCantidad(Integer.parseInt(et_cantidad.getText().toString()));
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


        }
    }
}
