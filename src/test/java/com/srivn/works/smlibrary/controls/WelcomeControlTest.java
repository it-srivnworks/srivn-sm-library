package com.srivn.works.smlibrary.controls;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(WelcomeControl.class)
class WelcomeControlTest {

	@Autowired
	MockMvc mockMvc;
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	@Test
	@DisplayName("Positive : API connections")
	void testTester() throws Exception {
		mockMvc.perform(get("/welcome/tester"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message", Matchers.is("Howdy!")));
	}
	
}
