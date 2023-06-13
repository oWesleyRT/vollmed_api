package br.com.med.voll.api.service;

import br.com.med.voll.api.dto.entrada.ConsultaDtoEntrada;
import br.com.med.voll.api.dto.saida.ConsultaDtoSaida;
import br.com.med.voll.api.exception.ValidacaoException;
import br.com.med.voll.api.model.Consulta;
import br.com.med.voll.api.model.Medico;
import br.com.med.voll.api.repository.ConsultaRepository;
import br.com.med.voll.api.repository.MedicoRepository;
import br.com.med.voll.api.repository.PacienteRepository;
import br.com.med.voll.api.validacoes.ValidadorAgendamentoDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    public ConsultaDtoSaida agendar(ConsultaDtoEntrada consultaDtoEntrada){
        if(!pacienteRepository.existsById(consultaDtoEntrada.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        if(consultaDtoEntrada.idMedico() != null && !medicoRepository.existsById(consultaDtoEntrada.idMedico())){
            throw new ValidacaoException("Id do medico informado não existe!");
        }

        validadores.forEach(v -> v.validar(consultaDtoEntrada));

        var medico = escolherMedico(consultaDtoEntrada);
        var paciente = pacienteRepository.findById(consultaDtoEntrada.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente, consultaDtoEntrada.data());
        consultaRepository.save(consulta);

        return new ConsultaDtoSaida(consulta);

    }

    private Medico escolherMedico(ConsultaDtoEntrada consultaDtoEntrada) {
        if(consultaDtoEntrada.idMedico() != null) {
            return medicoRepository.getReferenceById(consultaDtoEntrada.idMedico());
        }
        if(consultaDtoEntrada.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(consultaDtoEntrada.especialidade(),
                consultaDtoEntrada.data());
    }

}
