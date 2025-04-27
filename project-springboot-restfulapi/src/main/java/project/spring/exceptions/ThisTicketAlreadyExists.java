package project.spring.exceptions;

import java.io.Serial;

public class ThisTicketAlreadyExists extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

	public ThisTicketAlreadyExists() {
		super("Esse boleto já existe!");
	}

}
