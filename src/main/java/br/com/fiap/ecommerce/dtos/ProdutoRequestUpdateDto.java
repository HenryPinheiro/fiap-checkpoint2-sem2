package br.com.fiap.ecommerce.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProdutoRequestUpdateDto {
    private String nome;
    private BigDecimal valor;
}