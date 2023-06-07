package br.com.med.voll.api.controller;

import br.com.med.voll.api.dto.entrada.ConsultaDtoEntrada;
import br.com.med.voll.api.dto.saida.ConsultaDtoSaida;
import br.com.med.voll.api.service.ConsultaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(@RequestBody @Valid ConsultaDtoEntrada consultaDtoEntrada){
        System.out.println(consultaDtoEntrada);
        consultaService.agendar(consultaDtoEntrada);
        return ResponseEntity.ok(new ConsultaDtoSaida());
    }

}
