package com.srivn.works.smlibrary.controls;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

	@BeforeEach
	void setUp() {
		bookInfo = BookInfo.builder().title("TITLE").isbn("ISBN").units(1).build();
		bookInfoStr = "{\"title\":\"TITLE\",\"author\":null,\"publlication\":null,\"isbn\":\"ISBN\",\"category\":null,\"units\":1}";
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	@Test
	@DisplayName("Positive : Adding new Book entry")
	void testAddNewBook01P() throws Exception {
		SMMessage msg = SMMessage.builder().code(AppMsg.Msg.MSG_001.getCode()).message(AppMsg.Msg.MSG_001.getMsg())
				.build();
		when(manageLibService.addNewBook(bookInfo)).thenReturn(msg);
		mockMvc.perform(post("/manage/addNewBook").contentType(MediaType.APPLICATION_JSON_VALUE).content(bookInfoStr))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("Negative : Adding new Book entry -- Exception")
	void testAddNewBook02N() throws Exception {
		when(manageLibService.addNewBook(bookInfo)).thenThrow(new SMException(AppMsg.Err.ERR_002.getCode(), AppMsg.Err.ERR_002.getMsgWithParam("Book")));
		mockMvc.perform(post("/manage/addNewBook").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(bookInfoStr)).andExpect(status().isInternalServerError());
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	@Test
	@DisplayName("Positive : Get Book by title")
	void testGetBookByTitle01P() throws Exception {
		when(manageLibService.getBookByTitle("TITLE")).thenReturn(bookInfo);
		mockMvc.perform(post("/manage/getBookByTitle").contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE).content("TITLE"))
		.andExpect(status().isOk());
	}

	@Test
	void testGetBookByTitle02N() {
		fail("Not yet implemented");
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	@Test
	void testGetBookAll() {
		fail("Not yet implemented");
	}

	@Test
	void testAddNewAuthor() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAuthorByName() {
		fail("Not yet implemented");
	}

	@Test
	void testGetBookByTitle() {
		fail("Not yet implemented");
	}

	@Test
	void testManageLibControl() {
		fail("Not yet implemented");
	}

}
