package com.srivn.works.smlibrary.exception;

import java.io.Serializable;
import com.srivn.works.smlibrary.util.AppMsg.Msg;
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
	
	public static SMMessage getSMMessage(Msg msg) {
		return SMMessage.builder().code(msg.getCode()).message(msg.getMsg())
				.build();
	}
	
}
