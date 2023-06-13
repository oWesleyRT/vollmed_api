package br.com.med.voll.api.repository;

import br.com.med.voll.api.enums.Especialidade;
import br.com.med.voll.api.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    @Query(value = "SELECT m.* FROM medicos m where m.ativo = 1 and m.especialidade = :especialidade and m.id not in (select c.medico_id from consultas c where c.data = :data) order by rand() limit 1", nativeQuery = true)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    @Query(value = "select m.ativo from medicos m where m.id = :id", nativeQuery = true)
    Boolean findAtivoById(Integer id);
}
