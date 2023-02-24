package com.example.cronometro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tempo;
    private Button btn_start, btn_stop;
    private  int mils = 0;
    private int seconds = 0;
    private int minutes = 0;
    private  int horas = 0;
    private boolean arranque = false;
    private boolean pausado= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();


        tempo = (TextView) findViewById(R.id.tempo);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_stop = (Button) findViewById(R.id.btn_stop);

        Handler handler = new Handler();
        Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                if(!pausado){
                    cronometro();
                }
                handler.postDelayed(this, 1);
            }
        };



        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!pausado && arranque){
                    pausado = true;
                    btn_start.setText("Reanudar");
                    btn_stop.setText("Reiniciar");
                }else{
                    arranque = true;
                    pausado = false;
                    btn_start.setText("Detener");
                    btn_stop.setText("Vuelta");
                }

                handler.postDelayed(runnableCode, 1);

            }
        });


        btn_stop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(pausado){
                    mils = 0;
                    seconds = 0;
                    minutes = 0;
                    horas = 0;
                    String time = String.format("%02d:%02d:%02d",minutes, seconds, mils);
                    tempo.setText(time);
                    btn_start.setText("Iniciar");
                    btn_stop.setText("Detener");
                }else{
                    Toast.makeText(MainActivity.this, "Vuelta", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    public void cronometro(){
        mils++;
        if(mils == 100){
            mils = 0;
            seconds++;
        }

        if(seconds == 60){
            seconds = 0;
            minutes++;
        }

        if(minutes == 60){
            minutes = 0;
            horas++;
        }

        String time = "";
        if(horas == 0){
            time = String.format("%02d:%02d:%02d",minutes, seconds, mils);
        }else{
            time = String.format("%02d:%02d:%02d:%02d", horas, minutes, seconds, mils);
        }

        tempo.setText(time);
    }

}