package com.example.e_quality.Asignaturas;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.e_quality.Alarmas.AlarmaActivity;
import com.example.e_quality.Principal.AboutActivity;
import com.example.e_quality.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.String.valueOf;

// CODIGO DE https://mrwhotheengineer.com/2018/03/how-to-create-android-wikipedia-app-in-android-studio.html
public class MujeresActivity extends AppCompatActivity implements View.OnClickListener, OnInitListener {

    private TextView textView;
    //TTS object
    private TextToSpeech myTTS;
    //status check code
    private int MY_DATA_CHECK_CODE = 0;
    private Button speakButton;
    public static int cientifica;
    private static String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mujeres);
        textView=(TextView) findViewById(R.id.id_text_view);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        speakButton = (Button)findViewById(R.id.speak);
        speakButton.setOnClickListener(this);
        //Se verifica si hay datos TTS
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);

        selectInsert();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_option, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.opcion_alarma){
            Intent configuracion=new Intent(this, AlarmaActivity.class);
            startActivity(configuracion);
        }
        if(item.getItemId()==R.id.option_about){
            Intent about=new Intent(this, AboutActivity.class);
            startActivity(about);
        }

        return super.onOptionsItemSelected(item);
    }


    public void selectInsert(){
        Intent miIntent = getIntent();
        int boton = miIntent.getIntExtra("ButtonId", 0);

        if(boton == R.id.liseMeitner){
            String wiki_url=getResources().getString(R.string.liseMeitnerURL);
            //START ASYNC TASK
            WikiDataAsync wikiDataAsync = new WikiDataAsync();
            wikiDataAsync.execute(wiki_url);
            cientifica = 1;
        }else if(boton == R.id.louiseDolan){
            String wiki_url=getResources().getString(R.string.loiseDolanURL);
            //START ASYNC TASK
            WikiDataAsync wikiDataAsync = new WikiDataAsync();
            wikiDataAsync.execute(wiki_url);
            cientifica = 2;
        }else if(boton == R.id.rachelWebster){
            String wiki_url=getResources().getString(R.string.rachalWebsterURL);
            //START ASYNC TASK
            WikiDataAsync wikiDataAsync = new WikiDataAsync();
            wikiDataAsync.execute(wiki_url);
            cientifica = 3;
        }else if(boton == R.id.dianFossey){
            String wiki_url=getResources().getString(R.string.dianFosseyURL);
            //START ASYNC TASK
            WikiDataAsync wikiDataAsync = new WikiDataAsync();
            wikiDataAsync.execute(wiki_url);
            cientifica = 4;
        }else if(boton == R.id.mariaEugenia){
            String wiki_url=getResources().getString(R.string.mariaEugeniaURL);
            //START ASYNC TASK
            WikiDataAsync wikiDataAsync = new WikiDataAsync();
            wikiDataAsync.execute(wiki_url);
            cientifica = 5;
        }else if(boton == R.id.zehraSayers){
            String wiki_url=getResources().getString(R.string.zehraSayersURL);
            //START ASYNC TASK
            WikiDataAsync wikiDataAsync = new WikiDataAsync();
            wikiDataAsync.execute(wiki_url);
            cientifica = 6;
        }else if(boton == R.id.angelaBelcher){
            String wiki_url=getResources().getString(R.string.angelaBelcherURL);
            //START ASYNC TASK
            WikiDataAsync wikiDataAsync = new WikiDataAsync();
            wikiDataAsync.execute(wiki_url);
            cientifica = 7;
        }else if(boton == R.id.marieCurie){
            String wiki_url=getResources().getString(R.string.mariaEugeniaURL);
            //START ASYNC TASK
            WikiDataAsync wikiDataAsync = new WikiDataAsync();
            wikiDataAsync.execute(wiki_url);
            cientifica = 8;
        }else if(boton == R.id.susanSolomon){
            String wiki_url=getResources().getString(R.string.susanSolomonURL);
            //START ASYNC TASK
            WikiDataAsync wikiDataAsync = new WikiDataAsync();
            wikiDataAsync.execute(wiki_url);
            cientifica = 9;
        }else if(boton == R.id.sojournerTruth){
            String wiki_url=getResources().getString(R.string.sojournerTruthURL);
            //START ASYNC TASK
            WikiDataAsync wikiDataAsync = new WikiDataAsync();
            wikiDataAsync.execute(wiki_url);
            cientifica = 10;
        }else if(boton == R.id.shulamithFirestone){
            String wiki_url=getResources().getString(R.string.shumalamithFirestoneURL);
            //START ASYNC TASK
            WikiDataAsync wikiDataAsync = new WikiDataAsync();
            wikiDataAsync.execute(wiki_url);
            cientifica = 11;
        }else if(boton == R.id.elizabethCady){
            String wiki_url=getResources().getString(R.string.elizabethCadyURL);
            //START ASYNC TASK
            WikiDataAsync wikiDataAsync = new WikiDataAsync();
            wikiDataAsync.execute(wiki_url);
            cientifica = 12;
        }
    }

    private class WikiDataAsync extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            // progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected  String doInBackground(String[] params){
            try{
                String sUrl=params[0];

                URL url= new URL(sUrl);
                // Conecta con la api de wikipedia
                HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream= urlConnection.getInputStream();
                InputStreamReader inputStreamReader= new InputStreamReader(inputStream, "UTF-8");

                BufferedReader bufferedReader= new BufferedReader(inputStreamReader);

                StringBuilder stringBuilder= new StringBuilder();
                String line;

                while((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line);

                }
                String wikiData= stringBuilder.toString();

                //Parse JSON Data
                String formattedData= parseJSONData(wikiData);
                return formattedData;
            } catch (IOException e){
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(String formattedData){
            super.onPostExecute(formattedData);
            System.out.println(formattedData);
            // progressBar.setVisibility(View.GONE);

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                //HTML Data
                textView.setText(Html.fromHtml(formattedData,Html.FROM_HTML_MODE_LEGACY));
                text = valueOf(Html.fromHtml(formattedData,Html.FROM_HTML_MODE_LEGACY));
            } else{
                // HTML Data
                textView.setText(Html.fromHtml(formattedData));
                text = valueOf(Html.fromHtml(formattedData));
            }
        }

        private String parseJSONData(String wikiData){
            try{
                //CONVERT String JSON(wikiData) to JSON Object
                JSONObject rootJSON= new JSONObject(wikiData);
                JSONObject query=rootJSON.getJSONObject("query");
                JSONObject pages=query.getJSONObject("pages");
                JSONObject number=pages.getJSONObject(pages.keys().next());
                String formattedData=number.getString("extract");

                return formattedData;

            } catch(JSONException json){
                json.printStackTrace();
            }

            return null;
        }
    }

    public void onClick(View view) {
        String [] textSplit = text.split("\\r?\\n");
        String textToReplaced = textSplit[0];
        String name = "";
        String welcome = getResources().getString(R.string.bienvenido);

        String text1 = textToReplaced.replaceAll(",", "  ");
        String text2 = text1.replace("[1]", "");
        String text3 = text2.replace("[2]", "");
        String text4 = text3.replace("[3]", "");
        String text5 = text4.replace("[4]", "");
        String text6 = text5.replace("[5]", "");
        String text7 = text6.replace("[6]", "");
        String text8 = text7.replace("[7]", "");
        String text9 = text8.replace("[8]", "");
        String textSpeak = text9.replace("[9]", "");

        switch(cientifica){
            case 1:
                name = "Lise Meitner";
                break;
            case 2:
                name = "Loise Dolan";
                break;
            case 3:
                name = "Rachal Webster";
                break;
            case 4:
                name = "Dian Fossey";
                break;
            case 5:
                name = "Maria Eugenia";
                break;
            case 6:
                name = "Zehra Sayers";
                break;
            case 7:
                name = "Angela Belcher";
                break;
            case 8:
                name = "Marie Curie";
                break;
            case 9:
                name = "Susan Solomon";
                break;
            case 10:
                name = "Sojourner Truth";
                break;
            case 11:
                name = "Shulamith Firestone";
                break;
            case 12:
                name = "Elizabeth Cady";
                break;
            default:
                break;
        }
        myTTS.speak(  welcome + " "+ name + ". " + textSpeak, TextToSpeech.QUEUE_ADD, null, "speak");
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                //the user has the necessary data - create the TTS
                myTTS = new TextToSpeech(this, this);
            }
            else {
                //no data - install it now
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myTTS != null) {
            myTTS.stop();
            myTTS.shutdown();
        }
    }

    public void onInit(int initStatus) {
        //check for successful instantiation
        if (initStatus == TextToSpeech.SUCCESS) {
            myTTS.setPitch(0.8f);
            myTTS.setSpeechRate(1.1f);
        }
        else if (initStatus == TextToSpeech.ERROR) {
            Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
        }
    }
}