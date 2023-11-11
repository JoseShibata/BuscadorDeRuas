package com.example.identificadorderuas.layouts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.identificadorderuas.DadosPlanilha;
import com.example.identificadorderuas.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.io.InputStream;

public class RuaActivity extends AppCompatActivity {

    private DadosPlanilha dadosPlanilha;
    private InputStream arquivo;
    private RecyclerView recyclerRuas2;
    private TextInputEditText textNumero;
    private TextView text_sigla;
    private Button botaoEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rua);

        recyclerRuas2 = findViewById(R.id.recyclerRuas2);
        textNumero = findViewById(R.id.inputNumero);
        botaoEnviar = findViewById(R.id.botaoPesquisar);
        text_sigla = findViewById(R.id.text_id_sigla);
        dadosPlanilha = new DadosPlanilha(RuaActivity.this);

            botaoEnviar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try{
                        arquivo = getAssets().open("RuasTeste25.xlsx");
                        dadosPlanilha.recuperaRuas(arquivo, text_sigla, textNumero, recyclerRuas2);
                    }catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
    }
}
