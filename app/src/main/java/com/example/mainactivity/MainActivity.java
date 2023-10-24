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

    private CRUDRecetas CRUD;
    private ArrayAdapter<String> Adaptador;
    private ArrayList<String> listaRecetitas;
    private ListView listarecetas;

    private EditText IDreceta, editTextTitulo, editTextDescripcion;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CRUD = new CRUDRecetas(this);
        listaRecetitas = new ArrayList<>();
        listarecetas = findViewById(R.id.listarecetas);

        Button btnAgregarReceta = findViewById(R.id.btnAgregarReceta);
        Button btnEditarReceta = findViewById(R.id.btnEditarReceta);
        Button btnBorrar = findViewById(R.id.btnBorrar);

        IDreceta = findViewById(R.id.IDreceta);
        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextDescripcion = findViewById(R.id.editTextDescripcion);


        btnAgregarReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = editTextTitulo.getText().toString();
                String descripcion = editTextDescripcion.getText().toString();
                if (!titulo.isEmpty() && !descripcion.isEmpty()) {
                    CRUD.insertarReceta(titulo, descripcion);
                }
            }
        });

        btnEditarReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener los valores actualizados de título y descripción
                String IDString = IDreceta.getText().toString();
                String titulo = editTextTitulo.getText().toString();
                String descripcion = editTextDescripcion.getText().toString();

                if (!IDString.isEmpty()) {
                    int id = Integer.parseInt(IDString);
                    if (!titulo.isEmpty() && !descripcion.isEmpty()) {
                        CRUD.actualizarReceta(id, titulo, descripcion);
                        refrescarlista();
                        limpiarcampo();
                    } else {
                        Cursor c = CRUD.mostrarRecetas();
                        while (c.moveToNext()) {
                            if (c.getInt(0) == id) {
                                editTextTitulo.setText(c.getString(1));
                                editTextDescripcion.setText(c.getString(2));
                                break;

                            }
                        }
                    }
                }
            }
        });


        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String IDString = IDreceta.getText().toString();
                if(!IDString.isEmpty()){
                int id = Integer.parseInt(IDString);
                    CRUD.eliminarReceta(id);
                    refrescarlista();
                    limpiarcampo();
                }
            }
        });
    }
    // Método para refrescar la lista de recetas
    private void refrescarlista() {
        listaRecetitas.clear();
        Cursor c = CRUD.mostrarRecetas();

        while (c.moveToNext()) {
            String tituloReceta = c.getString(1);
            listaRecetitas.add(tituloReceta);
        }
        c.close();

        Adaptador.notifyDataSetChanged();
    }

    // Método para limpiar los campos de entrada
    private void limpiarcampo() {
        IDreceta.setText("");
        editTextTitulo.setText("");
        editTextDescripcion.setText("");


    }
}
