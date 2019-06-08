package com.example.demo.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	
	private List<FieldErro> list = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<FieldErro> getList() {
		return list;
	}

	public void addError(String fieldName,String mensagem) {
		list.add(new FieldErro(fieldName,mensagem));
	}
	
	
}
