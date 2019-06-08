package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Categoria;
import com.example.demo.dto.CategoriaDTO;
import com.example.demo.exceptions.DataIntegrityException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	 
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	public Categoria buscar(Integer id) {
	
		Optional<Categoria> obj= repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado"
				));
	}
	
	public Categoria insert(Categoria obj) {
		return repo.save(obj);
	}
	public Categoria update(Categoria obj) {
		buscar(obj.getId());
		return repo.save(obj);
	}
	public void delete(Integer id) {
		buscar(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Produtos com categorias");
		}
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage,String orderBy,String direction){
		PageRequest pageRequest=  PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(),objDTO.getNome());
	}
}
