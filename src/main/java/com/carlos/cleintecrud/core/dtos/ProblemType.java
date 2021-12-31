package com.carlos.cleintecrud.core.dtos;

import lombok.Getter;

@Getter
public enum ProblemType {

	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
	DADOS_INVALIDOS("/dados invalidos", "Dados invalidos");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = "https://clientes.com.br" + path;
		this.title = title;
	}
	
}
