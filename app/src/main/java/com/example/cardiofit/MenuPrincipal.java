package com.example.cardiofit;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MenuPrincipal extends AppCompatActivity {

    CardView CardViewCrossfit, CardViewCuadrices, CardViewEstiramientos, CardViewTonificacion, CardViewResistencia, CardViewAbdominales, CardViewDietas, CardViewGluteos;
    ImageButton BotonCrossfit, BotonCuadriceps, BotonEstiramientos, BotonTonificacion, BotonResistencia, BotonAbdominales, BotonDietas, BotonGluteos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        CardViewCrossfit = findViewById(R.id.CardCrossfit);
        CardViewCuadrices = findViewById(R.id.CardCrossfit);
        CardViewEstiramientos = findViewById(R.id.CardCrossfit);
        CardViewTonificacion = findViewById(R.id.CardCrossfit);
        CardViewResistencia = findViewById(R.id.CardCrossfit);
        CardViewAbdominales = findViewById(R.id.CardCrossfit);
        CardViewDietas = findViewById(R.id.CardCrossfit);
        CardViewGluteos = findViewById(R.id.CardCrossfit);

        BotonCrossfit = findViewById(R.id.Crossfit);
        BotonCuadriceps = findViewById(R.id.Cuadriceps);
        BotonEstiramientos = findViewById(R.id.Estiramientos);
        BotonTonificacion = findViewById(R.id.Tonificacion);
        BotonResistencia = findViewById(R.id.Resistencia);
        BotonAbdominales = findViewById(R.id.Abdominales);
        BotonDietas = findViewById(R.id.Dieta);
        BotonGluteos = findViewById(R.id.Gluteos);

    }

    public void IrCrossfit(View view){
        Intent intent = new Intent(this, EjercicioCrossfit.class);
        startActivity(intent);
    }
    public void IrCuadriceps(View view){
        Intent intent2 = new Intent(this, EjercicioCuadriceps.class);
        startActivity(intent2);
    }
    public void IrEstiramientos(View view){
        Intent intent3 = new Intent(this, EjercicioEstiramientos.class);
        startActivity(intent3);
    }
    public void IrTonificacion(View view){
        Intent intent4 = new Intent(this, EjercicioTonificacion.class);
        startActivity(intent4);
    }
    public void IrResistencia(View view){
        Intent intent5 = new Intent(this, EjercicioResistencia.class);
        startActivity(intent5);
    }
    public void IrAbdominales(View view){
        Intent intent6 = new Intent(this, EjercicioAbdominales.class);
        startActivity(intent6);
    }
    public void IrDietas(View view){
        Intent intent7 = new Intent(this, EjemplosDietas.class);
        startActivity(intent7);
    }
    public void IrGluteos(View view){
        Intent intent8 = new Intent(this, EjercicioGluteos.class);
        startActivity(intent8);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menusuperior, menu);
        return true;
    }

    //Método para asignar las funciones correspondientes a las opciones.
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.ItemAjustes:
                Intent intent = new Intent(this, VentanaAjustes.class);
                startActivity(intent);
                break;

            case R.id.ItemPerfil:
                Intent intentrecuperar3 = getIntent();

                String string_usuario_recibido2 = intentrecuperar3.getStringExtra("str_usuario");

                Intent intent2 = new Intent(this, VentanaPerfil.class);

                intent2.putExtra("str_usuario", string_usuario_recibido2);

                startActivity(intent2);

                /*Intent intent2 = new Intent(this, VentanaPerfil.class);
                startActivity(intent2);*/
                break;

            case R.id.ItemRegistrarEntrenamiento:
                Intent intentrecuperar1 = getIntent();

                String string_usuario_recibido = intentrecuperar1.getStringExtra("str_usuario");

                Intent intent5 = new Intent(this, VentanaRegistroEntrenamiento.class);

                intent5.putExtra("str_usuario", string_usuario_recibido);

                startActivity(intent5);
                /*Intent intent5 = new Intent(this, VentanaRegistroEntrenamiento.class);
                startActivity(intent5);*/
                break;

            case R.id.ItemHistorial:
                Intent intentrecuperar2 = getIntent();

                String string_usuario_recibido1 = intentrecuperar2.getStringExtra("str_usuario");

                Intent intent3 = new Intent(this, VentanaHistorialActividades.class);

                intent3.putExtra("str_usuario", string_usuario_recibido1);

                startActivity(intent3);

                /*Intent intent3 = new Intent(this, VentanaHistorialActividades.class);
                startActivity(intent3);*/
                break;

            case R.id.ItemCerrarSesion:
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Cerrando Sesion...");

                progressDialog.show();

                Intent intent4 = new Intent(this, MainActivity.class);
                startActivity(intent4);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
