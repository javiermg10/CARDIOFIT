package com.example.cardiofit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class VentanaRegistroEntrenamiento extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText escribe_ejercicio, escribe_peso_usado, escribe_categoria;
    Button guardar_datos;
    Spinner SpinnerCategoria;
    String [] categorias = {"--Seleccione una categoria--", "Crossfit", "Cuadriceps", "Estiramientos", "Gluteos", "Resistencia", "Tonificacion", "Abdominales"};
    String str_escribe_ejercicio,str_escribe_peso_usado,str_escribe_categoria;
    String url = "https://rogdomain.ddns.net:8860/cardiofit/insertar_actividades.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_registro_entrenamiento);

        escribe_ejercicio=findViewById(R.id.etEjercicio);
        escribe_peso_usado=findViewById(R.id.etPesoUtilizado);
        escribe_categoria=findViewById(R.id.etCategoriaActividad);
        SpinnerCategoria=findViewById(R.id.SpinnerCategoria);

        guardar_datos=findViewById(R.id.btnSave);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorias);
        SpinnerCategoria.setAdapter(adapter1);
        SpinnerCategoria.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

    }



    public void GuardarEntrenamiento(View view){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Por Favor espere..");

        String verificar_ejercicio = escribe_ejercicio.getText().toString();
        String verificar_peso_usado = escribe_peso_usado.getText().toString();
        String verificar_categoria = escribe_categoria.getText().toString();

        if(verificar_ejercicio.isEmpty()){
            escribe_ejercicio.setError("Ingrese un ejercicio");
        }
        else if(verificar_peso_usado.isEmpty()){
            escribe_peso_usado.setError("Ingrese un peso");
        }
        else if(verificar_categoria.isEmpty()){
            escribe_categoria.setError("Ingrese una categoria");
        }
        else{

            progressDialog.show();
            str_escribe_ejercicio = escribe_ejercicio.getText().toString().trim();
            str_escribe_peso_usado = escribe_peso_usado.getText().toString().trim();
            str_escribe_categoria = escribe_categoria.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    escribe_ejercicio.setText("");
                    escribe_peso_usado.setText("");
                    escribe_categoria.setText("");

                    if(response.equalsIgnoreCase("Registro guardado")){

                        Toast.makeText(VentanaRegistroEntrenamiento.this, response, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(VentanaRegistroEntrenamiento.this, response, Toast.LENGTH_SHORT).show();
                    }
                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(VentanaRegistroEntrenamiento.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();

                    params.put("ejercicios",str_escribe_ejercicio);
                    params.put("peso_usado",str_escribe_peso_usado);
                    params.put("categoria",str_escribe_categoria);

                    Intent intent = getIntent();

                    String string_usuario_recibido = intent.getStringExtra("str_usuario");

                    params.put("nombre_usuario",string_usuario_recibido);

                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(VentanaRegistroEntrenamiento.this);
            requestQueue.add(request);

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        escribe_categoria.setText(SpinnerCategoria.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        escribe_categoria.setText("Selecciona alguna categoria");
    }
}
