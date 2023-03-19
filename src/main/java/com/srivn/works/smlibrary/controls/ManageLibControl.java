package com.srivn.works.smlibrary.controls;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srivn.works.smlibrary.exception.SMException;
import com.srivn.works.smlibrary.model.BookInfo;
import com.srivn.works.smlibrary.service.ManageLibService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("manage")
public class ManageLibControl {

	private final ManageLibService manageLibService;

	@PostMapping(value = "/addNewBook", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNewBook(@RequestBody BookInfo bookInfo) {
		try {
			return new ResponseEntity<>(manageLibService.addNewBook(bookInfo), HttpStatus.OK);
		} catch (SMException sme) {
			return new ResponseEntity<>(sme, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(value = "/getBookByTitle", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getBookByTitle(@RequestParam String bookTitle) {
		try {
			return new ResponseEntity<>(manageLibService.getBookByTitle(bookTitle), HttpStatus.OK);
		} catch (SMException sme) {
			return new ResponseEntity<>(sme, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getBookAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getBookAll() {
		try {
			return new ResponseEntity<>(manageLibService.getBookAll(), HttpStatus.OK);
		} catch (SMException sme) {
			return new ResponseEntity<>(sme, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/addNewAuthor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNewAuthor(String authorName) {
		try {
			return new ResponseEntity<>(manageLibService.addNewAuthor(authorName), HttpStatus.OK);
		} catch (SMException sme) {
			return new ResponseEntity<>(sme, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getAuthorByName", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAuthorByName(@RequestParam String authorName) {
		try {
			return new ResponseEntity<>(manageLibService.getAuthorByName(authorName), HttpStatus.OK);
		} catch (SMException sme) {
			return new ResponseEntity<>(sme, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getAuthorAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getBookByTitle() {
		try {
			return new ResponseEntity<>(manageLibService.getAuthorAll(), HttpStatus.OK);
		} catch (SMException sme) {
			return new ResponseEntity<>(sme, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
