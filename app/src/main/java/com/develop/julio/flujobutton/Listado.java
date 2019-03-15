package com.develop.julio.flujobutton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

public class Listado extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private UsuariosAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        
        llenarArray();

        mRecyclerView = findViewById(R.id.listadoUsuarios);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.HORIZONTAL));
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter= new UsuariosAdapter(Informacion.usuarios);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"item  seleccionado "+Informacion.usuarios.get(mRecyclerView.getChildAdapterPosition(v)).getNombre(),Toast.LENGTH_SHORT).show();
                Intent irRegistro = new Intent(getApplicationContext(),Registro.class);
                irRegistro.putExtra("nombre",Informacion.usuarios.get(mRecyclerView.getChildAdapterPosition(v)).getNombre());
                irRegistro.putExtra("correo",Informacion.usuarios.get(mRecyclerView.getChildAdapterPosition(v)).getCorreo());
                irRegistro.putExtra("tipo_usuario",Informacion.usuarios.get(mRecyclerView.getChildAdapterPosition(v)).getTipo());

                startActivity(irRegistro);
                finish();

            }
        });

        mRecyclerView.setAdapter(adapter);
    }

    private void llenarArray() {
        for (int i=0; i<10;i++){
            Informacion  informacion= new Informacion();
            informacion.setNombre("usuaio "+i);
            informacion.setCorreo("correo"+i+"@mail.com");
            informacion.setTipo("tipo "+i);

            Informacion.usuarios.add(informacion);
        }
    }
}
