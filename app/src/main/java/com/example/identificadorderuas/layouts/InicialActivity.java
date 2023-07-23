package com.example.identificadorderuas.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.identificadorderuas.R;

public class InicialActivity extends AppCompatActivity {

    ImageButton pesquisaRua, pesquisaBairro;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        pesquisaRua = findViewById(R.id.pesquisaRua);
        pesquisaBairro = findViewById(R.id.pesquisaBairro);

        pesquisaBairro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), BairroActivity.class);
                startActivity(intent);
            }
        });

        pesquisaRua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), RuaActivity.class);
                startActivity(intent);
            }
        });

    }
}
