package project.spring.exceptions_advice;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import project.spring.exceptions.TweetAccessDeniedException;
import project.spring.services.TweetServices;

import java.io.Serial;


@RestControllerAdvice(assignableTypes = TweetServices.class)
@Order(1)
public class TweetExceptionAdvice extends RuntimeException{

    /**
     * 
     */
    @Serial
    private static final long serialVersionUID = 1L;

	@ExceptionHandler(TweetAccessDeniedException.class)
	public ResponseEntity<ProblemDetail> handleAccessDenied(TweetAccessDeniedException erro){
		ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, erro.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(detail);
	}
	
}
