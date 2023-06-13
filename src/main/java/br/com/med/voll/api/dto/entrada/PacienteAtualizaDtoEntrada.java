package br.com.med.voll.api.dto.entrada;

import jakarta.validation.constraints.NotNull;

public record PacienteAtualizaDtoEntrada(
        @NotNull
        Integer id,
        String nome,
        String telefone,
        EnderecoDtoEntrada endereco
) {
}
