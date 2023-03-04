package com.srivn.works.smlibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.srivn.works.smlibrary.db.repo.BookRepo;
import com.srivn.works.smlibrary.model.BookInfo;

@Service
public class ManageLibService {

	@Autowired
	BookRepo bookRepo;
	

	public void addNewBook(BookInfo bookInfo){
		
	}
	
}
