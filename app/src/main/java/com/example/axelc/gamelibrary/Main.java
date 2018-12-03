package com.example.axelc.gamelibrary;

import android.content.Intent;
import android.os.AsyncTask;
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

import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main extends AppCompatActivity {
    boolean session = false;
    EditText etUsuario, etContraseña;
    Button btnIniciar, btnRegistrar;
    private static String URL = "https://axelhibridas.000webhostapp.com/proyecto/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
         setContentView(R.layout.activity_main);
        btnIniciar = findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this,Detalles.class);
                startActivityForResult(intent,0);
            }
        });
=======
         setContentView(R.layout.activity_juegos);
>>>>>>> fb92e8c8d4c451188bba5187e1808cb22bf59f19
      /*  etUsuario = findViewById(R.id.etUsuario);
        etContraseña = findViewById(R.id.etContraseña);
        btnIniciar = findViewById(R.id.btnIniciar);
        btnRegistrar = findViewById(R.id.btnCrearUsuario);
          btnIniciar.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                 // loginRequest();
                  startActivity(new Intent(getApplicationContext(), Juegos.class));

              }
          });

          btnRegistrar.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent = new Intent(Main.this, CrearUsuario.class);
                  startActivityForResult(intent,0);
              }
          });
*/
    }

    private void loginRequest(){
        RequestQueue queue = Volley.newRequestQueue(Main.this);
        String response = null;

        final String finalResponse = response;

        StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {

                        if(response.equals("Login")) {
                            startActivity(new Intent(getApplicationContext(), Juegos.class));
                            Toast.makeText(Main.this, "Iniciando sesion", Toast.LENGTH_SHORT).show();
                        }else if(response.equals("invalid")){
                            Toast.makeText(Main.this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();

                        }


                    }

                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ErrorResponse", finalResponse);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("nombre", etUsuario.getText().toString());
                params.put("contraseña", etContraseña.getText().toString());
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);



    }

}




