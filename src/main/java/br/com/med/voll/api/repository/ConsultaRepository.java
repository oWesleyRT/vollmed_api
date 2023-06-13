package br.com.med.voll.api.repository;

import br.com.med.voll.api.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {
    Boolean existsByMedicoIdAndData(Integer integer, LocalDateTime data);

    Boolean existsByPacienteIdAndDataBetween(Integer integer, LocalDateTime primeiroHorairo, LocalDateTime ultimoHorario);
}
