package com.srivn.works.smlibrary.exception;

import com.srivn.works.smlibrary.util.AppMsg;
import com.srivn.works.smlibrary.util.AppMsg.Err;
import com.srivn.works.smlibrary.util.AppMsg.Msg;

import jakarta.annotation.Nullable;
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

	public static SMException getSMException(Err err,@Nullable String xtraString) {
		return new SMException(err.getCode(), err.getMsgWithParam(xtraString));
	}
}
