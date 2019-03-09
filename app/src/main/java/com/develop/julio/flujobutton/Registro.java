package com.develop.julio.flujobutton;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    static final int REGISTRO_ACTIVITY_REQUEST=3;

    private EditText edtnombre,edtemail,edtpassword,edtconfirmar;
    private RadioButton rbusuario,rbadministrador,rbasistente;
    private Button btnGuardar,btnCancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);



        edtnombre = findViewById(R.id.edtnombre);
        edtemail = findViewById(R.id.edtcorreo);
        edtpassword = findViewById(R.id.edtpass);
        edtconfirmar = findViewById(R.id.edtconfirmar);

        rbusuario = findViewById(R.id.rbusuario);
        rbadministrador = findViewById(R.id.rbadministrador);
        rbasistente = findViewById(R.id.rbasistente);

        btnGuardar = findViewById(R.id.btnguardar);
        btnCancelar = findViewById(R.id.btncancelarR);



        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = edtnombre.getText().toString().trim();
                String email = edtemail.getText().toString().trim();
                String pass = edtpassword.getText().toString().trim();
                String confirmar = edtconfirmar.getText().toString().trim();

                if (TextUtils.isEmpty(nombre)){
                    edtnombre.setError("campo requerido");
                    edtnombre.requestFocus();
                }else if (TextUtils.isEmpty(email)){
                    edtemail.setError("campo requerifo");
                    edtemail.requestFocus();
                }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    edtemail.setError("ingresa un correo valido");
                    edtemail.requestFocus();
                }else if (TextUtils.isEmpty(pass)){
                    edtpassword.setError("ingresa una contraseña");
                    edtpassword.requestFocus();
                }else if (TextUtils.isEmpty(confirmar)){
                    edtconfirmar.setError("ingresa otra vez tu contraseña");
                    edtconfirmar.requestFocus();
                }else if (!confirmar.equals(pass)){
                    edtconfirmar.setError("las contraseñas no son iguales");
                    edtconfirmar.requestFocus();
                }else {
                    Intent irDatos = new Intent(getApplicationContext(),Datos.class);
                    irDatos.putExtra("nombre",nombre);
                    irDatos.putExtra("email",email);
                    irDatos.putExtra("pass",pass);

                    if (rbusuario.isChecked()){
                        irDatos.putExtra("tipo","usuario");
                        //startActivityForResult(irDatos,Datos.DATOS_REQUEST);
                        //finish();
                    }else if(rbasistente.isChecked()){
                        irDatos.putExtra("tipo","asistente");
                        //startActivityForResult(irDatos,Datos.DATOS_REQUEST);
                        //finish();
                    }else if (rbadministrador.isChecked()){
                        irDatos.putExtra("tipo","administrador");
                        //startActivityForResult(irDatos,Datos.DATOS_REQUEST);
                        //finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"debes seleccionar un cargo",Toast.LENGTH_SHORT).show();
                    }
                    startActivityForResult(irDatos,Datos.DATOS_REQUEST);
                }
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"quieres salir ",Snackbar.LENGTH_LONG).setAction("si", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent regresar = new Intent(getApplicationContext(),Nuevo.class);
                        setResult(RESULT_CANCELED,regresar);
                        finish();
                    }
                }).show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REGISTRO_ACTIVITY_REQUEST){

            if (resultCode == RESULT_CANCELED){
                Toast.makeText(getApplicationContext(),"operacion cancelada",Toast.LENGTH_SHORT).show();
                //Bundle datos = getIntent().getExtras();
                String tipo="";
                try{

                    Bundle d = data.getExtras();
                    edtnombre.setText(d.getString("nombre"));
                    edtemail.setText(d.getString("email"));
                     tipo = d.getString("tipo");
                }catch (Exception e){
                    e.printStackTrace();
                }

                switch (tipo){
                    case "usuario":
                        rbusuario.setChecked(true);
                        break;
                    case "asistente":
                        rbasistente.setChecked(true);
                        break;
                    case  "administrador":
                        rbadministrador.setChecked(true);
                        break;
                }
            }
        }
    }
}
