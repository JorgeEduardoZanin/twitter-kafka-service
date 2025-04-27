package project.spring.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BoletoRequest(@NotNull(message = "O código de barras não pode ser nulo")
@NotEmpty(message = "Não pode ser vazio")
String codigoBarras) {

}
