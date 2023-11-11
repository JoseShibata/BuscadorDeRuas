package com.example.identificadorderuas;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ServicoDeCep {

    private static final String BASE_URL = "https://viacep.com.br/ws/SP/Taquaritinga/";

    public interface ViaCepListener {
        void onSuccess(String returnedCep, String street, String response);
        void onError(Exception e);
    }

    public static void searchCep(String nomeRua, final ViaCepListener listener) {
        OkHttpClient client = new OkHttpClient();
        String url = BASE_URL + nomeRua + "/json/";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                try {
                    if (response.isSuccessful()) {
                        String responseBody = response.body().string();

                        JSONArray jsonArray = new JSONArray(responseBody);
                        if (jsonArray.length() > 0) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String returnedCep = jsonObject.optString("cep");
                                String street = jsonObject.optString("logradouro");
                                String district = jsonObject.optString("bairro");
                                listener.onSuccess(returnedCep, street, district);
                            }
                        } else {
                            listener.onError(new JSONException("Nenhum resultado encontrado."));
                        }
                    } else {
                        listener.onError(new IOException("Erro na requisição: " + response));
                    }
                } catch (JSONException | IOException e) {
                    listener.onError(e);
                } finally {
                    response.close();
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                listener.onError(e);
            }
        });
    }
}
