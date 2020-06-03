package com.example.cardiofit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
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

public class MainActivity extends AppCompatActivity {

    EditText NombreDelUsuario, PonerContraseña;
    Button IniciarLaSesion, BotonParaRegistrarse;
    ImageView LogoDeAplicacion;
    CheckBox muestra_contrasena;
    String str_usuario,str_contraseña;
    String url = "https://rogdomain.ddns.net:8860/cardiofit/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NombreDelUsuario = findViewById(R.id.NombreSesion);
        PonerContraseña = findViewById(R.id.Contraseña);
        IniciarLaSesion = findViewById(R.id.BotonIniciarSesion);
        BotonParaRegistrarse = findViewById(R.id.BotonRegistrarse);
        LogoDeAplicacion = findViewById(R.id.Logo);
        muestra_contrasena = findViewById(R.id.mostrar_contraseña);

        muestra_contrasena.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean chequeo) {
                if(chequeo){
                    PonerContraseña.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                else{
                    PonerContraseña.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

    }

    public void InicioSesion (View view){

        String verificar_correo = NombreDelUsuario.getText().toString();
        String verificar_contraseña = PonerContraseña.getText().toString();

        if(verificar_correo.isEmpty()){
            NombreDelUsuario.setError("Ingrese un correo electronico");
        }
        else if(verificar_contraseña.isEmpty()){
            PonerContraseña.setError("Ingrese una contraseña");
        }
        else{

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Iniciando Sesion..");

            progressDialog.show();

            str_usuario = NombreDelUsuario.getText().toString().trim();
            str_contraseña = PonerContraseña.getText().toString().trim();


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

                    if(response.equalsIgnoreCase("Sesion Iniciada")){

                        NombreDelUsuario.setText("");
                        PonerContraseña.setText("");
                        //startActivity(new Intent(getApplicationContext(),MenuPrincipal.class));
                        Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);

                        intent.putExtra("str_usuario", str_usuario);

                        startActivity(intent);
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        NombreDelUsuario.setError("correo no valido");
                    }
                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("nombre_usuario",str_usuario);
                    params.put("contrasena",str_contraseña);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(request);

        }
    }

    public void Registrarse(View view){
        Intent intent = new Intent(MainActivity.this, VentanaRegistro.class);
        startActivity(intent);
    }
}
