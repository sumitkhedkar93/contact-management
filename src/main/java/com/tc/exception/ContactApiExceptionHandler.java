package com.tc.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@ControllerAdvice
public class ContactApiExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger log = LoggerFactory.getLogger(ContactApiExceptionHandler.class);
	private static final ObjectMapper mapper = new ObjectMapper();
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

		if (ex instanceof ValidationException) {
			ObjectNode node = mapper.createObjectNode();
			node.put("Error", ex.getMessage());
			try {
				return new ResponseEntity<>(mapper.writeValueAsString(node), HttpStatus.BAD_REQUEST);
			} catch (JsonProcessingException e) {
				log.error("Error occurred while processing request.");
			}
		}

		return new ResponseEntity<>("{\"Error\": \"Unexpected error occurred.\"}", HttpStatus.BAD_REQUEST);
	}
}
