package com.fup.jennyferlopez.proyectokitetkiwe.activities;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.User;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.ServicioUsuario;

import java.net.URL;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmConfiguration;

public class RegistrarseActivity extends AppCompatActivity implements View.OnClickListener{

    GestorBd bd;
    EditText nombreUsuario, contraseña;
    Button btn_registrar, btn_cancelar;
    TextView tvNombre, tvContra;
    String activity, nombreUsu, contra;
    String pathimg;
    String activitydos;
    Realm realm;
    ServicioUsuario servicioUsuario;
    RealmAsyncTask transaction;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        bd= new GestorBd(getApplicationContext());
        nombreUsuario = (EditText) findViewById(R.id.edt_usuario_registro);
        contraseña = (EditText) findViewById(R.id.edt_contraseña_registro);
        btn_cancelar = (Button) findViewById(R.id.btn_cancelar);
        btn_registrar = (Button) findViewById(R.id.btn_registrar);
        tvNombre= (TextView) findViewById(R.id.tvNombreR);
        tvContra= (TextView) findViewById(R.id.tv_contraR);

        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        nombreUsuario.setTypeface(font);
        contraseña.setTypeface(font);
        btn_cancelar.setTypeface(font);
        btn_registrar.setTypeface(font);
        tvNombre.setTypeface(font);
        tvContra.setTypeface(font);
        activity= String.valueOf(RegistrarseActivity.class);
        activitydos="RegistrarseActivity";
        btn_registrar.setOnClickListener(this);
        loadRealm();
    }

    private void loadRealm() {
        Realm.init(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("Test1")
                .schemaVersion(1)
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);

        servicioUsuario = new ServicioUsuario(Realm.getDefaultInstance());

        User[] usuarios = servicioUsuario.obtenerUsuarios();

        for ( int i = 0; i< usuarios.length; i++){
            Log.d("RESULTADOS",usuarios[i].getNombreUsuario());
        }


        User usuario_por_id = servicioUsuario.obtenerUsuarioPorId("1");

//        Log.d("USUARIO 1",usuario_por_id.getNombreUsuario());

       // servicioUsuario.actualizarUsuarioNombre(usuario_por_id,"1","","","","",0);

        User usuario_tmp = servicioUsuario.obtenerUsuarioPorId("1");

//        Log.d("USUARIO 1",usuario_tmp.getNombreUsuario());

    }

    public void cancelar(View view) {
        Intent irLogin= new Intent(this, LogingActivity.class);
        startActivity(irLogin);
        finish();
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.btn_registrar){
            registrarUsuario();
        }
    }

    @SuppressLint("ShowToast")
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void registrarUsuario() {
        if (!nombreUsuario.getText().toString().isEmpty() && !contraseña.getText().toString().isEmpty()){
        nombreUsu = nombreUsuario.getText().toString();
        contra = contraseña.getText().toString();
        User usuario_por_id = servicioUsuario.obtenerUsuarioPorId(nombreUsu);
        if (usuario_por_id==null) {
            servicioUsuario.crearUsuario(nombreUsu,contra, "img","Nivel6Activity",0);
            nombreUsuario.setText("");
            contraseña.setText("");
        } else if (usuario_por_id.getNombreUsuario().equals(nombreUsu)){
            Toast.makeText(this,"El usuario ya existe", Toast.LENGTH_LONG).show();
            nombreUsuario.setText("");
            contraseña.setText("");
        }
        }if (nombreUsuario.getText().toString().isEmpty()){
            nombreUsuario.setError("Campo obligatorio");
        }if (contraseña.getText().toString().isEmpty()){
            contraseña.setError("Campo obligatorio");
        }

    }



}

