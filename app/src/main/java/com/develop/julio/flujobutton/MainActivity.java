package com.develop.julio.flujobutton;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtusuario,edtpassword;
    private Button btningresar;
    static final  int MAIN_REQUEST=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtusuario = findViewById(R.id.edtusuario);
        edtpassword = findViewById(R.id.edtpassword);
        btningresar = findViewById(R.id.btningresar);
        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  usuario = edtusuario.getText().toString();
                String password = edtpassword.getText().toString();
                if (TextUtils.isEmpty((usuario))){
                    edtusuario.setError("debes completar este campo");
                }else if (TextUtils.isEmpty(password)){
                    edtpassword.setError("debes completar este campo");
                }else {
                    Intent intent = new Intent(getApplicationContext(),Nuevo.class);
                    startActivityForResult(intent,Nuevo.NUEVO_REQUEST);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==MAIN_REQUEST){
            if (resultCode == RESULT_CANCELED){
                Toast.makeText(getApplicationContext(),"el usuario cancelo la operacion",Toast.LENGTH_SHORT).show();
            }
        }

    }


}
