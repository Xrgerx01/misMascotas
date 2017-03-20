package com.idigital.mismascotas;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.idigital.mismascotas.Mascotas;
import com.idigital.mismascotas.R;

import java.util.ArrayList;

import static com.idigital.mismascotas.R.id.rvMascotas;

/**
 * Created by Roger on 19/03/2017.
 */

public class DetalleMascota extends AppCompatActivity{

    ArrayList<Mascotas> mascotas ;
    private RecyclerView listamascotas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_mascotas);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


        Bundle parametros = getIntent().getExtras();

        String[] nombres = parametros.getStringArray("nombres");
        int[] fotos = parametros.getIntArray("fotos");
        int[] likes = parametros.getIntArray("likes");

        listamascotas = (RecyclerView) findViewById(rvMascotas);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listamascotas.setLayoutManager(llm);
        inicializarListadeMascotas();

        for (int i=0; i < mascotas.size(); i++){
            mascotas.get(i).setFavorito(likes[i]);
        }
        inicializarAdaptador();
    }

    public mascotaAdaptador adaptador;
    public void inicializarAdaptador(){
        adaptador = new mascotaAdaptador(mascotas, this);
        listamascotas.setAdapter(adaptador);
    }

    public void inicializarListadeMascotas () {
        mascotas= new ArrayList<Mascotas>();

        mascotas.add(new Mascotas(R.drawable.conejo,"Conejo",0));
        mascotas.add(new Mascotas(R.drawable.gato,"Gato",0));
        mascotas.add(new Mascotas(R.drawable.perro,"Perro",0));
        mascotas.add(new Mascotas(R.drawable.pluto,"Pluto",0));
        mascotas.add(new Mascotas(R.drawable.garfield,"Garfield",0));
    }
}
