package com.srivn.works.smlibrary.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.srivn.works.smlibrary.db.entity.books.BookInfoEn;
import com.srivn.works.smlibrary.db.mapper.CustomBookInfoMapper;
import com.srivn.works.smlibrary.db.repo.AuthorInfoRepo;
import com.srivn.works.smlibrary.db.repo.BookInfoRepo;
import com.srivn.works.smlibrary.exception.SMException;
import com.srivn.works.smlibrary.exception.SMMessage;
import com.srivn.works.smlibrary.model.BookInfo;
import com.srivn.works.smlibrary.util.AppConstants;

@ExtendWith(MockitoExtension.class)
class ManageLibServiceTest {

	@Mock
	private BookInfoRepo bookRepo;
	@Mock
	private AuthorInfoRepo authorInfoRepo;

	@Mock
	private CustomBookInfoMapper customBookInfoMapper;

	@InjectMocks
	private ManageLibService manageLibService;

	private BookInfoEn en;
	private BookInfo dto;

	@BeforeEach
	void setUp() {
		en = BookInfoEn.builder().id(1).title("TITLE").isbn("ISBN").units(1).build();
		dto = BookInfo.builder().title("TITLE").isbn("ISBN").units(1).build();
	}

	@Test
	@DisplayName("Test to add new book entry")
	void addNewBookP() {
		when(bookRepo.findByTitle("TITLE")).thenReturn(null);
		when(customBookInfoMapper.EnToDTO(en)).thenReturn(dto);
		when(customBookInfoMapper.DTOToEn(dto)).thenReturn(en);
		when(bookRepo.save(en)).thenReturn(en);
		assertThat(manageLibService.addNewBook(dto)).isEqualTo(SMMessage.builder().code(AppConstants.AppMessages.MSG_0001.getCode()).message(AppConstants.AppMessages.MSG_0001.getMsg()).build());
}

	@Test
	@DisplayName("Test to add new book entry negative")
	void addNewBookN1() {
		when(bookRepo.findByTitle("TITLE")).thenReturn(en);
		assertThatThrownBy(() -> {
			manageLibService.addNewBook(dto);
		    }).isInstanceOf(SMException.class)
		            .hasMessage("Book already present.");
	}
	
	@Test
	@DisplayName("Test to add new book entry exception")
	void addNewBookN2() {
		when(bookRepo.findByTitle("TITLE")).thenReturn(null);
		when(customBookInfoMapper.EnToDTO(en)).thenReturn(dto);
		when(customBookInfoMapper.DTOToEn(dto)).thenReturn(en);
		when(bookRepo.save(en)).thenReturn(null);
		assertThatThrownBy(() -> {
			manageLibService.addNewBook(dto);
		    }).isInstanceOf(SMException.class)
		            .hasMessage("Unknown Error, Please Try Again!");
	}

	@Test
	@DisplayName("Test to Get Book By title Positive")
	void testGetBookByTitleP() {
		when(bookRepo.findByTitle("TITLE")).thenReturn(en);
		when(customBookInfoMapper.EnToDTO(en)).thenReturn(dto);
		assertThat(manageLibService.getBookByTitle("TITLE")).isEqualTo(dto);
	}

	@Test
	@DisplayName("Test to Get Book By title Negative")
	void testGetBookByTitleN() {
		when(bookRepo.findByTitle("")).thenReturn(null);
		assertThatThrownBy(() -> {
			 manageLibService.getBookByTitle("");
		    }).isInstanceOf(SMException.class)
		            .hasMessage("Book not found");
	}
}
