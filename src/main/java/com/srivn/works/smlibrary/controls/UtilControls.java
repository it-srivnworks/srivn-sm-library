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

import com.srivn.works.smlibrary.db.mapper.CustomBookInfoMapper;
import com.srivn.works.smlibrary.db.repo.AuthorInfoRepo;
import com.srivn.works.smlibrary.db.repo.BookInfoRepo;
import com.srivn.works.smlibrary.service.UtilServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("utils")
public class UtilControls {


	private final UtilServices utilServices;
	
	@PostMapping(value = "/addClsnDes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addClsnDes(@RequestParam String clsnDes) {
		return new ResponseEntity<>(utilServices.addClsn(clsnDes), HttpStatus.OK);
	}
	
	@PostMapping(value = "/addClsnValue", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addClsnValue(@RequestParam String clsnDes,String clsnValue) {
		return new ResponseEntity<>(utilServices.addClsnValue(clsnDes,clsnValue), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getByClsnDes" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getByClsnDes(@RequestParam String clsnDes) {
		return new ResponseEntity<>(utilServices.getByClsnDes(clsnDes), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getByClsnVal" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getByClsnVal(@RequestParam String clsnVal) {
		return new ResponseEntity<>(utilServices.getByClsnValue(clsnVal), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getClsnAll" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getClsnAll() {
		return new ResponseEntity<>(utilServices.getClsnAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getClsnValAll" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getClsnValAll() {
		return new ResponseEntity<>(utilServices.getClsnValAll(), HttpStatus.OK);
	}
	
	/**
	 * Data 
	 */
	@PostMapping(value = "/addDataCat", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addDataCat(@RequestParam String dataCategory) {
		return new ResponseEntity<>(utilServices.addDataCat(dataCategory), HttpStatus.OK);
	}
	
	@PostMapping(value = "/addDataValue", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addDataValue(@RequestParam String dataCategory,String addDataValue) {
		return new ResponseEntity<>(utilServices.addDataValue(dataCategory,addDataValue), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getByCatValue" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getByCatValue(@RequestParam String catValue) {
		return new ResponseEntity<>(utilServices.getByCatValue(catValue), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getByDataValue" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getByDataValue(@RequestParam String dataValue) {
		return new ResponseEntity<>(utilServices.getByDataValue(dataValue), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getDataCategoryAll" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getDataCategoryAll() {
		return new ResponseEntity<>(utilServices.getDataCategoryAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getDataValueAll" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getDataValueAll() {
		return new ResponseEntity<>(utilServices.getDataValueAll(), HttpStatus.OK);
	}
}
