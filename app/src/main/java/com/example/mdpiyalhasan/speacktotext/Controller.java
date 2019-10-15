package com.example.mdpiyalhasan.speacktotext;

import android.annotation.TargetApi;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class Controller extends AppCompatActivity {
    TextToSpeech toSpeech;
    EditText text;
    int result;
    Button speak,stop,read;
    CheckBox us,uk;
    String laguage="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controller);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            String sms=bundle.getString("smsbody");
            text.setText(sms);
        }
        text= (EditText) findViewById(R.id.editText);
        speak=(Button)findViewById(R.id.speak);
        read=(Button)findViewById(R.id.read);
        stop=(Button)findViewById(R.id.stop);
        us=(CheckBox)findViewById(R.id.us);
        uk=(CheckBox)findViewById(R.id.uk);
        us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laguage= String.valueOf(Locale.UK);
            }
        });
        uk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laguage= String.valueOf(Locale.US);
            }
        });
        toSpeech=new TextToSpeech(Controller.this, new TextToSpeech.OnInitListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                    result=toSpeech.setLanguage(Locale.UK);
                }
                else{
                    Toast.makeText(getBaseContext(),"device not supported",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void test(View v){
        switch (v.getId()){
            case R.id.speak:
            {
                if(result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED){
                    Toast.makeText(getBaseContext(),"not supported in this device",Toast.LENGTH_SHORT).show();
                }
                else{
                    toSpeech.speak(text.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
                }
                break;
            }
            case R.id.stop:
            {
                if(toSpeech!=null){
                    toSpeech.stop();
                }
                break;
            }

        }

    }

    protected void onDestroy(){
        super.onDestroy();
        if(toSpeech!=null ){
            toSpeech.stop();
            toSpeech.shutdown();
        }
    }

}
