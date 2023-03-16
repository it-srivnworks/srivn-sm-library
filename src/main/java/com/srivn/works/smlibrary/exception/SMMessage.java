package com.srivn.works.smlibrary.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class SMMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	private String code;
	private String message;
	
}
