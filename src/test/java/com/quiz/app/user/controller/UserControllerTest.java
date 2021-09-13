package com.quiz.app.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.app.user.model.User;
import com.quiz.app.user.repository.UserRepository;
import com.quiz.app.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest
{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;


    @Test
    public void createUserTest() throws Exception {
        User user = new User();
        user.setUser_id(1);
        user.setUsername("Aks");
        user.setEmail("aks@g.com");
        user.setPassword("1223");
        String inputToJson = this.mapToJson(user);
        Mockito.when(userService.addUser(Mockito.any(User.class))).thenReturn(user);
        String url = "http://localhost:8080/api/user";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(url).accept(MediaType.APPLICATION_JSON).content(inputToJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String outputToJson = response.getContentAsString();
        assertNotSame(outputToJson,inputToJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    private String mapToJson(Object object) throws JsonProcessingException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    @Test
    public void updateUserTest() throws Exception {
        User user = new User();
        user.setUser_id(1);
        user.setUsername("Aksay");
        user.setEmail("aksay@g.com");
        user.setPassword("1223");
        String inputToJson = this.mapToJson(user);
        Mockito.when(userService.updateUser(Mockito.any(User.class),Mockito.anyInt())).thenReturn(user);
        String url = "http://localhost:8080/api/user/update/1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(url).accept(MediaType.APPLICATION_JSON).content(inputToJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String outputToJson = response.getContentAsString();
        assertNotSame(outputToJson,inputToJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void findUserTest() throws Exception {
        User user = new User();
        user.setUser_id(1);
        user.setUsername("Aksay");
        user.setEmail("aksay@g.com");
        user.setPassword("1223");
        Mockito.when(userService.updateUser(Mockito.any(User.class),Mockito.anyInt())).thenReturn(user);
        String url = "http://localhost:8080/api/user/find/1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(url).accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(user);
        String outputtoJson = mvcResult.getResponse().getContentAsString();
        assertNotSame(outputtoJson,expectedJson);
    }

    @Test
    public void getUsersTest() throws Exception {
        User user = new User();
        user.setUser_id(1);
        user.setUsername("Aks");
        user.setEmail("aks@g.com");
        user.setPassword("abc");

        User user1 = new User();
        user.setUser_id(2);
        user.setUsername("Aksay");
        user.setEmail("aksay@g.com");
        user.setPassword("1223");

        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);

        Mockito.when(userService.viewAllUsers()).thenReturn(users);

        String url = "http://localhost:8080/api/users";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(users);
        String outputToJson = mvcResult.getResponse().getContentAsString();
        assertNotSame(outputToJson, expectedJson);

    }
}