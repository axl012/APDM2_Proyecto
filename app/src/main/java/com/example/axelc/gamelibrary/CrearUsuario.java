package com.example.axelc.gamelibrary;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class CrearUsuario extends AppCompatActivity {
    private static String S_URL ="https://axelhibridas.000webhostapp.com/proyecto/signup.php";
    EditText etCrearUsuario, etCrearContraseña, etConfirmarContraseña;
    Button btnCrearUusuario, btnCancelarCreacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        etCrearUsuario = findViewById(R.id.etCrearUsuario);
        etCrearContraseña = findViewById(R.id.etCrearContraseña);
        etConfirmarContraseña = findViewById(R.id.etComfirmarContraseña);
        btnCrearUusuario = findViewById(R.id.btnCrearUsuario);
        btnCancelarCreacion = findViewById(R.id.btnCancelarCreacion);

        btnCrearUusuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    signupRequest();
            }
        });

        btnCancelarCreacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etCrearUsuario.setText("");
                etCrearContraseña.setText("");
                Intent intent = new Intent(CrearUsuario.this, Main.class);
                startActivityForResult(intent,0);
            }
        });

    }

    private void signupRequest(){
        if (etCrearContraseña.getText().toString().equals(etConfirmarContraseña.getText().toString())) {
            RequestQueue queue = Volley.newRequestQueue(CrearUsuario.this);
        String response = null;
        final String finalResponse = response;

        StringRequest postRequest = new StringRequest(Request.Method.POST, S_URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                            if (response.equals("Successfully Signed In")) {
                                Toast.makeText(CrearUsuario.this, "Uusario creado", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), Main.class));
                            }else {
                                Toast.makeText(CrearUsuario.this, "error al crear usuario", Toast.LENGTH_SHORT).show();
                            }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("ErrorResponse", finalResponse);


                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();

                params.put("contraseña", etCrearContraseña.getText().toString());
                params.put("nombre", etCrearUsuario.getText().toString());

                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);

        }else {
            Toast.makeText(CrearUsuario.this, "las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }

    }

}
