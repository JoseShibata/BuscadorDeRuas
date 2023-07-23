package com.example.identificadorderuas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class AlertManager {

    private Context context;

    public AlertManager(Context context) {
        this.context = context;
    }

    public void alertGoogle(String rua){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //ôôôÔÔÔôôh seu burro, não esquece de setar o icone de warning
        builder.setTitle("REDIRECIONAMENTO PARA O ENDEREÇO");

        builder.setMessage("Você deseja ser enviado ao endereço, " + rua + " ?");

        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(rua));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);

                Intent chooserIntent = Intent.createChooser(mapIntent, "Abrir com: ");

                if (mapIntent.resolveActivity(context.getPackageManager()) != null){
                    context.startActivity(chooserIntent);
                    //Toast.makeText(context, "Você será enviado ao " + chooserIntent, Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(context, "Aplicativo selecionado não instalado!!!", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }

}
