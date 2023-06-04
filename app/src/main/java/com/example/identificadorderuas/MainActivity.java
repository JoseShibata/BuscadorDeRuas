package com.example.identificadorderuas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    List<String> listaBairros;
    Button buttonCarregar;
    Spinner spinnerBairros;
    TextView textRuas, textBairro;
    String retorno;
    SpinnerManager gerenciadorSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // km montana123622
        //System.out.println(caminhoArquivo);

        //System.out.println("---- Digite algum dos nomes abaixo ----");
        spinnerBairros = findViewById(R.id.spinnerBairros);
        buttonCarregar = findViewById(R.id.buttonCarregar);
        textRuas = findViewById(R.id.textRuas);
        textBairro = findViewById(R.id.textBairro);
        listaBairros = new ArrayList<>();
        gerenciadorSpinner = new SpinnerManager();

        try {

            InputStream arquivo = getAssets().open("RuasTeste4.xlsx");

            Workbook workbook = new XSSFWorkbook(arquivo);

            Sheet sheet2 = workbook.getSheetAt(1);
            for(Row row : sheet2){
                for(Cell cell : row){
                    CellType cellType = cell.getCellType();
                    if (cellType == CellType.STRING){
                        String bairros = cell.getStringCellValue();
                        listaBairros.add(bairros);
                        System.out.print(cell.getStringCellValue() + "\t");
                    }
                }
                System.out.println();
            }
            System.out.println("0");
            System.out.println("Spinner no console " + gerenciadorSpinner.spinnerBairros(spinnerBairros,textRuas,listaBairros, getApplicationContext()));

            buttonCarregar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Spinner dentro do button listener: " + gerenciadorSpinner.spinnerBairros(spinnerBairros,textRuas,listaBairros, getApplicationContext()));
                    Sheet sheet1 = workbook.getSheetAt(0);
                    System.out.println("1");
                    List<String> ruasEncontradas = new ArrayList<>();
                    StringBuffer buffer = new StringBuffer();

                    for (Row rowTabela1 : sheet1) {
                        Cell cellBairroTabela1 = rowTabela1.getCell(0); // Coluna dos bairros na tabela 1
                        System.out.println("2");
                        if (cellBairroTabela1 != null && cellBairroTabela1.getStringCellValue().equals(gerenciadorSpinner.spinnerBairros(spinnerBairros,textRuas,listaBairros, getApplicationContext()))) {
                            Cell cellRuaTabela1 = rowTabela1.getCell(2); // Coluna das ruas na tabela 1
                            System.out.println("3");
                            if (cellRuaTabela1 != null) {
                                String ruaTabela1 = cellRuaTabela1.getStringCellValue();
                                System.out.println("Nome da rua: " + ruaTabela1);

                                if (ruaTabela1.equals("RUA")) {
                                    textBairro.setText("Selecione Algum Bairro Para Obter as Ruas!!!");
                                    textRuas.setText(" ");
                                }else{
                                    textBairro.setText(gerenciadorSpinner.spinnerBairros(spinnerBairros,textRuas,listaBairros, getApplicationContext()));
                                    ruasEncontradas.add(ruaTabela1);
                                    buffer.append(ruaTabela1 + "\n");
                                    textRuas.setText(buffer);
                                }

                            }
                        }
                    }

                }
            });

        }catch(IOException e){
            e.printStackTrace();
        }
    }
/*
    public String gerarSpinner(){
        ArrayAdapter<String>adapterBairros = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, listaBairros);

        spinnerBairros.setAdapter(adapterBairros);
        spinnerBairros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String bairroEscolhido = (spinnerBairros.getSelectedItem().toString());
                System.out.println("Bairro Escolhido: " + bairroEscolhido);
                if (bairroEscolhido == "BAIRRO")
                    textRuas.setText("Selecione Algum Bairro Para Obter as Ruas!!!");

                retorno = bairroEscolhido;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return retorno;
    }*/

}
