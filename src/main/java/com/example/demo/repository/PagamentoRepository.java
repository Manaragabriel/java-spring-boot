package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Pagamento;
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento,Integer>{

}
