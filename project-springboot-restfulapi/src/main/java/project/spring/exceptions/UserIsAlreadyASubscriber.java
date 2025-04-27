package project.spring.exceptions;

import java.io.Serial;

public class UserIsAlreadyASubscriber extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

	public UserIsAlreadyASubscriber() {
        super("O usuário já é assinante vitalício!");
    }
}
