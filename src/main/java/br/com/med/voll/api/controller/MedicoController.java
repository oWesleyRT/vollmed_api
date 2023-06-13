package br.com.med.voll.api.controller;

import br.com.med.voll.api.dto.entrada.MedicoAtualizaDtoEntrada;
import br.com.med.voll.api.dto.entrada.MedicoDtoEntrada;
import br.com.med.voll.api.dto.saida.MedicoAtualizaDtoSaida;
import br.com.med.voll.api.dto.saida.MedicoDtoSaida;
import br.com.med.voll.api.service.MedicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public ResponseEntity<MedicoDtoSaida> cadastrarMedico(@RequestBody @Valid MedicoDtoEntrada medicoDtoEntrada, UriComponentsBuilder uriBuilder){
        var medicoDtoSaida = medicoService.cadastraMedico(medicoDtoEntrada);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medicoDtoSaida.getId()).toUri();

        return ResponseEntity.created(uri).body(medicoDtoSaida);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoAtualizaDtoSaida> detalharMedico(@PathVariable Integer id){
        var medicoAtualizaDtoSaida = medicoService.detalharMedico(id);
        return ResponseEntity.ok().body(medicoAtualizaDtoSaida);
    }

    @GetMapping
    public ResponseEntity<Page<MedicoDtoSaida>> listarMedicos(Pageable pageable){
        //Buscando médicos do banco
        List<MedicoDtoSaida> listaMedicos = medicoService.listarMedicos();
        //Objeto pageable
        int pageNumero = pageable.getPageNumber();
        int pageTamanho = pageable.getPageSize();
        Pageable pageRequest = PageRequest.of(pageNumero, pageTamanho, Sort.by("nome").ascending());
        //Retorno
        PageImpl<MedicoDtoSaida> pageMedicoDtoSaida = new PageImpl<>(listaMedicos, pageRequest, listaMedicos.size());
        return ResponseEntity.status(HttpStatus.OK).body(pageMedicoDtoSaida);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<MedicoAtualizaDtoSaida> atualizaMedico(@RequestBody MedicoAtualizaDtoEntrada medicoAtualizaDtoEntrada){
        var medicoAtualizaDtoSaida = medicoService.atualizaMedico(medicoAtualizaDtoEntrada);
        return ResponseEntity.status(HttpStatus.OK).body(medicoAtualizaDtoSaida);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity ativaDesativaMedico (@PathVariable Integer id){
        var medicoDtoSaida = medicoService.ativaDesativaMedico(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
