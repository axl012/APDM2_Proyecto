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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{
    boolean session = false;
    EditText etUsuario, etContraseña;
    Button btnIniciar, btnRegistrar;
    RequestQueue rq;
    JsonRequest jrq;
    private static String URL = "https://axelhibridas.000webhostapp.com/proyecto/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
        etUsuario = findViewById(R.id.etUsuario);
        etContraseña = findViewById(R.id.etContraseña);
        btnIniciar = findViewById(R.id.btnIniciar);
        btnRegistrar = findViewById(R.id.btnCrearUsuario);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginRequest();
            }
        });


          btnRegistrar.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent = new Intent(Main.this, CrearUsuario.class);
                  startActivityForResult(intent,0);
              }
          });

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
                            startActivity(new Intent(getApplicationContext(), Detalles.class));
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

    private void iniciarSesion(){
        String url = "https://axelhibridas.000webhostapp.com/proyecto/login.php?nombre="+etUsuario.getText().toString()+"&contraseña="+etContraseña.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        if(response.equals("Login")) {
            startActivity(new Intent(getApplicationContext(), Juegos.class));
            Toast.makeText(Main.this, "Iniciando sesion", Toast.LENGTH_SHORT).show();
        }else if(response.equals("invalid")){
            Toast.makeText(Main.this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();

        }
    }
}




