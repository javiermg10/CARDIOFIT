package com.example.cardiofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class VentanaHistorialActividades extends AppCompatActivity {

    //EditText txtvalue;
    Button BotonVerActividades;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_historial_actividades);

        //txtvalue = findViewById(R.id.etMeterEmail);
        BotonVerActividades = findViewById(R.id.btnLoad);
        listview = findViewById(R.id.lvLista);

        BotonVerActividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

    }
    private void getData() {

        //String value = txtvalue.getText().toString().trim();

        /*if (value.equals("")) {
            Toast.makeText(this, "Pon tu usuario", Toast.LENGTH_LONG).show();
            return;
        }*/
        Intent intent = getIntent();

        String string_usuario_recibido = intent.getStringExtra("str_usuario");

        String url = RecuperarDatos.DATA_URL + string_usuario_recibido;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(VentanaHistorialActividades.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void showJSON(String response) {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(RecuperarDatos.JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {

                JSONObject jo = result.getJSONObject(i);

                String ejercicios = jo.getString(RecuperarDatos.KEY_EJERCICIOS);
                String peso_usado = jo.getString(RecuperarDatos.KEY_PESO_USADO);
                String categoria = jo.getString(RecuperarDatos.KEY_CATEGORIA);

                final HashMap<String, String> employees = new HashMap<>();

                employees.put(RecuperarDatos.KEY_EJERCICIOS, ejercicios);
                employees.put(RecuperarDatos.KEY_PESO_USADO, peso_usado);
                employees.put(RecuperarDatos.KEY_CATEGORIA, categoria);

                list.add(employees);

            }

        } catch (JSONException e) {

            e.printStackTrace();

        }
        ListAdapter adapter = new SimpleAdapter(

                VentanaHistorialActividades.this, list, R.layout.activity_mylist,

                new String[]{RecuperarDatos.KEY_EJERCICIOS, RecuperarDatos.KEY_PESO_USADO, RecuperarDatos.KEY_CATEGORIA},

                new int[]{R.id.ejercicios, R.id.peso_usado, R.id.categoria});

        listview.setAdapter(adapter);

    }
}
