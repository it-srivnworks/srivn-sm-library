package com.srivn.works.smlibrary.db.mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.srivn.works.smlibrary.db.entity.books.BookInfoEn;
import com.srivn.works.smlibrary.db.repo.common.ClsnValueRepo;
import com.srivn.works.smlibrary.model.BookInfo;

@Component
public class CustomBookInfoMapper{

	@Autowired
	ClsnValueRepo clsnValueRepo;
	
	BookInfoMapper bookInfoMapper = Mappers.getMapper(BookInfoMapper.class);
    
	public BookInfoEn DTOToEn(BookInfo bookInfo) {
		 BookInfoEn bookInfoEn = bookInfoMapper.DTOToEn(bookInfo);
		 bookInfoEn.setCategory(clsnValueRepo.findByClsnVal(bookInfo.getCategory()));
		 return bookInfoEn;
	 }
}
