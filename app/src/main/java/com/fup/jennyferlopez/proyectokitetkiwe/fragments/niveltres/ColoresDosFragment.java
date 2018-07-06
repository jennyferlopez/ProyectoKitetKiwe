package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveltres;

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
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos.Nivel21Activity;
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
public class ColoresDosFragment extends Fragment implements View.OnClickListener{
    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    TextView tv_puntos;
    ImageView icAvatarNiveles;
    TextView tv_title;
    int idUsuario;
    ServicioUsuario servicioUsuario;
    int id_user;
    ImageView c_verde, c_morado, c_rosado,c_gris, c_naranja;
    Button btnSiguiente;
    public ColoresDosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_colores_dos, container, false);
        c_verde =(ImageView) view.findViewById(R.id.c_verde);
        c_morado =(ImageView) view.findViewById(R.id.c_morado);
        c_rosado =(ImageView) view.findViewById(R.id.c_rosado);
        c_gris =(ImageView) view.findViewById(R.id.c_gris);
        c_naranja =(ImageView) view.findViewById(R.id.c_naranja);

        c_verde.setOnClickListener(this);
        c_morado.setOnClickListener(this);
        c_rosado.setOnClickListener(this);
        c_gris.setOnClickListener(this);
        c_naranja.setOnClickListener(this);
        btnSiguiente= (Button) view.findViewById(R.id.fab);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irNivel= new Intent(getActivity(), Niveles31Activity.class);
                startActivity(irNivel);
                getActivity().finish();
            }
        });
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        icAvatarNiveles = (ImageView) view.findViewById(R.id.ic_avatarNiveles);
        tv_puntos = (TextView) view.findViewById(R.id.tv_puntos);
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
        if (v.getId() == R.id.c_verde) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.verde);
            mp.start();
        }else if (v.getId() == R.id.c_morado) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.morado);
            mp.start();
        }else if (v.getId() == R.id.c_rosado) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.rosado);
            mp.start();
        }else if (v.getId() == R.id.c_gris) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.gris);
            mp.start();
        }else if (v.getId() == R.id.c_naranja) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.naranja);
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
