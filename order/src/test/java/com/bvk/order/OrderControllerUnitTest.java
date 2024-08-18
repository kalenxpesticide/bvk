package com.bvk.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void order() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/purchases")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer 7d3e2c7e-7ee8-49f4-b7fa-0dce69751774")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"checkout\":[{\"id\":1,\"quantity\":1},{\"id\":2,\"quantity\":1}]}");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        System.out.println();
        System.out.println("============================ORDER PURCHASE START============================");
        System.out.println("Http Status : " + response.getStatus());
        System.out.println("Body : " + response.getContentAsString());
        System.out.println("============================ORDER PURCHASE FINISH===========================");
        System.out.println();
    }
}
