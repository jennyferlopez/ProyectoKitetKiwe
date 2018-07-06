package com.fup.jennyferlopez.proyectokitetkiwe.Items;

import android.app.Activity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.GridView;
import android.widget.ImageView;

import com.fup.jennyferlopez.proyectokitetkiwe.R;


//childre = l

/**
 * Created by Yuri on 17/04/2016.
 */
public class adaptador extends BaseExpandableListAdapter{

    private final SparseArray<GrupoDeItems> grupos;
    public LayoutInflater inflater;
    public Activity activity;

    //llenando
    private GridView gridView;


    // Constructor
    public adaptador(Activity act, SparseArray<GrupoDeItems> grupos) {
        activity = act;
        this.grupos = grupos;
        inflater = act.getLayoutInflater();
    }

    // Nos devuelve los datos asociados a un subitem en base
    // a la posición
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        //return grupos.get(groupPosition).l.get(childPosition);
        return grupos.get(groupPosition).imagenes.get(childPosition);
    }

    // Devuelve el id de un item o subitem en base a la
    // posición de item y subitem
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    //La cantidad de ítem que tenemos definidos
    @Override
    public int getGroupCount() {
        return grupos.size();
    }

    // Nos devuelve la cantidad de subitems que tiene un ítem
    @Override
    public int getChildrenCount(int groupPosition) {
        //return grupos.get(groupPosition).l.size();
        return grupos.get(groupPosition).imagenes.size();
    }

    //Los datos de un ítem especificado por groupPosition
    @Override
    public Object getGroup(int groupPosition) {
        return grupos.get(groupPosition);
    }

    //Devuelve el id de un ítem
    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //Obtenemos el layout para los ítems
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item, null);
        }
        GrupoDeItems grupo = (GrupoDeItems) getGroup(groupPosition);
        ((CheckedTextView) convertView).setText(grupo.string);
        ((CheckedTextView) convertView).setChecked(isExpanded);
        return convertView;
    }

    // En base a la posición del item y de subitem nos devuelve
    // el objeto view correspondiente y el layout para los subitems
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //final String children = (String) getChild(groupPosition, childPosition);

        final int children = (int)getChild(groupPosition, childPosition);

        /*TextView textvw = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.subitems, null);
        }
        textvw = (TextView) convertView.findViewById(R.id.textView1);
        textvw.setText(children); convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, children, Toast.LENGTH_SHORT).show();
            }
        });*/

        ImageView imagenMostrar = null;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.subitems, null);
        }
        imagenMostrar = (ImageView)convertView.findViewById(R.id.imagen_mostrar);

        imagenMostrar.setImageResource(children);

        /*
        Uri path = Uri.parse(children);
        imagenMostrar.setImageURI(path);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, children, Toast.LENGTH_SHORT).show();
            }
        });*/

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    //Método que se invoca al contraer un ítem
    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }
    //Método que se invoca al expandir un ítem
    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

}
