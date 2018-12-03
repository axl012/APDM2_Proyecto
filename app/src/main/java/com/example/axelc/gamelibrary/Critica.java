package com.example.axelc.gamelibrary;

<<<<<<< HEAD
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
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

public class Critica extends AppCompatActivity {

    private static String S_URL ="https://axelhibridas.000webhostapp.com/proyecto/critica.php";
    EditText etNombreCritica, etCritica;
    RatingBar rbCalificacion;
    Button btnEnviarCritica, btnCancelarCritica;

=======
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Critica extends AppCompatActivity {

>>>>>>> fb92e8c8d4c451188bba5187e1808cb22bf59f19
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critica);
<<<<<<< HEAD

        etNombreCritica = findViewById(R.id.etNombreCritica);
        etCritica = findViewById(R.id.etCritica);
        rbCalificacion = findViewById(R.id.rbCalificacion);
        btnEnviarCritica = findViewById(R.id.btnEnviarCritica);
        btnCancelarCritica = findViewById(R.id.btnCancelarCritica);
        btnEnviarCritica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();
            }
        });

        btnCancelarCritica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Critica.this, Detalles.class);
                startActivityForResult(intent, 0);
            }
        });
    }


    private void sendRequest(){
            RequestQueue queue = Volley.newRequestQueue(Critica.this);
            String response = null;
            final String finalResponse = response;

            StringRequest postRequest = new StringRequest(Request.Method.POST, S_URL,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("Envio exitoso")) {
                                Toast.makeText(Critica.this, "Enviado correctamente", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), Detalles.class));
                            }else {
                                Toast.makeText(Critica.this, "error al enviar ", Toast.LENGTH_SHORT).show();
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
                    Float rating = rbCalificacion.getRating();

                    params.put("nombre_critica", etNombreCritica.getText().toString());
                    params.put("critica", etCritica.getText().toString());
                    params.put("calificacion", rating.toString());

                    return params;
                }
            };
            postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(postRequest);


=======
>>>>>>> fb92e8c8d4c451188bba5187e1808cb22bf59f19
    }
}
