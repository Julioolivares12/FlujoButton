package com.develop.julio.flujobutton;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.UsuarioHolder> implements View.OnClickListener {

    private ArrayList<Informacion> usuarios;
    private View.OnClickListener mListener;

    public  UsuariosAdapter(ArrayList<Informacion>usuarios){
        this.usuarios = usuarios;
    }
    @NonNull
    @Override
    public UsuarioHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_recycler,viewGroup,false);
        view.setOnClickListener(this);
        return new UsuarioHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioHolder usuarioHolder, int i) {
        Informacion informacion = usuarios.get(i);
        usuarioHolder.tvusuario.setText(informacion.getNombre());
        usuarioHolder.tvcorreo.setText(informacion.getCorreo());
        usuarioHolder.tipo.setText(informacion.getTipo());
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.mListener = listener;
    }
    @Override
    public void onClick(View v) {
        if (this.mListener!=null){
            mListener.onClick(v);
        }
    }

    public class UsuarioHolder extends RecyclerView.ViewHolder {
        private TextView tvusuario;
        private  TextView tvcorreo;
        private  TextView tipo;
        public UsuarioHolder(@NonNull View itemView) {
            super(itemView);

            tvusuario = itemView.findViewById(R.id.tvusuarioR);
            tvcorreo = itemView.findViewById(R.id.tvcorreoR);
            tipo = itemView.findViewById(R.id.tvtipoR);
        }
    }
}
