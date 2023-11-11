package com.example.identificadorderuas;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

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
    private RecyclerManager gerenciadorRecycler;
    private Context contexto;


    public DadosPlanilha(Context contexto) {
        this.contexto = contexto;
    }

    public void populaCombo(InputStream nomeArquivo, List<String> listaBairros, Spinner spinnerBairros){

        try(Workbook workbook = new XSSFWorkbook(nomeArquivo)){
            Sheet sheet2 = workbook.getSheetAt(2);
            for(Row row : sheet2){
                if (listaBairros != null){
                    for(Cell cell : row){
                        CellType cellType = cell.getCellType();
                        if (cellType == CellType.STRING){
                            String bairros = cell.getStringCellValue();
                            listaBairros.add(bairros);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        gerenciadorSpinner.spinnerBairros(spinnerBairros, listaBairros, contexto);
    }

    public void recuperaDados(InputStream nomeArquivo, List<String> listaBairros, Spinner spinnerBairros,
                              TextView textBairro, RecyclerView recycler) throws IOException {

        try(Workbook workbook = new XSSFWorkbook(nomeArquivo)){
            Sheet sheet1 = workbook.getSheetAt(0);  // Identifica a tabela a ser usada
            List<String> siglasEncontradas = new ArrayList<>();
            List<String> ruasEncontradas = new ArrayList<>();
            gerenciadorRecycler = new RecyclerManager();

            for (Row rowTabela1 : sheet1) {
                Cell cellBairroTabela1 = rowTabela1.getCell(0); // Coluna dos bairros na tabela 1
                if (cellBairroTabela1 != null && cellBairroTabela1.getStringCellValue().equals(gerenciadorSpinner.spinnerBairros(spinnerBairros,listaBairros, contexto))) {
                    Cell cellRuaTabela1 = rowTabela1.getCell(5); // Coluna das ruas na tabela 1
                    Cell cellSiglaTabela1 = rowTabela1.getCell(4); // Coluna das siglas na tabela 1
                    if (cellRuaTabela1 != null && cellSiglaTabela1 != null) {
                        String ruaTabela1 = cellRuaTabela1.getStringCellValue();
                        String siglaTabela1 = cellSiglaTabela1.getStringCellValue();
                        if (ruaTabela1.equals("RUA")) {
                            textBairro.setText("Selecione Algum Bairro Para Obter as Ruas!!!");
                        }else{
                            textBairro.setText(gerenciadorSpinner.spinnerBairros(spinnerBairros,listaBairros, contexto));
                            ruasEncontradas.add(ruaTabela1);
                            if (siglaTabela1.equals("remove")) {
                                siglasEncontradas.add(ruaTabela1);
                            }else{
                                siglasEncontradas.add(siglaTabela1);
                            }
                        }
                    }
                }
            }
            gerenciadorRecycler.montadorRecyclerRua(recycler, ruasEncontradas, siglasEncontradas, contexto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recuperaRuas(InputStream nomeArquivo, TextView text_id_siglas, TextInputEditText textNumero, RecyclerView recycler){
        try {

            Workbook workbook = new XSSFWorkbook(nomeArquivo);
            System.out.println(workbook);

            Sheet sheet1 = workbook.getSheetAt(0);
            List<String> bairrosEncontrados = new ArrayList<>();
            List<String> ruasEncontradas = new ArrayList<>();
            gerenciadorRecycler = new RecyclerManager();

            boolean ruaEncontrada = false;

            for (Row rowTabela1 : sheet1) {
                Cell cellSiglaTabela1 = rowTabela1.getCell(3);
                if (cellSiglaTabela1 != null && cellSiglaTabela1.getStringCellValue().equals("(" + textNumero.getText().toString().toUpperCase() + ")")) {
                    Cell cellRuaTabela1 = rowTabela1.getCell(5); // Coluna das ruas na tabela 1
                    Cell cellBairroTabela1 = rowTabela1.getCell(0); // Coluna dos bairros na tabela 1
                    if (cellRuaTabela1 != null && cellBairroTabela1 != null) {
                        String nomeRua = cellRuaTabela1.getStringCellValue(); //ATENÇÃÃÃÃÃÃO -> aqui, eu estou atribuindo a variável, a rua resgatada do xlsx e concatenando com a sigla recuperada do usuário
                        String nomeBairro = cellBairroTabela1.getStringCellValue();
                        ruasEncontradas.add(nomeRua);
                        bairrosEncontrados.add(nomeBairro);
                        ruaEncontrada = true;
                    }
                }
            }

            if (!ruaEncontrada) {
                Toast.makeText(contexto, "Nenhuma Rua Encontrada", Toast.LENGTH_LONG).show();
            }
            gerenciadorRecycler.montadorRecyclerSigla(recycler, ruasEncontradas, bairrosEncontrados, contexto);
            text_id_siglas.setText("Buscando por termos: " + "(" + textNumero.getText().toString().toUpperCase() + ")");

        }catch(IOException e){
            e.printStackTrace();
        }

    }

}