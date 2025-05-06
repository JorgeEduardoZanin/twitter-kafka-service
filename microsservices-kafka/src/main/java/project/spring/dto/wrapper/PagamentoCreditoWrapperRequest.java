package project.spring.dto.wrapper;

import jakarta.validation.Valid;
import project.spring.dto.request.PagamentoCreditoRequest;
import project.spring.dto.request.TitularCartaoCreditoRequest;

public record PagamentoCreditoWrapperRequest(
    @Valid PagamentoCreditoRequest pagamentoCredito,
    @Valid TitularCartaoCreditoRequest titularCartao
) {}
