package br.com.med.voll.api.validacoes;

import br.com.med.voll.api.dto.entrada.ConsultaDtoEntrada;

public interface ValidadorAgendamentoDeConsulta {
    void validar(ConsultaDtoEntrada dados);
}
