package com.example.demo.services;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.ClienteNewDTO;
import com.example.demo.enums.TipoCliente;
import com.example.demo.exceptions.FieldErro;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.services.validation_cpf.BR;

public class ClienteInsertValidation implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}
	@Override
	public boolean isValid (ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldErro> list = new ArrayList<>();
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpf_cnpj())) {
			list.add(new FieldErro("cpf_cnpj","CPF Invalído"));
		}
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCPF(objDto.getCpf_cnpj())) {
			list.add(new FieldErro("cpf_cnpj","CNPJ Inválido"));
		}
		if(repo.findByEmail(objDto.getEmail()) !=null) {
			list.add(new FieldErro("Email","Email já está cadastrado"));
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
