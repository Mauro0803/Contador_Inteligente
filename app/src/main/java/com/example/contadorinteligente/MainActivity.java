package com.example.contadorinteligente;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    // Declarar Variables
    private TextView tv_contador;
    private Button btn_Aumentar, btn_Disminuir, btn_Reset, btn_x2;
    private int contador = 0;
    private ConstraintLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        tv_contador = findViewById(R.id.tv_contador);
        btn_Aumentar = findViewById(R.id.btn_aumentar);
        btn_Disminuir = findViewById(R.id.btn_disminuir);
        btn_Reset = findViewById(R.id.btn_reset);
        btn_x2 = findViewById(R.id.btn_x2);
        main = findViewById(R.id.main); // Layout principal


        // Cargar el contador guardado
        SharedPreferences prefs = getSharedPreferences("MisDatos", MODE_PRIVATE);
        contador = prefs.getInt("contador_guardado", 0);
        tv_contador.setText(String.valueOf(contador));
        actualizarColorFondo(); // Ajustar color al abrir la app

        // Botón aumentar
        btn_Aumentar.setOnClickListener(v -> {
            contador++;
            tv_contador.setText(String.valueOf(contador));
            guardarContador();
            actualizarColorFondo();
        });

        // Botón restar
        btn_Disminuir.setOnClickListener(v -> {
            if (contador > 0) {
                contador--;
                tv_contador.setText(String.valueOf(contador));
                guardarContador();
                actualizarColorFondo();
            }
        });

        // Botón reset
        btn_Reset.setOnClickListener( v -> {
            contador = 0;
            tv_contador.setText(String.valueOf(contador));
            guardarContador();
            actualizarColorFondo();
        });

        // Botón x2
        btn_x2.setOnClickListener( v -> {
            if (contador > 0) {
                contador = contador * 2;
                tv_contador.setText(String.valueOf(contador));
                guardarContador();
                actualizarColorFondo();
            }
        });

    }

    private void guardarContador() {
        SharedPreferences prefs = getSharedPreferences("MisDatos", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("contador_guardado", contador);
        editor.apply();

    }
    // Metodo para actualizar el color de fondo
    private void actualizarColorFondo() {
        if (contador > 10) {
            main.setBackgroundColor(getResources().getColor(R.color.fondo_alerta));
            tv_contador.setBackgroundColor(getResources().getColor(R.color.fondo_alerta));
        } else {
            main.setBackgroundColor(getResources().getColor(R.color.white));
            tv_contador.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }
}