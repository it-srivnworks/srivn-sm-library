package com.srivn.works.smlibrary.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import com.srivn.works.smlibrary.db.entity.books.AuthorInfoEn;
import com.srivn.works.smlibrary.db.entity.books.BookInfoEn;
import com.srivn.works.smlibrary.db.mapper.CustomBookInfoMapper;
import com.srivn.works.smlibrary.db.repo.AuthorInfoRepo;
import com.srivn.works.smlibrary.db.repo.BookInfoRepo;
import com.srivn.works.smlibrary.exception.SMException;
import com.srivn.works.smlibrary.exception.SMMessage;
import com.srivn.works.smlibrary.model.BookInfo;
import com.srivn.works.smlibrary.util.AppMsg;

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
	private AuthorInfoEn authorEn;
	
	private List<BookInfoEn> enList;
	private List<BookInfo> dtoList;
	private List<AuthorInfoEn> authorEnList;
	
	@BeforeEach
	void setUp() {
		en = BookInfoEn.builder().id(1).title("TITLE").isbn("ISBN").units(1).build();
		dto = BookInfo.builder().title("TITLE").isbn("ISBN").units(1).build();
		authorEn = AuthorInfoEn.builder().authorName("AUTHOR").build();
		
		enList = new ArrayList<BookInfoEn>();
		enList.add(en);
		dtoList = new ArrayList<BookInfo>();
		dtoList.add(dto);
		authorEnList = new ArrayList<AuthorInfoEn>();
		authorEnList.add(authorEn);
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	@Test
	@DisplayName("Positive ; Adding new Book entry")
	void testAddNewBook01P() {
		when(bookRepo.findByTitle("TITLE")).thenReturn(null);
		when(customBookInfoMapper.EnToDTO(en)).thenReturn(dto);
		when(customBookInfoMapper.DTOToEn(dto)).thenReturn(en);
		when(bookRepo.save(en)).thenReturn(en);
		assertThat(manageLibService.addNewBook(dto)).isEqualTo(SMMessage.builder().code(AppMsg.Msg.MSG_001.getCode()).message(AppMsg.Msg.MSG_001.getMsg()).build());
}

	@Test
	@DisplayName("Negative : Adding new Book entry -- duplicate")
	void testAddNewBook02N1() {
		when(bookRepo.findByTitle("TITLE")).thenReturn(en);
		assertThatThrownBy(() -> {
			manageLibService.addNewBook(dto);
		    }).isInstanceOf(SMException.class)
		            .hasMessage("Book already present.");
	}

	@Test
	@DisplayName("Negative : Adding new Book entry -- some exception")
	void testAddNewBook03N2() {
		when(bookRepo.findByTitle("TITLE")).thenReturn(null);
		when(bookRepo.save(en)).thenThrow(new DataAccessException("..."){ });
		assertThatThrownBy(() -> {
			manageLibService.addNewBook(dto);
		    }).isInstanceOf(SMException.class)
		            .hasMessage("Unknown Error, Please Try Again!");
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	@Test
	@DisplayName("Positive : Get book by Title")
	void testGetBookByTitle01P() {
		when(bookRepo.findByTitle("TITLE")).thenReturn(en);
		when(customBookInfoMapper.EnToDTO(en)).thenReturn(dto);
		assertThat(manageLibService.getBookByTitle("TITLE")).isEqualTo(dto);
	}

	@Test
	@DisplayName("Negative : Get book by Title -- not found")
	void testGetBookByTitle02N1() {
		when(bookRepo.findByTitle("")).thenReturn(null);
		assertThatThrownBy(() -> {
			 manageLibService.getBookByTitle("");
		    }).isInstanceOf(SMException.class)
		            .hasMessage("Book not found");
	}
	
	@Test
	@DisplayName("Negative : Get book by Title -- some exception")
	void testGetBookByTitle03N2() {
		when(bookRepo.findByTitle("TITLE")).thenThrow(new DataAccessException("..."){ });
		assertThatThrownBy(() -> {
			 manageLibService.getBookByTitle("");
		    }).isInstanceOf(SMException.class)
		            .hasMessage("Unknown Error, Please Try Again!");
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	@Test
	@DisplayName("Positive : Get All book")
	void testGetBookAll01P() {
		when(bookRepo.findAll()).thenReturn(enList);
		when(customBookInfoMapper.EnToDTO(en)).thenReturn(dto);
		assertThat(manageLibService.getBookAll().size() > 0);
		assertThat(manageLibService.getBookAll()).containsExactlyInAnyOrder(dto);
	}

	@Test
	@DisplayName("Negative : Get All book -- not found")
	void testGetBookAll02N1() {
		when(bookRepo.findAll()).thenReturn(new ArrayList<BookInfoEn>());
		assertThatThrownBy(() -> {
			 manageLibService.getBookAll();
		    }).isInstanceOf(SMException.class)
		            .hasMessage("Books not found");
	}
	
	@Test
	@DisplayName("Negative : Get All book -- some exception")
	void testGetBookAll03N2() {
		when(bookRepo.findAll()).thenThrow(new DataAccessException("..."){ });
		assertThatThrownBy(() -> {
			 manageLibService.getBookAll();
		    }).isInstanceOf(SMException.class)
		            .hasMessage("Unknown Error, Please Try Again!");
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	@Test
	@DisplayName("Positive ; Adding new Author entry")
	void testAddNewAuthor01P() {
		when(authorInfoRepo.findByAuthorName("AUTHOR")).thenReturn(null);
		when(authorInfoRepo.save(authorEn)).thenReturn(authorEn);
		assertThat(manageLibService.addNewAuthor("AUTHOR")).isEqualTo(SMMessage.builder().code(AppMsg.Msg.MSG_001.getCode()).message(AppMsg.Msg.MSG_001.getMsg()).build());
}

	@Test
	@DisplayName("Negative : Adding new Author entry -- duplicate")
	void testAddNewAuthor02N1() {
		when(authorInfoRepo.findByAuthorName("AUTHOR")).thenReturn(authorEn);
		assertThatThrownBy(() -> {
			manageLibService.addNewAuthor("AUTHOR");
		    }).isInstanceOf(SMException.class)
		            .hasMessage("Author already present.");
	}

	@Test
	@DisplayName("Negative : Adding new Author entry -- some exception")
	void testaAdNewAuthor03N2() {
		when(authorInfoRepo.findByAuthorName("AUTHOR")).thenReturn(null);
		when(authorInfoRepo.save(authorEn)).thenThrow(new DataAccessException("..."){ });
		assertThatThrownBy(() -> {
			manageLibService.addNewAuthor("AUTHOR");
		    }).isInstanceOf(SMException.class)
		            .hasMessage("Unknown Error, Please Try Again!");
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	@Test
	@DisplayName("Positive : Get Author")
	void testGetAuthorByName01P() {
		when(authorInfoRepo.findByAuthorName("AUTHOR")).thenReturn(authorEn);
		assertThat(manageLibService.getAuthorByName("AUTHOR")).isEqualTo("AUTHOR");
	}

	@Test
	@DisplayName("Negative : Get Author -- not found")
	void testGetAuthorByName02N1() {
		when(authorInfoRepo.findByAuthorName("AUTHOR")).thenReturn(null);
		assertThatThrownBy(() -> {
			manageLibService.getAuthorByName("AUTHOR");
		    }).isInstanceOf(SMException.class)
		            .hasMessage("Author not found");
	}
	
	@Test
	@DisplayName("Negative : Get Author -- some exception")
	void testGetAuthorByName03N2() {
		when(authorInfoRepo.findByAuthorName("AUTHOR")).thenThrow(new DataAccessException("..."){ });
		assertThatThrownBy(() -> {
			manageLibService.getAuthorByName("AUTHOR");
		    }).isInstanceOf(SMException.class)
		            .hasMessage("Unknown Error, Please Try Again!");
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	@Test
	@DisplayName("Positive : Get All authors")
	void testGetAuthorAll01P() {
		when(authorInfoRepo.findAll()).thenReturn(authorEnList);
		assertThat(manageLibService.getAuthorAll().size() > 0);
		assertThat(manageLibService.getAuthorAll()).containsExactlyInAnyOrder("AUTHOR");
	}

	@Test
	@DisplayName("Negative : Get All authors -- not found")
	void testGetAuthorAll02N1() {
		when(authorInfoRepo.findAll()).thenReturn(new ArrayList<AuthorInfoEn>());
		assertThatThrownBy(() -> {
			 manageLibService.getAuthorAll();
		    }).isInstanceOf(SMException.class)
		            .hasMessage("Authors not found");
	}
	
	@Test
	@DisplayName("Negative : Get All authors -- some exception")
	void testGetAuthorAll03N2() {
		when(authorInfoRepo.findAll()).thenThrow(new DataAccessException("..."){ });
		assertThatThrownBy(() -> {
			 manageLibService.getAuthorAll();
		    }).isInstanceOf(SMException.class)
		            .hasMessage("Unknown Error, Please Try Again!");
	}
}
