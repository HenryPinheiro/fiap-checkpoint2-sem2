package br.com.fiap.ecommerce.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import org.modelmapper.ModelMapper;
import br.com.fiap.ecommerce.model.Pedido;

@Data
public class PedidoRequestUpdateDto {
    private Long idCliente;
    private LocalDate dataPedido;
    private String status;
    private String formaPagamento;
    private BigDecimal valorTotal;
}