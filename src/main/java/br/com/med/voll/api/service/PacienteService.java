package br.com.med.voll.api.service;

import br.com.med.voll.api.dto.entrada.PacienteAtualizaDtoEntrada;
import br.com.med.voll.api.dto.entrada.PacienteDtoEntrada;
import br.com.med.voll.api.dto.saida.MedicoAtualizaDtoSaida;
import br.com.med.voll.api.dto.saida.MedicoDtoSaida;
import br.com.med.voll.api.dto.saida.PacienteAtualizaDtoSaida;
import br.com.med.voll.api.dto.saida.PacienteDtoSaida;
import br.com.med.voll.api.model.Endereco;
import br.com.med.voll.api.model.Medico;
import br.com.med.voll.api.model.Paciente;
import br.com.med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PacienteRepository pacienteRepository;

    public PacienteDtoSaida cadastraPaciente(PacienteDtoEntrada pacienteDtoEntrada){
        var endereco = new Endereco(pacienteDtoEntrada.endereco());
        var paciente = new Paciente(pacienteDtoEntrada);

        try {
            var enderecoDtoValidado = enderecoService.validaCep(pacienteDtoEntrada.endereco());
            endereco.setLogradouro(enderecoDtoValidado.getLogradouro());
            endereco.setBairro(enderecoDtoValidado.getBairro());
            endereco.setUf(enderecoDtoValidado.getUf());
            endereco.setCidade(enderecoDtoValidado.getLocalidade());
            paciente.setEndereco(endereco);
            pacienteRepository.save(paciente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        var pacienteDtoSaida = new PacienteDtoSaida(pacienteRepository.getReferenceById(paciente.getId()));
        return pacienteDtoSaida;
    }

    public List<PacienteDtoSaida> listaPacientes() {
        List<Paciente> listaPaciente = pacienteRepository.findAll();
        List<PacienteDtoSaida> listaPacienteDtoSaida = new ArrayList<>();
        for (Paciente paciente : listaPaciente) {
            if (paciente.getAtivo()) {
                var pacienteDtoSaida = new PacienteDtoSaida(paciente);
                listaPacienteDtoSaida.add(pacienteDtoSaida);
            }
        }
        Collections.sort(listaPacienteDtoSaida);
        return listaPacienteDtoSaida;
    }

    public PacienteAtualizaDtoSaida atualizaPaciente(PacienteAtualizaDtoEntrada pacienteAtualizaDtoEntrada) {
        //Buscando paciente na base de dados
        var paciente = pacienteRepository.getReferenceById(pacienteAtualizaDtoEntrada.id());
        //Atualizando nome
        if(pacienteAtualizaDtoEntrada.nome() != null){
            paciente.setNome(pacienteAtualizaDtoEntrada.nome());
        }
        //Atualizando telefone
        if(pacienteAtualizaDtoEntrada.telefone() != null){
            paciente.setTelefone(pacienteAtualizaDtoEntrada.telefone());
        }
        //Atualizando endereço
        if(pacienteAtualizaDtoEntrada.endereco() != null){
            try {
                var enderecoDtoValidado = enderecoService.validaCep(pacienteAtualizaDtoEntrada.endereco());
                var endereco = paciente.getEndereco();
                endereco.setLogradouro(enderecoDtoValidado.getLogradouro());
                endereco.setBairro(enderecoDtoValidado.getBairro());
                endereco.setUf(enderecoDtoValidado.getUf());
                endereco.setCidade(enderecoDtoValidado.getLocalidade());
                endereco.setCep(pacienteAtualizaDtoEntrada.endereco().getCep());
                paciente.setEndereco(endereco);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return new PacienteAtualizaDtoSaida(paciente);
    }

    public PacienteDtoSaida ativaDesativaPaciente(Integer id) {
        //Buscando médico na base de dados
        var paciente = pacienteRepository.getReferenceById(id);
        //Alterando o atributo ativo
        if(!paciente.getAtivo()){
            paciente.setAtivo(true);
        } else {
            paciente.setAtivo(false);
        }
        //Retornando um dto saída
        return new PacienteDtoSaida(paciente);
    }

    public PacienteAtualizaDtoSaida detalharPaciente(Integer id) {
        var paciente = pacienteRepository.getReferenceById(id);
        return new PacienteAtualizaDtoSaida(paciente);
    }
}
