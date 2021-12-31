package com.carlos.cleintecrud.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table( name  = "tb_cliente")
public class Cliente {

	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String nome;
	private String cpf;
	private LocalDate dataNasimento;
	private String celular;
	private String telefone;
	private String email;
	private String cep;
	
	private String rua;
	private int numero;
	private String bairro;
	private String cidade;
	private String estado;
}
