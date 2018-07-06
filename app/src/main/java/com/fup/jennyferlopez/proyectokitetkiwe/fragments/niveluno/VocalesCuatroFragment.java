package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
public class VocalesCuatroFragment extends Fragment implements View.OnClickListener{

    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    TextView tv_puntos;
    ImageView icAvatarNiveles;
    TextView tv_title;
    Button btnSiguiente;
    int id_user;
    private static final String TAG = "VocalesCuatroFragment";
    ImageView v_asp_a, v_asp_e, v_asp_i, v_asp_u, n_asp_a, n_asp_e, n_asp_i, n_asp_u;

    ServicioUsuario servicioUsuario;
    public VocalesCuatroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_vocales_cuatro, container, false);

        btnSiguiente= (Button) view.findViewById(R.id.fab);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irMenu= new Intent(getActivity(), Niveles11Activity.class);
                startActivity(irMenu);
                getActivity().finish();
            }
        });
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        icAvatarNiveles = (ImageView) view.findViewById(R.id.ic_avatarNiveles);
        tv_puntos = (TextView) view.findViewById(R.id.tv_puntos);

        v_asp_a=(ImageView) view.findViewById(R.id.v_asp_ah);
        v_asp_e=(ImageView) view.findViewById(R.id.v_asp_eh);
        v_asp_i=(ImageView) view.findViewById(R.id.v_asp_ih);
        v_asp_u=(ImageView) view.findViewById(R.id.v_asp_uh);
        n_asp_a=(ImageView) view.findViewById(R.id.v_aspnasa_ah);
        n_asp_e=(ImageView) view.findViewById(R.id.v_aspnasa_eh);
        n_asp_i=(ImageView) view.findViewById(R.id.v_aspnasa_ih);
        n_asp_u=(ImageView) view.findViewById(R.id.v_aspnasa_uh);

        v_asp_a.setOnClickListener(this);
        v_asp_e.setOnClickListener(this);
        v_asp_i.setOnClickListener(this);
        v_asp_u.setOnClickListener(this);
        n_asp_a.setOnClickListener(this);
        n_asp_e.setOnClickListener(this);
        n_asp_i.setOnClickListener(this);
        n_asp_u.setOnClickListener(this);

        String font_url = "font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);
        tv_title.setTypeface(font);

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
        if (v.getId() == R.id.v_asp_ah) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.v_asp_a);
            mp.start();

        }else if (v.getId() == R.id.v_asp_eh) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.v_asp_e);
            mp.start();
        }else if (v.getId() == R.id.v_asp_ih) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.v_asp_i);
            mp.start();
        }else if (v.getId() == R.id.v_asp_uh) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.v_asp_u);
            mp.start();
        }else if (v.getId() == R.id.v_aspnasa_ah) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.n_asp_a);
            mp.start();
        }else if (v.getId() == R.id.v_aspnasa_eh) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.n_asp_e);
            mp.start();
        }else if (v.getId() == R.id.v_aspnasa_ih) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.n_asp_i);
            mp.start();
        }else if (v.getId() == R.id.v_aspnasa_uh) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.n_asp_u);
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
