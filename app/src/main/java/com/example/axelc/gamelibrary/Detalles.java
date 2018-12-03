package com.example.axelc.gamelibrary;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;

public class Detalles extends AppCompatActivity {
    private String TAG = Detalles.class.getSimpleName();

    private ListView lvCriticas;

    // URL to get contacts JSON
    private static String url = "https://axelhibridas.000webhostapp.com/proyecto/selectcritica.php";

    ArrayList<HashMap<String, String>> criticasList;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detalles);

                    criticasList = new ArrayList<>();

            lvCriticas = findViewById(R.id.lvCriticas);

            new getCriticas().execute();
        }

        private class getCriticas extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... arg0) {
                HttpHandler sh = new HttpHandler();

                // Making a request to url and getting response
                String jsonStr = sh.makeServiceCall(url);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray criticas = jsonObj.getJSONArray("criticas");

                        // looping through All Contacts
                        for (int i = 0; i < criticas.length(); i++) {
                            JSONObject c = criticas.getJSONObject(i);

                            String id = c.getString("id_critica");
                            String nombre = c.getString("nombre_critica");
                            String critica = c.getString("critica");
                            String calificacion = c.getString("calificacion");
                            String id_juegos = c.getString("id_juegos");

                            // tmp hash map for single contact
                            HashMap<String, String> crit = new HashMap<>();

                            // adding each child node to HashMap key => value
                            crit.put("id", id);
                            crit.put("name", nombre);
                            crit.put("email", critica);
                            crit.put("mobile", calificacion);
                            crit.put("id_juegos", id_juegos);

                            // adding contact to contact list
                            criticasList.add(crit);
                        }
                    } catch (final JSONException e) {
                        Log.e(TAG, "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Json parsing error: " + e.getMessage(),
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });

                    }
                } else {
                    Log.e(TAG, "Couldn't get json from server.");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Couldn't get json from server. Check LogCat for possible errors!",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                /**
                 * Updating parsed JSON data into ListView
                 * */
                ListAdapter adapter = new SimpleAdapter(
                        Detalles.this, criticasList,
                        R.layout.critica_item, new String[]{"nombre", "calificacion",
                        "critica"}, new int[]{R.id.txtNameList,
                        R.id.txtCalificacion, R.id.txtCriticaList});

                lvCriticas.setAdapter(adapter);
            }

        }
    }

