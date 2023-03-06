package com.srivn.works.smlibrary.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srivn.works.smlibrary.model.BookInfo;
import com.srivn.works.smlibrary.service.ManageLibService;


@RestController
@RequestMapping("manage")
public class ManageLibControl {

	@Autowired
	ManageLibService manageLibService;
	
	@PostMapping(value = "/addNewBook", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewBook(@RequestBody BookInfo bookInfo) {
		return new ResponseEntity<>(manageLibService.addNewBook(bookInfo) , HttpStatus.OK);
    }
	
	@GetMapping(value = "/getBookByTitle" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getBookByTitle(@RequestParam String bookTitle) {
		return new ResponseEntity<>(manageLibService.getBookByTitle(bookTitle), HttpStatus.OK);
	}
	
	@PostMapping(value = "/addNewAuthor" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNewAuthor(String authorName) {
		return new ResponseEntity<>(manageLibService.addNewAuthor(authorName), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAuthorByName" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAuthorByName(@RequestParam String authorName) {
		return new ResponseEntity<>(manageLibService.getAuthorByName(authorName), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAuthorAll" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getBookByTitle() {
		return new ResponseEntity<>(manageLibService.getAuthorAll(), HttpStatus.OK);
	}
}
