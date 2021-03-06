package com.develop.julio.flujobutton;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Nuevo extends AppCompatActivity {

    private Button btnNuevo , btnSalir,btnListado,btnBuscar,btnEditar,btnEliminar;

    static final int NUEVO_ACTIVITY_REQUEST=2;
    static final int NUEVO_REQUEST=1;
    public String operacion="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        btnNuevo = findViewById(R.id.btnnuevo);
        btnSalir = findViewById(R.id.btnsalir);
        btnListado = findViewById(R.id.btnListado);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);

        btnListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent irListado = new Intent(getApplicationContext(),Listado.class);
                 startActivity(irListado);
            }
        });
        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irRegistro = new Intent(getApplicationContext(),Registro.class);
                startActivityForResult(irRegistro,Registro.REGISTRO_ACTIVITY_REQUEST);
            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"sair",Snackbar.LENGTH_LONG).setAction("salir", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent ir_actividad_principal = new Intent(getApplicationContext(),MainActivity.class);
                        setResult(RESULT_CANCELED,ir_actividad_principal);
                        finish();
                    }
                }).show();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacion = "eliminar";
                Intent irEliminar = new Intent(getApplicationContext(),Buscar.class);
                irEliminar.putExtra("operacion",operacion);
                startActivityForResult(irEliminar,Buscar.BUSCAR_ACTIVITY_REQUEST);
            }
        });
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacion = "buscar";
                Intent irEditar = new Intent(getApplicationContext(),Buscar.class);
                irEditar.putExtra("operacion",operacion);
            }
        });
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacion = "editar";
                Intent irEditar = new Intent(getApplicationContext(),Buscar.class);
                irEditar.putExtra("operacion",operacion);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NUEVO_ACTIVITY_REQUEST){
            if (resultCode== RESULT_OK)
                Toast.makeText(getApplicationContext(),"Agregado",Toast.LENGTH_SHORT).show();
            if (resultCode == RESULT_CANCELED)
                Toast.makeText(getApplicationContext(),"Operacion cancelada",Toast.LENGTH_SHORT).show();
        }
    }
}
