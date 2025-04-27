package project.spring.exceptions_advice;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import project.spring.exceptions.ThisTicketAlreadyExists;
import project.spring.exceptions.UserIsAlreadyASubscriber;
import project.spring.services.BoletoServices;

import java.io.Serial;


@RestControllerAdvice(assignableTypes = BoletoServices.class)
@Order(1)
public class BoletoExceptionAdvice extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

	@ExceptionHandler(UserIsAlreadyASubscriber.class)
	public ResponseEntity<ProblemDetail> handleSignatureDenied(UserIsAlreadyASubscriber erro){
		ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, erro.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(detail);		
	}
	
	@ExceptionHandler(ThisTicketAlreadyExists.class)
	public ResponseEntity<ProblemDetail> handleTicketAlreadyExists(ThisTicketAlreadyExists erro){
		ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, erro.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(detail);
	}
	
}




