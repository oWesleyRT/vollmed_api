package br.com.med.voll.api.dto.entrada;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnderecoDtoEntrada {
    private int numero;
    private String complemento;
    @NotBlank
    @Pattern(regexp = "\\d{8}")
    private String cep;
}
