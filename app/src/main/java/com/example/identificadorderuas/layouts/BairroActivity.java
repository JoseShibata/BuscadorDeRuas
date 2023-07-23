package com.example.identificadorderuas.layouts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.identificadorderuas.DadosPlanilha;
import com.example.identificadorderuas.R;
import com.example.identificadorderuas.SpinnerManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BairroActivity extends AppCompatActivity {

    private List<String> listaBairros;
    private Button buttonCarregar;
    private Spinner spinnerBairros;
    private TextView textBairro;
    private String retorno;
    private SpinnerManager gerenciadorSpinner;
    private DadosPlanilha dadosPlanilha;
    private RecyclerView recyclerRuas;
    private InputStream arquivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bairro);

        spinnerBairros = findViewById(R.id.spinnerBairros);
        buttonCarregar = findViewById(R.id.buttonCarregar);
        textBairro = findViewById(R.id.textBairro);
        recyclerRuas = findViewById(R.id.recyclerRuas);

        listaBairros = new ArrayList<>();
        gerenciadorSpinner = new SpinnerManager();
        dadosPlanilha = new DadosPlanilha(BairroActivity.this);

        try {
            arquivo = getAssets().open("RuasTeste25.xlsx");
            dadosPlanilha.populaCombo(arquivo, listaBairros, spinnerBairros);
        } catch (IOException e) {
            e.printStackTrace();
        }

        buttonCarregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    arquivo = getAssets().open("RuasTeste25.xlsx");
                    dadosPlanilha.recuperaDados(arquivo, listaBairros, spinnerBairros, textBairro, recyclerRuas);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
