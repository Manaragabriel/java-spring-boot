package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Produto;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository repo_categ;
	
	public Page<Produto> search(String nome,List<Integer> ids,Integer page,Integer linesPerPage,String orderBy,String direction)
	{
	
		Pageable pageRequest= PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
		List<Categoria> categs= repo_categ.findAllById(ids);
		return repo.search(nome, categs,  pageRequest);
	}
}
