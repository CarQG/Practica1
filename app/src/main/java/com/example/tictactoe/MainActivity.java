package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView jugador1Marcador, jugador2Marcador, estado;
    private Button[] buttons = new Button[9];
    private Button reinicio;

    private int jugador1MarcadorCont, jugador2MarcadorCont, puntosPart;
    boolean jugadorActivo;

    int[] estadodeljuego = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] formasdeGanar = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},//horizontal
            {0, 5, 6}, {1, 4, 7}, {2, 3, 8},//vertical
            {0, 4, 8}, {2, 4, 6}//diagonal
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jugador1Marcador = (TextView) findViewById(R.id.jugador1Marcador);
        jugador2Marcador = (TextView) findViewById(R.id.jugador2Marcador);
        estado = (TextView) findViewById(R.id.estado);

        reinicio = (Button) findViewById(R.id.reinicio);

        for (int i = 0; i < buttons.length; i++) {
            String buttonID = "btn_" + i;
            int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = (Button) findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }
        puntosPart = 0;
        jugador1MarcadorCont = 0;
        jugador2MarcadorCont = 0;
        jugadorActivo = true;
    }

    @Override
    public void onClick(View v) {

        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        String buttonID = v.getResources().getResourceEntryName(v.getId());
        int estjuego = Integer.parseInt(buttonID.substring(buttonID.length() - 1, buttonID.length()));

        if (jugadorActivo) {
            ((Button) v).setText("X");
            ((Button) v).setTextColor(Color.parseColor("#FF03DAC5"));
            estadodeljuego[estjuego] = 0;
        } else {
            ((Button) v).setText("O");
            ((Button) v).setTextColor(Color.parseColor("#FF1744"));
            estadodeljuego[estjuego] = 1;
        }
        puntosPart++;

        if (ganador()){
            if (jugadorActivo){
                jugador1MarcadorCont++;
                actualizarMarcador();
                Toast.makeText(this, "Jugador 1 es el Ganador", Toast.LENGTH_SHORT).show();
                jugar();
            }else{
                jugador2MarcadorCont++;
                actualizarMarcador();
                Toast.makeText(this, "Jugador 2 es el Ganador", Toast.LENGTH_SHORT).show();
                jugar();
            }
        }else if(puntosPart == 9){
                jugar();
            Toast.makeText(this, "Empate", Toast.LENGTH_SHORT).show();
        }else{
            jugadorActivo = !jugadorActivo;
        }


    }

    public boolean ganador() {
        boolean resultado = false;

        for (int[] formasdeGanar : formasdeGanar) {
            if (estadodeljuego[formasdeGanar[0]] == estadodeljuego[formasdeGanar[1]] && estadodeljuego[formasdeGanar[1]] == estadodeljuego[formasdeGanar[2]] && estadodeljuego[formasdeGanar[0]] != 2) {
           resultado = true;
            }
        }
        return  resultado;
    }
    public void actualizarMarcador(){
        jugador1Marcador.setText(Integer.toString(jugador1MarcadorCont));
        jugador2Marcador.setText(Integer.toString(jugador2MarcadorCont));
    }
    public void jugar(){
        puntosPart = 0;
        jugadorActivo = true;

        for (int i = 0; i< buttons.length; i++){
            estadodeljuego[i] = 2;
            buttons[i].setText("");
        }
    }
}
