package br.com.fiap.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.fiap.ecommerce.dtos.ItemPedidoRequestCreateDto;
import br.com.fiap.ecommerce.dtos.ItemPedidoRequestUpdateDto;
import br.com.fiap.ecommerce.dtos.ItemPedidoResponseDto;
import br.com.fiap.ecommerce.mapper.ItemPedidoMapper;
import br.com.fiap.ecommerce.model.ItemPedido;
import br.com.fiap.ecommerce.service.ItemPedidoService;

import java.util.List;

@RestController
@RequestMapping("/item-pedidos")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService itemPedidoService;

    @Autowired
    private ItemPedidoMapper itemPedidoMapper;

    @GetMapping
    public List<ItemPedidoResponseDto> list() {
        return itemPedidoService.list().stream()
            .map(itemPedidoMapper::toDto)
            .toList();
    }

    @PostMapping
    public ResponseEntity<ItemPedidoResponseDto> create(@RequestBody ItemPedidoRequestCreateDto dto) {
        ItemPedido itemPedido = itemPedidoMapper.toModel(dto);
        itemPedido = itemPedidoService.save(itemPedido);
        return ResponseEntity.ok(itemPedidoMapper.toDto(itemPedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoResponseDto> update(@PathVariable Long id, @RequestBody ItemPedidoRequestUpdateDto dto) {
        if (!itemPedidoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ItemPedido itemPedido = itemPedidoMapper.toModel(id, dto);
        itemPedido = itemPedidoService.save(itemPedido);
        return ResponseEntity.ok(itemPedidoMapper.toDto(itemPedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!itemPedidoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        itemPedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
