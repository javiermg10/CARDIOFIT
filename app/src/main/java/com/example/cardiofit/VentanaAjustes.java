package com.example.cardiofit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class VentanaAjustes extends AppCompatActivity  {

    Button botonapoliticas, botonversion, botonaplicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_ajustes);

        botonapoliticas = findViewById(R.id.BotonPoliticas);
        botonaplicacion = findViewById(R.id.BotonInformacion);
        botonversion = findViewById(R.id.BotonVersion);


    }
    public void AceptarTerminos(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Términos y Condiciones de privacidad");
        builder.setMessage("Estos Términos y condiciones regulan el acceso y uso del sitio web www.edp.com, bajo la responsabilidad de EDP S.A., con sede en la Avenida 24 de julio, 12, en Lisboa (en lo sucesivo, «EDP S.A.»). Cualquier cuestión legal relacionada con el sitio web se podrá consultar en las secciones Política de privacidad y Política de cookies, que forman parte integral de estos Términos y condiciones.\n" +
                "\n" +
                "El uso de este sitio web por parte de cualquier usuario se regirá por los siguientes Términos y condiciones, y su uso implica una aceptación de los mismos por el usuario. Si el usuario rechaza estos Términos y condiciones, debe cesar el uso del mismo.\n" +
                "\n" +
                "EDP S.A. podrá modificar o actualizar, de manera total o parcial, estos Términos y condiciones, así como la Política de privacidad y la Política de cookies. Cualquier cambio o actualización de estos Términos y condiciones, así como los de la Política de privacidad y Política de cookies, entrarán en vigor tan pronto como se publiquen en la sección correspondiente del sitio web. Se aconseja a los usuarios consultar regularmente las secciones de los Términos y condiciones, la Política de privacidad y la Política de cookies para comprobar las versiones más actualizadas.\n" +
                "\n" +
                "El acceso y uso del sitio web están disponibles exclusivamente para uso personal y promoción de la actividad de EDP S.A.\n" +
                "\n" +
                "EDP S.A. se reserva el derecho de evaluar, a su entera discreción, el cumplimiento de estos Términos y condiciones por parte de cualquier usuario. La violación de los Términos y condiciones podrá implicar la retirada de la licencia de uso concedida por EDP S.A., en virtud de esta cláusula n.º 1 y, así, ejercer sus derechos en la máxima extensión posible permitida por la ley.\n" +
                "\n" +
                "Los usuarios se comprometen a indemnizar y exonerar a EDP S.A., así como a los representantes legales, socios y trabajadores, de cualquier daño, responsabilidades, reclamaciones o solicitudes de compensación, incluidos los gastos y costes de representación, solicitados por terceros como consecuencia del uso del sitio web en disconformidad con estos Términos y condiciones o en virtud de la violación de las condiciones previstas en los mismos o que resulten del incumplimiento de las representaciones y garantías contenidas en estos Términos y condiciones.");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    public void Aplicacion(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cardiofit");
        builder.setMessage("CardioFit es una aplicación la cual nos permitirá llevar una rutina de entrenamiento equilibrada. Se pueden realizar rutinas de gimnasio, incluye diferentes ejercicios y están organizados por grupos musculares y zonas concretas del cuerpo. Además, los ejercicios se reflejan con varios accesorios, como mancuernas, máquinas o barras. \n"+
                        "\n" +
                        "En esta aplicación, podremos ver el progreso continuado del usuario, diseñado en el apartado de perfil donde tendrá sus datos físicos y datos personales recogidos de la base de datos. La aplicación también consta de un apartado de ajustes donde nos explicará y dejará ver varias opciones para aplicar en cuya aplicación. ");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    public void Version(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Version de CardioFit");
        builder.setMessage("v1.0");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

}
