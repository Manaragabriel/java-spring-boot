package com.example.demo.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.example.demo.domain.Cliente;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.enums.TipoCliente;
import com.example.demo.exceptions.FieldErro;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.services.validation_cpf.BR;

public class ClienteUpdateValidation implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}
	@Override
	public boolean isValid (ClienteDTO objDto, ConstraintValidatorContext context) {
		List<FieldErro> list = new ArrayList<>();
		Map<String,String> map= (Map<String,String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer url_id= Integer.parseInt(map.get("id"));
		Cliente aux= repo.findByEmail(objDto.getEmail());
		if(aux != null && !aux.getId().equals(url_id)) {
			list.add(new FieldErro("Email","Emaíl já existe"));
		}
		// inclua os testes aqui, inserindo erros na lista
		for (FieldErro e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
			.addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
	
}
