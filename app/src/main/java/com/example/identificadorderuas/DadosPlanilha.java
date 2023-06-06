package com.example.identificadorderuas;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.identificadorderuas.adapter.AdapterRecycler;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DadosPlanilha {

    private SpinnerManager gerenciadorSpinner = new SpinnerManager();
    List<String> retorno;

    public void recuperaDados(InputStream nomeArquivo, List<String> listaBairros,
                              Context contexto, Spinner spinnerBairros,
                              TextView textBairro, Button buttonCarregar, RecyclerView recycler){



        try {

            Workbook workbook = new XSSFWorkbook(nomeArquivo);

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
            System.out.println("Spinner no console " + gerenciadorSpinner.spinnerBairros(spinnerBairros,listaBairros, contexto));

            buttonCarregar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Spinner dentro do button listener: " + gerenciadorSpinner.spinnerBairros(spinnerBairros,listaBairros, contexto));
                    Sheet sheet1 = workbook.getSheetAt(0);
                    System.out.println("1");
                    List<String> ruasEncontradas = new ArrayList<>();
                    StringBuffer buffer = new StringBuffer();

                    for (Row rowTabela1 : sheet1) {
                        Cell cellBairroTabela1 = rowTabela1.getCell(0); // Coluna dos bairros na tabela 1
                        System.out.println("2");
                        if (cellBairroTabela1 != null && cellBairroTabela1.getStringCellValue().equals(gerenciadorSpinner.spinnerBairros(spinnerBairros,listaBairros, contexto))) {
                            Cell cellRuaTabela1 = rowTabela1.getCell(2); // Coluna das ruas na tabela 1
                            System.out.println("3");
                            if (cellRuaTabela1 != null) {
                                String ruaTabela1 = cellRuaTabela1.getStringCellValue();
                                System.out.println("Nome da rua: " + ruaTabela1);

                                if (ruaTabela1.equals("RUA")) {
                                    textBairro.setText("Selecione Algum Bairro Para Obter as Ruas!!!");
                                }else{
                                    textBairro.setText(gerenciadorSpinner.spinnerBairros(spinnerBairros,listaBairros, contexto));
                                    System.out.println(ruasEncontradas.add(ruaTabela1));
                                    retorno = ruasEncontradas;
                                    buffer.append(ruaTabela1 + "\n");
                                }

                            }
                        }
                    }

                    //configurar adapter
                    AdapterRecycler adapter = new AdapterRecycler( ruasEncontradas );
                    //configurar Recycler view
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(contexto);
                    recycler.setLayoutManager(layoutManager);
                    recycler.setHasFixedSize(true);
                    recycler.addItemDecoration(new DividerItemDecoration(contexto, LinearLayout.VERTICAL));
                    recycler.setAdapter(adapter);

                }
            });

        }catch(IOException e){
            e.printStackTrace();
        }

    }

}
