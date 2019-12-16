package com.faceit.usermicroservice.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.faceit.usermicroservice.ExpectedTestUsers;
import com.faceit.usermicroservice.entities.User;
import com.faceit.usermicroservice.repositories.user_repository.UserRepository;
import com.faceit.usermicroservice.services.UserResponseService;
import com.faceit.usermicroservice.web_entities.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest(UserCRUDController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class UserCRUDControllerTests {

	  @Autowired
	  private MockMvc mockMvc;
	  @Autowired
	  private ObjectMapper objectMapper;
	  @MockBean
	  private UserRepository userRepo;	
	  @MockBean
	  UserResponseService userResponseService;


	  @Test
	  public void testGetAllUsersEndPoint() throws Exception {	
		 List<User> expectedUsers = new ArrayList<>(Arrays.asList(
				 ExpectedTestUsers .getUser1(), ExpectedTestUsers .getUser2()));
		 List<UserResponse> expectedUsersResponses = new ArrayList<>(Arrays.asList(
				 new UserResponse(1, "Ross", "Geller", "the_geller", "ross@live.com", "USA"),
				 new UserResponse(2, "Rachel", "Green", "rachelg", "rachel@live.com", "Greece")));
		  
	    when(userRepo.findAll()).thenReturn(expectedUsers);        
	    when(userResponseService.UserToResponse(expectedUsers)).thenReturn(expectedUsersResponses);    
	    
	    ResultActions resultActions = this.mockMvc.perform(get("/users/list"))
	    		.andDo(print())
	    		.andDo(document("get-all-users"))
				.andExpect(status().isOk());
		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();
		List<UserResponse> actualUserResponses = objectMapper.readValue(contentAsString, objectMapper.getTypeFactory().constructCollectionType(List.class, UserResponse.class));
	    assertEquals(expectedUsersResponses, actualUserResponses);
	  }  	  
	  
	  @Test
	  public void testGetUserByIDEndPoint() throws Exception {	
		 Optional<User> expectedUser = Optional.of(ExpectedTestUsers .getUser1() );
		 UserResponse expectedUsersResponse = new UserResponse(1, "Ross", "Geller", "the_geller", "ross@live.com", "USA");
		  
	    when(userRepo.findById(1)).thenReturn(expectedUser);        
	    when(userResponseService.UserToResponse(expectedUser.get())).thenReturn(expectedUsersResponse);    
	    ResultActions resultActions = this.mockMvc.perform(org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get("/users/view/{userID}", "1"))
	    		.andDo(print())
	            .andDo(document("get-user-by-id", pathParameters( 
	        			parameterWithName("userID").description("The user's ID") )))
	            .andExpect(status().isOk());    
	    MvcResult result = resultActions.andReturn();
	    String contentAsString = result.getResponse().getContentAsString();
	    UserResponse actualUserResponse = objectMapper.readValue(contentAsString, UserResponse.class);
	    assertEquals(expectedUsersResponse, actualUserResponse);
	  }  
	  
	  @Test
	  public void testGetUserByIDEndPointWrongID() throws Exception {
		Optional<User> expectedUser = Optional.empty();
	    when(userRepo.findById(1)).thenReturn(expectedUser);         
	    this.mockMvc.perform(org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get("/users/view/{userID}", "1"))
	    		.andDo(print())
	            .andExpect(status().isNotFound());    
	  }  
	  
	  @Test
	  public void testAddUserEndPoint() throws Exception {	
	  	User requestUser = new User();
	  	requestUser.setCountry("Scotland");
	  	requestUser.setEmail("chandler@live.com");
	  	requestUser.setFirstName("Chandler");
	  	requestUser.setLastName("Bing");
	  	requestUser.setNickname("chandlerb");
	  	requestUser.setPassword("$2y$10$HvhIUiCXMJyITv1E5GtMNuUGeTE0xfcDTNGCXfqaJjo7kHBlhxZM2");

	  	ObjectMapper objectMapper = new ObjectMapper();
        String requestBodyJson = objectMapper.writeValueAsString(requestUser);
	    this.mockMvc.perform(
	            post("/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(requestBodyJson))
    		.andDo(print())
    		.andDo(document("add-user",  
                    requestFields(
                    		fieldWithPath("id").ignored(),
                            fieldWithPath("firstName").description("First name of user"),
                            fieldWithPath("lastName").description("Last name of user"),
                            fieldWithPath("nickname").description("User's nickname"),
                            fieldWithPath("password").description("Password for user"),
                            fieldWithPath("email").description("User's email address"),
                            fieldWithPath("country").description("User's country of origin") )))
    		.andExpect(status().isOk());  
	  }  	  
	  
	  @Test
	  public void testAddUserEndPointNoData() throws Exception {	
        String requestBodyJson = objectMapper.writeValueAsString(null);
	    this.mockMvc.perform(
	            post("/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(requestBodyJson))
    		.andDo(print())
    		.andExpect(status().isBadRequest());  
	  }  	  
	  
	  @Test
	  public void testModifyUserEndPoint() throws Exception {	
	  	User requestUser = ExpectedTestUsers.getUser3();
	  	requestUser.setNickname("Ken_Adams");
	    when(userRepo.updateUser(requestUser)).thenReturn(true);        

	  	ObjectMapper objectMapper = new ObjectMapper();
        String requestBodyJson = objectMapper.writeValueAsString(requestUser);
	    this.mockMvc.perform(
	            put("/users/modify")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(requestBodyJson))
    		.andDo(print())
    		.andDo(document("modify-user",  
                    requestFields(
                    		fieldWithPath("id").ignored(),
                            fieldWithPath("firstName").description("First name of user"),
                            fieldWithPath("lastName").description("Last name of user"),
                            fieldWithPath("nickname").description("User's nickname"),
                            fieldWithPath("password").description("Password for user"),
                            fieldWithPath("email").description("User's email address"),
                            fieldWithPath("country").description("User's country of origin") )))
    		.andExpect(status().isOk());
	  }  	  
	  
	  @Test
	  public void testModifyUserEndPointNoData() throws Exception {	
        String requestBodyJson = objectMapper.writeValueAsString(null);
	    this.mockMvc.perform(
	            put("/users/modify")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(requestBodyJson))
    		.andDo(print())
    		.andExpect(status().isBadRequest());
	  }  	 
	  
	  @Test
	  public void testDeleteUserEndPoint() throws Exception {	
	    this.mockMvc.perform(
	    		org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete("/users/remove/{userID}", "1")
                .contentType(MediaType.TEXT_PLAIN_VALUE))
    		.andDo(print())
            .andDo(document("delete-user", pathParameters( 
        			parameterWithName("userID").description("The user's ID") )))
    		.andExpect(status().isOk());
	  }  
	
	  
	  
	  
}
