package br.com.med.voll.api.service;

import br.com.med.voll.api.dto.entrada.MedicoAtualizaDtoEntrada;
import br.com.med.voll.api.dto.entrada.MedicoDtoEntrada;
import br.com.med.voll.api.dto.saida.MedicoAtualizaDtoSaida;
import br.com.med.voll.api.dto.saida.MedicoDtoSaida;
import br.com.med.voll.api.model.Endereco;
import br.com.med.voll.api.model.Medico;
import br.com.med.voll.api.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private EnderecoService enderecoService;

    @Transactional
    public MedicoDtoSaida cadastraMedico(MedicoDtoEntrada medicoDtoEntrada) {
        var endereco = new Endereco(medicoDtoEntrada.getEndereco());
        var medicoEntity = new Medico(medicoDtoEntrada);

        try {
            var enderecoDtoValidado = enderecoService.validaCep(medicoDtoEntrada.getEndereco());
            endereco.setLogradouro(enderecoDtoValidado.getLogradouro());
            endereco.setBairro(enderecoDtoValidado.getBairro());
            endereco.setUf(enderecoDtoValidado.getUf());
            endereco.setCidade(enderecoDtoValidado.getLocalidade());
            medicoEntity.setEndereco(endereco);
            medicoRepository.save(medicoEntity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        var medicoDtoSaida = new MedicoDtoSaida(medicoRepository.getReferenceById(medicoEntity.getId()));
        return medicoDtoSaida;
    }

    public List<MedicoDtoSaida> listarMedico() {
        List<Medico> listaMedico = medicoRepository.findAll();
        List<MedicoDtoSaida> listaMedicoDtoSaida = new ArrayList<>();
        for (Medico medico : listaMedico) {
            var medicoDtoSaida = new MedicoDtoSaida(medico);
            listaMedicoDtoSaida.add(medicoDtoSaida);
        }
        return listaMedicoDtoSaida;
    }

    public MedicoAtualizaDtoSaida atualizaMedico(MedicoAtualizaDtoEntrada medicoAtualizaDtoEntrada) {
        //Buscando médico na base de dados
        var medico = medicoRepository.getReferenceById(medicoAtualizaDtoEntrada.id());
        //Atualizando nome
        if(medicoAtualizaDtoEntrada.nome() != null){
            medico.setNome(medicoAtualizaDtoEntrada.nome());
        }
        //Atualizando telefone
        if(medicoAtualizaDtoEntrada.telefone() != null){
            medico.setTelefone(medicoAtualizaDtoEntrada.telefone());
        }
        //Atualizando endereço
        if(medicoAtualizaDtoEntrada.endereco() != null){
            try {
                var enderecoDtoValidado = enderecoService.validaCep(medicoAtualizaDtoEntrada.endereco());
                var endereco = medico.getEndereco();
                endereco.setLogradouro(enderecoDtoValidado.getLogradouro());
                endereco.setBairro(enderecoDtoValidado.getBairro());
                endereco.setUf(enderecoDtoValidado.getUf());
                endereco.setCidade(enderecoDtoValidado.getLocalidade());
                endereco.setCep(medicoAtualizaDtoEntrada.endereco().getCep());
                medico.setEndereco(endereco);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return new MedicoAtualizaDtoSaida(medico);

    }

    public MedicoDtoSaida ativaDesativaMedico(Integer id) {
        //Buscando médico na base de dados
        var medico = medicoRepository.getReferenceById(id);
        //Alterando o atributo ativo
        if(!medico.getAtivo()){
            medico.setAtivo(true);
        } else {
            medico.setAtivo(false);
        }
        //Retornando um dto saída
        return new MedicoDtoSaida(medico);
    }
}
