package com.example.mainactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AgregarRecetaActivity extends AppCompatActivity {

    private CRUDRecetas CRUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CRUD = new CRUDRecetas(this);

        final EditText editTextTitulo = findViewById(R.id.editTextTitulo);
        final EditText editTextDescripcion = findViewById(R.id.editTextDescripcion);
        Button btnAgregarReceta = findViewById(R.id.btnAgregarReceta);

        btnAgregarReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener el título y la descripción ingresados por el usuario
                String titulo = editTextTitulo.getText().toString();
                String descripcion = editTextDescripcion.getText().toString();

                // Insertar la receta en la base de datos
                CRUD.insertarReceta(titulo, descripcion);


                Toast.makeText(AgregarRecetaActivity.this, "Receta agregada con éxito", Toast.LENGTH_SHORT).show();
                 finish(); // Para regresar a la actividad principal

                // Limpiar los campos de entrada
                editTextTitulo.setText("");
                editTextDescripcion.setText("");
            }
        });
    }
}