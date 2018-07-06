package com.fup.jennyferlopez.proyectokitetkiwe.adapters;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.activities.GlosarioCardsActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Glosario;

import java.util.ArrayList;
import java.util.List;

public class GlosarioAdapter extends RecyclerView.Adapter<GlosarioAdapter.AnimeViewHolder> {
    private List<Glosario> listItems, filterList;

    public static Activity activity;
    public static class AnimeViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre;
        public TextView nombreKitet;

        public AnimeViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.imagen);
            nombre = (TextView) v.findViewById(R.id.nombre);
            nombreKitet = (TextView) v.findViewById(R.id.nombreKitet);

            String font_url ="font/dklemonyellowsun.otf";
            Typeface font = Typeface.createFromAsset(activity.getResources().getAssets(), font_url);
            nombre.setTypeface(font);
            nombreKitet.setTypeface(font);
        }
    }

    public GlosarioAdapter(GlosarioCardsActivity activity, List<Glosario> listItems) {
        this.listItems = listItems;
        this.activity = activity;
        this.filterList = new ArrayList<Glosario>();
        this.filterList.addAll(this.listItems);
    }

    @Override
    public int getItemCount() {
        return (null != filterList ? filterList.size() : 0);
    }

    @Override
    public AnimeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.glosario_card, viewGroup, false);
        return new AnimeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AnimeViewHolder viewHolder, int i) {
        Glosario listItem = filterList.get(i);
        viewHolder.imagen.setImageResource(listItem.getImagen());
        viewHolder.nombre.setText(listItem.getNombre());
    }
    public void filter(final String text) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                filterList.clear();
                if (TextUtils.isEmpty(text)) {

                    filterList.addAll(listItems);

                } else {
                    for (Glosario item : listItems) {
                        if (item.getNombre().toLowerCase().contains(text.toLowerCase()) ||
                                item.getNombreKitet().toLowerCase().contains(text.toLowerCase())) {
                            filterList.add(item);
                        }
                    }
                }

                ((Activity) activity).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        notifyDataSetChanged();
                    }
                });

            }
        }).start();

    }
}
