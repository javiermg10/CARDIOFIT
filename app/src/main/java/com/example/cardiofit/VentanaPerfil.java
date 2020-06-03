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

public class VentanaPerfil extends AppCompatActivity {

    //EditText txtvalue;
    Button BotonConsultarPerfil;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_perfil);

        //txtvalue = findViewById(R.id.etMeterEmail);
        BotonConsultarPerfil = findViewById(R.id.btnLoad);
        listview = findViewById(R.id.lvLista);

        BotonConsultarPerfil.setOnClickListener(new View.OnClickListener() {
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

        String url = RecuperarDatosPersonales.DATA_URL + string_usuario_recibido;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(VentanaPerfil.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void showJSON(String response) {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(RecuperarDatosPersonales.JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {

                JSONObject jo = result.getJSONObject(i);

                String nombre = jo.getString(RecuperarDatosPersonales.KEY_NOMBRE);
                String nombre_usuario = jo.getString(RecuperarDatosPersonales.KEY_NOMBRE_USUARIO);
                String contrasena = jo.getString(RecuperarDatosPersonales.KEY_CONTRASENA);
                String edad = jo.getString(RecuperarDatosPersonales.KEY_EDAD);
                String peso = jo.getString(RecuperarDatosPersonales.KEY_PESO);
                String altura = jo.getString(RecuperarDatosPersonales.KEY_ALTURA);

                final HashMap<String, String> employees = new HashMap<>();

                employees.put(RecuperarDatosPersonales.KEY_NOMBRE, nombre);
                employees.put(RecuperarDatosPersonales.KEY_NOMBRE_USUARIO, nombre_usuario);
                employees.put(RecuperarDatosPersonales.KEY_CONTRASENA, contrasena);
                employees.put(RecuperarDatosPersonales.KEY_EDAD, edad);
                employees.put(RecuperarDatosPersonales.KEY_PESO, peso);
                employees.put(RecuperarDatosPersonales.KEY_ALTURA, altura);

                list.add(employees);

            }

        } catch (JSONException e) {

            e.printStackTrace();

        }
        ListAdapter adapter = new SimpleAdapter(

                VentanaPerfil.this, list, R.layout.activity_mylistcuenta,

                new String[]{RecuperarDatosPersonales.KEY_NOMBRE, RecuperarDatosPersonales.KEY_NOMBRE_USUARIO, RecuperarDatosPersonales.KEY_CONTRASENA, RecuperarDatosPersonales.KEY_EDAD, RecuperarDatosPersonales.KEY_PESO, RecuperarDatosPersonales.KEY_ALTURA},

                new int[]{R.id.nombre, R.id.nombre_usuario, R.id.contrasena, R.id.edad, R.id.peso, R.id.altura});

        listview.setAdapter(adapter);

    }
}
