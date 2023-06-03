package br.com.med.voll.api.dto.entrada;

import jakarta.validation.constraints.NotBlank;

public record MedicoAtualizaDtoEntrada(
        @NotBlank
        Integer id,
        String nome,
        String telefone,
        EnderecoDtoEntrada endereco
) {
}
