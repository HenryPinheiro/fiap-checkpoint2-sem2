package br.com.fiap.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.fiap.ecommerce.dtos.PedidoRequestCreateDto;
import br.com.fiap.ecommerce.dtos.PedidoRequestUpdateDto;
import br.com.fiap.ecommerce.dtos.PedidoResponseDto;
import br.com.fiap.ecommerce.mapper.PedidoMapper;
import br.com.fiap.ecommerce.model.Pedido;
import br.com.fiap.ecommerce.service.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoMapper pedidoMapper;

    @GetMapping
    public List<PedidoResponseDto> list() {
        return pedidoService.list().stream()
            .map(pedidoMapper::toDto)
            .toList();
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDto> create(@RequestBody PedidoRequestCreateDto dto) {
        Pedido pedido = pedidoMapper.toModel(dto);
        pedido = pedidoService.save(pedido);
        return ResponseEntity.ok(pedidoMapper.toDto(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDto> update(@PathVariable Long id, @RequestBody PedidoRequestUpdateDto dto) {
        if (!pedidoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Pedido pedido = pedidoMapper.toModel(id, dto);
        pedido = pedidoService.save(pedido);
        return ResponseEntity.ok(pedidoMapper.toDto(pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!pedidoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
