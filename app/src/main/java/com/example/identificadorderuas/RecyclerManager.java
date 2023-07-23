package com.example.identificadorderuas;

import android.content.Context;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.identificadorderuas.adapter.AdapterBairros;
import com.example.identificadorderuas.adapter.AdapterSiglas;

import java.util.List;

public class RecyclerManager {

    public void montadorRecyclerRua(RecyclerView recyclerView, List<String> listRua, List<String> listSiglas ,Context contexto){
        //configurar adapter
        AdapterBairros adapter = new AdapterBairros( listRua, listSiglas, contexto);
        //configurar Recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(contexto);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(contexto, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    public void montadorRecyclerSigla(RecyclerView recycler, List<String> listaRuas, List<String> listaBairros, Context contexto){
        AdapterSiglas adapterSiglas = new AdapterSiglas(listaRuas, listaBairros, contexto);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(contexto);
        recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);
        recycler.addItemDecoration(new DividerItemDecoration(contexto, LinearLayout.VERTICAL));
        recycler.setAdapter(adapterSiglas);
    }

}
