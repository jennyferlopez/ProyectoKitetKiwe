package com.fup.jennyferlopez.proyectokitetkiwe.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.adapters.GlosarioAdapter;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Glosario;

import java.util.ArrayList;
import java.util.List;
import android.support.v7.widget.SearchView;

public class GlosarioCardsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{


    private RecyclerView recycler;
    public GlosarioAdapter adapter;
    private RecyclerView.LayoutManager lManager;
    private SearchView mSearchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glosario_cards);
        List<Glosario> items = new ArrayList<>();

        mSearchView = (SearchView) findViewById(R.id.search_view);
        items.add(new Glosario(R.drawable.color_glosario_uno, "Colores", "no"));
        items.add(new Glosario(R.drawable.color_glosario_dos, "amarillo","cxkitx"));
        items.add(new Glosario(R.drawable.color_glosario_tres, "azul","cey"));

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout

        recycler.setHasFixedSize(true);
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new GlosarioAdapter(this,items);
        recycler.setAdapter(adapter);
        setupSearchView();
    }
    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Buscar...");
    }

    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return true;
    }

    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    }

