package br.com.fiap.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.fiap.ecommerce.dtos.ClienteRequestCreateDto;
import br.com.fiap.ecommerce.dtos.ClienteRequestUpdateDto;
import br.com.fiap.ecommerce.dtos.ClienteResponseDto;
import br.com.fiap.ecommerce.mapper.ClienteMapper;
import br.com.fiap.ecommerce.model.Cliente;
import br.com.fiap.ecommerce.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteMapper clienteMapper;

    @GetMapping
    public List<ClienteResponseDto> list() {
        return clienteService.list().stream()
            .map(clienteMapper::toDto)
            .toList();
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDto> create(@RequestBody ClienteRequestCreateDto dto) {
        Cliente cliente = clienteMapper.toModel(dto);
        cliente = clienteService.save(cliente);
        return ResponseEntity.ok(clienteMapper.toDto(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> update(@PathVariable Long id, @RequestBody ClienteRequestUpdateDto dto) {
        if (!clienteService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Cliente cliente = clienteMapper.toModel(id, dto);
        cliente = clienteService.save(cliente);
        return ResponseEntity.ok(clienteMapper.toDto(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!clienteService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
