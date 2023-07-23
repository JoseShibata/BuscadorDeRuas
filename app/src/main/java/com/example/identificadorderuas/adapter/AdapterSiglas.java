package com.example.identificadorderuas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.identificadorderuas.AlertManager;
import com.example.identificadorderuas.R;

import java.util.List;

public class AdapterSiglas extends RecyclerView.Adapter<AdapterSiglas.MyViewHolder>{

    private List<String> listaRuas;
    private List<String> listaBairros;
    private Context context;
    private AlertManager gerenciadorAlert;

    public AdapterSiglas(List<String> listaRuas, List<String> listaBairros, Context context) {
        this.listaRuas = listaRuas;
        this.listaBairros = listaBairros;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterSiglas.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_ruas, parent, false);

        return new AdapterSiglas.MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String rua = listaRuas.get(position);
        String bairros = listaBairros.get(position);

        holder.nomeRua.setText(rua + ", "  + bairros);

        holder.nomeRua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, siglas, Toast.LENGTH_LONG).show();
                gerenciadorAlert = new AlertManager(context);
                gerenciadorAlert.alertGoogle(rua + " - TAQUARITINGA");
            }
        });
    }

    @Override
    public int getItemCount() {

        return (listaRuas.size());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nomeRua;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeRua = itemView.findViewById(R.id.textNomeRua);


        }
    }

}
