package br.com.microsservice.pagamentoms.dto;

import br.com.microsservice.pagamentoms.domain.StatusPedidoEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PedidoDTO {

    private Long id;

    private BigDecimal valorTotal;

    private StatusPedidoEnum status;

}
