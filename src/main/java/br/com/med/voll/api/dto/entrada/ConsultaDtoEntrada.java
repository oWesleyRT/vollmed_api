package br.com.med.voll.api.dto.entrada;

import br.com.med.voll.api.enums.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaDtoEntrada(
        Integer idMedico,

        @NotNull
        Integer idPaciente,

        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade
) {
}
