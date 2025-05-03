package project.spring.dto.request;

import java.util.UUID;

public record PagamentoPixRequest(UUID customerId, String billingType,  Long amount, String dueDate) {
	
}
