package com.develop.julio.flujobutton;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Optional;

public class Buscar extends AppCompatActivity {

    public static  final int BUSCAR_ACTIVITY_REQUEST=5;
    private Button btnBuscar;
    private EditText edtBuscarCorreo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        btnBuscar = findViewById(R.id.btnBuscarCorreo);
        edtBuscarCorreo = findViewById(R.id.edtBuscarCorreo);


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = edtBuscarCorreo.getText().toString().trim();
                if (TextUtils.isEmpty(correo)){
                    edtBuscarCorreo.setError("escribe un correo");
                    edtBuscarCorreo.requestFocus();
                }else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
                    edtBuscarCorreo.requestFocus();
                    edtBuscarCorreo.setError("correo invalido");
                }else {
                    Bundle datos = getIntent().getExtras();
                    String operacion = datos.getString("operacion");
                    if (operacion.equals("editar")){
                        Intent irRegistro = new Intent(getApplicationContext(),Registro.class);
                        startActivity(irRegistro);
                        finish();
                    }else if (operacion.equals("eliminar")){
                        Intent irEliminar = new  Intent(getApplicationContext(),Datos.class);
                        irEliminar.putExtra("operacionP",operacion);
                        startActivity(irEliminar);
                        finish();
                    }else {
                        Intent irBuscar = new Intent(getApplicationContext(),Datos.class);
                        irBuscar.putExtra("operacionP",operacion);
                        Informacion usu = buscarUsuario(correo);
                        irBuscar.putExtra("correoB",usu.getClass());
                        startActivity(irBuscar);
                        finish();
                    }
                }
            }
        });
    }


    private Informacion buscarUsuario(final String e){
        Informacion usuario = new Informacion();

        /*
         //forma con java 8 y los streams
        Optional<Informacion> usuario  = Informacion.usuarios.stream()
                .filter(u -> u.getCorreo()
                        .equals(e))
                .findFirst();*/
        /*
        *
        * for (int i =0;i<Informacion.usuarios.size();i++){
            if (Informacion.usuarios.contains(e)){
                usuario = Informacion.usuarios.get(i);
            }
        }*/
        //recorro todo el array de usuaios y si encuento un valor igual lo retorno
        for (Informacion usu : Informacion.usuarios){
            if (usu.getCorreo().equals(e))
                usuario = usu;
        }
        return usuario;
    }
}
