package ru.bengobro;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.bengobro.model.RequestInfo;
import ru.bengobro.model.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserInfoProcessingApplicationTests {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CacheManager cacheManager;

	private static final String vkServiceToken = "d9b83319d9b83319d9b83319eedaa97915dd9b8d9b83319bad93186009debb836fa184b";

	@AfterEach
	public void cacheEvict() {
		cacheManager.getCache("fullName").clear();
		cacheManager.getCache("isMember").clear();
	}

	@Test
	void getUserInfo_shouldReturnUserInfo() throws Exception {
		RequestInfo requestInfo = new RequestInfo(237595431, 103408267);
		User user = new User("Стародубцев", "Михаил",
				"", false);

		this.mockMvc.perform(get("/gpn/get_info")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(requestInfo)).header("vk_service_token", vkServiceToken))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(user)));
	}

	@Test
	void getUserInfo_whenServiceTokenIncorrect_shouldReturnApiAuthException() throws Exception {
		RequestInfo requestInfo = new RequestInfo(237595431, 103408267);

		this.mockMvc.perform(get("/gpn/get_info")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(requestInfo)).header("vk_service_token", "pass"))
				.andDo(print())
				.andExpect(status().isUnauthorized());
	}

	@Test
	void getUserInfo_whenGroupIsPrivate_shouldReturnApiAccessException() throws Exception {
		RequestInfo requestInfo = new RequestInfo(237595431, 189400545);

		this.mockMvc.perform(get("/gpn/get_info")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(requestInfo)).header("vk_service_token", vkServiceToken))
				.andDo(print())
				.andExpect(status().isNotAcceptable());
	}
}
