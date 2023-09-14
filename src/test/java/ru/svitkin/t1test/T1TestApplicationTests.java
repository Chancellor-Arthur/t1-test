package ru.svitkin.t1test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.svitkin.t1test.task.TaskInputDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class T1TestApplicationTests {
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	private final LinkedHashMap<String, Integer> originOutput = new LinkedHashMap<>();

	private final TaskInputDto originInput = new TaskInputDto("aaaaabccccooooooooooooooo33366");

	private LinkedHashMap<Character, Integer> jsonOutput;

	@BeforeAll
	public void prepare() throws Exception {
		originOutput.put("o", 15);
		originOutput.put("a", 5);
		originOutput.put("c", 4);
		originOutput.put("3", 3);
		originOutput.put("6", 2);
		originOutput.put("b", 1);

		String requestBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(originInput);

		MvcResult result = this.mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andDo(print()).andExpect(status().isOk()).andReturn();

		String content = result.getResponse().getContentAsString();

		jsonOutput = objectMapper.readValue(content, LinkedHashMap.class);
	}

	@Test
	public void testCorrectOrder() throws Exception {
		String[] responseKeyOrder = jsonOutput.keySet().toArray(new String[0]);
		String[] originKeyOrder = originOutput.keySet().toArray(new String[0]);

		assertThat(Arrays.equals(responseKeyOrder, originKeyOrder), is(true));
	}

	@Test
	public void testCorrectCalculation() throws Exception {
		Integer[] responseValues = jsonOutput.values().toArray(new Integer[0]);
		Integer[] originValues = originOutput.values().toArray(new Integer[0]);

		assertThat(Arrays.equals(responseValues, originValues), is(true));
	}
}
