package com.carlos.cleintecrud.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlos.cleintecrud.dto.ClienteDTO;
import com.carlos.cleintecrud.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	
	
	private ClienteService service;

	@Autowired
	public ClienteController(ClienteService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> listar() {
		List<ClienteDTO> response = this.service.listar();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> criar(@RequestBody @Valid ClienteDTO clienteDTO) {
		ClienteDTO response = this.service.salvar(clienteDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> editar(@RequestBody ClienteDTO clienteDTO, @PathVariable Long id) {
		ClienteDTO response = this.service.editar(clienteDTO, id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	 
	
	
}
