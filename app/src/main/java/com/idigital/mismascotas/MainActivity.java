package com.idigital.mismascotas;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import static com.idigital.mismascotas.R.menu.top5;

public class MainActivity extends AppCompatActivity {

    ArrayList<Mascotas> mascotas;
    private RecyclerView listamascotas;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        ActionBar ab = getSupportActionBar();
        ab.setDefaultDisplayHomeAsUpEnabled(true);

        listamascotas = (RecyclerView) findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listamascotas.setLayoutManager(llm);
        inicializarListadeMascotas();
        inicializarAdaptador();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(top5, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){

        switch (item.getItemId()) {
            case R.id.btnStar:
                Intent intent = new Intent(this, DetalleMascota.class);
                ArrayList<Mascotas> ranking = mascotas;
                do {
                    int index = 0;
                    int likes = 0;
                    likes = ranking.get(0).getFavorito();
                    for (int i = 0; i < ranking.size(); i++) {

                        if (ranking.get(i).getFavorito() <= likes) {
                            likes = ranking.get(i).getFavorito();
                            index = i;
                        }
                    }
                    ranking.remove(ranking.get(index));
                } while (ranking.size() > 5);

                int i = 0;
                int[] fotos = new int[5];
                int[] likes = new int[5];
                String[] nombre = new String[5];

                for (Mascotas pet : ranking) {
                    fotos[i] = pet.getFoto();
                    nombre[i] = pet.getNombre();
                    likes[i] = pet.getFavorito();
                    i++;
                }

                intent.putExtra("nombres", nombre);
                intent.putExtra("fotos", fotos);
                intent.putExtra("likes", likes);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected (item);
    }

    public void inicializarAdaptador() {
        mascotaAdaptador adaptador = new mascotaAdaptador(mascotas, this);
        listamascotas.setAdapter(adaptador);

    }

    public void inicializarListadeMascotas() {
        mascotas = new ArrayList<Mascotas>();

        mascotas.add(new Mascotas(R.drawable.conejo, "Conejo", 0));
        mascotas.add(new Mascotas(R.drawable.gato, "Gato", 0));
        mascotas.add(new Mascotas(R.drawable.perro, "Perro", 0));
        mascotas.add(new Mascotas(R.drawable.pluto, "Pluto", 0));
        mascotas.add(new Mascotas(R.drawable.garfield, "Garfield", 0));


    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
