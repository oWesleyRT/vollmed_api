package br.com.med.voll.api.dto.saida;

import br.com.med.voll.api.enums.Especialidade;
import br.com.med.voll.api.model.Endereco;
import br.com.med.voll.api.model.Medico;
import br.com.med.voll.api.model.Paciente;

public record PacienteAtualizaDtoSaida(
        Integer id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco
) {
    public PacienteAtualizaDtoSaida(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(),
                paciente.getCpf(), paciente.getEndereco());
    }
}
