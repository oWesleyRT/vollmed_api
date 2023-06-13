package br.com.med.voll.api.dto.saida;

import br.com.med.voll.api.model.Consulta;

import java.time.LocalDateTime;

public record ConsultaDtoSaida(
        Integer id,
        Integer idMedico,
        Integer idPaciente,
        LocalDateTime data
) {
    public ConsultaDtoSaida(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
