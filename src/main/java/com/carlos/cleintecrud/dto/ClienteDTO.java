package com.carlos.cleintecrud.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.br.CPF;

import com.carlos.cleintecrud.model.Cliente;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

	private Long id;
	@NotNull
	private String nome;
	@NotNull
	@CPF
	private String cpf;
	@NotNull
	private LocalDate dataNasimento;
	private String celular;
	private String telefone;
	@NotNull
	@Email
	private String email;
	private String cep;
	private String rua;
	private int numero;
	private String bairro;
	private String cidade;
	private String estado;
}
