package br.com.microsservice.pagamentoms.listener;

import br.com.microsservice.pagamentoms.domain.StatusPedidoEnum;
import br.com.microsservice.pagamentoms.dto.PagamentoDTO;
import br.com.microsservice.pagamentoms.dto.PedidoDTO;
import br.com.microsservice.pagamentoms.service.CadastroPedidoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RabbitMQListener {

    @Autowired
    private CadastroPedidoService cadastroPedidoService;

    @RabbitListener(queues = "pagamento.concluido")
    public void pagamentoConcluido(List<PagamentoDTO> pagamentoDTOList) {
        cadastroPedidoService.pagarPedidos(pagamentoDTOList);
    }

}
