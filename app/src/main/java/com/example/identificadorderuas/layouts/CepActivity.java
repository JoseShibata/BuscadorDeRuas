package com.example.identificadorderuas.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.identificadorderuas.R;
import com.example.identificadorderuas.ServicoDeCep;
import com.google.android.material.textfield.TextInputEditText;

public class CepActivity extends AppCompatActivity {

    private Button buscaRua;
    private TextInputEditText input_rua;
    private String nome_da_rua, nome_anterior = "";
    private StringBuffer varios_ceps;
    private TextView text_ceps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cep);

        buscaRua = findViewById(R.id.busca_rua);
        input_rua = findViewById(R.id.input_rua);
        text_ceps = findViewById(R.id.text_cep);
        varios_ceps = new StringBuffer();

        buscaRua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (input_rua.getText().toString().isEmpty()){
                    Toast.makeText(getApplication(), "Campo Vazio, Digite o Nome de Uma Rua", Toast.LENGTH_SHORT).show();
                }else{
                    nome_da_rua = input_rua.getText().toString();
                    text_ceps.setText(servicoDeCEP(nome_da_rua));
                }

            }
        });

    }

    public StringBuffer servicoDeCEP(String nome_rua){
        if (!nome_anterior.equals(nome_rua)){
            varios_ceps.setLength(0);
            ServicoDeCep.searchCep(nome_rua, new ServicoDeCep.ViaCepListener() {
                @Override
                public void onSuccess(String cep, String rua, String bairro) {
                    // Utilize as informações (cep, rua, bairro) aqui
                    //System.out.println("CEP: " + cep + ", " + rua + ", Bairro: " + bairro);
                    varios_ceps.append("CEP: ").append(cep).append(", ").append(rua).append(", Bairro: ").append(bairro).append("\n\n");
                }

                @Override
                public void onError(Exception e) {
                    // Tratar erros de requisição aqui
                    e.printStackTrace();
                }
            });
            nome_anterior = nome_rua;
        }

        return varios_ceps;

    }

}
