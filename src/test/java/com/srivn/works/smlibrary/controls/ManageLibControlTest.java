package com.srivn.works.smlibrary.controls;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srivn.works.smlibrary.exception.SMException;
import com.srivn.works.smlibrary.exception.SMMessage;
import com.srivn.works.smlibrary.model.BookInfo;
import com.srivn.works.smlibrary.service.ManageLibService;
import com.srivn.works.smlibrary.util.AppMsg;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ManageLibControl.class)
class ManageLibControlTest {

	@MockBean
	private ManageLibService manageLibService;

	@Autowired
	MockMvc mockMvc;

	private BookInfo bookInfo;
	private String bookInfoStr;
	private List<BookInfo> dtoList;
	private List<String> authorList;

	@BeforeEach
	void setUp() {
		bookInfo = BookInfo.builder().title("TITLE").isbn("ISBN").units(1).build();
		bookInfoStr = "{\"title\":\"TITLE\",\"author\":null,\"publlication\":null,\"isbn\":\"ISBN\",\"category\":null,\"units\":1}";
		dtoList = new ArrayList<BookInfo>();
		authorList = Arrays.asList("Author1","Author2");
		dtoList.add(bookInfo);
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	@Test
	@DisplayName("Positive : Adding new Book entry")
	void testAddNewBook01P() throws Exception {
		SMMessage msg = SMMessage.builder().code(AppMsg.Msg.MSG_ADD_001.getCode()).message(AppMsg.Msg.MSG_ADD_001.getMsg())
				.build();
		when(manageLibService.addNewBook(bookInfo)).thenReturn(msg);
		mockMvc.perform(post("/manage/addNewBook").contentType(MediaType.APPLICATION_JSON_VALUE).content(bookInfoStr))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("Negative : Adding new Book entry -- Exception")
	void testAddNewBook02N() throws Exception {
		when(manageLibService.addNewBook(bookInfo)).thenThrow(SMException.class);
		mockMvc.perform(post("/manage/addNewBook").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(bookInfoStr)).andExpect(status().isInternalServerError());
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	@Test
	@DisplayName("Positive : Get Book by title")
	void testGetBookByTitle01P() throws Exception {
		when(manageLibService.getBookByTitle("TITLE")).thenReturn(bookInfo);
		mockMvc.perform(get("/manage/getBookByTitle").contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE).param("bookTitle", "TITLE")).andExpect(status().isOk());
	}

	@Test
	@DisplayName("Negative :  Get Book by title -- Exception")
	void testGetBookByTitle02N() throws Exception {
		when(manageLibService.getBookByTitle("TITLE")).thenThrow(SMException.class);
		mockMvc.perform(get("/manage/getBookByTitle").contentType(MediaType.APPLICATION_JSON_VALUE)
				.param("bookTitle", "TITLE")).andExpect(status().isInternalServerError());
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	@Test
	@DisplayName("Positive : Get ALL Books")
	void testGetBookAll01P() throws Exception {
		when(manageLibService.getBookAll()).thenReturn(dtoList);
		mockMvc.perform(get("/manage/getBookAll").contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}

	@Test
	@DisplayName("Negative : Get ALL Books -- Exception")
	void testGetBookAll02N() throws Exception {
		when(manageLibService.getBookAll()).thenThrow(SMException.class);
		mockMvc.perform(get("/manage/getBookAll").contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isInternalServerError());
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	@Test
	@DisplayName("Positive :Add New Author")
	void testAddNewAuthor01P() throws Exception {
		SMMessage msg = SMMessage.builder().code(AppMsg.Msg.MSG_ADD_001.getCode()).message(AppMsg.Msg.MSG_ADD_001.getMsg())
				.build();
		when(manageLibService.addNewAuthor("AUTHOR")).thenReturn(msg);
		mockMvc.perform(post("/manage/addNewAuthor").contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE).param("authorName", "AUTHOR")).andExpect(status().isOk());
	}

	@Test
	@DisplayName("Negative : Get ALL Books")
	void testAddNewAuthor02N() throws Exception {
		when(manageLibService.addNewAuthor("AUTHOR")).thenThrow(SMException.class);
		mockMvc.perform(post("/manage/addNewAuthor").contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE).param("authorName", "AUTHOR"))
		.andExpect(status().isInternalServerError());
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	@Test
	@DisplayName("Positive :Get Author By Name")
	void testGetAuthorByName01P() throws Exception {
		when(manageLibService.getAuthorByName("AUTHOR")).thenReturn("AUTHOR");
		mockMvc.perform(get("/manage/getAuthorByName").contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE).param("authorName", "AUTHOR")).andExpect(status().isOk());
	}

	@Test
	@DisplayName("Negative : Get Author ?by Name -- exception")
	void testGetAuthorByName02N() throws Exception {
		when(manageLibService.getAuthorByName("AUTHOR")).thenThrow(SMException.class);
		mockMvc.perform(get("/manage/getAuthorByName").contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE).param("authorName", "AUTHOR")).andExpect(status().isInternalServerError());
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	@Test
	@DisplayName("Positive :Get Author By Name")
	void testGetAuthorAll01P() throws Exception {
		when(manageLibService.getAuthorAll()).thenReturn(authorList);
		mockMvc.perform(get("/manage/getAuthorAll").contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE).param("authorName", "AUTHOR")).andExpect(status().isOk());
	}

	@Test
	@DisplayName("Negative : Get Author ?by Name -- exception")
	void testGetAuthorAll02N() throws Exception {
		when(manageLibService.getAuthorAll()).thenThrow(SMException.class);
		mockMvc.perform(get("/manage/getAuthorAll").contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE).param("authorName", "AUTHOR")).andExpect(status().isInternalServerError());
	}
}
