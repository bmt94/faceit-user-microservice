package com.faceit.usermicroservice.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
@WebMvcTest(UserFilteredReadController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class UserFilteredReadControllerTests {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @MockBean
  private UserRepository userRepo;	
  @MockBean
  UserResponseService userResponseService;
  
  List<User> expectedUsers = new ArrayList<>(Arrays.asList(ExpectedTestUsers .getUser1()));
  List<UserResponse> expectedUsersResponses = new ArrayList<>(Arrays.asList(new UserResponse(1, "Ross", "Geller", "the_geller", "ross@live.com", "USA")));

  
  private List<UserResponse> deserialiseResponse( ResultActions resultActions) throws Exception {
	  MvcResult result = resultActions.andReturn();
	  String contentAsString = result.getResponse().getContentAsString();
	  return objectMapper.readValue(contentAsString, objectMapper.getTypeFactory().constructCollectionType(List.class, UserResponse.class));
  }
  
  @Test
  public void testGetUserByNameEndPoint() throws Exception {	
    when(userRepo.findByName("Ross", "Geller")).thenReturn(expectedUsers);        
    when(userResponseService.UserToResponse(expectedUsers)).thenReturn(expectedUsersResponses);    
    ResultActions resultActions = this.mockMvc.perform(get("/users/list").param("firstName", "Ross").param("lastName", "Geller"))
    		.andDo(print())
    		.andDo(document("get-users-by-name", requestParameters( 
    				parameterWithName("firstName").description("First name of the user"),
    				parameterWithName("lastName").description("Last name of the user")) ))
    		.andExpect(status().isOk());    
    assertEquals(expectedUsersResponses, deserialiseResponse(resultActions));
  }  
  
  @Test
  public void testGetUserByNameEndPointEmptyParams() throws Exception {	
	List<UserResponse> expectedEmptyUsersResponses = new ArrayList<>();
    when(userRepo.findByName("", "")).thenReturn(new ArrayList<User>());        
    when(userResponseService.UserToResponse(expectedUsers)).thenReturn(expectedEmptyUsersResponses);    
    ResultActions resultActions = this.mockMvc.perform(get("/users/list").param("firstName", "").param("lastName", ""))
    		.andDo(print()).andExpect(status().isOk());    
    assertEquals(expectedEmptyUsersResponses, deserialiseResponse(resultActions));
  }  
  
  @Test
  public void testGetUserByFirstNameEndPoint() throws Exception {
    when(userRepo.findByFirstName("Ross")).thenReturn(expectedUsers);    
    when(userResponseService.UserToResponse(expectedUsers)).thenReturn(expectedUsersResponses);    
    ResultActions resultActions = this.mockMvc.perform(get("/users/list").param("firstName", "Ross"))
    		.andDo(print())
    		.andDo(document("get-users-by-first-name", requestParameters( 
    				parameterWithName("firstName").description("First name of the user")) ))
    		.andExpect(status().isOk());    
    assertEquals(expectedUsersResponses, deserialiseResponse(resultActions));
  }  
  
  @Test
  public void testGetUserByFirstNameEndPointEmptyParams() throws Exception {	
	List<UserResponse> expectedEmptyUsersResponses = new ArrayList<>();
    when(userRepo.findByFirstName("")).thenReturn(new ArrayList<User>());        
    when(userResponseService.UserToResponse(expectedUsers)).thenReturn(expectedEmptyUsersResponses);    
    ResultActions resultActions = this.mockMvc.perform(get("/users/list").param("firstName", ""))
    		.andDo(print()).andExpect(status().isOk());    
    assertEquals(expectedEmptyUsersResponses, deserialiseResponse(resultActions));
  }  
  
  @Test
  public void testGetUserByLastNameEndPoint() throws Exception {
    when(userRepo.findByLastName("Geller")).thenReturn(expectedUsers);    
    when(userResponseService.UserToResponse(expectedUsers)).thenReturn(expectedUsersResponses);    
    ResultActions resultActions = this.mockMvc.perform(get("/users/list").param("lastName", "Geller"))
    		.andDo(print())
    		.andDo(document("get-users-by-last-name", requestParameters( 
    				parameterWithName("lastName").description("Last name of the user")) ))
    		.andExpect(status().isOk());    
    assertEquals(expectedUsersResponses, deserialiseResponse(resultActions));
  } 
  
  @Test
  public void testGetUserByLastNameEndPointEmptyParams() throws Exception {	
	List<UserResponse> expectedEmptyUsersResponses = new ArrayList<>();
    when(userRepo.findByLastName("")).thenReturn(new ArrayList<User>());        
    when(userResponseService.UserToResponse(expectedUsers)).thenReturn(expectedEmptyUsersResponses);    
    ResultActions resultActions = this.mockMvc.perform(get("/users/list").param("lastName", ""))
    		.andDo(print()).andExpect(status().isOk());    
    assertEquals(expectedEmptyUsersResponses, deserialiseResponse(resultActions));
  }  
  
  @Test
  public void testGetUserByNicknameEndPoint() throws Exception {
    when(userRepo.findByNickName("the_geller")).thenReturn(expectedUsers);    
    when(userResponseService.UserToResponse(expectedUsers)).thenReturn(expectedUsersResponses);    
    ResultActions resultActions = this.mockMvc.perform(get("/users/list").param("nickName", "the_geller"))
    		.andDo(print())
    		.andDo(document("get-users-by-nickname", requestParameters( 
    				parameterWithName("nickName").description("Nickname of the user")) ))
    		.andExpect(status().isOk());    
    assertEquals(expectedUsersResponses, deserialiseResponse(resultActions));
  }
  
  @Test
  public void testGetUserByNicknameEndPointEmptyParams() throws Exception {	
	List<UserResponse> expectedEmptyUsersResponses = new ArrayList<>();
    when(userRepo.findByNickName("")).thenReturn(new ArrayList<User>());        
    when(userResponseService.UserToResponse(expectedUsers)).thenReturn(expectedEmptyUsersResponses);    
    ResultActions resultActions = this.mockMvc.perform(get("/users/list").param("nickName", ""))
    		.andDo(print()).andExpect(status().isOk());    
    assertEquals(expectedEmptyUsersResponses, deserialiseResponse(resultActions));
  }  
  
  @Test
  public void testGetUserByCountryEndPoint() throws Exception {
    when(userRepo.findByCountry("USA")).thenReturn(expectedUsers);    
    when(userResponseService.UserToResponse(expectedUsers)).thenReturn(expectedUsersResponses);    
    ResultActions resultActions = this.mockMvc.perform(get("/users/list").param("country", "USA"))
    		.andDo(print())
    		.andDo(document("get-users-by-country", requestParameters( 
    				parameterWithName("country").description("Country of origin")) ))
    		.andExpect(status().isOk());    
    assertEquals(expectedUsersResponses, deserialiseResponse(resultActions));
  }
  
  @Test
  public void testGetUserByCountryEndPointEmptyParams() throws Exception {	
	List<UserResponse> expectedEmptyUsersResponses = new ArrayList<>();
    when(userRepo.findByCountry("")).thenReturn(new ArrayList<User>());        
    when(userResponseService.UserToResponse(expectedUsers)).thenReturn(expectedEmptyUsersResponses);    
    ResultActions resultActions = this.mockMvc.perform(get("/users/list").param("country", ""))
    		.andDo(print()).andExpect(status().isOk());    
    assertEquals(expectedEmptyUsersResponses, deserialiseResponse(resultActions));
  }  
  
  @Test
  public void testGetUserByEmailEndPoint() throws Exception {
    when(userRepo.findByEmail("ross@live.com")).thenReturn(expectedUsers);    
    when(userResponseService.UserToResponse(expectedUsers)).thenReturn(expectedUsersResponses);    
    ResultActions resultActions = this.mockMvc.perform(get("/users/list").param("email", "ross@live.com"))
    		.andDo(print())
    		.andDo(document("get-users-by-email", requestParameters( 
    				parameterWithName("email").description("Email address")) ))
    		.andExpect(status().isOk());    
    assertEquals(expectedUsersResponses, deserialiseResponse(resultActions));
  }
  
  @Test
  public void testGetUserByEmailEndPointEmptyParams() throws Exception {	
	List<UserResponse> expectedEmptyUsersResponses = new ArrayList<>();
    when(userRepo.findByEmail("")).thenReturn(new ArrayList<User>());        
    when(userResponseService.UserToResponse(expectedUsers)).thenReturn(expectedEmptyUsersResponses);    
    ResultActions resultActions = this.mockMvc.perform(get("/users/list").param("email", ""))
    		.andDo(print()).andExpect(status().isOk());    
    assertEquals(expectedEmptyUsersResponses, deserialiseResponse(resultActions));
  }  
  
  
  
  
  
}




