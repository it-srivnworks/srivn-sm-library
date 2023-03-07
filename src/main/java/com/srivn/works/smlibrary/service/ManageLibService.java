package com.srivn.works.smlibrary.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srivn.works.smlibrary.db.entity.books.AuthorInfoEn;
import com.srivn.works.smlibrary.db.entity.books.BookInfoEn;
import com.srivn.works.smlibrary.db.entity.common.ClsnEn;
import com.srivn.works.smlibrary.db.mapper.CustomBookInfoMapper;
import com.srivn.works.smlibrary.db.repo.AuthorInfoRepo;
import com.srivn.works.smlibrary.db.repo.BookInfoRepo;
import com.srivn.works.smlibrary.model.BookInfo;

@Service
public class ManageLibService {

	@Autowired
	BookInfoRepo bookRepo;
	
	@Autowired
	AuthorInfoRepo authorInfoRepo;
	
	@Autowired
	CustomBookInfoMapper customBookInfoMapper;
	
    private static final Logger logger = LoggerFactory.getLogger(ManageLibService.class);
    
	public BookInfo addNewBook(BookInfo bookInfo){
		BookInfoEn bookInfoEn = customBookInfoMapper.DTOToEn(bookInfo);
		bookInfo = customBookInfoMapper.EnToDTO(bookRepo.save(bookInfoEn));
		return bookInfo;
	}
	
	public BookInfo getBookByTitle(String bookTitle){
		return customBookInfoMapper.EnToDTO(bookRepo.findByTitle(bookTitle));
	}
	
	public  List<BookInfo> getBookAll(){
		return bookRepo.findAll().stream().map(bookEn-> customBookInfoMapper.EnToDTO(bookEn)).collect(Collectors.toList());
	}

	public AuthorInfoEn addNewAuthor(String authorName) {
		return authorInfoRepo.save(AuthorInfoEn.builder().authorName(authorName).build());
	}
	
	public AuthorInfoEn getAuthorByName(String authorName){
		return authorInfoRepo.findByAuthorName(authorName);
	}
	
	public List<AuthorInfoEn> getAuthorAll(){
		return  StreamSupport.stream(authorInfoRepo.findAll().spliterator(), false).collect(Collectors.toList());
	}
}
