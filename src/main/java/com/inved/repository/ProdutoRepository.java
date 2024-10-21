package com.inved.repository;

import com.inved.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByNomeProdutoAndCodigoProduto(String nomeProduto, Integer codigoProduto);
}
