package br.com.med.voll.api.model;

import br.com.med.voll.api.dto.entrada.MedicoDtoEntrada;
import br.com.med.voll.api.enums.Especialidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Médico")
@Table(name = "medicos")
@EqualsAndHashCode(of = "id")
@Data
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Medico(MedicoDtoEntrada medicoDtoEntrada){
        this.nome = medicoDtoEntrada.getNome();
        this.email = medicoDtoEntrada.getEmail();
        this.telefone = medicoDtoEntrada.getTelefone();
        this.crm = medicoDtoEntrada.getCrm();
        this.especialidade = medicoDtoEntrada.getEspecialidade();
        this.ativo = true;
    }
}
