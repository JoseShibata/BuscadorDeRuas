package com.example.identificadorderuas;

import androidx.annotation.NonNull;

public class DadosEndereco {

    private String cep;
    private String bairro;
    private String logradouro;

    public String getCep() {
        return cep;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    @NonNull
    @Override
    public String toString() {
        return "Endereco{" + "CEP=" + cep  + ", logradouro=" + logradouro + ", bairro=" + bairro + '}';
    }
}
