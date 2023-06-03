package br.com.med.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AopaMundoController {
    @GetMapping
    public String AopaMundo(){
        return "Aopa mundo!";
    }
}
