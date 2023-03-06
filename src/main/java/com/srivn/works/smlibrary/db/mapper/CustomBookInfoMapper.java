package com.srivn.works.smlibrary.db.mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.srivn.works.smlibrary.db.entity.books.BookInfoEn;
import com.srivn.works.smlibrary.db.repo.AuthorInfoRepo;
import com.srivn.works.smlibrary.db.repo.common.ClsnValueRepo;
import com.srivn.works.smlibrary.db.repo.common.DataValueRepo;
import com.srivn.works.smlibrary.model.BookInfo;

@Component
public class CustomBookInfoMapper{

	@Autowired
	ClsnValueRepo clsnValueRepo;
	
	@Autowired
	AuthorInfoRepo authorInfoRepo;
	
	@Autowired
	DataValueRepo dataValueRepo;
	
	BookInfoMapper bookInfoMapper = Mappers.getMapper(BookInfoMapper.class);
    
	public BookInfoEn DTOToEn(BookInfo bookInfo) {
		 BookInfoEn bookInfoEn = bookInfoMapper.DTOToEn(bookInfo);
		 bookInfoEn.setCategory(clsnValueRepo.findByClsnValue(bookInfo.getCategory()));
		 bookInfoEn.setAuthor(authorInfoRepo.findByAuthorName(bookInfo.getAuthor()));
		 bookInfoEn.setPubllication(dataValueRepo.findByDataValue(bookInfo.getPubllication()));
		 return bookInfoEn;
	 }
	
	public BookInfo EnToDTO(BookInfoEn bookInfoEn) {
		BookInfo bookInfo = bookInfoMapper.EnToDTO(bookInfoEn);
		 return bookInfo;
	 }
}
