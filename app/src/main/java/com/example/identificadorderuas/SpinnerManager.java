package com.example.identificadorderuas;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SpinnerManager {
    String retorno;

    public String spinnerBairros(Spinner spinnerBairros, List<String> listaBairros, Context contexto){
        ArrayAdapter<String> adapterBairros = new ArrayAdapter<String>(contexto,
                android.R.layout.simple_spinner_dropdown_item, listaBairros);

        spinnerBairros.setAdapter(adapterBairros);
        spinnerBairros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String bairroEscolhido = (spinnerBairros.getSelectedItem().toString());
                System.out.println("Bairro Escolhido: " + bairroEscolhido);
                if (bairroEscolhido == "BAIRRO") {
                    Toast.makeText(contexto,"Selecione Algum Bairro Para Obter as Ruas!!!", Toast.LENGTH_LONG).show();
                }
                retorno = bairroEscolhido;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return retorno;
    }

}
