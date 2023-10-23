package com.example.mainactivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBorrar= findViewById(R.id.btnBorrar);
        CRUDRecetas CRUD = new CRUDRecetas(this);
        ArrayList<String> listaRecetitas = new ArrayList<String>();
        CRUD.insertarReceta("Chilaquiles", "rica comida casera");

        Button btnAgregarReceta = findViewById(R.id.btnAgregarReceta);
        btnAgregarReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Añadir una nueva receta a la base de datos
                CRUD.insertarReceta("Nueva Receta", "Descripción de la nueva receta");

                // Actualizar la lista de recetas mostradas
                listaRecetitas.clear();
                Cursor informacion = CRUD.mostrarRecetas();
                while (informacion.moveToNext()) {
                    String titulo = informacion.getString(1);
                    listaRecetitas.add(titulo);
                }
            }
        });
        EditText ID = findViewById(R.id.IDREceta);

        Button btnGuardarCambios = findViewById(R.id.btnEditarReceta);
        btnGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener los valores actualizados de título y descripción
                EditText nuevotitulo= findViewById(R.id.editTextNuevoTitulo);
                EditText vuevadescripcion= findViewById(R.id.editTextNuevaDescripcion);

                String nuevoTitulotext = nuevotitulo.getText().toString();
                String nuevaDescripciontext = vuevadescripcion.getText().toString();



                // Actualizar la receta en la base de datos
                CRUD.actualizarReceta(ID,nuevoTitulotext, nuevaDescripciontext);

                // Actualizar la lista de recetas mostradas
                listaRecetitas.clear();
                Cursor informacion = CRUD.mostrarRecetas();
                while (informacion.moveToNext()) {
                    String tituloReceta = informacion.getString(1);
                    listaRecetitas.add(tituloReceta);
                }
            }
        });

        EditText titulo= findViewById(R.id.editTextTitulo);
        EditText descripcion= findViewById(R.id.editTextDescripcion);




        String TituloText = titulo.getText().toString();
        String DescripcionText = descripcion.getText().toString();



       // CRUD.insertarReceta(TituloText, DescripcionText);
       // CRUD.actualizarReceta(id, TituloText, DescripcionText);

        ListView listaRecetas=findViewById(R.id.listarecetas);
        Cursor informacion=CRUD.mostrarRecetas();

        while (informacion.moveToNext()){
            String tituloText=informacion.getString(1);
            listaRecetitas.add(tituloText);


        }
        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaRecetitas);
        listaRecetas.setAdapter(adaptador);

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CRUD.eliminarReceta(1);

            }
        });


    }
}