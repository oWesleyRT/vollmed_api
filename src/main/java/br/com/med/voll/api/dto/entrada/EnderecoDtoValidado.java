package br.com.med.voll.api.dto.entrada;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDtoValidado {

    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

}
