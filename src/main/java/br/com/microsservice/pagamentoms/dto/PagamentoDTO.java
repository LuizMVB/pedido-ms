package br.com.microsservice.pagamentoms.dto;

import br.com.microsservice.pagamentoms.domain.StatusPagamentoEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PagamentoDTO {

    @Null
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Null
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String ticket;

    @NotNull
    private BigDecimal valorTotal;

    private StatusPagamentoEnum status = StatusPagamentoEnum.REALIZADO;

    @NotNull
    private Long idPedido;

    @Null
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dataEfetivacao;

}
