package com.example.cardiofit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
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

public class VentanaRegistro extends AppCompatActivity {

    EditText escribe_nombre, escribe_usuario, escribe_contraseña, escribe_edad, escribe_peso, escribe_altura;
    Button guardar_datos;
    RadioButton aceptar_terminos;
    String str_escribe_nombre,str_escribe_usuario,str_escribe_contraseña, str_escribe_edad, str_escribe_peso, str_escribe_altura;
    String url = "https://rogdomain.ddns.net:8860/cardiofit/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_registro);

        escribe_nombre=findViewById(R.id.RegistroNombre);
        escribe_usuario=findViewById(R.id.RegistroNombreUsuario);
        escribe_contraseña=findViewById(R.id.RegistroContraseña);
        escribe_edad=findViewById(R.id.RegistroEdad);
        escribe_peso=findViewById(R.id.RegistroPeso);
        escribe_altura=findViewById(R.id.RegistroAltura);
        aceptar_terminos=findViewById(R.id.AceptaTerminos);
        guardar_datos=findViewById(R.id.BotonDeRegistro);

    }


    public void Guardar_Registro(View view) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registrando datos..");

        String verificar_nombre = escribe_nombre.getText().toString();
        String verificar_correo = escribe_usuario.getText().toString();
        String verificar_contraseña = escribe_contraseña.getText().toString();
        String verificar_edad = escribe_edad.getText().toString();
        String verificar_peso = escribe_peso.getText().toString();
        String verificar_altura = escribe_altura.getText().toString();

        if(verificar_nombre.isEmpty()){
            escribe_nombre.setError("Ingrese un nombre de usuario");
        }
        else if(verificar_correo.isEmpty()){
            escribe_usuario.setError("Ingrese un correo electronico");
        }
        else if(verificar_contraseña.isEmpty()){
            escribe_contraseña.setError("Ingrese una contraseña");
        }
        else if(verificar_edad.isEmpty()){
            escribe_edad.setError("Ingrese su edad");
        }
        else if(verificar_peso.isEmpty()){
            escribe_peso.setError("Ingrese su peso");
        }
        else if(verificar_altura.isEmpty()){
            escribe_altura.setError("Ingrese su altura");
        }
        else if(aceptar_terminos.isChecked()){

            progressDialog.show();
            str_escribe_nombre = escribe_nombre.getText().toString().trim();
            str_escribe_usuario = escribe_usuario.getText().toString().trim();
            str_escribe_contraseña = escribe_contraseña.getText().toString().trim();
            str_escribe_edad = escribe_edad.getText().toString().trim();
            str_escribe_peso = escribe_peso.getText().toString().trim();
            str_escribe_altura = escribe_altura.getText().toString().trim();


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    escribe_nombre.setText("");
                    escribe_usuario.setText("");
                    escribe_contraseña.setText("");
                    escribe_edad.setText("");
                    escribe_peso.setText("");
                    escribe_altura.setText("");

                    if(response.equalsIgnoreCase("Registro guardado")){

                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        Toast.makeText(VentanaRegistro.this, response, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(VentanaRegistro.this, response, Toast.LENGTH_SHORT).show();
                    }
                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(VentanaRegistro.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();

                    params.put("nombre",str_escribe_nombre);
                    params.put("nombre_usuario",str_escribe_usuario);
                    params.put("contrasena",str_escribe_contraseña);
                    params.put("edad",str_escribe_edad);
                    params.put("peso",str_escribe_peso);
                    params.put("altura",str_escribe_altura);

                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(VentanaRegistro.this);
            requestQueue.add(request);


        }
        else {
            Toast.makeText(this, "Debes aceptar las condiciones y politcas de privacidad", Toast.LENGTH_SHORT).show();
        }

    }
}
