package br.com.med.voll.api.dto.saida;

import br.com.med.voll.api.enums.Especialidade;
import br.com.med.voll.api.model.Medico;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MedicoDtoSaida implements Comparable<MedicoDtoSaida>{
    private Integer id;
    private String nome;
    private String email;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    public MedicoDtoSaida(Medico medico){
        this.id = medico.getId();
        this.nome = medico.getNome();
        this.email = medico.getEmail();
        this.crm = medico.getCrm();
        this.especialidade = medico.getEspecialidade();
    }

    @Override
    public int compareTo(MedicoDtoSaida medicoDtoSaida) {
        return this.getNome().compareTo(medicoDtoSaida.getNome());
    }
}
