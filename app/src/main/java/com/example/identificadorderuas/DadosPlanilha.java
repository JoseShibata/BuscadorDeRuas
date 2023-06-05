package com.example.identificadorderuas;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

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

    public void recuperaDados(InputStream nomeArquivo, List<String> listaBairros,
                              Context contexto, Spinner spinnerBairros, TextView textRuas,
                              TextView textBairro, Button buttonCarregar){

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
            System.out.println("Spinner no console " + gerenciadorSpinner.spinnerBairros(spinnerBairros,textRuas,listaBairros, contexto));

            buttonCarregar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Spinner dentro do button listener: " + gerenciadorSpinner.spinnerBairros(spinnerBairros,textRuas,listaBairros, contexto));
                    Sheet sheet1 = workbook.getSheetAt(0);
                    System.out.println("1");
                    List<String> ruasEncontradas = new ArrayList<>();
                    StringBuffer buffer = new StringBuffer();

                    for (Row rowTabela1 : sheet1) {
                        Cell cellBairroTabela1 = rowTabela1.getCell(0); // Coluna dos bairros na tabela 1
                        System.out.println("2");
                        if (cellBairroTabela1 != null && cellBairroTabela1.getStringCellValue().equals(gerenciadorSpinner.spinnerBairros(spinnerBairros,textRuas,listaBairros, contexto))) {
                            Cell cellRuaTabela1 = rowTabela1.getCell(2); // Coluna das ruas na tabela 1
                            System.out.println("3");
                            if (cellRuaTabela1 != null) {
                                String ruaTabela1 = cellRuaTabela1.getStringCellValue();
                                System.out.println("Nome da rua: " + ruaTabela1);

                                if (ruaTabela1.equals("RUA")) {
                                    textBairro.setText("Selecione Algum Bairro Para Obter as Ruas!!!");
                                    textRuas.setText(" ");
                                }else{
                                    textBairro.setText(gerenciadorSpinner.spinnerBairros(spinnerBairros,textRuas,listaBairros, contexto));
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

}
