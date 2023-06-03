package br.com.med.voll.api.service;

import br.com.med.voll.api.dto.entrada.EnderecoDtoEntrada;
import br.com.med.voll.api.dto.entrada.EnderecoDtoValidado;
import br.com.med.voll.api.model.Endereco;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class EnderecoService {

    public EnderecoDtoValidado validaCep(EnderecoDtoEntrada enderecoDtoEntrada) throws IOException, InterruptedException {
        String cep = enderecoDtoEntrada.getCep();
        String link = String.format("https://viacep.com.br/ws/%s/json/", cep);
        var cliente = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .build();
        HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        Gson gson = new GsonBuilder().create();
        EnderecoDtoValidado enderecoDtoValidado = gson.fromJson(json, EnderecoDtoValidado.class);
        return enderecoDtoValidado;
    }
}
