package com.serjer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.serjer.controller.MainController;


@SpringBootTest
@AutoConfigureMockMvc
class TextAnalyzerApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private MainController mainController;
	
	@Test
	void testController() {
		assertThat(mainController).isNotNull();
	}
	
	@Test
	void inputContextLoads() throws Exception {
		this.mockMvc.perform(get("/"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Text Analyzer")));
	}
	
	@Test
	void inputFormLoadsOnPost() throws Exception {
		this.mockMvc.perform(post("/"))
			.andDo(print())
			.andExpect(status().is4xxClientError());
			
	}
	
	@Test
	void historyFormContextLoads() throws Exception {
		this.mockMvc.perform(get("/history"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("History")));
	
	}
	
	@Test
	void uploadFormContextLoads() throws Exception {
		this.mockMvc.perform(get("/upload"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Upload file")));
	
	}
}