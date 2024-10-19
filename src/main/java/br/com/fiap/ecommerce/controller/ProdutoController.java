package br.com.fiap.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.fiap.ecommerce.dtos.ProdutoRequestCreateDto;
import br.com.fiap.ecommerce.dtos.ProdutoRequestUpdateDto;
import br.com.fiap.ecommerce.dtos.ProdutoResponseDto;
import br.com.fiap.ecommerce.mapper.ProdutoMapper;
import br.com.fiap.ecommerce.model.Produto;
import br.com.fiap.ecommerce.service.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoMapper produtoMapper;

    @GetMapping
    public List<ProdutoResponseDto> list() {
        return produtoService.list().stream()
            .map(produtoMapper::toDto)
            .toList();
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDto> create(@RequestBody ProdutoRequestCreateDto dto) {
        Produto produto = produtoMapper.toModel(dto);
        produto = produtoService.save(produto);
        return ResponseEntity.ok(produtoMapper.toDto(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> update(@PathVariable Long id, @RequestBody ProdutoRequestUpdateDto dto) {
        if (!produtoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Produto produto = produtoMapper.toModel(id, dto);
        produto = produtoService.save(produto);
        return ResponseEntity.ok(produtoMapper.toDto(produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!produtoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
