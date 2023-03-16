package com.srivn.works.smlibrary.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SMException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String exType;
	private String message;

}
