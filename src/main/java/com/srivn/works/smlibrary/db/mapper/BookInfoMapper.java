package com.srivn.works.smlibrary.db.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.srivn.works.smlibrary.db.entity.books.BookInfoEn;
import com.srivn.works.smlibrary.db.entity.common.ClsnValueEn;
import com.srivn.works.smlibrary.model.BookInfo;

@Mapper
public interface BookInfoMapper {

	 	@Mapping(target="category", source="category", qualifiedByName = "CVToString")
		BookInfo EnToDTO(BookInfoEn bookInfoEn);
	
	 	
	 	@Mapping(target = "id", ignore = true)
	 	@Mapping(target = "category", ignore = true)
		BookInfoEn DTOToEn(BookInfo bookInfo);
	 	
	 	@Named("CVToString")
	    public static String CVTOString(ClsnValueEn cv) {
	        if(cv == null){
	            return "";
	        }else{
	            return cv.getClsnVal();
	        }
	    }
}
