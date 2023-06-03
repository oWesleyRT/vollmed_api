package br.com.med.voll.api.dto.entrada;

import br.com.med.voll.api.enums.Especialidade;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MedicoDtoEntrada {
    @NotBlank
    private String nome;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String telefone;
    @NotBlank
    @Pattern(regexp = "\\d{6,8}")
    private String crm;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @NotNull
    @Valid
    private EnderecoDtoEntrada endereco;

}
