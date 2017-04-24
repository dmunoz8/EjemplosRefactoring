package cr.ac.ucr.ecci.cql.ejemplorefactoring;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Instanciar los textos y botones del layout activity_main.xml
        Button buttonFragmentos = (Button) findViewById(R.id.buttonFragmentos);
        // Asocia evento clic al boton
        buttonFragmentos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                irFrageentos();
            }
        });
    }
    // Ir a la actividad de fragmentos
    private void irFrageentos(){
        // Intent para llamar a la Actividad EjemploFragmentosActivity
        Intent intent = new Intent(this, EjemploFragmentosActivity.class);
        // Llamada a la actividad
        startActivity(intent);
    }
}
