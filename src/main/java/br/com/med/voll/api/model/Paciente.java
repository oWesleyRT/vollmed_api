package br.com.med.voll.api.model;

import br.com.med.voll.api.dto.entrada.PacienteDtoEntrada;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pacientes")
@Entity(name = "Paciente")
@EqualsAndHashCode(of = "id")
@Repository
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Paciente(PacienteDtoEntrada pacienteDtoEntrada) {
        this.nome = pacienteDtoEntrada.nome();
        this.email = pacienteDtoEntrada.email();
        this.telefone = pacienteDtoEntrada.telefone();
        this.cpf = pacienteDtoEntrada.cpf();
        this.ativo = true;
    }
}
