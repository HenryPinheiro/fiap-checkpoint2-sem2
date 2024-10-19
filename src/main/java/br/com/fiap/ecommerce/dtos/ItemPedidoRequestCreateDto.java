package br.com.fiap.ecommerce.dtos;

import lombok.Data;

import java.math.BigDecimal;

import org.modelmapper.ModelMapper;
import br.com.fiap.ecommerce.model.ItemPedido;

@Data
public class ItemPedidoRequestCreateDto {
    private Long idPedido;
    private Long idProduto;
    private BigDecimal quantidade;
    private BigDecimal valorTotal;
}