package com.srivn.works.smlibrary.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srivn.works.smlibrary.service.UtilServices;

@RestController
@RequestMapping("utils")
public class UtilControls {


	@Autowired
	UtilServices utilServices;
	
	@PostMapping(value = "/addClsnDes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addClsnDes(@RequestParam String clsnDes) {
		return new ResponseEntity<>(utilServices.addClsn(clsnDes), HttpStatus.OK);
	}
	
	@PostMapping(value = "/addClsnValue", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addClsnValue(@RequestParam String clsnDes,String clsnValue) {
		return new ResponseEntity<>(utilServices.addClsnValue(clsnDes,clsnValue), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getClsnByDes" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getClsnByDes(@RequestParam String clsnDes) {
		return new ResponseEntity<>(utilServices.getClsnByDes(clsnDes), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getClsnByVal" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getClsnByVal(@RequestParam String clsnVal) {
		return new ResponseEntity<>(utilServices.getClsnValByValue(clsnVal), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getClsnAll" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getClsnAll() {
		return new ResponseEntity<>(utilServices.getClsnAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getClsnValAll" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getClsnValAll() {
		return new ResponseEntity<>(utilServices.getClsnValAll(), HttpStatus.OK);
	}
}
