package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos;


import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.models.User;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.ServicioUsuario;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsPalatalesFragment extends Fragment implements View.OnClickListener{

    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    TextView tv_puntos;
    ImageView icAvatarNiveles;
    TextView tv_title;
    int idUsuario;
    GestorBd db;
    int id_user;
    ImageView con_px, con_nx, con_tx, con_bx, con_cx, con_dx, con_kx, con_gx, con_vx, con_jx, con_lx, con_zx;

    ServicioUsuario servicioUsuario;
    public ConsPalatalesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cons_palatales, container, false);
        con_px =(ImageView) view.findViewById(R.id.c_pal_px);
        con_nx =(ImageView) view.findViewById(R.id.c_pal_nx);
        con_tx =(ImageView) view.findViewById(R.id.c_pal_tx);
        con_bx =(ImageView) view.findViewById(R.id.c_pal_bx);
        con_cx =(ImageView) view.findViewById(R.id.c_pal_cx);
        con_dx =(ImageView) view.findViewById(R.id.c_pal_dx);
        con_kx =(ImageView) view.findViewById(R.id.c_pal_kx);
        con_gx =(ImageView) view.findViewById(R.id.c_pal_gx);
        con_vx =(ImageView) view.findViewById(R.id.c_pal_vx);
        con_jx =(ImageView) view.findViewById(R.id.c_pal_jx);
        con_lx =(ImageView) view.findViewById(R.id.c_pal_lx);
        con_zx =(ImageView) view.findViewById(R.id.c_pal_zx);

        con_px.setOnClickListener(this);
        con_nx.setOnClickListener(this);
        con_tx.setOnClickListener(this);
        con_bx.setOnClickListener(this);
        con_cx.setOnClickListener(this);
        con_dx.setOnClickListener(this);
        con_kx.setOnClickListener(this);
        con_gx.setOnClickListener(this);
        con_vx.setOnClickListener(this);
        con_jx.setOnClickListener(this);
        con_lx.setOnClickListener(this);
        con_zx.setOnClickListener(this);

        tv_title = (TextView) view.findViewById(R.id.tv_title);
        icAvatarNiveles = (ImageView) view.findViewById(R.id.ic_avatarNiveles);
        tv_puntos = (TextView) view.findViewById(R.id.tv_puntos);
        String font_url = "font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);
        tv_title.setTypeface(font);
        db=new GestorBd(getContext());

        loadPreference();
        loadRealm();
        actualizarPuntos();
        return view;
    }

    private void loadRealm() {
        Realm.init(getActivity());

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("Test1")
                .schemaVersion(1)
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);

        servicioUsuario = new ServicioUsuario(Realm.getDefaultInstance());

    }

    private void loadPreference() {
        preferences = getActivity().getSharedPreferences(Preference.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        avatarSeleccionado = preferences.getString(Preference.AVATAR_SEECCIONADO, "");
        userName = preferences.getString(Preference.USER_NAME, "");
        idUsuario = preferences.getInt(Preference.USER_ID, 0);

        if (avatarSeleccionado.equals(null)) {
            icAvatarNiveles.setBackgroundResource(Integer.parseInt(null));
        } else if (avatarSeleccionado.equals("1")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_uno_n);
        } else if (avatarSeleccionado.equals("2")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_dos_n);
        } else if (avatarSeleccionado.equals("3")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_tres_n);
        } else if (avatarSeleccionado.equals("4")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_uno_n);
        } else if (avatarSeleccionado.equals("5")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_dos_n);
        } else if (avatarSeleccionado.equals("6")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_tres_n);
        }

    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.c_pal_px) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.c_palatales_px);
            mp.start();
        }else if (v.getId() == R.id.c_pal_nx) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.c_palatales_nx);
            mp.start();
        }else if (v.getId() == R.id.c_pal_tx) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.c_palatales_tx);
            mp.start();
        }else if (v.getId() == R.id.c_pal_bx) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.c_palatales_bx);
            mp.start();
        }else if (v.getId() == R.id.c_pal_cx) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.c_palatales_cx);
            mp.start();
        }else if (v.getId() == R.id.c_pal_dx) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.c_palatales_dx);
            mp.start();
        }else if (v.getId() == R.id.c_pal_kx) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.c_palatales_kx);
            mp.start();
        }else if (v.getId() == R.id.c_pal_gx) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.c_palatales_gx);
            mp.start();
        }else if (v.getId() == R.id.c_pal_vx) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.c_palatales_vx);
            mp.start();
        }else if (v.getId() == R.id.c_pal_jx) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.c_palatales_jx);
            mp.start();
        }else if (v.getId() == R.id.c_pal_lx) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.c_palatales_lx);
            mp.start();
        }else if (v.getId() == R.id.c_pal_zx) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.c_palatales_zx);
            mp.start();
        }
    }
    private void actualizarPuntos() {
        userName =preferences.getString(Preference.USER_NAME, "");
        User usuario_por_id = servicioUsuario.obtenerUsuarioPorId(userName);
        if (usuario_por_id!=null) {
            int p=Integer.parseInt(String.valueOf(usuario_por_id.getPuntos()));
            tv_puntos.setText(""+ p);
        }
    }

}
