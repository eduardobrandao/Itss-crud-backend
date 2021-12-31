package com.carlos.cleintecrud.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.carlos.cleintecrud.core.dtos.ProblemType;
import com.carlos.cleintecrud.core.dtos.Problema;
import com.carlos.cleintecrud.core.exception.EntidadeNaoEncontradaException;

import org.springframework.http.HttpHeaders;
import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(
			EntidadeNaoEncontradaException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
		String detail = ex.getMessage();
		
		Problema problema = createProblemBuilder(status, problemType, detail);
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

	    ProblemType problemType = ProblemType.DADOS_INVALIDOS;
	    String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
	    
	    BindingResult bindingResult = ex.getBindingResult();
	    
	    List<Problema.Field> problemFields = bindingResult.getFieldErrors().stream()
	    		.map(fieldError -> Problema.Field.builder()
	    				.name(fieldError.getField())
	    				.userMessage(fieldError.getDefaultMessage())
	    				.build())
	    		.toList();
	    
	    Problema problem = createProblemBuilder(status, problemType, detail);
	    
	    return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if (body == null) {
			body = Problema.builder()
				.title(status.getReasonPhrase())
				.status(status.value())
				.build();
		} else if (body instanceof String) {
			body = Problema.builder()
				.title((String) body)
				.status(status.value())
				.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private Problema createProblemBuilder(HttpStatus status,
			ProblemType problemType, String detail) {
		
		return Problema.builder()
			.status(status.value())
			.type(problemType.getUri())
			.title(problemType.getTitle())
			.detail(detail).build();
		
	}
}
