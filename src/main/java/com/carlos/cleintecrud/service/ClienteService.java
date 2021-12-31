package com.carlos.cleintecrud.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlos.cleintecrud.core.exception.EntidadeNaoEncontradaException;
import com.carlos.cleintecrud.dto.ClienteDTO;
import com.carlos.cleintecrud.model.Cliente;
import com.carlos.cleintecrud.repository.ClienteRepository;



@Service
public class ClienteService {

	private ClienteRepository repository;

	private static final String MSG_USUÁRIO_EM_USO
    = "Cliente de código %d não encontrado.";
	
	private static final String MSG_USUÁRIO_EM_USO_CPF
    = "CPF já cadastro .";
	
	@Autowired
	public ClienteService(ClienteRepository repository) {
		this.repository = repository;
	}

	public ClienteDTO salvar(ClienteDTO clienteDTO) {
		Cliente cliente  = getCliente(clienteDTO);
		var novoCliente = repository.findByCpf(cliente.getCpf());
		if(novoCliente.isPresent()) {
			throw new EntidadeNaoEncontradaException(MSG_USUÁRIO_EM_USO_CPF);
		}
		Cliente salvo = repository.save(cliente);
		
		return getClienteDTO(salvo);
	}
	
	public ClienteDTO editar(ClienteDTO clienteDTO, Long id) {
		Cliente cliente = searchOrFailEntity(id);
		
		BeanUtils.copyProperties(clienteDTO, cliente, "id");
		Cliente edit = repository.save(cliente);
		
		return getClienteDTO(edit);
	}
	
	public List<ClienteDTO> listar() {
		List<Cliente> cleintes = repository.findAll();
		List<ClienteDTO> dtos = cleintes.stream().map( t -> getClienteDTO(t)).toList();
				
		return dtos;
	}
	
	private Cliente searchOrFailEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_USUÁRIO_EM_USO, id)
                ));
    }
	
	private Cliente getCliente(ClienteDTO dto) {
		return Cliente
				.builder()
				.id(dto.getId())
				.nome(dto.getNome())
				.cpf(dto.getCpf())
				.dataNasimento(dto.getDataNasimento())
				.celular(dto.getCelular())
				.telefone(dto.getTelefone())
				.email(dto.getEmail())
				.cep(dto.getCep())
				.rua(dto.getRua())
				.numero(dto.getNumero())
				.bairro(dto.getBairro())
				.cidade(dto.getCidade())
				.estado(dto.getEstado())
				.build();
	}
	
	private ClienteDTO getClienteDTO(Cliente dto) {
		return ClienteDTO
				.builder()
				.id(dto.getId())
				.nome(dto.getNome())
				.cpf(dto.getCpf())
				.dataNasimento(dto.getDataNasimento())
				.celular(dto.getCelular())
				.telefone(dto.getTelefone())
				.email(dto.getEmail())
				.cep(dto.getCep())
				.rua(dto.getRua())
				.numero(dto.getNumero())
				.bairro(dto.getBairro())
				.cidade(dto.getCidade())
				.estado(dto.getEstado())
				.build();
	}

	

	
	
}
