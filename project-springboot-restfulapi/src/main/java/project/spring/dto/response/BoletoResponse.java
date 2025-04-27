package project.spring.dto.response;

import java.time.LocalDateTime;

import project.spring.enums.StatusBoleto;

public record BoletoResponse(String codigoBarras, StatusBoleto status, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {

}
