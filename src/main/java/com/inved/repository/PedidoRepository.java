package com.inved.repository;

import com.inved.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT p FROM Pedido p WHERE p.cliente.cpf = :cpf AND p.dataPedido BETWEEN :dataInicio AND :dataFim ")
    List<Pedido> findByClienteAndDataPedidoAfter(@Param("cpf") String cpf, @Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);
}

