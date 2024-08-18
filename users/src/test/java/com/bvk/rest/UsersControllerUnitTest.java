package com.bvk.rest;

import com.bvk.rest.controller.UsersController;
import org.junit.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void login() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/oauth/token")
                //.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
                .header("Authorization", "Basic bW9iaWxlOnBpbg==")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED).content(buildUrlEncodedFormEntity(
                    "grant_type" , "password",
                    "username" , "admin",
                    "password" , "admin"
                ));
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        System.out.println();
        System.out.println("============================LOGIN TEST START============================");
        System.out.println("Http Status : " + response.getStatus());
        System.out.println("Body : " + response.getContentAsString());
        System.out.println("============================LOGIN TEST FINISH===========================");
        System.out.println();
        //assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    private String buildUrlEncodedFormEntity(String... params) {
        if( (params.length % 2) > 0 ) {
            throw new IllegalArgumentException("Need to give an even number of parameters");
        }
        StringBuilder result = new StringBuilder();
        for (int i=0; i<params.length; i+=2) {
            if( i > 0 ) {
                result.append('&');
            }
            try {
                result.
                        append(URLEncoder.encode(params[i], StandardCharsets.UTF_8.name())).
                        append('=').
                        append(URLEncoder.encode(params[i+1], StandardCharsets.UTF_8.name()));
            }
            catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return result.toString();
    }
}
