package br.com.microsservice.pagamentoms.repository;

import br.com.microsservice.pagamentoms.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
