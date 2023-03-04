package com.srivn.works.smlibrary.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srivn.works.smlibrary.model.BookInfo;
import com.srivn.works.smlibrary.service.ManageLibService;


@RestController
@RequestMapping("manage")
public class ManageLibControl {

	@Autowired
	ManageLibService manageLibService;
	
	@PostMapping(value = "/addNewEntry", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewEntry(@RequestBody BookInfo bookInfo) {
		manageLibService.addNewBook(bookInfo);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
