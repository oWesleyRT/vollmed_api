package br.com.med.voll.api.dto.saida;

import java.time.LocalDateTime;

public record ConsultaDtoSaida(
        Integer id,
        Integer idMedico,
        Integer idPaciente,
        LocalDateTime data
) {
}
