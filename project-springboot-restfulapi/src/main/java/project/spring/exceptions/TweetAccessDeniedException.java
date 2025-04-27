package project.spring.exceptions;

import java.io.Serial;

public class TweetAccessDeniedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

	public TweetAccessDeniedException() {
        super("Você não tem permissão para deletar este tweet");
    }
}
