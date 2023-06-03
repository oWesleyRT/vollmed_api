package br.com.med.voll.api.dto.saida;

import br.com.med.voll.api.enums.Especialidade;
import br.com.med.voll.api.model.Endereco;
import br.com.med.voll.api.model.Medico;

public record MedicoAtualizaDtoSaida(
        Integer id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        Endereco endereco
) {
    public MedicoAtualizaDtoSaida(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(),
                medico.getEspecialidade(), medico.getEndereco());
    }
}
