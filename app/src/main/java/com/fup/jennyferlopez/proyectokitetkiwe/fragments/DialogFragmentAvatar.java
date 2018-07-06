package com.fup.jennyferlopez.proyectokitetkiwe.fragments;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.models.User;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.ServicioUsuario;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DialogFragmentAvatar extends DialogFragment implements View.OnClickListener {

    ImageView avatar, im_uno, im_dos, im_tres;
    RadioButton rbHombre, rbMujeres;
    Button btnGuardar, btn_mujer, btn_hombre;
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    String avatarSeleccionado, genero_avatar, userName;
    TextView escojer;

    ServicioUsuario servicioUsuario;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dialog_fragment, container, false);

        preferences = getActivity().getSharedPreferences(Preference.PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        avatar = (ImageView) view.findViewById(R.id.imgAvatar);
        im_uno = (ImageView) view.findViewById(R.id.img_unoC);
        im_uno.setOnClickListener(this);
        im_uno.setVisibility(View.INVISIBLE);
        im_dos = (ImageView) view.findViewById(R.id.img_dos);
        im_dos.setOnClickListener(this);
        im_dos.setVisibility(View.INVISIBLE);
        im_tres = (ImageView) view.findViewById(R.id.img_tres);
        im_tres.setOnClickListener(this);
        im_tres.setVisibility(View.INVISIBLE);

        btnGuardar=(Button) view.findViewById(R.id.btn_guardarAvatar);
        btnGuardar.setVisibility(View.INVISIBLE);
        btn_mujer=(Button) view.findViewById(R.id.btn_mujer);
        btn_mujer.setOnClickListener(this);
        btn_hombre=(Button) view.findViewById(R.id.btn_hombre);
        btn_hombre.setOnClickListener(this);
        escojer=(TextView) view.findViewById(R.id.escojer_a);

        String font_url ="font/dklemonyellowsun.otf";

        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        btnGuardar.setTypeface(font);
        btn_mujer.setTypeface(font);
        btn_hombre.setTypeface(font);
        escojer.setTypeface(font);
        btnGuardar.setOnClickListener(this);

        avatar.setBackgroundResource(R.drawable.avatar_blanco);

        loadRealm();
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


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id== R.id.btn_mujer){
            im_uno.setVisibility(View.VISIBLE);
            im_dos.setVisibility(View.VISIBLE);
            im_tres.setVisibility(View.VISIBLE);
            btnGuardar.setVisibility(View.VISIBLE);
            btnGuardar.setBackgroundResource(R.drawable.btn_mujer);
            im_uno.setBackgroundResource(R.drawable.ic_nina_uno);
            im_dos.setBackgroundResource(R.drawable.ic_nina_dos);
            im_tres.setBackgroundResource(R.drawable.ic_nina_tres);
            editor.putString(Preference.GENERO_AVATAR, "1");
            editor.commit();

        }else if (id== R.id.btn_hombre){
            im_uno.setVisibility(View.VISIBLE);
            im_dos.setVisibility(View.VISIBLE);
            im_tres.setVisibility(View.VISIBLE);
            btnGuardar.setVisibility(View.VISIBLE);
            btnGuardar.setBackgroundResource(R.drawable.btn_hombre);

            im_uno.setBackgroundResource(R.drawable.ic_nino_uno);
            im_dos.setBackgroundResource(R.drawable.ic_nino_dos);
            im_tres.setBackgroundResource(R.drawable.ic_nino_tres);

            editor.putString(Preference.GENERO_AVATAR, "2");
            editor.commit();
        }

        genero_avatar =preferences.getString(Preference.GENERO_AVATAR, "");
        if (id == R.id.img_unoC && genero_avatar.equals("2")) {
            avatar.setBackgroundResource(R.drawable.nino_uno);
            avatarSeleccionado="1";
        } else if (id == R.id.img_dos && genero_avatar.equals("2")) {
            avatar.setBackgroundResource(R.drawable.nino_dos);
            avatarSeleccionado="2";
        } else if (id == R.id.img_tres && genero_avatar.equals("2")) {
            avatar.setBackgroundResource(R.drawable.nino_tres);
            avatarSeleccionado="3";
        } else if (id == R.id.img_unoC && genero_avatar.equals("1")) {
            avatar.setBackgroundResource(R.drawable.nina_uno);
            avatarSeleccionado="4";
        } else if (id == R.id.img_dos && genero_avatar.equals("1")) {
            avatar.setBackgroundResource(R.drawable.nina_dos);
            avatarSeleccionado="5";
        } else if (id == R.id.img_tres && genero_avatar.equals("1")) {
            avatar.setBackgroundResource(R.drawable.nina_tres);
            avatarSeleccionado="6";
        }if (id == R.id.btn_guardarAvatar){
            userName =preferences.getString(Preference.USER_NAME, "");
            User usuario_por_id = servicioUsuario.obtenerUsuarioPorId(userName);
            if (usuario_por_id!=null) {
                servicioUsuario.actualizarUsuarioNombre(usuario_por_id,"","","",avatarSeleccionado,"",0);
                dismiss();
            }

            //getActivity().recreate();
        }

    }

}
