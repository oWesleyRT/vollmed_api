package br.com.med.voll.api.repository;

import br.com.med.voll.api.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    @Query(value = "select p.ativo from pacientes p " +
            "where p.id = :id", nativeQuery = true)
    boolean findAtivoById(Integer id);
}
