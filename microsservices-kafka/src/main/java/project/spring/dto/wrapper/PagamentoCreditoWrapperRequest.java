package project.spring.dto.wrapper;

import java.math.BigDecimal;

import jakarta.validation.Valid;
import project.spring.dto.request.PagamentoCreditoRequest;
import project.spring.dto.request.TitularCartaoCreditoRequest;
import project.spring.dto.request.UsuarioPagamentoRequest;

public record PagamentoCreditoWrapperRequest(
    @Valid PagamentoCreditoRequest pagamentoCredito,
    @Valid TitularCartaoCreditoRequest titularCartao,
    @Valid UsuarioPagamentoRequest usuario,
    @Valid BigDecimal value,
    @Valid Long identificadorApiPrincipal
) {}
