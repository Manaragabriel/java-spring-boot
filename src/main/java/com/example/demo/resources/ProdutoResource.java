package com.example.demo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Produto;
import com.example.demo.dto.CategoriaDTO;
import com.example.demo.dto.ProdutoDTO;
import com.example.demo.resources.utils.URL;
import com.example.demo.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome",defaultValue="0") String nome,
			@RequestParam(value="categorias",defaultValue="0") String categorias,
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue="nome") String orderBy,
			@RequestParam(value="direction",defaultValue="ASC") String direction
			){
		nome= URL.decodeParam(nome);
		List<Integer> ids = URL.decodeListInteger(categorias);
		Page<Produto> prods= service.search(nome,ids,page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> prodsDTO= prods.map(obj->new ProdutoDTO(obj));
		return ResponseEntity.ok().body(prodsDTO);
		
	}
	
}
