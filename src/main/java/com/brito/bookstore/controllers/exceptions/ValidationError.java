package com.brito.bookstore.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private List<FieldMessage> erros = new ArrayList<>();

	public ValidationError() {

	}

	public ValidationError(Long timestamp, Integer status, String error) {
		super(timestamp, status, error);

	}

	public List<FieldMessage> getErros() {
		return erros;
	}

	public void addErros(String fieldName, String message) {
		this.erros.add(new FieldMessage(fieldName, message));
	}

}
