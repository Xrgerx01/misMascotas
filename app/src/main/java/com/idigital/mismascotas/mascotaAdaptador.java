package com.idigital.mismascotas;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Roger on 19/03/2017.
 */

public class mascotaAdaptador extends RecyclerView.Adapter<mascotaAdaptador.MascotaViewHolder>{

    ArrayList <Mascotas> mascotas ;
    private Integer contador;
    Activity activity;

    public mascotaAdaptador(ArrayList<Mascotas> mascotas, Activity activity) {

        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Asocia Layout de la tarjeta
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascotas, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int position) { // Recorre la lista y obtiene la posición
        final Mascotas mascota = mascotas.get(position);
        mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvNombreCV.setText(mascota.getNombre());
        mascotaViewHolder.tvFavoritoCV.setText("" + mascota.getFavorito());

        mascotaViewHolder.imghuesop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contador = mascota.getFavorito();
                contador = contador + 1;
                mascota.setFavorito(contador);
                mascotaViewHolder.tvFavoritoCV.setText(Integer.toString(mascota.getFavorito()));
            }
        });

        mascotaViewHolder.imghuesob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contador = mascota.getFavorito();
                if (contador <= 0){
                    contador = 1;
                } else {
                    contador = contador - 1;
                    mascota.setFavorito(contador);
                    mascotaViewHolder.tvFavoritoCV.setText(Integer.toString(mascota.getFavorito()));
                }
            }
        });
    }

    @Override
    public int getItemCount() { //Cantidad de elementos de la lista
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto ;
        private TextView tvNombreCV;
        private TextView tvFavoritoCV;
        private ImageView imghuesob;
        private ImageView imghuesop;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto      = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombreCV   = (TextView) itemView.findViewById(R.id.tvNombreCV);
            tvFavoritoCV = (TextView) itemView.findViewById(R.id.tvFavoritoCV);
            imghuesob = (ImageView) itemView.findViewById(R.id.imghuesob);
            imghuesop = (ImageView) itemView.findViewById(R.id.imghuesop);
        }
    }
}
