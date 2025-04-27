package project.spring.exceptions_advice;

import java.io.Serial;
import java.util.stream.Collectors;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(1)
public class GlobalExceptionsHandler extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ProblemDetail> MethodArgumentNotValidException(MethodArgumentNotValidException erro){
		
		var erros = erro.getFieldErrors().stream()
				.map(item -> item.getField() + ": " + item.getDefaultMessage()+". ")
				.collect(Collectors.joining());
		
		ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, erros);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detail);
	}
	
}




