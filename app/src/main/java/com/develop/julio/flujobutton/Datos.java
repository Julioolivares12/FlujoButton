package com.develop.julio.flujobutton;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Datos extends AppCompatActivity {

    static final int DATOS_REQUEST = 4;

    private TextView tvnombre,tvemail,tvclave,tvtipo;

    private Button btnconfirmar,btncancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);


        tvnombre = findViewById(R.id.tvnombre);
        tvemail = findViewById(R.id.tvemail);
        tvclave = findViewById(R.id.tvclave);
        tvtipo = findViewById(R.id.tvtipo);


        btnconfirmar = findViewById(R.id.btnconfirmar);
        btncancelar = findViewById(R.id.btncancelar);


        Bundle datos = getIntent().getExtras();


        tvnombre.setText("nombre "+ "  "+datos.getString("nombre"));
        tvemail.setText("correo"+" "+datos.getString("email"));
        tvclave.setText("contraseña"+" "+datos.getString("pass"));
        tvtipo.setText("tipo"+" "+datos.getString("tipo"));


        btnconfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Datos guardados correctamente",Toast.LENGTH_SHORT).show();
                Intent irNuevo = new Intent(getApplicationContext(),Nuevo.class);
                setResult(Nuevo.NUEVO_ACTIVITY_REQUEST,irNuevo);
                finish();
            }
        });
        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Snackbar.make(v,"quieres salir",Snackbar.LENGTH_LONG).setAction("si", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent irRegistro = new Intent(Datos.this,Registro.class);
                        setResult( RESULT_CANCELED,irRegistro);
                        finish();
                    }
                }).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
