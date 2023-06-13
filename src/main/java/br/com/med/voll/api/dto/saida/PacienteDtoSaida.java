package br.com.med.voll.api.dto.saida;

import br.com.med.voll.api.enums.Especialidade;
import br.com.med.voll.api.model.Medico;
import br.com.med.voll.api.model.Paciente;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PacienteDtoSaida implements Comparable<PacienteDtoSaida>{
    private Integer id;
    private String nome;
    private String email;
    private String cpf;

    public PacienteDtoSaida(Paciente paciente){
        this.id = paciente.getId();
        this.nome = paciente.getNome();
        this.email = paciente.getEmail();
        this.cpf = paciente.getCpf();
    }

    @Override
    public int compareTo(PacienteDtoSaida pacienteDtoSaida) {
        return this.getNome().compareTo(pacienteDtoSaida.getNome());
    }
}
