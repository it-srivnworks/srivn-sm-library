package com.srivn.works.smlibrary.controls;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srivn.works.smlibrary.exception.SMMessage;
import com.srivn.works.smlibrary.util.AppMsg;

@RestController
@RequestMapping("welcome")
public class WelcomeControl {

	@GetMapping(value = "/tester", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SMMessage> tester() {
		System.out.println("Tester");
		SMMessage msg = SMMessage.builder().code(AppMsg.Msg.MSG_000.getCode()).message(AppMsg.Msg.MSG_000.getMsg()).build();
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
