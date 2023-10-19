package br.com.microsservice.pagamentoms.controller;

import br.com.microsservice.pagamentoms.dto.ItemDTO;
import br.com.microsservice.pagamentoms.dto.PedidoDTO;
import br.com.microsservice.pagamentoms.service.CadastroPedidoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PedidoController {

    @Autowired
    private CadastroPedidoService cadastroPedidoService;

    @PostMapping(value = "/pedido")
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO criar(@Valid @RequestBody List<ItemDTO> itemList) {
        return cadastroPedidoService.pedir(itemList);
    }

    @GetMapping(value = "/pedido/{id}")
    public PedidoDTO detalhar(@PathVariable @NotNull Long id) {
        return cadastroPedidoService.detalhar(id);
    }

}
