package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.demo.domain.Cidade;
import com.example.demo.domain.Cliente;
import com.example.demo.domain.Endereco;
import com.example.demo.domain.Cliente;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.ClienteNewDTO;
import com.example.demo.enums.TipoCliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.EnderecoRepository;
import com.example.demo.exceptions.DataIntegrityException;
import com.example.demo.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	
	@Autowired
	private EnderecoRepository repo_end;
	public Cliente find(Integer id) {
		Optional<Cliente> obj= repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto nao encontrado"));
		
	}
	@Transactional
	public Cliente insert(Cliente obj) {
		obj= repo.save(obj);
		repo_end.saveAll(obj.getEnderecos());
		return obj;
	}
	public Cliente update(Cliente obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Produtos com Clientes");
		}
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage,String orderBy,String direction){
		PageRequest pageRequest=  PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO){
		return new Cliente(objDTO.getId(),objDTO.getNome(),objDTO.getEmail(),null,null);
	}	
	public Cliente fromDTO(ClienteNewDTO objDTO){
		Cliente c= new Cliente(null,objDTO.getNome(),objDTO.getEmail(),objDTO.getCpf_cnpj(),TipoCliente.toEnum(objDTO.getTipo()));
		Cidade city= new Cidade(objDTO.getCidade(),null,null);
		Endereco end= new Endereco(null,objDTO.getLogradouro(),objDTO.getNumero(),objDTO.getComplemento(),
				objDTO.getBairro(),
				objDTO.getCep(),
				c,
				city
				);
		c.getEnderecos().add(end);
		c.getTelefones().add(objDTO.getTelefone());
		if(objDTO.getTelefone2() != null) {
			c.getTelefones().add(objDTO.getTelefone2());
		}
		return c;
	}	
	
}
