package com.fup.jennyferlopez.proyectokitetkiwe.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import com.fup.jennyferlopez.proyectokitetkiwe.Items.GrupoDeItems;
import com.fup.jennyferlopez.proyectokitetkiwe.Items.adaptador;
import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.database.CategoriaDao;
import com.fup.jennyferlopez.proyectokitetkiwe.database.DatabaseHelper;
import com.fup.jennyferlopez.proyectokitetkiwe.database.ImagenDao;
import com.fup.jennyferlopez.proyectokitetkiwe.modelos.Categoria;
import com.fup.jennyferlopez.proyectokitetkiwe.modelos.Imagen;

import java.util.List;

public class GlosarioDos extends AppCompatActivity implements View.OnClickListener {

    SparseArray<GrupoDeItems> grupos = new SparseArray<GrupoDeItems>();

    CategoriaDao ca;
    Categoria c;
    ImagenDao im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glosario_dos);

        //Base de datos
        DatabaseHelper helper = new DatabaseHelper(this);

        ca = new CategoriaDao(getApplicationContext());
        im = new ImagenDao(getApplicationContext());

        ca = new CategoriaDao(getApplicationContext());

        crearDatos(); //aqui lleno el atributo "grupos"
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listaBD);
        adaptador adapter = new adaptador(this, grupos);
        listView.setAdapter(adapter);

        //Aqui inserto mis categorias
        c = new Categoria(0, "Vocales");
        ca.insertCategoria(c);
        c = new Categoria(0, "Consonantes");
        ca.insertCategoria(c);
        c = new Categoria(0,"Animales");
        ca.insertCategoria(c);
        c = new Categoria(0,"Plantas");
        ca.insertCategoria(c);
        c = new Categoria(0,"Flores");
        ca.insertCategoria(c);
        c = new Categoria(0,"Seres");
        ca.insertCategoria(c);
        c = new Categoria(0,"Simbolos");
        ca.insertCategoria(c);
        c = new Categoria(0,"Numeros");
        ca.insertCategoria(c);
        c = new Categoria(0,"Partesdelcuerpo");
        ca.insertCategoria(c);
        c = new Categoria(0,"Colores");
        ca.insertCategoria(c);
        c = new Categoria(0,"Familia");
        ca.insertCategoria(c);
        c = new Categoria(0,"Saludos");
        ca.insertCategoria(c);
        c = new Categoria(0,"Otros");
        ca.insertCategoria(c);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {



    }

    public void crearDatos() {

        List<Categoria> Lcategorias = ca.getAllCategoria();



        for(int i=0 ; i< Lcategorias.size() ; i++){
            List<Imagen> Limagen = im.getAllImagenByName(String.valueOf(i+1)); //las imagenes que existen en la categoria
            GrupoDeItems grupo = new GrupoDeItems(Lcategorias.get(i).getNombre()); //Colocamos el nombre de la categoria
            String titulo1 = Lcategorias.get(i).getNombre(), titulo2="";
            for(int k=1 ; k<titulo1.length() ; k++)
            {
                titulo2 = titulo2 + "" + String.valueOf(titulo1.charAt(k));
            }
            Log.d("categoria:", titulo2);
            for(int j=0 ; j<70 ; j++){
                String titulo = titulo2 + "" + j;
                if(getResources().getIdentifier(titulo, "drawable", getPackageName()) != 0) {
                    grupo.imagenes.add(getResources().getIdentifier(titulo, "drawable", getPackageName()));
                    Log.d("entro:",String.valueOf(getResources().getIdentifier(titulo, "drawable", getPackageName())));
                }

            }

            grupos.append(i, grupo);
        }

    }
}
