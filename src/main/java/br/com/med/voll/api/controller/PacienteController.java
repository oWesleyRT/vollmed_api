package br.com.med.voll.api.controller;

import br.com.med.voll.api.dto.entrada.MedicoAtualizaDtoEntrada;
import br.com.med.voll.api.dto.entrada.PacienteAtualizaDtoEntrada;
import br.com.med.voll.api.dto.entrada.PacienteDtoEntrada;
import br.com.med.voll.api.dto.saida.MedicoAtualizaDtoSaida;
import br.com.med.voll.api.dto.saida.MedicoDtoSaida;
import br.com.med.voll.api.dto.saida.PacienteAtualizaDtoSaida;
import br.com.med.voll.api.dto.saida.PacienteDtoSaida;
import br.com.med.voll.api.service.PacienteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteDtoSaida> cadastraPaciente(@RequestBody @Valid PacienteDtoEntrada pacienteDtoEntrada, UriComponentsBuilder uriComponentsBuilder){
        var pacienteDtoSaida = pacienteService.cadastraPaciente(pacienteDtoEntrada);
        var uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(pacienteDtoSaida.getId()).toUri();

        return ResponseEntity.ok().body(pacienteDtoSaida);
    }

    @GetMapping
    public ResponseEntity<Page<PacienteDtoSaida>> listarPacientes(Pageable pageable){
        //Buscando médicos do banco
        List<PacienteDtoSaida> listaPacientes = pacienteService.listaPacientes();
        //Objeto pageable
        int pageNumero = pageable.getPageNumber();
        int pageTamanho = pageable.getPageSize();
        Pageable pageRequest = PageRequest.of(pageNumero, pageTamanho, Sort.by("nome").ascending());
        //Retorno
        PageImpl<PacienteDtoSaida> pagePacienteDtoSaida = new PageImpl<>(listaPacientes, pageRequest, listaPacientes.size());
        return ResponseEntity.status(HttpStatus.OK).body(pagePacienteDtoSaida);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteAtualizaDtoSaida> detalharPaciente(@PathVariable Integer id){
        var pacienteAtualizaDtoSaida = pacienteService.detalharPaciente(id);
        return ResponseEntity.ok().body(pacienteAtualizaDtoSaida);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PacienteAtualizaDtoSaida> atualizaPaciente(@RequestBody PacienteAtualizaDtoEntrada pacienteAtualizaDtoEntrada){
        var pacienteAtualizaDtoSaida = pacienteService.atualizaPaciente(pacienteAtualizaDtoEntrada);
        return ResponseEntity.status(HttpStatus.OK).body(pacienteAtualizaDtoSaida);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity ativaDesativaPaciente(@PathVariable Integer id){
        var pacienteDtoSaida = pacienteService.ativaDesativaPaciente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
