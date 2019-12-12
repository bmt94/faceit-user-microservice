package com.faceit.usermicroservice.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.faceit.usermicroservice.ExpectedTestUsers;
import com.faceit.usermicroservice.entities.User;
import com.faceit.usermicroservice.repositories.user_repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(UserFilteredReadController.class)
public class UserFilteredReadControllerTests {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @MockBean
  private UserRepository userRepo;

  @Test
  public void testGetUserByNameEndPoint() throws Exception {
	List<User> expectedUsers = new ArrayList<>();
	expectedUsers.add(ExpectedTestUsers .getUser3());
    when(userRepo.findByName("joey", "tribiani")).thenReturn(expectedUsers);
    ResultActions resultActions = this.mockMvc.perform(get("/users/list?firstName=joey&lastName=tribiani")).andDo(print()).andExpect(status().isOk());
    
    MvcResult result = resultActions.andReturn();
    String contentAsString = result.getResponse().getContentAsString();
    List<User> actualUsers = objectMapper.readValue(contentAsString, objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
    assertEquals(expectedUsers, actualUsers);
  }
  
  
  @Test
  public void testGetUserByFirstNameEndPoint() throws Exception {
	List<User> expectedUsers = new ArrayList<>();
	expectedUsers.add(ExpectedTestUsers .getUser3());
    when(userRepo.findByFirstName("joey")).thenReturn(expectedUsers);
    ResultActions resultActions = this.mockMvc.perform(get("/users/list?firstName=joey")).andDo(print()).andExpect(status().isOk());
    
    MvcResult result = resultActions.andReturn();
    String contentAsString = result.getResponse().getContentAsString();
    List<User> actualUsers = objectMapper.readValue(contentAsString, objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
    assertEquals(expectedUsers, actualUsers);
  }
  
  
  
  
  
}




