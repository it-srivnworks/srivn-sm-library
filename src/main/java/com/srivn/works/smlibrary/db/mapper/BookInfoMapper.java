package com.srivn.works.smlibrary.db.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.srivn.works.smlibrary.db.entity.books.BookInfoEn;
import com.srivn.works.smlibrary.db.entity.common.ClsnValueEn;
import com.srivn.works.smlibrary.db.entity.common.DataValueEn;
import com.srivn.works.smlibrary.model.BookInfo;

@Mapper
public interface BookInfoMapper {

	 	@Mapping(target="category", source="category", qualifiedByName = "CVToString")
	 	@Mapping(target="author", source="author.authorName")
	 	@Mapping(target="publlication", source="publlication", qualifiedByName = "DVToString")
		BookInfo EnToDTO(BookInfoEn bookInfoEn);
	
	 	
	 	@Mapping(target = "id", ignore = true)
	 	@Mapping(target = "category", ignore = true)
	 	@Mapping(target = "author", ignore = true)
	 	@Mapping(target = "publlication", ignore = true)
		BookInfoEn DTOToEn(BookInfo bookInfo);
	 	
	 	@Named("CVToString")
	    public static String CVTOString(ClsnValueEn cv) {
	        if(cv == null){
	            return "";
	        }else{
	            return cv.getClsnValue();
	        }
	    }
	 	
	 	@Named("DVToString")
	    public static String DVTOString(DataValueEn dv) {
	        if(dv == null){
	            return "";
	        }else{
	            return dv.getDataValue();
	        }
	    }
	 	
}
