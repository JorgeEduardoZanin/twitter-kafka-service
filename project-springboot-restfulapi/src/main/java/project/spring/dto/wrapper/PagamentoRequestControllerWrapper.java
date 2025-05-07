package project.spring.dto.wrapper;

import jakarta.validation.Valid;
import project.spring.dto.request.CartaoCreditoRequest;
import project.spring.dto.request.TitularCartaoCreditoRequest;

public record PagamentoRequestControllerWrapper(
		@Valid TitularCartaoCreditoRequest titularRequest,
		@Valid CartaoCreditoRequest cartaoRequest
		) {}

