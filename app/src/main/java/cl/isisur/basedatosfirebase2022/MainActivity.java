package cl.isisur.basedatosfirebase2022;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cl.isisur.basedatosfirebase2022.Clases.Libro;


public class MainActivity extends AppCompatActivity {
        private List<Libro> ListLibro = new ArrayList<Libro>();
        ArrayAdapter<Libro> arrayAdapterLibro;

        EditText eTNombre,eTEditorial;
        Button bTBoton, btEliminar;
        ListView lvListadoLibros;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eTNombre=findViewById(R.id.eTNombre);
        eTEditorial=findViewById(R.id.eTEditorial);
        bTBoton=findViewById(R.id.bTAgregar);
        btEliminar=findViewById(R.id.btEliminar);
        lvListadoLibros=findViewById(R.id.lvListadoLibros);
        inicializarFireBase();
        //listarDatos();

        bTBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Libro libro = new Libro();
                //libro.setIdAutor("11111");
                libro.setIdAutor(UUID.randomUUID().toString());
                libro.setNombre(eTNombre.getText().toString());
                libro.setEstado(eTEditorial.getText().toString());
                databaseReference.child("Libro").child(libro.getIdAutor()).setValue(libro);


            }
        });


    }

    private void inicializarFireBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase =FirebaseDatabase.getInstance();
        databaseReference =firebaseDatabase.getReference();
    }
}