package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveltres;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.activities.SplashTodosActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.adapters.AdaptadorImagenes;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro.Nivel4Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos.ConcenConsonanActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno.Niveles14Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.ArrayList;
import java.util.List;

public class ConcenColoresActivity extends AppCompatActivity implements View.OnClickListener{

    int fondo = R.drawable.img_concentrate;
    int [] listadoImagenes = {
            R.drawable.voltear_azul,
            R.drawable.voltear_blanco,
            R.drawable.voltear_rosado,
            R.drawable.voltear_naranja,
            R.drawable.voltear_negro,
            R.drawable.voltear_verde
    };
    int cont_intentos=0, cont_good=0, cont_fail=0;

    int cantidadParejas = 6;
    int numeroParejas=6;
    int []imagenesFondo,imagenesSeleccionadasAleatorias;
    ArrayList<Integer> imagenSeleccionada;
    GridView gridView;
    AdaptadorImagenes adaptadorImagenes;
    ImageView img1,img2;
    int pos1=-1,pos2=-1, id_user;
    int cantImagenSeleccionada=0;
    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    TextView tv_puntos;
    ImageView icAvatarNiveles, imgAyuda;
    TextView tv_title;
    GestorBd db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concen_colores);
        db=new GestorBd(getApplication());

        tv_title = (TextView) findViewById(R.id.tv_title);
        icAvatarNiveles = (ImageView) findViewById(R.id.ic_avatarNiveles);
        tv_puntos = (TextView) findViewById(R.id.tv_puntos);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);
        tv_title.setTypeface(font);

        imgAyuda = (ImageView) findViewById(R.id.img_ayuda);
        imgAyuda.setOnClickListener(this);
        gridView = (GridView) findViewById(R.id.gridImagenes);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cantImagenSeleccionada++;

                ImageView imagen = (ImageView) view;
                if (pos1 == position || pos2 == position) {
                    cantImagenSeleccionada--;

                    return;
                }
                if (cantImagenSeleccionada == 1) {
                    pos1 = position;
                    img1 = imagen;
                }
                if (cantImagenSeleccionada == 2) {
                    pos2 = position;
                    img2 = imagen;
                }

                colocarImagen(position, imagen);
            }
        });
        inicializar();
        adaptadorImagenes =new AdaptadorImagenes(imagenesFondo, this);
        gridView.setAdapter(adaptadorImagenes);

        loadPreference();
        cargarTextV();
        cargarTextV();
        loadSplash();
    }

    private void loadSplash() {
        final Animation zoomAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom);
        imgAyuda.startAnimation(zoomAnimation);
        Bundle b= new Bundle();
        b.putString("text_uno", "Selecciona las parejas de los colores correspondientes");
        b.putString("text_dos", "");
        b.putInt("img_uno", R.drawable.img_concentrate);
        b.putInt("img_dos", 0);
        Intent irActivity= new Intent(ConcenColoresActivity.this, SplashTodosActivity.class);
        irActivity.putExtras(b);
        startActivity(irActivity);
    }
    private void cargarTextV() {
        id_user =db.obtenerId(userName);
        List<Puntos> pts=db.sumaPuntos(id_user);
        pts=db.sumaPuntos(id_user);
        int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
        tv_puntos.setText(""+ p);
    }

    private void loadPreference() {
        preferences = getSharedPreferences(Preference.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        avatarSeleccionado = preferences.getString(Preference.AVATAR_SEECCIONADO, "");
        userName =preferences.getString(Preference.USER_NAME, "");

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

    private void colocarImagen(int position, ImageView imagen) {
        imagen.setBackgroundResource(imagenesSeleccionadasAleatorias[position]);
        if (cantImagenSeleccionada==2){
            new Validar().execute();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_ayuda) {
            loadSplash();
        }
    }

    private class Validar extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            gridView.setEnabled(false);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (imagenesSeleccionadasAleatorias[pos1]==imagenesSeleccionadasAleatorias[pos2]){
                img1.setVisibility(View.INVISIBLE);
                img2.setVisibility(View.INVISIBLE);
                pos1=-1;
                pos2=-1;
                cantImagenSeleccionada=0;
                numeroParejas--;
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;

                // Toast.makeText(getApplication(), "u",Toast.LENGTH_SHORT).show();
            }

            gridView.setEnabled(true);
            resetImagenes();

            if (numeroParejas==0){
                if (cont_good ==6) {
                    Intent irMenu = new Intent(getApplication(), Niveles34Activity.class);
                    startActivity(irMenu);
                    finish();
                }if (cont_good==6 && cont_intentos ==6){
                    Puntos puntos= new Puntos(id_user, 3);
                    db.insertarPuntos(puntos);
                    List<Puntos> pts=db.sumaPuntos(id_user);
                    int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
                    tv_puntos.setText(""+ p);
                }else if (cont_good==6 && (cont_intentos >6 || cont_intentos <9)){
                    id_user =db.obtenerId(userName);
                    Puntos puntos= new Puntos(id_user, 2);
                    db.insertarPuntos(puntos);
                    List<Puntos> pts=db.sumaPuntos(id_user);
                    int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
                    tv_puntos.setText(""+ p);
                }else if (cont_good==6 && (cont_intentos >=9 || cont_intentos <=12)){
                    id_user =db.obtenerId(userName);
                    Puntos puntos= new Puntos(id_user, 1);
                    db.insertarPuntos(puntos);
                    List<Puntos> pts=db.sumaPuntos(id_user);
                    int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
                    tv_puntos.setText(""+ p);
                }else if (cont_good<4 && cont_intentos >12){
                    toastWarning();
                }
            }
        }

        private void toastWarning() {
            Toast toasta = new Toast(getApplicationContext());
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.toast_personalizado,
                    (ViewGroup) findViewById(R.id.lytLayout));

            TextView txtMsg = (TextView)layout.findViewById(R.id.tvMsjToast);
            ImageView imgToast =(ImageView) layout.findViewById(R.id.imgToast);
            imgToast.setBackgroundResource(R.drawable.toast_warning);
            txtMsg.setText("te recomendamos volver al nivel de reconocimiento tienes "+ cont_intentos +" fallidos");
            String font_url ="font/dklemonyellowsun.otf";
            Typeface font = Typeface.createFromAsset(getApplication().getResources().getAssets(), font_url);
            txtMsg.setTypeface(font);
            toasta.setDuration(Toast.LENGTH_SHORT);
            toasta.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0,0);
            toasta.setView(layout);
            toasta.show();

        }


    }

    // si las imagenes no coinciden se voltean
    private void resetImagenes() {
        if (img1!=null){
            img1.setBackgroundResource(fondo);
            img2.setBackgroundResource(fondo);
        }
        pos1=-1;
        pos2=-1;
        cantImagenSeleccionada=0;
        cont_fail=cont_fail+1;
        cont_intentos=cont_intentos+1;
        //Toast.makeText(this, "dos",Toast.LENGTH_SHORT).show();
    }

    // se inicia el juego
    private void inicializar() {
        getImagenesFondo();
        getImagenesSeleccionadas();
        getImagenesSeleccionadasAleatorias();
    }

    //se desordenan las imagenes del juego
    private void getImagenesSeleccionadasAleatorias() {
        imagenesSeleccionadasAleatorias=new  int[cantidadParejas*2];

        for (int i =0;i<cantidadParejas;i++) {
            int aux=0;
            do {
                int pos = (int) (Math.random() * (cantidadParejas * 2));
                if (imagenesSeleccionadasAleatorias[pos] == 0) {
                    imagenesSeleccionadasAleatorias[pos] = imagenSeleccionada.get(i);
                    aux++;
                }
            } while (aux < 2);
        }
    }

    // se seleccionan las imagenes que utilizaran en el juego
    private void getImagenesSeleccionadas() {
        imagenSeleccionada =new ArrayList<>();
        for (int i=0;i<cantidadParejas;i++){
            int aux= (int) (Math.random()*cantidadParejas);
            if (imagenSeleccionada.contains(listadoImagenes[aux])){
                i--;
            }else{
                imagenSeleccionada.add(listadoImagenes[aux]);
            }
        }
    }

    // se colocan las imagenes de fondo
    private void getImagenesFondo() {
        imagenesFondo=new int[cantidadParejas*2];
        for (int i=0;i<imagenesFondo.length;i++){
            imagenesFondo[i]=fondo;
        }
    }
}
