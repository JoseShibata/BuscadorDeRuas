package com.example.identificadorderuas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.identificadorderuas.R;

import java.util.List;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.MyViewHolder> {

    private List<String> listaRuas;

    public AdapterRecycler(List<String> listRuas) {
        this.listaRuas = listRuas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_ruas, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String rua = listaRuas.get(position);
        holder.nomeRua.setText(rua);
    }

    @Override
    public int getItemCount() {

        return listaRuas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nomeRua;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeRua = itemView.findViewById(R.id.textNomeRua);

        }
    }

}
