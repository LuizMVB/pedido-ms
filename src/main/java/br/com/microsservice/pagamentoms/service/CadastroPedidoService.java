package br.com.microsservice.pagamentoms.service;

import br.com.microsservice.pagamentoms.domain.StatusPedidoEnum;
import br.com.microsservice.pagamentoms.domain.entity.Item;
import br.com.microsservice.pagamentoms.domain.entity.Pedido;
import br.com.microsservice.pagamentoms.dto.ItemDTO;
import br.com.microsservice.pagamentoms.dto.PagamentoDTO;
import br.com.microsservice.pagamentoms.dto.PedidoDTO;
import br.com.microsservice.pagamentoms.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PedidoDTO pedir(List<ItemDTO> itemDTOList) {
        var itemList = itemDTOList
                .stream()
                .map(itemDTO -> modelMapper.map(itemDTO, Item.class))
                .toList();
        var pedido = pedidoRepository.save(new Pedido(itemList));
        return modelMapper.map(pedido, PedidoDTO.class);
    }

    public PedidoDTO detalhar(Long id) {
        var pedido = pedidoRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(pedido, PedidoDTO.class);
    }

    private void modificar(List<PedidoDTO> pedidoDTOList) {
        var pedidoIdList = pedidoDTOList
                .stream()
                .map(PedidoDTO::getId)
                .toList();

        var pedidoList = pedidoRepository
                .findAllById(pedidoIdList);

        var pedidoListToBe = pedidoDTOList
                .stream()
                .map(pedidoDTO -> modelMapper.map(pedidoDTO, Pedido.class))
                .toList();

        modificarItensPedidoList(pedidoListToBe, pedidoList);

        pedidoRepository.saveAll(pedidoList);
    }

    private void modificarItensPedidoList(List<Pedido> pedidoListToBe, List<Pedido> pedidoList) {
        pedidoList.forEach(pedido -> {
            var pedidoToBeCorrespondente = pedidoListToBe
                    .stream()
                    .filter(pedidoToBe -> pedidoToBe.getId().equals(pedido.getId()))
                    .findFirst()
                    .orElseThrow(EntityNotFoundException::new);

            modelMapper.map(pedidoToBeCorrespondente, pedido);
        });
    }

    public void pagarPedidos(List<PagamentoDTO> pagamentoDTOList) {
        List<PedidoDTO> pedidoDTOList = pagamentoDTOList
                .stream()
                .map(pagamentoDTO -> {
                    var pedidoDTO = new PedidoDTO();
                    pedidoDTO.setId(pagamentoDTO.getIdPedido());
                    pedidoDTO.setStatus(StatusPedidoEnum.PAGO);
                    return pedidoDTO;
                })
                .toList();
        modificar(pedidoDTOList);
    }
}
