package br.com.med.voll.api.model;

import br.com.med.voll.api.dto.entrada.EnderecoDtoEntrada;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Endereco {
    @NotBlank
    private String logradouro;
    private int numero;
    private String complemento;
    @NotBlank
    private String bairro;
    @NotBlank
    private String cidade;
    @NotBlank
    private String uf;
    @NotBlank
    private String cep;

    public Endereco(EnderecoDtoEntrada enderecoDtoEntrada){
        this.cep = enderecoDtoEntrada.getCep();
        this.complemento = enderecoDtoEntrada.getComplemento();
        this.numero = enderecoDtoEntrada.getNumero();
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "logradouro='" + logradouro + '\'' +
                ", numero=" + numero +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}
