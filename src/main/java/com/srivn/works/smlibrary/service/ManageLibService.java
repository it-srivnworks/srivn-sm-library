package com.srivn.works.smlibrary.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srivn.works.smlibrary.db.entity.books.BookInfoEn;
import com.srivn.works.smlibrary.db.mapper.CustomBookInfoMapper;
import com.srivn.works.smlibrary.db.repo.BookRepo;
import com.srivn.works.smlibrary.model.BookInfo;

@Service
public class ManageLibService {

	@Autowired
	BookRepo bookRepo;
	
	@Autowired
	CustomBookInfoMapper customBookInfoMapper;
	
    private static final Logger logger = LoggerFactory.getLogger(ManageLibService.class);
    
	public void addNewBook(BookInfo bookInfo){
		logger.info(bookInfo.toString());
		BookInfoEn bookInfoEn = customBookInfoMapper.DTOToEn(bookInfo);
		logger.info(bookInfoEn.toString());
	}
	
}
